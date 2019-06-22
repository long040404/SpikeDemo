/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.stu.edu.spike.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

    @Autowired
    JedisPool pool;

    @Autowired
    RedisConfig redisConfig;

    public <T> T get(String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            String str = jedis.get(key);
            T t = stringTobean(str, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    private <T> T stringTobean(String str, Class<?> clazz) {
        if (str == null || str.length() <=0 || clazz == null){
            return null;
        }

        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class){
            return (T) String.valueOf(str);
        }else if (clazz == long.class || clazz == Long.class){
            return (T) Long.valueOf(str);
        }else {
            return (T) JSON.toJavaObject(JSON.parseObject(str), clazz);
        }

    }

    public <T> boolean set(String key, T value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0){
                return false;
            }
            jedis.set(key, str);
            return true;

        }finally {
            returnToPool(jedis);
        }

    }

    private <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return ""+value;
        }else if (clazz == String.class){
            return (String) value;
        }else if (clazz == long.class || clazz == Long.class){
            return ""+ value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis !=null){
            jedis.close();
        }
    }


}
