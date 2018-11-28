package com.cloudoer.framework.code.generator.db;

import com.cloudoer.framework.code.generator.utils.TemplateUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Table implements Serializable {
	private String name;
	private String comment;
	private List<Column> columns;
//
//	public String genDAOClass() {
//		// code here
//        String template = TemplateUtil.getTemplateContent("");
//		if (StringUtils.hasLength(template)) {
//			String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module)
//                    .replace("{App}", StringUtil.capitalize(CodeGenContext.app));
//			String module = this.getModule();
//			if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//				daoclass = daoclass.replace("{module}", module);
//			} else {
//				daoclass = daoclass.replace(".{module}", "");
//			}
//			String domainName = this.genDomainClassName(true, true);
//			daoclass = daoclass.replace("{DomainName}", domainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genDomainClass() {
//		String template = TemplateUtil.getDomainTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module);
//			daoclass = daoclass.replace("{App}", StringUtil.capitalize(CodeGenContext.app));
//			String domainName = this.genDomainClassName(true, true);
//			daoclass = daoclass.replace("{DomainName}", domainName);
//
//			String children = "";
//			String parents = "";
//			for (Column c : columns) {
//				String controller = c.genChildrenSnippet();
//				if (StringUtil.isNotNullOrEmpty(controller)) {
//					children += controller;
//				}
//				String parent = c.genParentSnippet();
//				if (StringUtil.isNotNullOrEmpty(parent)) {
//					parents += parent;
//				}
//			}
//			daoclass = daoclass.replace("{children}", children);
//			daoclass = daoclass.replace("{parent}", parents);
//
//			String module = this.getModule();
//			if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//				daoclass = daoclass.replace("{module}", module);
//			} else {
//				daoclass = daoclass.replace(".{module}", "");
//			}
//			if (null != this.getColumns()) {
//				StringBuffer sb1 = new StringBuffer();
//				for (Column column : this.getColumns()) {
//					String[] array = column.getFieldSnippet();
//					if (array != null) {
//						sb1.append(array[0]);
//					}
//				}
//				daoclass = daoclass.replace("{fields}", sb1.toString());
//			}
//
//			return daoclass;
//		}
//		return null;
//	}
//
//    public String genDTOClass() {
//        String template = TemplateUtil.getDTOTemplate();
//        if (StringUtil.isNotNullOrEmpty(template)) {
//            String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module);
//            daoclass = daoclass.replace("{App}", StringUtil.capitalize(CodeGenContext.app));
//            String domainName = this.genDomainClassName(true, true);
//            daoclass = daoclass.replace("{DomainName}", domainName);
//            daoclass = daoclass.replace("{domainNameCN}", this.getDomainNameCN());
//
//            String children = "";
//            String parents = "";
//            for (Column c : columns) {
//                String controller = c.genChildrenSnippet();
//                if (StringUtil.isNotNullOrEmpty(controller)) {
//                    children += controller;
//                }
//                String parent = c.genParentSnippet();
//                if (StringUtil.isNotNullOrEmpty(parent)) {
//                    parents += parent;
//                }
//            }
//            daoclass = daoclass.replace("{children}", children);
//            daoclass = daoclass.replace("{parent}", parents);
//
//            String module = this.getModule();
//            if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//                daoclass = daoclass.replace("{module}", module);
//            } else {
//                daoclass = daoclass.replace(".{module}", "");
//            }
//            if (null != this.getColumns()) {
//                StringBuffer sb1 = new StringBuffer();
//                for (Column column : this.getColumns()) {
//                    String[] array = column.getDTOFieldSnippet();
//                    if (array != null) {
//                        sb1.append(array[0]);
//                    }
//                }
//                daoclass = daoclass.replace("{fields}", sb1.toString());
//            }
//
//            return daoclass;
//        }
//        return null;
//    }
//
//    public String genServiceClass() {
//        String template = TemplateUtil.getServiceTemplate();
//        if (StringUtil.isNotNullOrEmpty(template)) {
//            String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module);
//            daoclass = daoclass.replace("{App}", StringUtil.capitalize(CodeGenContext.app));
//            String domainName = this.genDomainClassName(true, true);
//            daoclass = daoclass.replace("{DomainName}", domainName);
//            daoclass = daoclass.replace("{domainNameCN}", this.getDomainNameCN());
//
//            String children = "";
//            String parents = "";
//            for (Column c : columns) {
//                String controller = c.genChildrenSnippet();
//                if (StringUtil.isNotNullOrEmpty(controller)) {
//                    children += controller;
//                }
//                String parent = c.genParentSnippet();
//                if (StringUtil.isNotNullOrEmpty(parent)) {
//                    parents += parent;
//                }
//            }
//            daoclass = daoclass.replace("{children}", children);
//            daoclass = daoclass.replace("{parent}", parents);
//
//            String module = this.getModule();
//            if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//                daoclass = daoclass.replace("{module}", module);
//            } else {
//                daoclass = daoclass.replace(".{module}", "");
//            }
//            if (null != this.getColumns()) {
//                StringBuffer sb1 = new StringBuffer();
//                for (Column column : this.getColumns()) {
//                    String[] array = column.getDTOFieldSnippet();
//                    if (array != null) {
//                        sb1.append(array[0]);
//                    }
//                }
//                daoclass = daoclass.replace("{fields}", sb1.toString());
//            }
//
//            return daoclass;
//        }
//        return null;
//    }
//
//    public String genServiceImplClass() {
//        String template = TemplateUtil.getServiceImplTemplate();
//        if (StringUtil.isNotNullOrEmpty(template)) {
//            String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module);
//            daoclass = daoclass.replace("{App}", StringUtil.capitalize(CodeGenContext.app));
//            String domainName = this.genDomainClassName(true, true);
//            daoclass = daoclass.replace("{DomainName}", domainName);
//            daoclass = daoclass.replace("{domainNameCN}", this.getDomainNameCN());
//
//            String children = "";
//            String parents = "";
//            for (Column c : columns) {
//                String controller = c.genChildrenSnippet();
//                if (StringUtil.isNotNullOrEmpty(controller)) {
//                    children += controller;
//                }
//                String parent = c.genParentSnippet();
//                if (StringUtil.isNotNullOrEmpty(parent)) {
//                    parents += parent;
//                }
//            }
//            daoclass = daoclass.replace("{children}", children);
//            daoclass = daoclass.replace("{parent}", parents);
//
//            String module = this.getModule();
//            if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//                daoclass = daoclass.replace("{module}", module);
//            } else {
//                daoclass = daoclass.replace(".{module}", "");
//            }
//            if (null != this.getColumns()) {
//                StringBuffer sb1 = new StringBuffer();
//                for (Column column : this.getColumns()) {
//                    String[] array = column.getDTOFieldSnippet();
//                    if (array != null) {
//                        sb1.append(array[0]);
//                    }
//                }
//                daoclass = daoclass.replace("{fields}", sb1.toString());
//            }
//
//            return daoclass;
//        }
//        return null;
//    }
//
//	public String genControllerClass() {
//		// code here
//		String template = TemplateUtil.getControllerTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String daoclass = template.replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module)
//                    .replace("{app_module}", CodeGenContext.app_module)
//                    .replace("{App}", StringUtil.capitalize(CodeGenContext.app))
//                    .replace("{DomainNameCN}", this.getDomainNameCN());
//			String domainName = this.genDomainClassName(true, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String domainName1 = this.genDomainClassName(false, true);
//			daoclass = daoclass.replace("{DomainName}", domainName);
//			daoclass = daoclass.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainnames}", domainNames.toLowerCase());
//			daoclass = daoclass.replace("{domainName}", domainName1);
//			daoclass = daoclass.replace("{domainname}", domainName.toLowerCase());
//
//			String cc = "";
//			for (Column c : columns) {
//				String controller = c.genChildrenControllerSnippet();
//				if (StringUtil.isNotNullOrEmpty(controller)) {
//					cc += controller;
//				}
//			}
//			daoclass = daoclass.replace("{controller_children}", cc);
//
//			String module = this.getModule();
//			if (StringUtil.isNotNullOrEmpty(module) && !"_root_".equals(module)) {
//				daoclass = daoclass.replace("{module}", module);
//			} else {
//				daoclass = daoclass.replace(".{module}", "");
//			}
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWebListView() {
//		String template = TemplateUtil.getWebListTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			thead.append("<th>#</th>");
//			tbody.append("<td>{{=i+1}}</td>");
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//						continue;
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//						continue;
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//						continue;
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//						continue;
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//						continue;
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//						continue;
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//						continue;
//					}
//					// thead.append("<th class=\"" + field + "\"><msg:message key=\"" + localstr +
//					// "\" /></th>\n\t\t\t");
//					thead.append("<th class=\"" + field + "\">" + field + "</th>\n\t\t\t");
//					tbody.append("<td>${item." + field + "}</td>\n\t\t\t\t");
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{thead}", thead.toString());
//			daoclass = daoclass.replace("{tbody}", tbody.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWebListJSView() {
//		String template = TemplateUtil.getWebListJSTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			thead.append("<th>#</th>");
//			tbody.append("<td>{{=i+1}}</td>");
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					// thead.append("<th class=\"" + field + "\"><msg:message key=\"" + localstr +
//					// "\" /></th>\n\t\t\t");
//					thead.append("<th class=\"" + field + "\">" + field + "</th>\n\t\t\t");
//					tbody.append("<td>${item." + field + "}</td>\n\t\t\t\t");
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{thead}", thead.toString());
//			daoclass = daoclass.replace("{tbody}", tbody.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWebEditorView() {
//		// code here
//		String template = TemplateUtil.getWebEditorTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String itemTeplate = TemplateUtil.getWebEditorItemTemplate();
//					if (column.isNilable()) {
//						itemTeplate = itemTeplate.replace("{required}", "");
//					} else {
//						itemTeplate = itemTeplate.replace("{required}", "required=\"required\"");
//					}
//					itemTeplate = itemTeplate.replace("{DomainFieldName}", field);
//					itemTeplate = itemTeplate.replace("{domainName}", domainName);
//					itemTeplate = itemTeplate.replace("{app}", CodeGenContext.app.toLowerCase());
//					fieldEditors.append(itemTeplate + "\n");
//
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{field_editors}", fieldEditors.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWebDetailView() {
//		// code here
//		String template = TemplateUtil.getWebViewTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					fieldEditors
//							.append("<msg:message key=\"" + localstr + "\" />:${" + domainName + "." + field + "}\n");
//				}
//			}
//			String daoclass = template.replace("{field_editors}", fieldEditors.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWxmlListView() {
//		String template = TemplateUtil.getWxmlListTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			thead.append("<th>#</th>");
//			tbody.append("<td>{{=i+1}}</td>");
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					// thead.append("<th class=\"" + field + "\"><msg:message key=\"" + localstr +
//					// "\" /></th>\n\t\t\t");
//					thead.append("<th class=\"" + field + "\">" + field + "</th>\n\t\t\t");
//					tbody.append("<td>${item." + field + "}</td>\n\t\t\t\t");
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{thead}", thead.toString());
//			daoclass = daoclass.replace("{tbody}", tbody.toString());
//			daoclass = daoclass.replace("{DomainNameCN}", this.getDomainNameCN());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWxmlListJSView() {
//		String template = TemplateUtil.getWxmlListJSTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			thead.append("<th>#</th>");
//			tbody.append("<td>{{=i+1}}</td>");
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					// thead.append("<th class=\"" + field + "\"><msg:message key=\"" + localstr +
//					// "\" /></th>\n\t\t\t");
//					thead.append("<th class=\"" + field + "\">" + field + "</th>\n\t\t\t");
//					tbody.append("<td>${item." + field + "}</td>\n\t\t\t\t");
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{thead}", thead.toString());
//			daoclass = daoclass.replace("{tbody}", tbody.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWxmlEditorView() {
//		// code here
//		String template = TemplateUtil.getWxmlEditorTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String itemTeplate = TemplateUtil.getWebEditorItemTemplate();
//					if (column.isNilable()) {
//						itemTeplate = itemTeplate.replace("{required}", "");
//					} else {
//						itemTeplate = itemTeplate.replace("{required}", "required=\"required\"");
//					}
//					itemTeplate = itemTeplate.replace("{DomainFieldName}", field);
//					itemTeplate = itemTeplate.replace("{domainName}", domainName);
//					itemTeplate = itemTeplate.replace("{app}", CodeGenContext.app.toLowerCase());
//					fieldEditors.append(itemTeplate + "\n");
//
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{field_editors}", fieldEditors.toString());
//			daoclass = daoclass.replace("{DomainNameCN}", this.getDomainNameCN());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genWxmlDetailView() {
//		// code here
//		String template = TemplateUtil.getWxmlViewTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					fieldEditors
//							.append("<msg:message key=\"" + localstr + "\" />:${" + domainName + "." + field + "}\n");
//				}
//			}
//			String daoclass = template.replace("{field_editors}", fieldEditors.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJSONCreatedView() {
//		// code here
//		String template = TemplateUtil.getJsonCreatedTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer jsonFields = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:object name=\"" + field + "\">"
//								+ "\n\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//								+ "}</json:property>\n" + "\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//								+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t</json:object>");
//					} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//								+ "}</json:property>\n");
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//								+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//								|| column.getType().equals("double") || column.getType().equals("float")
//								|| column.getType().equals("decimal") || column.getType().equals("money")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName
//									+ "." + field + "}\" />");
//						} else if (column.getType().equals("date") || column.getType().equals("datetime")
//								|| column.getType().equals("timestamp")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//									+ domainName + "." + field
//									+ "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//						} else {
//							Column fkc = column.getForeign();
//							if (fkc == null) {
//								if (i == 0) {
//									jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "."
//											+ field + "}</json:property>");
//								} else {
//									jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName
//											+ "." + field + "}</json:property>");
//								}
//							} else {
//								Column showf = fkc.getTable().getShowField();
//								if (i == 0) {
//									jsonFields.append("<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								} else {
//									jsonFields.append("\t\t\t<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								}
//							}
//						}
//					}
//					i++;
//					if (i < this.getColumns().size()) {
//						jsonFields.append("\n");
//					}
//				}
//			}
//			String daoclass = template.replace("{json_fields}", jsonFields.toString());
//			daoclass = daoclass.replace("{domainName}", domainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJSONUpdatedView() {
//		// code here
//		String template = TemplateUtil.getJsonUpdatedTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer jsonFields = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:object name=\"" + field + "\">"
//								+ "\n\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//								+ "}</json:property>\n" + "\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//								+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t</json:object>");
//					} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//								+ "}</json:property>\n");
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//								+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//								|| column.getType().equals("double") || column.getType().equals("float")
//								|| column.getType().equals("decimal") || column.getType().equals("money")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName
//									+ "." + field + "}\" />");
//						} else if (column.getType().equals("date") || column.getType().equals("datetime")
//								|| column.getType().equals("timestamp")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//									+ domainName + "." + field
//									+ "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//						} else {
//							Column fkc = column.getForeign();
//							if (fkc == null) {
//								if (i == 0) {
//									jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "."
//											+ field + "}</json:property>");
//								} else {
//									jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName
//											+ "." + field + "}</json:property>");
//								}
//							} else {
//								Column showf = fkc.getTable().getShowField();
//								if (i == 0) {
//									jsonFields.append("<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								} else {
//									jsonFields.append("\t\t\t<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								}
//							}
//						}
//					}
//					i++;
//					if (i < this.getColumns().size()) {
//						jsonFields.append("\n");
//					}
//				}
//			}
//			String daoclass = template.replace("{json_fields}", jsonFields.toString());
//			daoclass = daoclass.replace("{domainName}", domainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJSONRemovedView() {
//		// code here
//		String template = TemplateUtil.getJsonRemovedTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer jsonFields = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:object name=\"" + field + "\">"
//								+ "\n\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//								+ "}</json:property>\n" + "\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//								+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t</json:object>");
//					} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//								+ "}</json:property>\n");
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//								+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//								|| column.getType().equals("double") || column.getType().equals("float")
//								|| column.getType().equals("decimal") || column.getType().equals("money")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName
//									+ "." + field + "}\" />");
//						} else if (column.getType().equals("date") || column.getType().equals("datetime")
//								|| column.getType().equals("timestamp")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//									+ domainName + "." + field
//									+ "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//						} else {
//							Column fkc = column.getForeign();
//							if (fkc == null) {
//								if (i == 0) {
//									jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "."
//											+ field + "}</json:property>");
//								} else {
//									jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName
//											+ "." + field + "}</json:property>");
//								}
//							} else {
//								Column showf = fkc.getTable().getShowField();
//								if (i == 0) {
//									jsonFields.append("<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								} else {
//									jsonFields.append("\t\t\t<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								}
//							}
//						}
//					}
//					i++;
//					if (i < this.getColumns().size()) {
//						jsonFields.append("\n");
//					}
//				}
//			}
//			String daoclass = template.replace("{json_fields}", jsonFields.toString());
//			daoclass = daoclass.replace("{domainName}", domainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJSONListView() {
//		// code here
//		String template = TemplateUtil.getJsonListTemplate();
//		String domainNames = this.genDomainClassName(false, false);
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer jsonFields = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t\t<json:object name=\"" + field + "\">"
//								+ "\n\t\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//								+ "}</json:property>\n" + "\t\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//								+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t\t</json:object>");
//					} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//								+ "}</json:property>\n");
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//								+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else if (column.getType().equals("date") || column.getType().equals("datetime")
//							|| column.getType().equals("timestamp")) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//								+ domainName + "." + field + "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//								|| column.getType().equals("double") || column.getType().equals("float")
//								|| column.getType().equals("decimal") || column.getType().equals("money")) {
//							jsonFields.append("\t\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName
//									+ "." + field + "}\" />");
//						} else if (column.getType().equals("date") || column.getType().equals("datetime")
//								|| column.getType().equals("timestamp")) {
//							jsonFields.append("\t\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//									+ domainName + "." + field
//									+ "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//						} else {
//							Column fkc = column.getForeign();
//							if (fkc == null) {
//								if (i == 0) {
//									jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "."
//											+ field + "}</json:property>");
//								} else {
//									jsonFields.append("\t\t\t\t<json:property name=\"" + field + "\">${" + domainName
//											+ "." + field + "}</json:property>");
//								}
//							} else {
//								Column showf = fkc.getTable().getShowField();
//								if (i == 0) {
//									jsonFields.append("<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								} else {
//									jsonFields.append("\t\t\t<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								}
//							}
//						}
//					}
//					i++;
//					if (i < this.getColumns().size()) {
//						jsonFields.append("\n");
//					}
//				}
//			}
//			String daoclass = template.replace("{json_fields}", jsonFields.toString());
//			daoclass = daoclass.replace("{domainName}", domainName);
//			daoclass = daoclass.replace("{domainNames}", domainNames);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJSONDetailView() {
//		// code here
//		String template = TemplateUtil.getJsonViewTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			StringBuffer jsonFields = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:object name=\"" + field + "\">"
//								+ "\n\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//								+ "}</json:property>\n" + "\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//								+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t</json:object>");
//					} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//								+ "}</json:property>\n");
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//								+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else if (column.getType().equals("date") || column.getType().equals("datetime")
//							|| column.getType().equals("timestamp")) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//								+ domainName + "." + field + "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//								|| column.getType().equals("double") || column.getType().equals("float")
//								|| column.getType().equals("decimal") || column.getType().equals("money")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName
//									+ "." + field + "}\" />");
//						} else if (column.getType().equals("date") || column.getType().equals("datetime")
//								|| column.getType().equals("timestamp")) {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//									+ domainName + "." + field
//									+ "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//						} else {
//							Column fkc = column.getForeign();
//							if (fkc == null) {
//								if (i == 0) {
//									jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "."
//											+ field + "}</json:property>");
//								} else {
//									jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName
//											+ "." + field + "}</json:property>");
//								}
//							} else {
//								Column showf = fkc.getTable().getShowField();
//								if (i == 0) {
//									jsonFields.append("<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								} else {
//									jsonFields.append("\t\t\t<json:object name=\"" + field + "\">\n"
//											+ "\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//											+ "}</json:property>\n" + "\t\t\t\t<json:property name=\""
//											+ (showf != null ? showf.genDomainFieldName() : "name") + "\">${"
//											+ domainName + "."
//											+ (showf != null
//													? (fkc.getTable().genDomainClassName(false, true) + "."
//															+ showf.genDomainFieldName())
//													: field)
//											+ "}</json:property>\n" + "\t\t\t</json:object>");
//								}
//							}
//						}
//					}
//					i++;
//					if (i < this.getColumns().size()) {
//						jsonFields.append("\n");
//					}
//				}
//			}
//			String daoclass = template.replace("{json_fields}", jsonFields.toString());
//			daoclass = daoclass.replace("{domainName}", domainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public Map<String, String> genJSONChildren() {
//		Map<String, String> jsonc = new HashMap<String, String>();
//		for (Column c : columns) {
//			Map<String, String> jc = c.genJSONChidlren();
//			if (jc != null && jc.size() > 0) {
//				jsonc.putAll(jc);
//			}
//		}
//		return jsonc;
//	}
//
//	public String genJSONFieldSimple() {
//		String domainName = this.genDomainClassName(false, true);
//		StringBuffer jsonFields = new StringBuffer();
//		if (null != this.getColumns()) {
//			int i = 0;
//			for (Column column : this.getColumns()) {
//				String field = column.genDomainFieldName();
//				if ("createdBy".equalsIgnoreCase(field) || "modifiedBy".equalsIgnoreCase(field)) {
//					jsonFields.append("\t\t\t<json:object name=\"" + field + "\">"
//							+ "\n\t\t\t\t<json:property name=\"id\">${" + domainName + "." + field
//							+ "}</json:property>\n" + "\t\t\t\t<json:property name=\"name\"><user:View id=\"${"
//							+ domainName + "." + field + "}\" /></json:property>\n" + "\t\t\t</json:object>");
//				} else if ("createdDate".equalsIgnoreCase(field) || "modifiedDate".equalsIgnoreCase(field)) {
//					jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "." + field
//							+ "}</json:property>\n");
//					jsonFields.append("\t\t\t<json:property name=\"" + field + "Format\"><date:View date=\"${"
//							+ domainName + "." + field + "}\" format=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//				} else if (column.getType().equals("date") || column.getType().equals("datetime")
//						|| column.getType().equals("timestamp")) {
//					jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//							+ domainName + "." + field + "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//				} else {
//					if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//							|| column.getType().equals("double") || column.getType().equals("float")
//							|| column.getType().equals("decimal") || column.getType().equals("money")) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\" value=\"${" + domainName + "."
//								+ field + "}\" />");
//					} else if (column.getType().equals("date") || column.getType().equals("datetime")
//							|| column.getType().equals("timestamp")) {
//						jsonFields.append("\t\t\t<json:property name=\"" + field + "\"><fmt:formatDate value=\"${"
//								+ domainName + "." + field + "}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/></json:property>");
//					} else {
//						if (i == 0) {
//							jsonFields.append("<json:property name=\"" + field + "\">${" + domainName + "." + field
//									+ "}</json:property>");
//						} else {
//							jsonFields.append("\t\t\t<json:property name=\"" + field + "\">${" + domainName + "."
//									+ field + "}</json:property>");
//						}
//					}
//				}
//				i++;
//				if (i < this.getColumns().size()) {
//					jsonFields.append("\n");
//				}
//			}
//		}
//		return jsonFields.toString();
//	}
//
//	public String genMapperView() {
//		// code here
//		String template = TemplateUtil.getMapperTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(true, true);
//			StringBuffer fieldsmap = new StringBuffer();
//			StringBuffer fields = new StringBuffer();
//			StringBuffer values = new StringBuffer();
//			StringBuffer values2 = new StringBuffer();
//			StringBuffer udatas = new StringBuffer();
//			StringBuffer conditions = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					fieldsmap.append(column.genFieldMapSnippet());
//					fields.append("`" + column.getField() + "`");
//					if ("status".equalsIgnoreCase(field)) {
//						values.append("<choose>\n\t\t\t\t");
//						values.append("<when test=\"status != null\">#{status.value}</when>\n\t\t\t\t");
//						values.append("<otherwise>1</otherwise>\n\t\t\t");
//						values.append("</choose>");
//					} else {
//						values.append("#{" + field + "}");
//						values2.append("#{data." + field + "}");
//					}
//
//					if (!"id".equals(column.getField()) && !"modified_by".equals(column.getField())
//							&& !"modified_date".equals(column.getField()) && !"created_by".equals(column.getField())
//							&& !"created_date".equals(column.getField()) && !"status".equals(column.getField())) {
//						udatas.append("`" + column.getField() + "`=#{" + field + "}, \n\t\t\t\t");
//						String type = column.getType();
//						if (type.equals("date") || type.equals("time") || type.equals("datetime")
//								|| type.equals("timestamp") || type.equals("year")) {
//							conditions.append("<if test=\"" + field + "_dr != null\">\n\t\t\t\tAND `"
//									+ column.getField() + "` BETWEEN #{" + field + "_dr.start} AND #{" + field
//									+ "_dr.end}\n\t\t\t</if>\n\t\t\t");
//						} else {
//							conditions.append("<if test=\"" + field + " != null\">\n\t\t\t\tAND `" + column.getField()
//									+ "` = #{" + field + "}\n\t\t\t</if>\n\t\t\t");
//						}
//					}
//					if (i != getColumns().size() - 1) {
//						fields.append(",\n\t\t\t");
//						values.append(",\n\t\t\t");
//						values2.append(",\n\t\t\t");
//						fieldsmap.append("\n\t\t");
//					}
//					i++;
//				}
//			}
//			if (hasmetas) {
//				String metas = "<collection property=\"metaDatas\" resultMap=\"MetaDataMap\" column=\"id\" select=\"select_mds\" jdbcType=\"VARCHAR\"></collection>";
//				fieldsmap.append(metas);
//				fieldsmap.append("\n\t\t");
//			}
//			String daoclass = template.replace("{fieldsmap}", fieldsmap.toString())
//                    .replace("{app}", CodeGenContext.app)
//                    .replace("{app_module}", CodeGenContext.app_module);
//			/*if (CodeGenContext.mybatis_cache) {
//				daoclass = daoclass.replace("{cacheRef}",
//						"<cache-ref namespace=\"" + CodeGenContext.App + "CacheMapper\" />\n\t");
//			} else {
//				daoclass = daoclass.replace("{cacheRef}", "");
//			}*/
//			daoclass = daoclass.replace("{DomainName}", domainName);
//			daoclass = daoclass.replace("{table}", this.name);
//			if (fields.length() > 0) {
//				daoclass = daoclass.replace("{fields}", fields.toString());
//			}
//			if (values.length() > 0) {
//				daoclass = daoclass.replace("{values}", values.toString());
//			}
//			if (values2.length() > 0) {
//				daoclass = daoclass.replace("{values2}", values2.toString());
//			}
//			daoclass = daoclass.replace("{udatas}", udatas.toString());
//			daoclass = daoclass.replace("{conditions}", conditions.toString());
//			return daoclass;
//		}
//		return null;
//	}
//
//	/**
//	 * 实现通过表名生成Domain类名的方法
//	 *
//	 * @param capitalize
//	 *            首字母是否大写
//	 * @param plural
//	 *            是否转为单数
//	 * @return
//	 */
//	public String genDomainClassName(boolean capitalize, boolean singular) {
//		String f = name;
//		if (f.startsWith("t_")) {
//			f = f.substring(2);
//		}
//		String[] fs = f.split("_");
//		StringBuffer sb = new StringBuffer();
//		if (fs.length > 1) {
//			sb.append(fs[0]);
//			for (int i = 1; i < fs.length; i++) {
//				char[] chars = fs[i].toCharArray();
//				chars[0] = Character.toUpperCase(chars[0]);
//				chars.toString();
//				sb.append(StringUtil.capitalize(fs[i]));
//			}
//		} else {
//			sb.append(f);
//		}
//		f = sb.toString();
//		if (capitalize) {
//			f = StringUtil.capitalize(f);
//		}
//		if (singular) {
//			f = StringUtil.singularize(f);
//		}
//		return f;
//	}
//
//	public String getMetaTableName() {
//		String f = name;
//		int lastindex = f.lastIndexOf("_");
//		String prepart = f;
//		if (lastindex > 0) {
//			prepart = f.substring(0, lastindex);
//		}
//		String sufpart = f.substring(lastindex + 1);
//		if (StringUtil.isNotNullOrEmpty(sufpart)) {
//			sufpart = StringUtil.singularize(sufpart);
//		}
//		String mdn = prepart + "_" + sufpart + "_metas";
//		return mdn;
//	}
//
//	/**
//	 * Just for Unit Test
//	 *
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String f = "t_push_channel";
//		int lastindex = f.lastIndexOf("_");
//		String prepart = f.substring(0, lastindex);
//		String sufpart = f.substring(lastindex + 1);
//		if (StringUtil.isNotNullOrEmpty(sufpart)) {
//			sufpart = StringUtil.singularize(sufpart);
//		}
//		String mdn = prepart + "_" + sufpart + "_metas";
//		System.out.println(mdn);
//	}
//
//	public String genDomainFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName + ".java";
//	}
//
//    public String genDTOFileName() {
//        String domainClassName = genDomainClassName(true, true);
//        return domainClassName + "DTO.java";
//    }
//
//    public String genServiceFileName() {
//        String domainClassName = genDomainClassName(true, true);
//        return domainClassName + "Service.java";
//    }
//
//    public String genServiceImplFileName() {
//        String domainClassName = genDomainClassName(true, true);
//        return domainClassName + "ServiceImpl.java";
//    }
//
//	public String genControllerFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName + "Controller.java";
//	}
//
//	public String genDAOFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName + "DAO.java";
//	}
//
//	public String genMapperFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName + "Mapper.xml";
//	}
//
//	public String genHtmlListFileName() {
//		String domainClassName = genDomainClassName(true, false);
//		return domainClassName.toLowerCase() + ".html";
//	}
//
//	public String genHtmlListItemFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + ".html";
//	}
//
//	public String genHtmlDetailFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_view.html";
//	}
//
//	public String genHtmlEditorFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_editor.html";
//	}
//
//	public String genJsListFileName() {
//		String domainClassName = genDomainClassName(true, false);
//		return domainClassName.toLowerCase() + ".js";
//	}
//
//	public String genJsDetailFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + ".js";
//	}
//
//	public String genJsEditorFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_editor.js";
//	}
//
//	public String genJspListFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_list.jsp";
//	}
//
//	public String genJspViewFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_view.jsp";
//	}
//
//	public String genJspEditorFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_editor.jsp";
//	}
//
//	public String genWxmlListFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_list.jsp";
//	}
//
//	public String genWxmlViewFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_view.jsp";
//	}
//
//	public String genWxmlEditorFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_editor.jsp";
//	}
//
//	public String genJSONListFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_list.json";
//	}
//
//	public String genJSONViewFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_view.json";
//	}
//
//	public String genJSONCreatedFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_created.json";
//	}
//
//	public String genJSONUpdatedFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_updated.json";
//	}
//
//	public String genJSONRemovedFileName() {
//		String domainClassName = genDomainClassName(true, true);
//		return domainClassName.toLowerCase() + "_removed.json";
//	}
//
//	public String genTilesItem() {
//		String template = TemplateUtil.getTilesTemplate();
//		String domainname = this.genDomainClassName(false, true);
//		String module = this.getModule();
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}.", "").replace("{module}/", "");
//		}
//		template = template.replace("{domainName}", domainname);
//		template = template.replace("{domainname}", domainname.toLowerCase());
//		return template;
//	}
//
//	public String genTilesJSONItem() {
//		String template = TemplateUtil.getTilesJSONTemplate();
//		String domainname = this.genDomainClassName(false, true);
//		template = template.replace("{domainName}", domainname);
//		template = template.replace("{domainname}", domainname.toLowerCase());
//
//		for (Column c : columns) {
//			String tiles = c.genTilesJSONChidlren();
//			if (StringUtil.isNotNullOrEmpty(tiles)) {
//				template += tiles;
//			}
//		}
//
//		String module = this.getModule();
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}.", "").replace("{module}/", "");
//		}
//		return template;
//	}
//
//	public String genTilesWxmlItem() {
//		String template = TemplateUtil.getTilesWxmlTemplate();
//		String domainname = this.genDomainClassName(false, true);
//		template = template.replace("{domainName}", domainname);
//		template = template.replace("{domainname}", domainname.toLowerCase());
//		String module = this.getModule();
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}.", "").replace("{module}/", "");
//		}
//		return template;
//	}
//
//	public String genTypeAlias() {
//		String template = TemplateUtil.getMapperTypeAliasTemplate();
//		String domainname = this.genDomainClassName(true, true);
//		String module = this.getModule();
//		template = template.replace("{DomainName}", domainname).replace("{app}", CodeGenContext.app);
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}.", "").replace("{module}/", "");
//		}
//		return template;
//	}
//
//	public String genMapperIncludes() {
//		String template = TemplateUtil.getMapperIncludeTemplate();
//		String domainname = this.genDomainClassName(false, true);
//		String module = this.getModule();
//		template = template.replace("{domainName}", domainname).replace("{app}", CodeGenContext.app)
//				.replace("{domainname}", domainname.toLowerCase());
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}.", "").replace("{module}/", "");
//		}
//		return template;
//	}
//
//	public String genDAOInstance() {
//		String template = TemplateUtil.getDAOInstanceTemplate();
//		String domainname = this.genDomainClassName(true, true);
//		String domainname_lowercase = domainname.toLowerCase();
//		template = template.replace("{DomainName}", domainname).replace("{domainName}", domainname_lowercase);
//		return template;
//	}
//
//	public String genDAOImport() {
//		String domain = this.genDomainClassName(true, true);
//		String module = this.getModule();
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			return MessageFormat.format("import com.cloudoer.{0}.dao.{1}.{2}DAO;", CodeGenContext.app, module, domain);
//		} else {
//			return MessageFormat.format("import com.cloudoer.{0}.dao.{1}DAO;", CodeGenContext.app, domain);
//		}
//	}
//
//	public String genModulesList() {
//		String template = TemplateUtil.getModuleTemplate();
//		String domainname = this.genDomainClassName(false, true);
//		String domainname_lowercase = domainname.toLowerCase();
//		template = template.replace("{domainName}", domainname_lowercase);
//		String domainnames = this.genDomainClassName(false, false);
//		String domainnames_lowercase = domainnames.toLowerCase();
//		template = template.replace("{domainNames}", domainnames_lowercase);
//
//		String cmodules = "";
//		for (Column c : columns) {
//			String ms = c.genChildrenModulesSnippet();
//			if (StringUtil.isNotNullOrEmpty(ms)) {
//				cmodules += ms;
//			}
//		}
//		template = template.replace("{cmodules}", cmodules);
//
//		String module = this.getModule();
//		if (StringUtil.isNotNullOrEmpty(module)) {
//			template = template.replace("{module}", module);
//		} else {
//			template = template.replace("{module}_", "");
//			template = template.replace("/{module}", "");
//			template = template.replace("{module}.", "");
//		}
//		return template;
//	}
//
//	public String getLocalizedString() {
//		StringBuilder sb = new StringBuilder();
//		String domainName = this.genDomainClassName(false, true);
//		for (Column c : this.columns) {
//			sb.append(c.getLocalizedString(domainName) + "\r\n");
//		}
//		return sb.toString();
//	}
//
//	public String getLocalizedString(String lang) {
//		StringBuilder sb = new StringBuilder();
//		String domainName = this.genDomainClassName(false, true);
//		if (StringUtil.isNullOrEmpty(lang)) {
//			for (Column c : this.columns) {
//				sb.append(c.getLocalizedString(domainName) + "\r\n");
//			}
//		} else {
//			for (Column c : this.columns) {
//				sb.append(c.getLocalizedString(domainName, lang) + "\r\n");
//			}
//		}
//		return sb.toString();
//	}
//
//	public String genJsListView() {
//		String template = TemplateUtil.getJsListTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			String daoclass = template.replace("{domainName}", domainName).replace("{domainNames}", domainNames)
//					.replace("{DomainName}", DomainName).replace("{domainnames}", domainNames.toLowerCase())
//					.replace("{domainname}", domainName.toLowerCase());
//			return daoclass;
//		}
//		return null;
//
//	}
//
//	public String genHtmlListView() {
//		String template = TemplateUtil.getHtmlListTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					thead.append("<th>" + field + "</th>\n\t\t\t\t");
//					if (column.getForeign() != null) {
//						Column sfield = column.getForeign().getTable().getShowField();
//						tbody.append("<td>{{=" + domainName + "."
//								+ column.getForeign().getTable().genDomainClassName(false, true) + "."
//								+ (sfield != null ? sfield.genDomainFieldName()
//										: column.getForeign().genDomainFieldName())
//								+ "}}</td>\n\t\t\t\t\t");
//					} else {
//						if ("name".equals(field) || "title".equals(column.getField())) {
//							tbody.append("<td><a href=\"javascript:void(0);\" class=\"" + domainName
//									+ "_view\" data-id=\"{{=" + domainName + ".id}}\">{{=" + domainName + "." + field
//									+ "}}</a></td>\n\t\t\t\t\t");
//						} else {
//							tbody.append("<td>{{=" + domainName + "." + field + "}}</td>\n\t\t\t\t\t");
//						}
//					}
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{thead}", thead.toString()).replace("{domainName}", domainName)
//					.replace("{DomainName}", DomainName);
//			daoclass = daoclass.replace("{tbody}", tbody.toString()).replace("{domainName}", domainName)
//					.replace("{DomainName}", DomainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genHtmlListItemView() {
//		String template = TemplateUtil.getHtmlListItemTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			StringBuffer thead = new StringBuffer();
//			StringBuffer tbody = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					thead.append("<th>" + field + "</th>\n\t\t\t\t");
//					if (column.getForeign() != null) {
//						Column sfield = column.getForeign().getTable().getShowField();
//						tbody.append("<td>{{=" + domainName + "."
//								+ column.getForeign().getTable().genDomainClassName(false, true) + "."
//								+ (sfield != null ? sfield.genDomainFieldName()
//										: column.getForeign().genDomainFieldName())
//								+ "}}</td>\n\t\t\t\t\t");
//					} else {
//						if ("name".equals(field) || "title".equals(column.getField())) {
//							tbody.append("<td><a href=\"javascript:void(0);\" class=\"" + domainName
//									+ "_view\" data-id=\"{{=" + domainName + ".id}}\">{{=" + domainName + "." + field
//									+ "}}</a></td>\n\t\t\t\t\t");
//						} else {
//							tbody.append("<td>{{=" + domainName + "." + field + "}}</td>\n\t\t\t\t\t");
//						}
//
//					}
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{thead}", thead.toString()).replace("{domainName}", domainName)
//					.replace("{DomainName}", DomainName);
//			daoclass = daoclass.replace("{tbody}", tbody.toString()).replace("{domainName}", domainName)
//					.replace("{DomainName}", DomainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genHtmlEditorView() {
//		// code here
//		String template = TemplateUtil.getHtmlEditorTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					String itemTemplate = TemplateUtil.getHtmlEditorItemTemplate();
//					if (column.getForeign() != null) {
//						itemTemplate = TemplateUtil.getHtmlEditorSelectTemplate();
//						String parents = column.getTable().genDomainClassName(false, false);
//						itemTemplate = itemTemplate.replace("{parents}", parents);
//					}
//					if (column.isNilable()) {
//						itemTemplate = itemTemplate.replace("{required}", "");
//					} else {
//						itemTemplate = itemTemplate.replace("{required}", "required=\"required\"");
//					}
//					itemTemplate = itemTemplate.replace("{DomainFieldName}", field).replace("{DomainName}", DomainName);
//					itemTemplate = itemTemplate.replace("{domainName}", domainName).replace("{DomainName}", DomainName);
//					itemTemplate = itemTemplate.replace("{app}", CodeGenContext.app.toLowerCase());
//					String cnname = column.getFieldNameCN();
//					itemTemplate = itemTemplate.replace("{DomainFieldNameCN}", cnname);
//					String placeholder = column.getPlaceholder();
//					itemTemplate = itemTemplate.replace("{Placeholder}", placeholder);
//					fieldEditors.append(itemTemplate + "\n");
//
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName).replace("{DomainName}", DomainName);
//			daoclass = daoclass.replace("{field_editors}", "\n" + fieldEditors.toString() + "\t\t\t");
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJsEditorView() {
//		String template = TemplateUtil.getJsEditorTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			String daoclass = template.replace("{domainName}", domainName).replace("{domainNames}", domainNames)
//					.replace("{DomainName}", DomainName).replace("{domainnames}", domainNames.toLowerCase())
//					.replace("{domainname}", domainName.toLowerCase());
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genHtmlDetailView() {
//		// code here
//		String template = TemplateUtil.getHtmlViewTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String DomainName = this.genDomainClassName(true, true);
//			StringBuffer view = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					String localstr = column.getFieldNameCN();
//					view.append("<div><label>" + localstr + "：</label><div>{{=" + domainName + "." + field
//							+ "}}</div></div>\n");
//				}
//			}
//			String daoclass = template.replace("{field_editors}", view.toString()).replace("{DomainName}", DomainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String genJsDetailView() {
//		// TODO
//		// code here
//		String template = TemplateUtil.getHtmlViewTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String DomainName = this.genDomainClassName(true, true);
//			StringBuffer fieldEditors = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					String localstr = CodeGenContext.app + "." + domainName + "." + field.toLowerCase();
//					if ("id".equalsIgnoreCase(field)) {
//						localstr = "common.id";
//					}
//					if ("createdby".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.by";
//					}
//					if ("createddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.created.date";
//					}
//					if ("modifiedby".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.by";
//					}
//					if ("modifieddate".equalsIgnoreCase(field)) {
//						localstr = "common.action.modified.date";
//					}
//					if ("status".equalsIgnoreCase(field)) {
//						localstr = "common.status";
//					}
//					if ("remak".equalsIgnoreCase(field)) {
//						localstr = "common.remak";
//					}
//					if ("description".equalsIgnoreCase(field)) {
//						localstr = "common.description";
//					}
//					if ("accessibility".equalsIgnoreCase(field)) {
//						localstr = "common.accessibility";
//					}
//					fieldEditors
//							.append("<msg:message key=\"" + localstr + "\" />:${" + domainName + "." + field + "}\n");
//				}
//			}
//			String daoclass = template.replace("{field_editors}", fieldEditors.toString()).replace("{DomainName}",
//					DomainName);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//		if (StringUtil.isNotNullOrEmpty(this.comment)) {
//			int index = this.comment.indexOf("show_field:");
//			if (index > -1) {
//				String subs = this.comment.substring(index + 11);
//				index = subs.indexOf("\n");
//				if (index > 0) {
//					showfield = subs.substring(0, index).trim();
//				}
//			}
//		}
//	}
//
//	public String getDomainNameCN() {
//		String fieldname = this.genDomainClassName(true, true);
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
//	public String getDescription() {
//		String fieldname = this.genDomainClassName(true, true);
//		String label = StringUtil.capitalize(fieldname);
//		String comment = this.getComment();
//		if (StringUtil.isNotNullOrEmpty(comment)) {
//			label = comment;
//		}
//		return label;
//	}
//
//	public String getOpenAPI() {
//		String template = TemplateUtil.getOpenAPITemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String domainName = this.genDomainClassName(false, true);
//			String domainNames = this.genDomainClassName(false, false);
//			String DomainName = this.genDomainClassName(true, true);
//			String DomainNameCN = this.getDomainNameCN();
//			StringBuffer params_for_create = new StringBuffer();
//			StringBuffer params_for_update = new StringBuffer();
//			StringBuffer params_for_list = new StringBuffer();
//			StringBuffer result_for_create = new StringBuffer();
//			StringBuffer result_for_delete = new StringBuffer();
//			StringBuffer result_for_update = new StringBuffer();
//			StringBuffer result_for_list = new StringBuffer();
//			StringBuffer result_for_view = new StringBuffer();
//			if (null != this.getColumns()) {
//				int i = 0;
//				int len = this.getColumns().size() - 1;
//				for (Column column : this.getColumns()) {
//					String field = column.genDomainFieldName();
//					if ("id".equals(field) || "modified_by".equals(column.getField())
//							|| "modified_date".equals(column.getField()) || "created_by".equals(column.getField())
//							|| "created_date".equals(column.getField()) || "status".equals(column.getField())) {
//						continue;
//					}
//					params_for_create.append(MessageFormat.format(
//							"<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td></tr>", field,
//							column.getType(), column.isNilable() ? "N" : "Y", column.getFieldNameCN(),
//							column.getComment()));
//					params_for_update.append(MessageFormat.format(
//							"<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td></tr>", field,
//							column.getType(), column.isNilable() ? "N" : "Y", column.getFieldNameCN(),
//							column.getComment()));
//					params_for_list.append(MessageFormat.format(
//							"<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td></tr>", field,
//							column.getType(), "Y", column.getFieldNameCN(), column.getComment()));
//					if (column.getType().equals("tinyint") || column.getType().equals("smallint")
//							|| column.getType().equals("double") || column.getType().equals("float")
//							|| column.getType().equals("decimal") || column.getType().equals("money")) {
//						result_for_create.append(
//								"\t\t\t\t\"" + field + "\":" + column.getFieldNameCN() + (i == len ? "\n" : ",\n"));
//						result_for_delete.append(
//								"\t\t\t\t\"" + field + "\":" + column.getFieldNameCN() + (i == len ? "\n" : ",\n"));
//						result_for_update.append(
//								"\t\t\t\t\"" + field + "\":" + column.getFieldNameCN() + (i == len ? "\n" : ",\n"));
//						result_for_list.append(
//								"\t\t\t\t\"" + field + "\":" + column.getFieldNameCN() + (i == len ? "\n" : ",\n"));
//						result_for_view.append(
//								"\t\t\t\t\"" + field + "\":" + column.getFieldNameCN() + (i == len ? "\n" : ",\n"));
//					} else {
//						result_for_create.append("\t\t\t\t\"" + field + "\":\"" + column.getFieldNameCN() + "\""
//								+ (i == len ? "\n" : ",\n"));
//						result_for_delete.append("\t\t\t\t\"" + field + "\":\"" + column.getFieldNameCN() + "\""
//								+ (i == len ? "\n" : ",\n"));
//						result_for_update.append("\t\t\t\t\"" + field + "\":\"" + column.getFieldNameCN() + "\""
//								+ (i == len ? "\n" : ",\n"));
//						result_for_list.append("\t\t\t\t\"" + field + "\":\"" + column.getFieldNameCN() + "\""
//								+ (i == len ? "\n" : ",\n"));
//						result_for_view.append("\t\t\t\t\"" + field + "\":\"" + column.getFieldNameCN() + "\""
//								+ (i == len ? "\n" : ",\n"));
//					}
//					i++;
//				}
//			}
//			String daoclass = template.replace("{domainNames}", domainNames);
//			daoclass = daoclass.replace("{domainName}", domainName).replace("{DomainName}", DomainName)
//					.replace("{DomainNameCN}", DomainNameCN);
//			daoclass = daoclass.replace("{params_for_create}", params_for_create);
//			daoclass = daoclass.replace("{params_for_update}", params_for_update);
//			daoclass = daoclass.replace("{params_for_list}", params_for_list);
//			daoclass = daoclass.replace("{result_for_create}", result_for_create);
//			daoclass = daoclass.replace("{result_for_delete}", result_for_delete);
//			daoclass = daoclass.replace("{result_for_update}", result_for_update);
//			daoclass = daoclass.replace("{result_for_list}", result_for_list);
//			daoclass = daoclass.replace("{result_for_view}", result_for_view);
//			return daoclass;
//		}
//		return null;
//	}
//
//	public String getDatabaseDesignSection() {
//		String template = TemplateUtil.getDatabaseDesignSectionTemplate();
//		if (StringUtil.isNotNullOrEmpty(template)) {
//			String DomainNameCN = this.getDomainNameCN();
//			StringBuffer fields_of_table = new StringBuffer();
//			if (null != this.getColumns()) {
//				for (Column column : this.getColumns()) {
//					fields_of_table.append(MessageFormat.format(
//							"<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td></tr>",
//							column.getField(), column.getType(), column.isNilable() ? "N" : "Y",
//							column.getFieldNameCN(), column.getLength(), column.getComment()));
//				}
//			}
//			String section = template.replace("{DomainNameCN}", DomainNameCN).replace("{TableName}", name)
//					.replace("{Comment}", comment);
//			section = section.replace("{fields_of_table}", fields_of_table);
//			return section;
//		}
//		return null;
//	}
//
//	public boolean isHasmetas() {
//		return hasmetas;
//	}
//
//	public void setHasmetas(boolean hasmetas) {
//		this.hasmetas = hasmetas;
//	}
//
//	public String getShowfield() {
//		return showfield;
//	}
//
//	public void setShowfield(String showfield) {
//		this.showfield = showfield;
//	}
}
