package com.imocc.config.redis;

import com.imocc.cache.JedisPoolWriter;
import com.imocc.cache.JedisUtil;
import com.imocc.util.DESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by kqyang on 2019/5/30.
 */
public class RedisConfiguration {
    @Value("${redis.hostname}")
    private String hostname;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.pool.maxActive}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.maxWait}")
    private long maxWaitMillis;
    @Value("${redis.pool.timeout}")
    private int timeout;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;
    @Autowired
    private JedisPoolWriter jedisPoolWriter;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * <p>redis 连接池的设置
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 00:00
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig createJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例
        poolConfig.setMaxTotal(maxTotal);
        // 连接池中最多可空闲maxIdle个连接，表示没有数据库连接时依然可以保持20个空闲的连接而不被清楚，随时处于待命状态
        poolConfig.setMaxIdle(maxIdle);
        // 最大等待时间，当没有可用连接时，连接池等待连接被归还的最大时间(单位毫秒)，超时则抛出异常
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        // 获取连接时检查有效性
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }

    /**
     * <p>创建redis连接池，并做相关配置
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 00:13
     */
    @Bean(name = "jedisPoolWriter")
    public JedisPoolWriter createJedisPoolWriter() {
        JedisPoolWriter poolWriter = new JedisPoolWriter(jedisPoolConfig,
                DESUtil.getDecryptString(hostname), Integer.parseInt(DESUtil.getDecryptString(port)),
                timeout, DESUtil.getDecryptString(password));
        return poolWriter;
    }

    /**
     * <p>创建redis 工具类
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 00:26
     */
    @Bean(name = "jedisUtil")
    public JedisUtil createJedisUtil(){
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.setJedisPool(jedisPoolWriter);
        return jedisUtil;
    }

    /**
     * <p>redis 的key操作
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 00:28
     */
    @Bean(name = "jedisKeys")
    public JedisUtil.Keys createJedisKeys(){
        JedisUtil.Keys jedisKeys = jedisUtil.new Keys();
        return jedisKeys;
    }

    /**
     * <p>redis 的String操作
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 00:30
     */
    @Bean(name = "jedisStrings")
    public JedisUtil.Strings createJedisStrings(){
        JedisUtil.Strings jedisStrings = jedisUtil.new Strings();
        return jedisStrings;
    }
}