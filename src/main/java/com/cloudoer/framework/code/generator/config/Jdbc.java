package com.cloudoer.framework.code.generator.config;

import lombok.Data;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Data
public class Jdbc {

    private String host = "192.168.1.11";
    private String username = "root";
    private String password = "123456";
    private String database = "bdp_pom";

    private String url = "jdbc:mysql://" + host + ":3306" + "/"
            + database + "?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";

}
