package com.cloudoer.framework.code.generator.controller;

import com.cloudoer.framework.code.generator.dto.DownloadDTO;
import com.cloudoer.framework.code.generator.utils.DirUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.cloudoer.framework.code.generator.consts.Consts.MODULE_NAME;
import static com.cloudoer.framework.code.generator.consts.Consts.PROJECT_NAME;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/28
 */
@RestController
@RequestMapping("gen")
@Slf4j
public class GenController {


    @GetMapping("")
    public String downLoad(DownloadDTO downloadDTO, HttpServletResponse response) throws Exception {

        PROJECT_NAME = downloadDTO.getProjectName();
        MODULE_NAME = downloadDTO.getModuleName();
        DirUtil.genDirTree(downloadDTO);

        File file = new File("bin/" + downloadDTO.getProjectName() + "-" + downloadDTO.getModuleName() + ".zip");

        log.info("file.exists():{}", file.exists());
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + downloadDTO.getProjectName()
                    + "-" + downloadDTO.getModuleName() + "-" + System.currentTimeMillis() + ".zip");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            OutputStream os;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);

                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != fis) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "OK";
    }
}
