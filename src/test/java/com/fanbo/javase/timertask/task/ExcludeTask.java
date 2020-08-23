package com.fanbo.javase.timertask.task;

import com.fanbo.javase.timertask.constant.Constant;
import com.fanbo.javase.timertask.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.TimerTask;


/*
 * 排重任务，将redis里queue_exclude_timing队列里的数据，拷贝到queue_exclude里
 */
public class ExcludeTask extends TimerTask{

	@Override
	public void run() {

		Jedis jedis = RedisUtil.getRedisOfLocalhost();
		
		List<String> queueTimer = jedis.lrange(Constant.QUEUE_EXCLUDE_TIMING, 0, Integer.MAX_VALUE);
		
		if(queueTimer.size() == 0){
			return;
		}
		
		jedis.lpush(Constant.QUEUE_EXCLUDE, queueTimer.toArray(new String[queueTimer.size()]));
		
		jedis.del(Constant.QUEUE_EXCLUDE_TIMING);
		
	}

}
