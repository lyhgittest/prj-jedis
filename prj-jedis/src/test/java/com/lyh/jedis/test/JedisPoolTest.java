package com.lyh.jedis.test;

import org.junit.Test;

import com.lyh.jedis.util.JedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {

	@Test
	public void test() {
		JedisPool jedisPool = JedisPoolUtils.getInstance();
		if (jedisPool != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.set("m1", "v1");
				String value = jedis.get("m1");
				System.out.println("value="+value);
				
			} catch(Exception e){
				e.printStackTrace();
			}finally {
				JedisPoolUtils.release(jedisPool, jedis);
			}
			
		}
	}
	@Test
	public void testSuccess() {
		 //连接本地的 Redis 服务
	    //Jedis jedis = new Jedis("127.0.0.1",6379);
	    Jedis jedis = new Jedis("192.168.31.111",6379);
	    //查看服务是否运行，打出pong表示OK
	    System.out.println("connection is OK==========>: "+jedis.ping());
	}
}
