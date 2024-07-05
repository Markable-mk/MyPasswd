package com.itmark.mypasswdbackend.service.minio.impl;

import com.itmark.mypasswdbackend.service.minio.MinioService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: minio service
 * @author: MAKUAN
 * @date: 2024/7/5 10:03
 */
@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioClient minioClient;

    /**
     * 在很多时候 minio部署在容器中，自动生成的下载链接为容器id这就需要使用外部ip拼上授权参数
     */
    @Value("${minio.downloadurl:http://192.168.9.22:19001}")
    private String downloadUrl;

    @Override
    public List<String> getFilesInFolder(String bucketName, String pathName) {
        List<String> list = new ArrayList<>();
        try {
            for (Result<Item> itemResult : minioClient.listObjects(bucketName, pathName)) {
                list.add(itemResult.get().objectName());
            }
        } catch (Exception e) {
            log.error("获取桶：{}，路径下：{} 所有文件路径失败：{}",bucketName,pathName, e);
        }
        return list;
    }

    @Override
    public InputStream downloadFile(String bucketName, String filePath) {
        try {
            return minioClient.getObject(bucketName, filePath);
        } catch (Exception e) {
            log.error("下载桶：{}，文件：{}，失败",bucketName, filePath, e);
        }
        return null;
    }

    @Override
    public Map<String, String> getFilesShareLinkMap(String bucketName,List<String> filePathWithBucketList) {
        Map<String, String> result = new HashMap<>();
        for (String path : filePathWithBucketList) {
            try {
                URL url = new URL(minioClient.presignedGetObject(bucketName, path));
                String sharePath=downloadUrl + url.getPath() + "?" + url.getQuery();
                result.put(path,sharePath);
            } catch (Exception e) {
                log.error("获取文件路径：{}，外链失败：{}",path,e);
            }
        }
        return result;
    }
}
