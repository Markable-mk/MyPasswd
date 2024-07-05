package com.itmark.mypasswdbackend.service.minio;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/5 10:03
 */
public interface MinioService {

    /**
     * 01 获取桶下指定文件路径下的文件路径集合
     * @param bucketName
     * @param pathName 路径名称：/aaa/bbb
     * @return
     */
   List<String> getFilesInFolder(String bucketName,String pathName);

    /**
     * 02 功能说明：下载文件
     *
     * @param bucketName 桶名称
     * @param filePath   文件路径:aaa/bbb/a.txt
     */
    InputStream downloadFile(String bucketName, String filePath);

    /**
     * 03 获取minio文件路径的下载外链
     * String bucketName
     * List<String> filePathWithBucketList aaa/bbb/a.txt
     * @return
     */
    Map<String,String> getFilesShareLinkMap(String bucketName,List<String> filePathWithBucketList);
}
