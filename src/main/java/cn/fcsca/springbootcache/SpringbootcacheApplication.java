package cn.fcsca.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringbootcacheApplication
 * 1.Mybatis逆向工程
 * 2.缓存——默认jdk序列化对象，配置自定义JSON格式的对象
 * 3.引入Redis——默认RedisCacheManager是jdk序列化对象，配置自定义JSON格式的对象
 *
 * 注意：RedisCacheManager在该Pro未配置成功
 *
 * @author Fcscanf@樊乘乘
 * @date 上午 9:33 2018-08-24
 */
@MapperScan(value = "cn.fcsca.springbootcache.mapper")
@SpringBootApplication
public class SpringbootcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootcacheApplication.class, args);
    }
}
