package pl.mjasion.moviesrss.conf

import groovy.transform.CompileStatic
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.View
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.BeanNameViewResolver

@CompileStatic
@Configuration
@EnableWebMvc
class WebMvcConf extends WebMvcConfigurerAdapter {
    @Bean
    @ConditionalOnBean(View.class)
    public BeanNameViewResolver beanNameViewResolver() {
        return new BeanNameViewResolver()
    }
}
