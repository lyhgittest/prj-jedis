package com.lyh.jedis.util;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 获取redis连接的单实例工具类，需要高并发下数据安全,双端检索单实例模式
 * @author leehom
 *
 */
public class JedisPoolUtils {

	private static volatile JedisPool jedisPool = null;
	
	private JedisPoolUtils() {}
	
	public static JedisPool getInstance() {
		if (jedisPool == null) {
			synchronized (JedisPoolUtils.class) {
				if (jedisPool == null) {
					JedisPoolConfig jedisConfig = new JedisPoolConfig();
					jedisConfig.setMaxActive(1000);
					jedisConfig.setMaxIdle(32);
					jedisConfig.setMaxWait(100*1000);
					jedisConfig.setTestOnBorrow(true);
					jedisPool = new JedisPool(jedisConfig, "192.168.31.111", 6379);
					//jedisPool = new JedisPool(jedisConfig, "127.0.0.1");
				}
			}
		}
		return jedisPool;
	}
	public static void release(JedisPool jedisPool,Jedis jedis) {
		if (jedis != null && (jedisPool != null)) {
			jedisPool.returnResource(jedis);
		}
	}
}
