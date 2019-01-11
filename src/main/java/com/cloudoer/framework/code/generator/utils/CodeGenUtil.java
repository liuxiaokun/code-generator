package com.cloudoer.framework.code.generator.utils;

import com.cloudoer.framework.code.generator.db.Column;
import com.cloudoer.framework.code.generator.db.MysqlHelper;
import com.cloudoer.framework.code.generator.db.Table;
import com.cloudoer.framework.code.generator.dto.DownloadDTO;
import com.cloudoer.framework.code.generator.enums.ClassType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cloudoer.framework.code.generator.consts.Consts.*;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Slf4j
public class CodeGenUtil {

    public static void genCodes(DownloadDTO downloadDTO) throws Exception {

        String project = downloadDTO.getProjectName();
        String module = downloadDTO.getModuleName();
        DATABASE_NAME = downloadDTO.getDatabase();
        HOST = downloadDTO.getHost();
        PORT = downloadDTO.getPort();
        USER_NAME = downloadDTO.getUsername();
        PASSWORD = downloadDTO.getPassword();
        List<String> needGenTables = downloadDTO.getTables();
        List<Table> tables = MysqlHelper.getTables(downloadDTO);

        if (!CollectionUtils.isEmpty(needGenTables)) {
            tables = tables.stream().filter(item -> needGenTables.contains(item.getName())).collect(Collectors.toList());
        }

        for (Table table : tables) {
            //生成Entity
            String entityNameUpper = NameUtil.genEntityClassName(true, true, table.getName());
            String tableComment = NameUtil.getTableComment(table).replace("/r/n", "");
            String entityNameFirstLower = NameUtil.genEntityClassName(false, true, table.getName());

            StringBuilder entityFields = new StringBuilder();
            StringBuilder dtoFields = new StringBuilder();
            for (Column column : table.getColumns()) {
                String entityField = column.getFieldSnippet(ClassType.ENTITY);
                String dtoField = column.getFieldSnippet(ClassType.DTO);
                entityFields.append(entityField);
                dtoFields.append(dtoField);
            }
            entityFields.delete(0, 6);
            dtoFields.delete(0, 6);

            Map<String, String> commonData = new HashMap<>(4);
            commonData.put("project", project);
            commonData.put("module", module);
            commonData.put("EntityName", entityNameUpper);
            commonData.put("entityName", entityNameFirstLower);
            commonData.put("author", AUTHOR);
            commonData.put("version", VERSION);
            commonData.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            commonData.put("fields", entityFields.toString());
            commonData.put("tableComment", tableComment);

            //生成Entity
            FreemarkerUtil.genFile("entity.template", ClassType.ENTITY, commonData);
            //生成DAO
            FreemarkerUtil.genFile("DAO.template", ClassType.DAO, commonData);
            //生成DTO
            commonData.put("fields", dtoFields.toString());
            FreemarkerUtil.genFile("DTO.template", ClassType.DTO, commonData);
            //生成BaseController
            FreemarkerUtil.genFile("BaseController.template", ClassType.BASE_CONTROLLER, commonData);
            //生成BaseDAO
            FreemarkerUtil.genFile("BaseDAO.template", ClassType.BASE_DAO, commonData);
            //生成BaseEntity
            FreemarkerUtil.genFile("BaseEntity.template", ClassType.BASE_ENTITY, commonData);
            //生成Service
            FreemarkerUtil.genFile("Service.template", ClassType.SERVICE, commonData);
            //生成ServiceImpl
            FreemarkerUtil.genFile("ServiceImpl.template", ClassType.SERVICE_IMPL, commonData);
            //生成BeanUtil
            FreemarkerUtil.genFile("BeanUtil.template", ClassType.BEAN_UTIL, commonData);
            //生成BizException
            FreemarkerUtil.genFile("BizException.template", ClassType.BIZ_EXCEPTION, commonData);
            //生成BaseDTO
            FreemarkerUtil.genFile("BaseDTO.template", ClassType.BASE_DTO, commonData);
            //生成LongDateSerializer
            FreemarkerUtil.genFile("LongDateSerializer.template", ClassType.LONG_DATE_SERIALIZER, commonData);

            //生成Controller
            commonData.put("ControllerMapping", NameUtil.genControllerRequestMappingPath(table.getName()));
            FreemarkerUtil.genFile("Controller.template", ClassType.CONTROLLER, commonData);
            //生成application.yml文件
            commonData.put("databaseHost", HOST);
            commonData.put("databasePort", PORT);
            commonData.put("databaseName", DATABASE_NAME);
            commonData.put("databaseUsername", USER_NAME);
            commonData.put("databasePassword", PASSWORD);
            FreemarkerUtil.genFile("application.yml.template", ClassType.YML, commonData);

            //生成Mapper文件
            commonData.put("table", table.getName());
            commonData.remove("fields");
            FreemarkerUtil.genMapperFile(commonData, table);
        }
        ZipUtil.toZip(BIN_PATH + project + "-" + module, new FileOutputStream(new File(BIN_PATH + project + "-" + module
                + ".zip")), true);
    }
}
