package com.imocc.config.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * <p>开启mvc，自动注入spring容器
 * <p>WebMvcConfigurerAdapter：配置视图解析器
 * <p>当一个类实现了ApplicationContextAware接口之后，这个类就可以方便获得ApplicationContext容器中所有的bean
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/6/1 14:18
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;
    @Value("${kaptcha.image.width}")
    private String width;
    @Value("${kaptcha.textproducer.char.string}")
    private String charString;
    @Value("${kaptcha.image.height}")
    private String height;
    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;
    @Value("${kaptcha.noise.color}")
    private String ncolor;
    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fnames;

    // spring容器
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * <p>静态资源配置
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/1 14:25
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/data/image/upload/");
    }

    /**
     * <p>定义默认的请求处理器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/1 14:30
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * <p>创建viewResolver
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/1 14:33
     */
    @Bean(name = "viewResolver")
    public ViewResolver createViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 设置spring容器
        viewResolver.setApplicationContext(this.applicationContext);
        // 取消缓存
        viewResolver.setCache(false);
        // 设置解析的前缀
        viewResolver.setPrefix("/WEB-INF/html/");
        // 设置视图解析的后缀
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * <p>文件上传解析器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/1 14:39
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        // 1024 * 1024 * 20 = 20M
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }

    /**
     * <p>由于web.xml不生效了，需要在这里配置Kaptcha验证码到servlet
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/1 14:39
     */
    @Bean
    public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() {
        ServletRegistrationBean<KaptchaServlet> servlet = new ServletRegistrationBean<>(new KaptchaServlet(),"/Kaptcha");
        servlet.addInitParameter("kaptcha.border", border);
        servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
        servlet.addInitParameter("kaptcha.image.width", width);
        servlet.addInitParameter("kaptcha.textproducer.char.string", charString);
        servlet.addInitParameter("kaptcha.image.height", height);
        servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
        servlet.addInitParameter("kaptcha.noise.color", ncolor);
        servlet.addInitParameter("kaptcha.textproducer.char.length", charLength);
        servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);
        return servlet;
    }
}