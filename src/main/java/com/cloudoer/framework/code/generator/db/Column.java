package com.cloudoer.framework.code.generator.db;

import com.cloudoer.framework.code.generator.enums.ClassType;
import com.cloudoer.framework.code.generator.utils.NameUtil;
import lombok.Data;

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
                || "id".equalsIgnoreCase(this.field) || "status".equalsIgnoreCase(this.field)) {
            return "";
        }
        StringBuilder filedSegment = new StringBuilder();


        if (ClassType.DTO.equals(classType)) {
            filedSegment.append("\n\t");
            filedSegment.append("@ApiModelProperty(value = \"").append(comment).append("\", name = \"")
                    .append(genFieldName()).append("\")");
            filedSegment.append("\n\t");
        } else if (ClassType.ENTITY.equals(classType)) {
            filedSegment.append("\n\t");
            filedSegment.append("/**");
            filedSegment.append("\n\t");
            filedSegment.append(" * ").append(comment);
            filedSegment.append("\n\t");
            filedSegment.append(" */");
            filedSegment.append("\n\t");
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
        filedSegment.append("\n\t");
        return filedSegment.toString();
    }

    /**
     * 生成Domain中的字段名
     */
    public String genFieldName() {
        StringBuffer fileName = new StringBuffer();
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
		String res = null;
		String t = null;
		if (type.equals("date") || type.equals("time") || type.equals("year")) {
			t = "DATE";
		} else if (type.equals("timestamp") || type.equals("datetime")) {
			t = "TIMESTAMP";
		} else if (type.equals("tinyint") || type.equals("smallint") || type.equals("mediumint")
				|| type.equals("int")) {
			t = "INTEGER";
		} else if (type.equals("bigint")) {
			t = "BIGINT";
		} else if (type.equals("float") || type.equals("double")) {
			t = "DOUBLE";
		} else if (type.equals("decimal")) {
			t = "DECIMAL";
		} else {
			t = "VARCHAR";
		}
		if (this.field.equals("id")) {
			res = "<id property=\"" + genFieldName() + "\" column=\"" + this.field + "\" jdbcType=\"" + t
					+ "\" />";
		} else {
			res = "<result property=\"" + genFieldName() + "\" column=\"" + this.field + "\" jdbcType=\"" + t
					+ "\" />";
		}
		return res;
	}
