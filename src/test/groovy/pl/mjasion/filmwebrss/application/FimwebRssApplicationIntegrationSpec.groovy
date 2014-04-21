package pl.mjasion.filmwebrss.application

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.filmwebrss.FilmwebRssApplication
import pl.mjasion.filmwebrss.IntegrationSpec

class FimwebRssApplicationIntegrationSpec extends IntegrationSpec {
    @Autowired FilmwebRssApplication application

    def "should initialize spring"() {
        expect:
        application != null
    }
}
