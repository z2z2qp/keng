package code.java;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileTest {

    /**
     * 删除文件
     */
    public static void delete(File file) throws IOException {
        Files.delete(file.toPath());
    }

    /**
     * 把文件（目录）source 复制到target
     * 
     * @throws IOException
     */
    public static void copy(File sourcePath, File targetPath) throws IOException {
        copyFolder(sourcePath, targetPath);
    }

    private static void copyFolder(File source,File target) throws IOException {
        File copyFile = new File(target,source.getName());
        if(source.isDirectory()){
            copyFile.mkdir();
            File[] files = source.listFiles();
            if(files != null){
                for(File file:files){
                    copyFolder(file, copyFile);
                }
            }
        }else if(source.isFile()){
            copyFile(source,copyFile);
        }
    }

    private static void copyFile(File source,File target) throws IOException {
        Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * zip 打包
     * @param source 待打包文件
     * @param zos zip输出流
     */
    public static void zip(File source,ZipOutputStream zos){
        zip(source, "", zos);
    }

    private static void zip(File source,String relativePath,ZipOutputStream zos){
        File[] files = source.listFiles();
        if(!Objects.isNull(files)){
            for(File file:files){
                if(relativePath.length() == 0){
                    relativePath = file.getName();
                }else{
                    relativePath = relativePath+File.separatorChar+file.getName();
                }
                if(file.isFile()){
                    ZipEntry entry = new ZipEntry(relativePath);
                    try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
                        zos.putNextEntry(entry);
                        byte[] buff = new byte[1024*10];
                        int read;
                        while((read = bis.read(buff, 0, 1024*10))>0){
                            zos.write(buff,0,read);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }else if(file.isDirectory()){
                    zip(file.getAbsoluteFile(),relativePath,zos);
                }
            }
        }
    }
}