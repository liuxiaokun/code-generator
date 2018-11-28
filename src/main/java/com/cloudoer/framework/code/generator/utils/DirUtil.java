package com.cloudoer.framework.code.generator.utils;


import com.cloudoer.framework.code.generator.dto.DownloadDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.cloudoer.framework.code.generator.consts.Consts.ARCHETYPE_PATH;
import static com.cloudoer.framework.code.generator.consts.Consts.BIN_PATH;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Slf4j
public class DirUtil {

    public static void genDirTree(DownloadDTO downloadDTO) throws Exception {
        String projectName = downloadDTO.getProjectName();
        String moduleName = downloadDTO.getModuleName();

        FileUtils.delete(new File(BIN_PATH));
        // 把archetype复制到目标文件夹
        FileUtils.copy(new File(ARCHETYPE_PATH), new File(BIN_PATH + projectName + "-" + moduleName));

        //把目标文件夹下的包名（目录结构）替换成目标项目相关.
        String sourceDirectory = BIN_PATH + projectName + "-" + moduleName + "/" + "src/main/java/com/cloudoer/app";
        String targetDirectory = BIN_PATH + projectName + "-" + moduleName + "/" + "src/main/java/com/cloudoer/" + projectName;
        FileUtils.renameDirectory(sourceDirectory, targetDirectory);
        FileUtils.renameDirectory(targetDirectory + "/module", targetDirectory + "/" + moduleName);

        log.info("rename package complete, {}", moduleName);
        CodeGenUtil.genCodes(downloadDTO);
    }
}
