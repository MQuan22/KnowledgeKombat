package com.example.knowledgekombat.util;

import redis.clients.jedis.Jedis;
public class RedisUtils {
    private Jedis jedis;
    private RedisLock lock;

    public RedisUtils() {
        this.jedis = new Jedis("18.143.173.183", 6379);
        this.lock = new RedisLock("myLockKey", jedis);
    }

    public void SetKeyValue(String key, String value){
        jedis.set(key,value);
    }

    public String GetValue(String key){
        return jedis.get(key);
    }

    public void SetExpire(String key, long time){
        jedis.expire(key, time);
    }

    public boolean acquireLock() {
        return lock.acquire();
    }

    public void releaseLock() {
        lock.release();
    }
}
