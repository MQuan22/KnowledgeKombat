package com.example.knowledgekombat.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class RedisLock {
    private Jedis jedis;
    private String lockKey;
    private String lockValue;
    private long lockExpirationTime;

    public RedisLock(String lockKey, Jedis jedis) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockValue = Thread.currentThread().getName();
        this.lockExpirationTime = 30000; // 30 seconds
    }

    public boolean acquire() {
        String result = jedis.set(lockKey, lockValue, new SetParams().nx().px(lockExpirationTime));
        return result != null && result.equalsIgnoreCase("OK");
    }

    public void release() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        jedis.eval(script, 1, lockKey, lockValue);
    }
}

