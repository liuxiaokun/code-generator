package com.cloudoer.bigtour.core.model.entity;

import lombok.Data;

/**
 * @author code gen
 * @version 0.0.1
 * @since 2018/11/20
 */
@Data
public class BaseEntity {

    /**
     * 数据库表的主键, 生成策略基于雪花算法
     * 获取示例：<code>AppContext.IdGen.nextId()</>
     */
    private Long id;

    /**
     * 创建人ID，对应 t_user表的id.
     * 获取示例：<code>BaseController#getUserId(request)</code>
     */
    private Long createdBy;

    /**
     * 创建时间，存储时间戳。
     * 获取示例：<code>System.currentTimeMillis()</code>
     */
    private Long createdDate;

    /**
     * 最后修改人ID，对应t_user表的id
     * 获取示例：<code>BaseController#getUserId(request)</code>
     */
    private Long modifiedBy;

    /**
     * 最后修改时间，存储时间戳。
     * 获取示例：<code>System.currentTimeMillis()</code>
     */
    private Long modifiedDate;

    /**
     * 此记录的状态，例如逻辑删除
     * 示例：1：正常：0：删除 等等
     */
    private Integer status;
}
