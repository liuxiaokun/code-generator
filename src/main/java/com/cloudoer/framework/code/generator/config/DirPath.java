package com.cloudoer.framework.code.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@ConfigurationProperties(prefix = "path")
@Data
@Component
public class DirPath {

    private String bin;
    private String archetype;
    private String template;
}
