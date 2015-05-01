package pl.mjasion.moviesrss.conf

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(value = 'pl.mjasion.moviesrss')
@CompileStatic
class MongoConf {
}
