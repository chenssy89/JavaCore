package main.java.com.redis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

/**
 * Redis 调用方式
 *
 * @author chenming
 * @date 2016/11/6 16:12
 */
public class RedisInvokeTest {

    /**
     * 普通同步方式
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    @Test
    public void normalTest() {
        Jedis jedis = new Jedis("localhost");

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            //set之后都可以返回结果，标记是否成功
            System.out.println(i + "---" + jedis.set("n_" + i, "n_" + i));
        }

        long end = System.currentTimeMillis();

        System.out.println("normal Set:" + (end - begin));

        jedis.disconnect();
    }

    /**
     * 事务方式调用
     *
     * 主要目的是保障，一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    @Test
    public void transTest() {
        Jedis jedis = new Jedis("localhost");

        long begin = System.currentTimeMillis();
        Transaction transaction = jedis.multi();
        for (int i = 0; i < 1000; i++) {
            transaction.set("t_" + i, "t_" + i);
        }
        List<Object> result = transaction.exec();

        long end = System.currentTimeMillis();

        System.out.println("Transaction Set:" + (end - begin));
        jedis.disconnect();
        System.out.println(result);
    }

    /**
     * 管道方式调用
     * 采用异步方式，一次发送多个指令，不同步等待其返回结果
     *
     * @author chenming
     * @date 2016-11-06
     * @since v1.0.0
     */
    @Test
    public void PipelinedTest() {
        Jedis jedis = new Jedis("localhost");

        long begin = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipeline.set("p_" + i, "p_" + i);
        }
        List<Object> result = pipeline.syncAndReturnAll();

        long end = System.currentTimeMillis();

        System.out.println("pipeline Set:" + (end - begin));
        jedis.disconnect();
        System.out.println(result);
    }

    /**
     * 分布式直连同步调用/异步改为ShardedJedisPipeline 即可
     * 
     * @author chenming
     * @date 2016-10-31
     * @since v1.0.0
     */
    @Test
    public void shardNormalTest(){
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost",6379),
                new JedisShardInfo("localhost",6380)
        );

        ShardedJedis sharding = new ShardedJedis(shards);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String result = sharding.set("shardNormal " + i, "sn" + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("shardNormal SET: " + (end - start));

        sharding.disconnect();
    }

    /**
     * 分布式连接池异步调用
     * 
     * @author chenming
     * @date 2016-10-31
     * @since v1.0.0
     */
    @Test
    public void shardPipelinedPoolTest(){
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost",6379),
                new JedisShardInfo("localhost",6380));

        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(),shards);

        ShardedJedis one = pool.getResource();
        ShardedJedisPipeline pipeline = one.pipelined();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            pipeline.set("shardPipelinedPool" + i, "n" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();

        pool.returnResource(one);
        System.out.println("shardPipelinedPool SET: " + (end - start));
        pool.destroy();
    }
}
