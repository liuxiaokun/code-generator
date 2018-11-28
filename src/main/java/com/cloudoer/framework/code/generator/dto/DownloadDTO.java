package com.cloudoer.framework.code.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/28
 */
@Data
public class DownloadDTO {

    private String projectName;
    private String moduleName;
    private String host;
    private String port;
    private String username;
    private String password;
    private String database;
    private List<String> tables;
}
