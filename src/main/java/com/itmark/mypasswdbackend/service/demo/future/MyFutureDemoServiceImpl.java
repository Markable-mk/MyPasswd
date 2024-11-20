package com.itmark.mypasswdbackend.service.demo.future;

import cn.hutool.core.util.NumberUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/12 16:41
 */
@Service
public class MyFutureDemoServiceImpl implements MyFutureDemoService{
    ExecutorService executor = Executors.newFixedThreadPool(5);
    @Resource(name = "executorPool")
    private ThreadPoolExecutor executorPool;

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

    @Override
    public void demoThree(List<String> idList) {
        List<String> threadExecuteIdList = new ArrayList<>();
        for(int i=0;i<idList.size();i++){
            threadExecuteIdList.add(idList.get(i) + "_" + i);
            if(threadExecuteIdList.size() > 200){
                this.futureCalc(threadExecuteIdList);
                threadExecuteIdList = new ArrayList<>();
            }else if(i == (idList.size()-1)){
                this.futureCalc(threadExecuteIdList);
            }
        }
    }

    void futureCalc(List<String> idList){
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(idList);
        int maximumPoolSize = executorPool.getMaximumPoolSize();
        CompletableFuture<?>[] futures = new CompletableFuture[maximumPoolSize];
        for (int i = 0; i < maximumPoolSize; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
                while (true) {
                    String id;
                    try {
                        id = queue.poll(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (id == null && queue.isEmpty()) {
                        break;
                    }
                    if (id != null) {
                        // 调用方法
                    }
                }
            }, executorPool);
        }
        CompletableFuture.allOf(futures).join();
    }

}
