package pl.mjasion.moviesrss.application

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.moviesrss.IntegrationSpec
import pl.mjasion.moviesrss.MoviesRssApplication

class FimwebRssApplicationIntegrationSpec extends IntegrationSpec {
    @Autowired MoviesRssApplication application

    def "should initialize spring"() {
        expect:
        application != null
    }
}
