package com.itmark.mypasswdbackend.service.excel.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.itmark.mypasswdbackend.service.excel.MinioService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 功能说明:
 *
 * @author: feng
 * @DATE:2020年4月24日 @TIME:下午1:37:30
 */
@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    @Resource
    private MinioClient minioClient;

    @Value("${minio.downloadurl:null}")
    private String downloadUrl;

    @SuppressWarnings("deprecation")
    @Override
    public String uploadFile(byte[] data, String bucketName, String fileName) {
        InputStream inputStream = new ByteArrayInputStream(data);
        String path;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String datePath = LocalDateTime.now().format(formatter);
            //设置保存路径
            path = datePath + "/" + fileName;
            boolean bucketExists = minioClient.bucketExists(bucketName);
            if (!bucketExists) {
                //不存在桶则创建
                minioClient.makeBucket(bucketName);
            }
            //上传文件
            minioClient.putObject(bucketName, fileName,inputStream ,null);
            return path;
        } catch (Exception e) {
            log.error("minio:" + e.getMessage());
        }
        return null;
    }

    @Override
    public InputStream downloadFile(String bucketName, String filePath) {
        try {
            return minioClient.getObject(bucketName, filePath);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public boolean delete(String bucketName, String filePath) {
        try {
            minioClient.removeObject(bucketName, filePath);
            return true;
        } catch (Exception e) {
            log.error("minio:删除失败，" + e.getMessage());
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean uploadFile(InputStream inputStream, String bucketName, String fileName) {
        try {
            boolean bucketExists = minioClient.bucketExists(bucketName);
            if (!bucketExists) {
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName, fileName,inputStream ,null);
            return true;
        } catch (Exception e) {
            log.error("文件上传失败：", e);
            return false;
        }
    }

    @Override
    public List<String> downloadFilesInFolder(String bucketName, String fileName) {
        List<String> list = this.listObjects(bucketName, fileName);
        ArrayList<String> urls = new ArrayList<>();
        for (String path : list) {
            try {
                if (CharSequenceUtil.NULL.equals(downloadUrl)) {
                    urls.add(minioClient.presignedGetObject(bucketName, path));
                } else {
                    URL url = new URL(minioClient.presignedGetObject(bucketName, path));
                    urls.add(downloadUrl + url.getPath() + "?" + url.getQuery());
                }
            } catch (Exception e) {
                log.error("下载失败" + e);
            }
        }
        return urls;
    }

    @Override
    public List<String> getFilesInFolder(String bucketName, String fileName) {
        List<String> list = this.listObjects(bucketName, fileName);
        return list;
    }

    /**
     * 获取文件夹下的文件路径
     *
     * @param bucketName bucket名称
     * @param fileName   文件名称
     * @return {@link List<String> }
     * @author 杨中肖
     * @date 2021/10/15
     */
    private List<String> listObjects(String bucketName, String fileName) {
        List<String> list = new ArrayList<>();
        try {
            for (Result<Item> itemResult : minioClient.listObjects(bucketName, fileName)) {
                list.add(itemResult.get().objectName());
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return list;
    }
}