//
//	public String getFieldNameCN() {
//		String fieldname = this.genDomainFieldName().toLowerCase();
//		String label = StringUtil.capitalize(fieldname);
//		String comment = this.getComment();
//		if (StringUtil.isNotNullOrEmpty(comment)) {
//			int index = comment.indexOf(",");
//			if (index > 0) {
//				comment = comment.substring(0, index);
//			}
//			label = comment;
//		}
//		return label;
//	}
//
//	public String getPlaceholder() {
//		String fieldname = this.genDomainFieldName().toLowerCase();
//		String label = StringUtil.capitalize(fieldname);
//		String comment = this.getComment();
//		if (StringUtil.isNotNullOrEmpty(comment)) {
//			label = comment;
//		}
//		return label;
//	}
//
//	public String getLocalizedString(String domain) {
//		if (this.field.equalsIgnoreCase("id") || this.field.equalsIgnoreCase("createdBy")
//				|| this.field.equalsIgnoreCase("createdDate") || this.field.equalsIgnoreCase("modifiedBy")
//				|| this.field.equalsIgnoreCase("modifiedDate") || this.field.equalsIgnoreCase("status")) {
//			return "";
//		}
//		String format = "{0}.{1}.{2}             = {3}";
//		String fieldname = this.genDomainFieldName().toLowerCase();
//		String label = StringUtil.capitalize(fieldname);
//		String comment = this.getComment();
//		if (StringUtil.isNotNullOrEmpty(comment)) {
//			int index = comment.indexOf(",");
//			if (index > 0) {
//				comment = comment.substring(0, index);
//			}
//			comment = ChineseUtil.toUnicode(comment);
//			label = comment;
//		}
//		String v = MessageFormat.format(format, CodeGenContext.app, domain.toLowerCase(), fieldname.toLowerCase(),
//				label);
//		return v;
//	}
//
//	public String getLocalizedString(String domain, String lang) {
//		if (this.field.equalsIgnoreCase("id") || this.field.equalsIgnoreCase("createdBy")
//				|| this.field.equalsIgnoreCase("createdDate") || this.field.equalsIgnoreCase("modifiedBy")
//				|| this.field.equalsIgnoreCase("modifiedDate") || this.field.equalsIgnoreCase("status")) {
//			return "";
//		}
//		String format = "{0}.{1}.{2}             = {3}";
//		String fieldname = this.genDomainFieldName().toLowerCase();
//		String v = MessageFormat.format(format, CodeGenContext.app, domain.toLowerCase(), fieldname.toLowerCase(),
//				StringUtil.capitalize(fieldname));
//		return v;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	public Table getTable() {
//		return table;
//	}
//
//	public void setTable(Table table) {
//		this.table = table;
//	}
//
//	public Column getForeign() {
//		return foreign;
//	}
//
//	public void setForeign(Column foreign) {
//		this.foreign = foreign;
//	}
//
//	public List<Column> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<Column> children) {
//		this.children = children;
//	}
//
//	/**
//	 * 生成外键依赖的父表数据获取方法,getXXX()
//	 *
//	 * @return
//	 */
//	public String genParentSnippet() {
//		String p = "";
//		if (foreign != null) {
//			String template = TemplateManager.getDomainParentTemplate();
//			String domainName = foreign.getTable().genDomainClassName(true, true);
//			String domainNames = foreign.getTable().genDomainClassName(false, false);
//			String domainName1 = foreign.getTable().genDomainClassName(false, true);
//			p = template.replace("{DomainName}", domainName);
//			p = p.replace("{domainNames}", domainNames);
//			p = p.replace("{domainnames}", domainNames.toLowerCase());
//			p = p.replace("{domainName}", domainName1);
//			p = p.replace("{domainname}", domainName.toLowerCase());
//			p = p.replace("{domainFieldName}", this.genDomainFieldName());
//		}
//		return p;
//	}
//
//	public String genChildrenSnippet() {
//		String c = "";
//		if (children.size() > 0) {
//			String template = TemplateManager.getDomainChildrenTemplate();
//			for (Column column : children) {
//				String domainName = column.getTable().genDomainClassName(true, true);
//				String domainNames = column.getTable().genDomainClassName(false, false);
//				String domainName1 = column.getTable().genDomainClassName(false, true);
//				String DomainNames = column.getTable().genDomainClassName(true, false);
//				String p = template.replace("{DomainName}", domainName);
//				p = p.replace("{DomainNames}", DomainNames);
//				p = p.replace("{domainNames}", domainNames);
//				p = p.replace("{domainnames}", domainNames.toLowerCase());
//				p = p.replace("{domainName}", domainName1);
//				p = p.replace("{domainname}", domainName.toLowerCase());
//				p = p.replace("{fieldName}", column.getField());
//
//				String parent = this.getTable().genDomainClassName(true, true);
//				p = p.replace("{Parent}", parent);
//
//				String parentFieldName = StringUtil.capitalize(genDomainFieldName());
//				p = p.replace("{ParentFieldName}", parentFieldName);
//
//				c += p;
//			}
//		}
//		return c;
//	}
//
//	public String genChildrenControllerSnippet() {
//		String c = "";
//		if (children.size() > 0) {
//			String template = TemplateManager.getControllerChildrenTemplate();
//			String Parent = this.table.genDomainClassName(true, true);
//			String parent = Parent.toLowerCase();
//			for (Column column : children) {
//				String domainName = column.getTable().genDomainClassName(true, true);
//				String domainNames = column.getTable().genDomainClassName(false, false);
//				String domainName1 = column.getTable().genDomainClassName(false, true);
//				String p = template.replace("{Child}", domainName);
//				p = p.replace("{Children}", domainNames);
//				p = p.replace("{children}", domainNames.toLowerCase());
//				p = p.replace("{Child}", domainName1);
//				p = p.replace("{child}", domainName.toLowerCase());
//				p = p.replace("{Parent}", Parent);
//				p = p.replace("{parent}", parent);
//
//				c += p;
//			}
//		}
//		return c;
//	}
//
//	public String genChildrenModulesSnippet() {
//		String c = "";
//		if (children.size() > 0) {
//			String template = TemplateManager.getModulesChildrenTemplate();
//			String parent = this.table.genDomainClassName(false, true);
//			String parents = this.table.genDomainClassName(false, false);
//			for (Column column : children) {
//				String domainName = column.getTable().genDomainClassName(false, true);
//				String domainNames = column.getTable().genDomainClassName(false, false);
//				String p = template.replace("{child}", domainName);
//				p = p.replace("{children}", domainNames);
//				p = p.replace("{parents}", parents);
//				p = p.replace("{parent}", parent);
//
//				c += p;
//			}
//		}
//		return c;
//	}
//
//	public Map<String, String> genJSONChidlren() {
//		if (this.children.size() == 0) {
//			return null;
//		}
//		Map<String, String> jsonfiles = new HashMap<String, String>();
//		String parent = this.table.genDomainClassName(false, true);
//		String template4jccreated = TemplateManager.getJSONChildrenCreatedTemplate();
//		String template4jcremoved = TemplateManager.getJSONChildrenRemovedTemplate();
//		String template4jclist = TemplateManager.getJSONChildrenListTemplate();
//
//		for (Column c : children) {
//			String children = c.getTable().genDomainClassName(false, false);
//			String child = c.getTable().genDomainClassName(false, true);
//
//			Table pt = this.table;
//			String pjsonfields = pt.genJSONFieldSimple();
//			Table ct = c.table;
//			String cjsonfields = ct.genJSONFieldSimple();
//
//			String jcreated = template4jccreated.replace("{parent}", parent).replace("{children}", children)
//					.replace("{child}", child).replace("{parent_json_fields}", pjsonfields)
//					.replace("{child_json_fields}", cjsonfields);
//			jsonfiles.put(parent + "_" + child + "_created.json", jcreated);
//
//			String jremoved = template4jcremoved.replace("{parent}", parent).replace("{children}", children)
//					.replace("{child}", child).replace("{parent_json_fields}", pjsonfields)
//					.replace("{child_json_fields}", cjsonfields);
//			jsonfiles.put(parent + "_" + child + "_removed.json", jremoved);
//
//			String jlist = template4jclist.replace("{parent}", parent).replace("{children}", children)
//					.replace("{child}", child).replace("{parent_json_fields}", pjsonfields)
//					.replace("{child_json_fields}", cjsonfields);
//			jsonfiles.put(parent + "_" + child + "_list.json", jlist);
//		}
//		return jsonfiles;
//	}
//
//	public String genTilesJSONChidlren() {
//		if (this.children.size() == 0) {
//			return null;
//		}
//		String jsonfiles = "";
//		String parent = this.table.genDomainClassName(false, true);
//		String template4jtiles = TemplateManager.getTilesJSONChildrenTemplate();
//		for (Column c : children) {
//			String child = c.getTable().genDomainClassName(false, true);
//
//			String tiles = template4jtiles.replace("{parent}", parent).replace("{child}", child);
//			jsonfiles += tiles + "\n";
//		}
//		return jsonfiles;
//	}
}
