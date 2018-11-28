package com.cloudoer.framework.code.generator.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.cloudoer.framework.code.generator.consts.Consts.MODULE_NAME;
import static com.cloudoer.framework.code.generator.consts.Consts.PROJECT_NAME;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Slf4j
public class M {


    public static void main(String[] args) throws Exception {
        String project = "bigtour";
        String module = "pom";
        String binDirectory = "bin/";
        PROJECT_NAME = "bigtour";
        MODULE_NAME = "pom";

        FileUtils.delete(new File(binDirectory));

        // 把archetype复制到目标文件夹
        FileUtils.copy(new File("src/main/archetype"), new File(binDirectory + project + "-" + module));

        //把目标文件夹下的包名（目录结构）替换成目标项目相关.
        String sourceDirectory = binDirectory + project + "-" + module + "/" + "src/main/java/com/cloudoer/app";
        String targetDirectory = binDirectory + project + "-" + module + "/" + "src/main/java/com/cloudoer/" + project;
        FileUtils.renameDirectory(sourceDirectory, targetDirectory);
        FileUtils.renameDirectory(targetDirectory + "/module", targetDirectory + "/" + module);
        log.info("1");

        CodeGenUtil.gencodes(project, module);
    }
}
