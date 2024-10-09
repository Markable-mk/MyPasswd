package com.itmark.mypasswdbackend.service.excel;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface MinioService {

    /**
     * 功能说明：上传文件
     *
     * @param data
     * @param bucketName 桶名称
     * @param fileName   文件名称
     * @return
     * @author: feng
     * @DATE:2020年4月24日 @TIME: 下午10:57:55
     */
    String uploadFile(byte[] data, String bucketName, String fileName);

    /**
     * 功能说明：下载文件
     *
     * @param bucketName 桶名称
     * @param filePath   文件路径
     * @return
     * @author: feng
     * @DATE:2020年4月24日 @TIME: 下午11:25:14
     */
    InputStream downloadFile(String bucketName, String filePath);

    /**
     * 功能说明：删除文件
     *
     * @param bucketName
     * @param filePath
     * @return
     * @author: feng
     * @DATE:2020年4月24日 @TIME: 下午5:59:48
     */
    boolean delete(String bucketName, String filePath);

    /**
     * 功能说明：上传文件
     *
     * @param inputStream 文件的输入流
     * @param bucketName 桶名称
     * @param fileName  文件名称
     * @return boolean
     * @author 杨中肖
     * @date 2021/10/14
     */
    boolean uploadFile(InputStream inputStream, String bucketName, String fileName);


    /**
     * 下载文件夹下的所有文件(不支持深层文件)
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称
     * @return {@link List<String> }
     * @author 杨中肖
     * @date 2021/10/15
     */
    List<String>  downloadFilesInFolder(String bucketName, String fileName);


    List<String>  getFilesInFolder(String bucketName, String fileName);
}
