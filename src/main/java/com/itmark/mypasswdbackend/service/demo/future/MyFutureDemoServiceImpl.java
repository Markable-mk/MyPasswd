package com.itmark.mypasswdbackend.service.demo.future;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/12 16:41
 */
@Service
public class MyFutureDemoServiceImpl implements MyFutureDemoService{
    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void demoOne() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "step1 result";
        }, executor);

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            return "step2 result";
        });

        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println("获取到step 1，step 2结果作为step 3的入参"+result1 + " , " + result2);
            System.out.println("执行step 3");
            return "step3 result";
        }).thenAccept(System.out::println);
    }

}
