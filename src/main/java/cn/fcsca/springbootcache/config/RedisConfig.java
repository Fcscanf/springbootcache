package cn.fcsca.springbootcache.config;

import cn.fcsca.springbootcache.bean.Department;
import cn.fcsca.springbootcache.bean.Employee;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import sun.font.TrueTypeFont;

/**
 * RedisConfig
 * ++++++++++++++++++RedisCacheManager两个配置类不完善+++++++++++++++++++++++++
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 上午 9:04 2018-08-24
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> redisEmpTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisTemplate<Object, Department> redisDeptTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Department> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    /**
     * @Primary 配置主缓存管理，使用时默认用主缓存，其他的相应配置也可以在类@CacheConfig 方法@Cacheable引入
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 下午 20:31 2018-08-24
     */
    @Primary
    @Bean
    public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> redisEmpTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisEmpTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisEmpTemplate.getValueSerializer()));
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
    }

    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> redisDeptTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisDeptTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisDeptTemplate.getValueSerializer()));
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
    }
}
