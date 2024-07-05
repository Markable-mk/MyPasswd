package com.itmark.mypasswdbackend.controller.minio;

import com.itmark.mypasswdbackend.entity.resp.MarkAppRespEntity;
import com.itmark.mypasswdbackend.entity.sso.MarkUser;
import com.itmark.mypasswdbackend.service.minio.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/5 10:29
 */

@Slf4j
@RestController
@RequestMapping("/oss")
public class MinioController {
    @Resource
    private MinioService minioService;

    @GetMapping("/filesInFolder")
    public MarkAppRespEntity<List<String>> getFilesInFolder(@RequestParam(name = "bucketName") String bucketName,@RequestParam(name = "pathName") String pathName){
        List<String> filesInFolder = minioService.getFilesInFolder(bucketName, pathName);
        return MarkAppRespEntity.success(filesInFolder).message("获取成功");
    }

    @GetMapping("/filesShareLinkMap")
    public MarkAppRespEntity<Map<String, String>> getFilesShareLinkMap(@RequestParam(name = "bucketName")String bucketName, @RequestParam(name = "filePathWithBucketList")List<String> filePathWithBucketList){
        Map<String, String> filesShareLinkMap = minioService.getFilesShareLinkMap(bucketName, filePathWithBucketList);
        return MarkAppRespEntity.success(filesShareLinkMap).message("获取成功");
    }
}
