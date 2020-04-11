package com.agency.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@EnableWebMvc
@Configuration
@ComponentScan( "com.agency")
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        return localeResolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        List<MediaType> supportedMediaTypes=new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new HibernateAwareObjectMapper());
        converter.setPrettyPrint(true);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(converter);
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }
}
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//               .addResourceLocations("/resources/");
//       registry
//               .addResourceHandler("/webjars/**")
//               .addResourceLocations("/webjars/");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver =
//                new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//
//
////
////    @Bean
////    public InternalResourceViewResolver viewResolver() {
////        InternalResourceViewResolver viewResolver =
////                new InternalResourceViewResolver();
////        viewResolver.setViewClass(JstlView.class);
////        viewResolver.setPrefix("/WEB-INF/views/");
////        viewResolver.setSuffix(".jsp");
////        return viewResolver;
////    }
////
////    @Bean("messageSource")
////    public MessageSource messageSource() {
////        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
////        messageSource.setBasename("classpath:locale/messages");
////        messageSource.setDefaultEncoding("UTF-8");
////        messageSource.setUseCodeAsDefaultMessage(true);
////        return messageSource;
////    }
////
////    @Bean
////    public LocaleResolver localeResolver() {
////        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
////        return localeResolver;
////    }
////
////
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////
////        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
////        localeChangeInterceptor.setParamName("lang");
////        registry.addInterceptor(localeChangeInterceptor);
////    }
////
////    @Bean
////    public BCryptPasswordEncoder bCryptPasswordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////
////    @Override
////    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
////        List<MediaType> supportedMediaTypes=new ArrayList<>();
////        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
////        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
////        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
////        converter.setObjectMapper(new HibernateAwareObjectMapper());
////        converter.setPrettyPrint(true);
////        converter.setSupportedMediaTypes(supportedMediaTypes);
////        converters.add(converter);
////        WebMvcConfigurer.super.configureMessageConverters(converters);
////    }
////
//   @Bean
//    public ModelMapper modelMapper() {
//       ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration()
//               .setMatchingStrategy(MatchingStrategies.STRICT)
//               .setFieldMatchingEnabled(true)
//               .setSkipNullEnabled(true)
//                .setFieldAccessLevel(PRIVATE);
//       return mapper;
//   }
//}

















































//    <?xml version="1.0" encoding="UTF-8"?>
//
//<beans xmlns="http://www.springframework.org/schema/beans"
//        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//        xmlns:context="http://www.springframework.org/schema/context"
//        xmlns:mvc="http://www.springframework.org/schema/mvc"
//        xmlns:tx="http://www.springframework.org/schema/tx"
//        xmlns:p="http://www.springframework.org/schema/p"
//        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
//
//<tx:annotation-driven/>
//<mvc:annotation-driven/>
//<mvc:default-servlet-handler/>
//<context:component-scan base-package="com.example"/>
//<context:spring-configured/>
//<context:annotation-config/>
//
//<bean id="jdbcPropertyConfigurer"
//class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer"
//        p:location="classpath:application.properties"/>
//
//<bean id="persistenceUnitManager" class="org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager">
//<property name="persistenceXmlLocations">
//<list value-type="java.lang.String">
//<value>classpath*:/resources/META-INF/persistence.xml</value>
//</list>
//</property>
//</bean>
//
//
//<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//<property name="prefix" value="/WEB-INF/views/"/>
//<property name="suffix" value=".jsp"/>
//</bean>
//
//
//<bean id="helloService" class="com.example.service.HelloServiceImpl" scope="prototype"/>
//<bean id="userService" class="com.example.service.UserService" scope="prototype">
//<constructor-arg index="0" ref="userCrudService"/>
//<constructor-arg index="1" ref="bCryptPasswordEncoder"/>
//<constructor-arg index="2" ref="roleCrudService"/>
//</bean>
//
//<bean id="bCryptPasswordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"/>
//<bean id="roleCrudService" class="com.example.service.crudService.RoleCrudService" scope="prototype"/>
//<bean id="commentService" class="com.example.service.CommentService" scope="prototype"/>
//<bean id="userCrudService" class="com.example.service.crudService.UserCrudService" scope="prototype"/>
//<bean id="companyCrudService" class="com.example.service.crudService.CompanyCrudService" scope="prototype"/>
//<bean id="commentCrudService" class="com.example.service.crudService.CommentCrudService" scope="prototype"/>
//
//<bean id="travelCompanyService" class="com.example.service.TravelCompanyService" scope="prototype">
//<constructor-arg index="0" ref="companyCrudService"/>
//</bean>
//<bean id="myEmf" class="org.springframework.orm.jpa.LocalEntityManagerFactoryBean">
//<property name="persistenceUnitName" value="testPersUnit"/>
//<property name="jpaProperties">
//<props>
//<prop key="hibernate.dialect">${hibernate.dialect}</prop>
//<prop key="hibernate.hbm2ddl.auto">${hibernate.hbm2ddl.auto}</prop>
//<prop key="javax.persistence.jdbc.driver">${javax.persistence.jdbc.driver}</prop>
//<prop key="javax.persistence.jdbc.url">${javax.persistence.jdbc.url}</prop>
//<prop key="javax.persistence.jdbc.user">${javax.persistence.jdbc.user}</prop>
//<prop key="javax.persistence.jdbc.password">${javax.persistence.jdbc.password}</prop>
//</props>
//</property>
//</bean>
//
//<!--    <bean id="myEmf2" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">-->
//<!--        <property name="persistenceUnitName" value="testH2Unit"/>-->
//<!--        <property name="persistenceUnitManager" ref="persistenceUnitManager" />-->
//<!--    </bean>-->
//
//<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
//<property name="entityManagerFactory" ref="myEmf"/>
//</bean>
//</beans>