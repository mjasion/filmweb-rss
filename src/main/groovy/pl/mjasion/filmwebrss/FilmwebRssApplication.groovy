package pl.mjasion.filmwebrss

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
class FilmwebRssApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FilmwebRssApplication.class, args);
    }

}