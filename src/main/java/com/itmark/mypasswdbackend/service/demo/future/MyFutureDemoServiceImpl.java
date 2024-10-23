package com.itmark.mypasswdbackend.service.demo.future;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

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

    @Override
    public void demoTwo() {
         CompletableFuture<Void> runAsyncEnd = CompletableFuture.runAsync(() -> {
             try {
                 TimeUnit.MILLISECONDS.sleep(500);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             System.out.println("runAsync end");
        });
         Void unused = runAsyncEnd.getNow(null);
         CompletableFuture<String> supplyAsyncEnd = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync end");
            return "complete";
        });
        try {
            String supplyResult = supplyAsyncEnd.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
