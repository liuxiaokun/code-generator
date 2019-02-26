package com.cloudoer.framework.code.generator.db;

import com.cloudoer.framework.code.generator.enums.ClassType;
import com.cloudoer.framework.code.generator.utils.NameUtil;
import lombok.Data;

import static com.cloudoer.framework.code.generator.utils.FreemarkerUtil.QUICK_INDENT_1;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Data
public class Column {

    private Table table;
    private String field;
    private String type;
    private String key;
    private boolean nullAble;
    private int length;
    private String defaultValue;
    private String comment;

    /**
     * 生成Field片断，包括get与set方法
     */
    public String getFieldSnippet(ClassType classType) {

        if ("created_date".equalsIgnoreCase(this.field) || "created_by".equalsIgnoreCase(this.field)
                || "modified_date".equalsIgnoreCase(this.field) || "modified_by".equalsIgnoreCase(this.field)
                || "id".equalsIgnoreCase(this.field) || "status".equalsIgnoreCase(this.field) || "ip".equalsIgnoreCase(this.field)) {
            return "";
        }
        StringBuilder filedSegment = new StringBuilder();

        filedSegment.append("\n").append("\n").append(QUICK_INDENT_1);

        if (ClassType.DTO.equals(classType)) {
            filedSegment.append("@ApiModelProperty(value = \"").append(comment).append("\", name = \"")
                    .append(genFieldName()).append("\")");
            filedSegment.append("\n").append(QUICK_INDENT_1);
        } else if (ClassType.ENTITY.equals(classType)) {
            filedSegment.append("/**");
            filedSegment.append("\n").append(QUICK_INDENT_1);
            filedSegment.append(" * ").append(comment);
            filedSegment.append("\n").append(QUICK_INDENT_1);
            filedSegment.append(" */");
            filedSegment.append("\n").append(QUICK_INDENT_1);
        }
        filedSegment.append("private ");

        if ("date".equals(type) || "time".equals(type) || "datetime".equals(type)
                || "timestamp".equals(type) || "year".equals(type)) {
            filedSegment.append("Date ");
        } else if ("tinyint".equals(type) || "smallint".equals(type)
                || "mediumint".equals(type) || "int".equals(type)) {
            filedSegment.append("Integer ");
        } else if ("bigint".equals(type)) {
            filedSegment.append("Long ");
        } else if ("float".equals(type) || "double".equals(type)) {
            filedSegment.append("Double ");
        } else if ("decimal".equals(type)) {
            filedSegment.append("Double ");
        } else {
            filedSegment.append("String ");
        }
        filedSegment.append(genFieldName()).append(";");
        return filedSegment.toString();
    }

    /**
     * 生成Domain中的字段名
     */
    public String genFieldName() {
        StringBuilder fileName = new StringBuilder();
        String[] fields = field.split("_");

        if (fields.length > 1) {
            fileName.append(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                char[] chars = fields[i].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                fileName.append(NameUtil.capitalize(fields[i]));
            }
        } else {
            fileName.append(field);
        }
        return fileName.toString();
    }

    /**
     * 生成Mybatis配置文件中 对象Mapper的Field映射
     *
     * @return
     */
    public String genFieldMapSnippet() {
        // code here
        String res;
        String type;
        if ("date".equals(this.type) || "time".equals(this.type) || "year".equals(this.type)) {
            type = "DATE";
        } else if ("timestamp".equals(this.type) || "datetime".equals(this.type)) {
            type = "TIMESTAMP";
        } else if ("tinyint".equals(this.type) || "smallint".equals(this.type) || "mediumint".equals(this.type)
                || "int".equals(this.type)) {
            type = "INTEGER";
        } else if ("bigint".equals(this.type)) {
            type = "BIGINT";
        } else if ("float".equals(this.type) || "double".equals(this.type)) {
            type = "DOUBLE";
        } else if ("decimal".equals(this.type)) {
            type = "DECIMAL";
        } else {
            type = "VARCHAR";
        }
        if ("id".equals(this.field)) {
            res = "<id property=\"" + genFieldName() + "\" column=\"" + this.field + "\" jdbcType=\"" + type
                    + "\" />";
        } else {
            res = "<result property=\"" + genFieldName() + "\" column=\"" + this.field + "\" jdbcType=\"" + type
                    + "\" />";
        }
        return res;
    }
}
