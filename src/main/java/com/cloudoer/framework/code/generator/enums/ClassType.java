package com.cloudoer.framework.code.generator.enums;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
public enum ClassType {

    /**
     * entity.java类
     */
    ENTITY("entity"),
    /**
     * xxxDAO.java类
     */
    DAO("dao"),

    /**
     * xxxService.java类
     */
    SERVICE("serivce"),

    /**
     * xxxServiceImpl.java类
     */
    SERVICE_IMPL("service_impl"),

    /**
     * xxxController.java类
     */
    CONTROLLER("controller"),

    /**
     * xxxDTO.java类
     */
    MAPPER("mapper"),

    /**
     * xxxDTO.java类
     */
    DTO("dto");

    private String type;

    ClassType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
