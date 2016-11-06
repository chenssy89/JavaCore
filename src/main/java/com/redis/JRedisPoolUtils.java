package main.java.com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis的连接池
 *
 * @author:chenming
 * @date : 2016/10/31 8:56
 */
public class JRedisPoolUtils {
    private final static String REDIS_IP = "localhost";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化连接池
     */
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWait(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(config,REDIS_IP);
    }

    /**
     * 获取Redis实例
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    public synchronized static Jedis getJedis(){
        if(jedisPool != null){
            return jedisPool.getResource();
        }else{
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    public static void returnJedis(final Jedis jedis){
        if(jedisPool !=  null){
            jedisPool.returnResource(jedis);
        }
    }

}
