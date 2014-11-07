package pl.mjasion.moviesrss

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Slf4j
@CompileStatic
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan
class MoviesRssApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MoviesRssApplication.class, args);
    }

}