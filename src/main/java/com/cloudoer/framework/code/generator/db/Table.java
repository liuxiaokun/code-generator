package com.cloudoer.framework.code.generator.db;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/12/01
 */
@Data
public class Table implements Serializable {

    private String name;
    private String comment;
    private List<Column> columns;
}
