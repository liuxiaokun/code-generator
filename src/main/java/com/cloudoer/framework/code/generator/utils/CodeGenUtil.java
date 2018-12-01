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

import static com.cloudoer.framework.code.generator.consts.Consts.BIN_PATH;

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

            //生成Entity
            Map<String, String> dataEntity = new HashMap<>(4);
            dataEntity.put("project", project);
            dataEntity.put("module", module);
            dataEntity.put("EntityName", entityNameUpper);
            dataEntity.put("fields", entityFields.toString());
            FreemarkerUtil.genFile("domain.template", ClassType.ENTITY, dataEntity);

            //生成DAO
            Map<String, String> commonData = new HashMap<>(4);
            commonData.put("project", project);
            commonData.put("module", module);
            commonData.put("EntityName", entityNameUpper);
            commonData.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            commonData.put("version", "0.0.1");
            FreemarkerUtil.genFile("DAO.template", ClassType.DAO, commonData);

            //生成DTO
            Map<String, String> dataDTO = new HashMap<>(4);
            dataDTO.put("project", project);
            dataDTO.put("module", module);
            dataDTO.put("EntityName", entityNameUpper);
            dataDTO.put("tableComment", tableComment);
            dataDTO.put("fields", dtoFields.toString());
            FreemarkerUtil.genFile("DTO.template", ClassType.DTO, dataDTO);

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
            //生成BaseService
            FreemarkerUtil.genFile("BaseService.template", ClassType.BASE_SERVICE, commonData);
            //生成BaseServiceImpl
            FreemarkerUtil.genFile("BaseServiceImpl.template", ClassType.BASE_SERVICE_IMPL, commonData);

            //生成Controller
            Map<String, String> dataController = new HashMap<>(4);
            dataController.put("project", project);
            dataController.put("module", module);
            dataController.put("EntityName", entityNameUpper);
            dataController.put("entityName", entityNameFirstLower);
            dataController.put("tableComment", tableComment);
            FreemarkerUtil.genFile("Controller.template", ClassType.CONTROLLER, dataController);

            //生成Mapper文件
            Map<String, String> dataMapper = new HashMap<>(4);
            dataMapper.put("project", project);
            dataMapper.put("module", module);
            dataMapper.put("EntityName", entityNameUpper);
            dataMapper.put("entityName", entityNameFirstLower);
            dataMapper.put("table", table.getName());
            FreemarkerUtil.genMapperFile(dataMapper, table);
        }
        ZipUtil.toZip(BIN_PATH + project + "-" + module, new FileOutputStream(new File(BIN_PATH + project + "-" + module
                + ".zip")), true);
    }
}
