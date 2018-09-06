package com.neuedu.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TokenCache {

    private static LoadingCache<String, String> cacheLoader =
            CacheBuilder.newBuilder()
                    .initialCapacity(1000)// 初始化缓存项
                    .maximumSize(10000)// 最大缓存项
                    .expireAfterAccess(2, TimeUnit.HOURS) // 定时回收
                    .build(new CacheLoader<String, String>() {
                        // 当从缓存中读取数据时，如果不存在数据，就会调用load()
                        @Override
                        public String load(String s) throws Exception {
                            return "null";
                        }
                    });

    public static void set(String key, String value) {
        cacheLoader.put(key, value);
//        System.out.println("设置成功");

    }

    public static String get(String key) {
        try {
//            System.out.println("get"+cacheLoader.get(key));
             return  cacheLoader.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

