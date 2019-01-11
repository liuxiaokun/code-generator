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
     * BaseController.java类
     */
    BASE_CONTROLLER("base_controller"),

    /**
     * BaseDTO.java类
     */
    BASE_DTO("base_dto"),

    /**
     * BaseServiceImpl.java类
     */
    BASE_SERVICE_IMPL("base_service_impl"),

    /**
     * BaseDAO.java类
     */
    BASE_DAO("base_dao"),

    /**
     * xxxMapper.xml类
     */
    MAPPER("mapper"),

    /**
     * BeanUtil.java类
     */
    BEAN_UTIL("bean_util"),

    /**
     * BizException.java类
     */
    BIZ_EXCEPTION("biz_exception"),

    /**
     * BaseEntity.java类
     */
    BASE_ENTITY("base_entity"),

    /**
     * xxxDTO.java类
     */
    DTO("dto"),

    /**
     * application.yml类
     */
    YML("yml");

    private String type;

    ClassType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
