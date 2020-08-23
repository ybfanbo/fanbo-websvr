package com.fanbo.controller;

import com.fanbo.manager.ThreadPoolTask;
import com.fanbo.model.basemodel.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class ThreadPoolExecutorController {


    @RequestMapping(value = "/threadPoolExecutor")
    public Response threadPoolExecutor(){
        Response result = new Response();

        try {

            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));

            for (int i = 1; i <= 15; i++){

                System.out.println();
                ThreadPoolTask task = new ThreadPoolTask(i);

                executor.execute(task);  //执行任务

                System.out.println("线程池中线程数目：" + executor.getPoolSize());
                System.out.println("队列中等待执行的任务数目：" + executor.getQueue().size());
                System.out.println("已执行完的任务数目：" + executor.getCompletedTaskCount());
            }

            executor.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


}
