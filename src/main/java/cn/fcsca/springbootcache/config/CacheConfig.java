package cn.fcsca.springbootcache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * CacheConfig 自定义缓存KEY生成策略
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 下午 16:50 2018-08-23
 */
@Configuration
public class CacheConfig {

    @Bean("keyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
            }
        };
    }
}
