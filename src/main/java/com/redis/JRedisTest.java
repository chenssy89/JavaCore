package com.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenssy
 * @Date: 2016/10/30 9:41
 */
public class JRedisTest {
    private Jedis jedis = null;

    @Before
    public void setUp(){
        //连接redis服务器
        jedis = new Jedis("localhost");
    }

    /**
     * String Test
     *
     * @author chenssy
     * @date 2016-10-30
     * @since v1.0.0
     */
    @Test
    public void stringTest(){
        jedis.set("name", "chenssy");
        System.out.println(jedis.get("name"));
        
        jedis.append("name","_cmblogs.com");
        System.out.println(jedis.get("name"));
        
        jedis.del("name");
        System.out.println(jedis.get("name"));

        jedis.mset("name","chenssy","blog","cmsblogs");
        System.out.println(jedis.get("name") + "--" + jedis.get("blog"));
    }

    /**
     * Map test
     *
     * @author chenssy
     * @date 2016-10-30
     * @since v1.0.0
     */
    @Test
    public void mapTest(){
        Map<String,String> user = new HashMap<String,String>();
        user.put("name","chenssy");
        user.put("sex","boy");      //O(∩_∩)O~
        user.put("github","chenssy89");
        user.put("QQ","122448894");

        jedis.hmset("user" ,user);

        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> list = jedis.hmget("user","name","sex","github","QQ");
        System.out.println(list);

        jedis.hdel("user","QQ");
        System.out.println(jedis.hmget("user", "name", "QQ"));
        System.out.println("user 中元素的个数：" + jedis.hlen("user"));
        System.out.println("jedis 中是否存在user:" + jedis.exists("user"));
        System.out.println("user 中的key：" + jedis.hkeys("user"));
        System.out.println("user 中的key："+jedis.hvals("user"));
    }

    /**
     * List Test
     *
     * @author chenssy
     * @date 2016-10-30
     * @since v1.0.0
     */
    @Test
    public void listTest(){
        jedis.del("list");

        jedis.lpush("list","chenssy");
        jedis.lpush("list","boy");
        jedis.lpush("list","chenss89");
        jedis.lpush("list","122448894");

        /*
         *再取出所有数据jedis.lrange是按范围取出，
         *第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
         */

        System.out.println(jedis.lrange("list",0,-1));
        System.out.println(jedis.lpop("list"));
        System.out.println(jedis.lrange("list",0,-1));
    }

    /**
     * Set 集合Test
     *
     * @author chenssy
     * @date 2016-10-30
     * @since v1.0.0
     */
    @Test
    public void setTest(){
        jedis.sadd("set","chenssy");
        jedis.sadd("set","boy");
        jedis.sadd("set","chenssy89");
        jedis.sadd("set","122448894");
        jedis.sadd("set","chenssy");

        System.out.println(jedis.smembers("set"));
        System.out.println(jedis.sismember("set", "boy"));
        System.out.println(jedis.srandmember("set"));
        System.out.println(jedis.scard("set"));
    }

    /**
     * 排序
     *
     * @author chenssy
     * @date 2016-10-30
     * @since v1.0.0
     */
    @Test
    public void sortTest(){
        jedis.del("sort");

        jedis.rpush("sort","8");
        jedis.rpush("sort","7");
        jedis.rpush("sort","9");
        jedis.rpush("sort","6");
        jedis.rpush("sort","4");
        jedis.rpush("sort","5");

        System.out.println(jedis.lrange("sort",0,-1));
        System.out.println(jedis.sort("sort"));
        System.out.println(jedis.lrange("sort",0,-1));
    }

    /**
     * 连接池test
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    @Test
    public void redisPoolTest(){
        JRedisPoolUtils.getJedis().set("pool","chenssy-redis-pool");
        System.out.println(JRedisPoolUtils.getJedis().get("pool"));
    }
}
