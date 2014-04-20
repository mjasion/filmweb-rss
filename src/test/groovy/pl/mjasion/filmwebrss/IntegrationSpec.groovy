package pl.mjasion.filmwebrss

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = [FilmwebRssApplication], loader = SpringApplicationContextLoader)
abstract class IntegrationSpec extends Specification {
}
