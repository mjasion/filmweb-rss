package pl.mjasion.moviesrss

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = [MoviesRssApplication], loader = SpringApplicationContextLoader)
abstract class IntegrationSpec extends Specification {
}
