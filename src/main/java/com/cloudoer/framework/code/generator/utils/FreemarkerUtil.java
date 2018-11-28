package com.cloudoer.framework.code.generator.utils;

import com.cloudoer.framework.code.generator.db.Column;
import com.cloudoer.framework.code.generator.db.Table;
import com.cloudoer.framework.code.generator.enums.ClassType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.cloudoer.framework.code.generator.consts.Consts.*;
import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Slf4j
public class FreemarkerUtil {

    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>(3);
        data.put("project", "bdp");
        data.put("module", "dgov");
        data.put("DomainName", "User");

        genFile("DAO.template", ClassType.DAO, data);
    }

    public static void genFile(String templateFileName,
                               @NonNull ClassType classType, Map<String, String> data) {
        Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = configuration.getTemplate(templateFileName);
            File docFile = new File(getGenFilePath(classType, data.get("EntityName")));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(data, out);
            log.info("{}文件创建成功", templateFileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void genMapperFile(Map<String, String> data, Table table) throws IOException {

        data = packData(data, table);
        String mapperTemplateContent = null;

        try {
            mapperTemplateContent = FileUtils.readAsText(TEMPLATE_PATH + "mapper.template");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.hasLength(mapperTemplateContent)) {
            for (Map.Entry<String, String> temp : data.entrySet()) {
                mapperTemplateContent = mapperTemplateContent.replace("{" + temp.getKey() + "}", temp.getValue());
            }
        }

        String mapperPath = BIN_PATH + PROJECT_NAME + "-" + MODULE_NAME + "/src/main/resources/mapper/"
                + NameUtil.genEntityClassName(true, true, table.getName()) + "Mapper.xml";
        FileWriter fileWriter = new FileWriter(new File(mapperPath), false);
        fileWriter.write(mapperTemplateContent);
        fileWriter.close();
    }

    private static Map<String, String> packData(Map<String, String> data, Table table) {
        StringBuffer fieldsmap = new StringBuffer();
        StringBuffer fields = new StringBuffer();
        StringBuffer values = new StringBuffer();
        StringBuffer values2 = new StringBuffer();
        StringBuffer udatas = new StringBuffer();
        StringBuffer conditions = new StringBuffer();
        if (null != table.getColumns()) {
            int i = 0;
            for (Column column : table.getColumns()) {
                String field = column.genFieldName();
                fieldsmap.append(column.genFieldMapSnippet());
                fields.append("`" + column.getField() + "`");
                if ("status".equalsIgnoreCase(field)) {
                    values.append("<choose>\n\t\t\t\t");
                    values.append("<when test=\"status != null\">#{status.value}</when>\n\t\t\t\t");
                    values.append("<otherwise>1</otherwise>\n\t\t\t");
                    values.append("</choose>");
                } else {
                    values.append("#{" + field + "}");
                    values2.append("#{data." + field + "}");
                }

                if (!"id".equals(column.getField()) && !"modified_by".equals(column.getField())
                        && !"modified_date".equals(column.getField()) && !"created_by".equals(column.getField())
                        && !"created_date".equals(column.getField()) && !"status".equals(column.getField())) {
                    udatas.append("`" + column.getField() + "`=#{" + field + "}, \n\t\t\t\t");
                    String type = column.getType();
                    if ("date".equals(type) || "time".equals(type) || "datetime".equals(type)
                            || "timestamp".equals(type) || "year".equals(type)) {
                        conditions.append("<if test=\"" + field + "_dr != null\">\n\t\t\t\tAND `"
                                + column.getField() + "` BETWEEN #{" + field + "_dr.start} AND #{" + field
                                + "_dr.end}\n\t\t\t</if>\n\t\t\t");
                    } else {
                        conditions.append("<if test=\"" + field + " != null\">\n\t\t\t\tAND `" + column.getField()
                                + "` = #{" + field + "}\n\t\t\t</if>\n\t\t\t");
                    }
                }
                if (i != table.getColumns().size() - 1) {
                    fields.append(",\n\t\t\t");
                    values.append(",\n\t\t\t");
                    values2.append(",\n\t\t\t");
                    fieldsmap.append("\n\t\t");
                }
                i++;
            }
        }

        data.put("fieldsmap", fieldsmap.toString());
        data.put("fields", fields.toString());
        data.put("values", values.toString());
        data.put("values2", values2.toString());
        data.put("udatas", udatas.toString());
        data.put("conditions", conditions.toString());

        return data;
    }

    private static String getGenFilePath(@NonNull ClassType classType, String entityName) {
        String genFilePath;
        String basePath = BIN_PATH + PROJECT_NAME + "-" + MODULE_NAME + "/src/main/java/com/cloudoer/";

        switch (classType) {

            case ENTITY:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/model/entity/" + entityName + ".java";
                break;

            case DAO:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/dao/" + entityName + "DAO.java";
                break;

            case SERVICE:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/service/" + entityName + "Service.java";
                break;

            case SERVICE_IMPL:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/service/impl/" + entityName + "ServiceImpl.java";
                break;

            case CONTROLLER:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/controller/" + entityName + "Controller.java";
                break;

            case DTO:
                genFilePath = basePath + PROJECT_NAME + "/" + MODULE_NAME
                        + "/model/dto/" + entityName + "DTO.java";
                break;

            case MAPPER:
                genFilePath = BIN_PATH + PROJECT_NAME + "-" + MODULE_NAME + "/src/main/resources/mapper/"
                        + entityName + "Mapper.xml";
                break;

            default:
                genFilePath = "";
                break;
        }

        return genFilePath;
    }
}
