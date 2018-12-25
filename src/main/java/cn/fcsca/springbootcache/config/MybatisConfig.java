package cn.fcsca.springbootcache.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * MybatisConfig
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 下午 16:44 2018-08-14
 */
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    /**
     * 开启Mybatis设置驼峰命名 Example：departmentName--department_name
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 下午 16:45 2018-08-14
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
