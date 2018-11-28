package com.cloudoer.framework.code.generator.utils;

import com.cloudoer.framework.code.generator.db.Column;
import com.cloudoer.framework.code.generator.db.MysqlHelper;
import com.cloudoer.framework.code.generator.db.Table;
import com.cloudoer.framework.code.generator.dto.DownloadDTO;
import com.cloudoer.framework.code.generator.enums.ClassType;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        tables = tables.stream().filter(item -> needGenTables.contains(item.getName())).collect(Collectors.toList());

        for (Table table : tables) {
            //生成Entity
            String entityNameUpper = NameUtil.genEntityClassName(true, true, table.getName());
            String tableComment = NameUtil.getTableComment(table);
            String entityNameFirstLower = NameUtil.genEntityClassName(false, true, table.getName());

            StringBuilder entityFields = new StringBuilder();
            StringBuilder dtoFields = new StringBuilder();
            for (Column column : table.getColumns()) {
                String entityField = column.getFieldSnippet(ClassType.ENTITY);
                String dtoField = column.getFieldSnippet(ClassType.DTO);
                entityFields.append(entityField);
                dtoFields.append(dtoField);
            }

            Map<String, String> dataEntity = new HashMap<>(4);
            dataEntity.put("project", project);
            dataEntity.put("module", module);
            dataEntity.put("EntityName", entityNameUpper);
            dataEntity.put("fields", entityFields.toString());
            FreemarkerUtil.genFile("domain.template", ClassType.ENTITY, dataEntity);

            //生成DAO
            Map<String, String> dataDAO = new HashMap<>(4);
            dataDAO.put("project", project);
            dataDAO.put("module", module);
            dataDAO.put("EntityName", entityNameUpper);
            FreemarkerUtil.genFile("DAO.template", ClassType.DAO, dataDAO);

            //生成DTO
            Map<String, String> dataDTO = new HashMap<>(4);
            dataDTO.put("project", project);
            dataDTO.put("module", module);
            dataDTO.put("EntityName", entityNameUpper);
            dataDTO.put("tableComment", tableComment);
            dataDTO.put("fields", dtoFields.toString());
            FreemarkerUtil.genFile("DTO.template", ClassType.DTO, dataDTO);

            //生成Service
            Map<String, String> dataService = new HashMap<>(4);
            dataService.put("project", project);
            dataService.put("module", module);
            dataService.put("EntityName", entityNameUpper);
            FreemarkerUtil.genFile("Service.template", ClassType.SERVICE, dataService);

            //生成ServiceImpl
            Map<String, String> dataServiceImpl = new HashMap<>(4);
            dataServiceImpl.put("project", project);
            dataServiceImpl.put("module", module);
            dataServiceImpl.put("EntityName", entityNameUpper);
            FreemarkerUtil.genFile("ServiceImpl.template", ClassType.SERVICE_IMPL, dataServiceImpl);

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
        ZipUtil.toZip("bin/" + project + "-" + module, new FileOutputStream(new File("bin/" + project + "-" + module
                + ".zip")), true);
    }
}
