package com.cloudoer.framework.code.generator.utils;

import java.io.*;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
public class FileUtils {

    public static void copy(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                try {
                    destination.setWritable(true);
                    destination.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File[] files = source.listFiles();

            for (File file : files) {
                copy(file, new File(destination, file.getName()));
            }
        } else {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination));
            byte[] bys = new byte[1024];

            int len;
            while ((len = bis.read(bys)) != -1) {
                bos.write(bys, 0, len);
            }

            bos.flush();
            bos.close();
            bis.close();
        }
    }

    public static boolean renameDirectory(String fromDir, String toDir) {
        File from = new File(fromDir);

        if (!from.exists() || !from.isDirectory()) {
            System.out.println("Directory does not exist: " + fromDir);
            return false;
        }

        File to = new File(toDir);
        return from.renameTo(to);
    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File temp : files) {
                delete(temp);
            }
        }
        file.delete();
    }

    public static String readAsText(String file) throws IOException {
        return readAsText(new FileInputStream(file));
    }

    public static String readAsText(InputStream is) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        Reader reader = new InputStreamReader(is);
        char[] chars = new char[1024];

        int len;
        while ((len = reader.read(chars)) > 0) {
            for (int i = 0; i < len; ++i) {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();
    }
    public static void writeto(String text, String filepath) throws IOException {
        writeto(text, filepath, false);
    }

    public static void writeto(String text, String filepath, boolean append) throws IOException {
        int i = filepath.lastIndexOf(File.separator);
        String path = filepath.substring(0, i);
        File target = new File(path);
        if (!target.exists()) {
            target.mkdirs();
        }

        FileWriter fw = new FileWriter(filepath, append);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.flush();
        bw.close();
    }
}
