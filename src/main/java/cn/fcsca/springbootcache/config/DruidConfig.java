package cn.fcsca.springbootcache.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DruidConfig
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 上午 11:18 2018-08-14
 */
@Configuration
public class DruidConfig {

    /**
     * 注册Druid
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 上午 11:24 2018-08-14
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid监控

    /**
     * 配置Druid管理后台的Servlet
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 上午 11:25 2018-08-14
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //配置数据后台管理员
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "509165");
        //值为空则默认允许所有人访问
        initParams.put("allow", "");
        //指定用户不能登录
        initParams.put("deny", "101.132.167.127");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置Druid后台的监控Filter
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 上午 11:25 2018-08-14
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
