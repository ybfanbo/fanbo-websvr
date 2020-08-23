package com.fanbo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    //压缩文件
    public static void zip(String zipFileName, List<String> files) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        for (String string : files) {
            File f = new File(string);
            if (f.exists()) {
                byte[] buffer = new byte[1024 * 32];
                out.putNextEntry(new ZipEntry(f.getName()));
                FileInputStream in = new FileInputStream(f);
                int b;
                while ((b = in.read(buffer)) != -1) {
                    out.write(buffer, 0, b);
                }
                in.close();
            }
        }
        out.close();
    }


    //保存文件
    public static void saveFile(InputStream uploadedInputStream, String path) {

        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream outpuStream = new FileOutputStream(new File(path));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // 文件下载
    public static void downloadFile(String filePath, HttpServletResponse response, HttpServletRequest request) throws Exception {

        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent != null && agent.contains("firefox")) {
            fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        File file = new File(filePath);
        // 读数据
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        byte[] data = new byte[(int) file.length()];
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        while ((bis.read(data)) != -1) {
            os.write(data); // 写数据
        }
        os.flush();
        os.close();
    }

    //删除单个文件
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                boolean delete = file.delete();
                if (delete) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //删除文件夹下所有文件
    public static void deleteFileByFolder(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    f.delete();
                } else {
                    deleteFileByFolder(f.getPath());
                }
            }
        }
    }


    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            logger.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            logger.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                logger.debug("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                logger.debug(descFileName + " 文件创建成功!");
                return true;
            } else {
                logger.debug(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(descFileName + " 文件创建失败!");
            return false;
        }

    }

    /**
     * 创建目录
     *
     * @param descDirName 目录名,包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createDirectory(String descDirName) {
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists()) {
            logger.debug("目录 " + descDirNames + " 已存在!");
            return false;
        }
        // 创建目录
        if (descDir.mkdirs()) {
            logger.debug("目录 " + descDirNames + " 创建成功!");
            return true;
        } else {
            logger.debug("目录 " + descDirNames + " 创建失败!");
            return false;
        }

    }


    /**
     * 将文件压缩到ZIP输出流
     *
     * @param dirPath 目录路径
     * @param file    文件
     * @param zouts   输出流
     */
    public static void zipFilesToZipFile(String dirPath, File file,
                                         ZipOutputStream zouts) {
        FileInputStream fin = null;
        ZipEntry entry = null;
        // 创建复制缓冲区
        byte[] buf = new byte[4096];
        int readByte = 0;
        if (file.isFile()) {
            try {
                // 创建一个文件输入流
                fin = new FileInputStream(file);
                // 创建一个ZipEntry
                entry = new ZipEntry(getEntryName(dirPath, file));
                // 存储信息到压缩文件
                zouts.putNextEntry(entry);
                // 复制字节到压缩文件
                while ((readByte = fin.read(buf)) != -1) {
                    zouts.write(buf, 0, readByte);
                }
                zouts.closeEntry();
                fin.close();
                System.out
                        .println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
     *
     * @param file entry文件名
     * @return
     */
    private static String getEntryName(String dirPath, File file) {
        String dirPaths = dirPath;
        if (!dirPaths.endsWith(File.separator)) {
            dirPaths = dirPaths + File.separator;
        }
        String filePath = file.getAbsolutePath();
        // 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
        if (file.isDirectory()) {
            filePath += "/";
        }
        int index = filePath.indexOf(dirPaths);

        return filePath.substring(index + dirPaths.length());
    }


}
