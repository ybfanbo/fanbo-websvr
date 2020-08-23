package com.fanbo.manager;

import com.fanbo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadPoolTask implements Runnable{

    private int taskId; //当前执行的任务编号id

    public ThreadPoolTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {

        System.out.println("当前正在执行任务：" + taskId);

        try {
            Thread.currentThread().sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("任务：" + taskId + "执行结束");
    }



}
