package com.lyh.jedis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lyh.jedis.util.JedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	private Jedis jedis = null;
	private JedisPool jedisPool = null;
	@Before
	public void init() {
		jedisPool = JedisPoolUtils.getInstance();
		jedis = jedisPool.getResource();
	}
	@Test
	public void testString() {
		jedis.set("s1", "v1");
		jedis.set("s2", "v2");
		jedis.set("s3", "v3");
		jedis.set("s4", "klsajfdlasjd214194lsajflsdaj2342");
		System.out.println(jedis.get("s1"));
		System.out.println(jedis.get("s2"));
		System.out.println(jedis.get("s3"));
		System.out.println(jedis.echo("s1"));
		System.out.println(jedis.getrange("s4", 0, -1));
		//System.out.println(jedis.flushDB());
	}
	@Test
	public void testList() {
		/*jedis.lpush("l1", "1","2","sdaljf","slfj323");
		jedis.lpush("l2", "sd","xcv","asd","df","sv","cs");*/
		jedis.lset("l1", 1, "xiugai");
		System.out.println(jedis.lrange("l1", 0, -1));
		System.out.println(jedis.llen("l1"));
		System.out.println(jedis.keys("*"));
	}
	@Test
	public void testHash() {
		jedis.hset("h1", "k1", "v1");
		jedis.hset("h2", "name", "zhangsan");
		jedis.hset("h2", "sex", "女");
		
		System.out.println(jedis.hget("h2", "sex"));
		System.out.println(jedis.hget("h2", "name"));
		System.out.println(jedis.hmget("h2", "sex","name"));
		System.out.println(jedis.keys("*"));
	}
	@After
	public void destory() {
		JedisPoolUtils.release(jedisPool, jedis);
	}
}
