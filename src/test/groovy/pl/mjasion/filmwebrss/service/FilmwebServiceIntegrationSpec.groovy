package pl.mjasion.filmwebrss.service

import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import pl.mjasion.filmwebrss.IntegrationSpec

class FilmwebServiceIntegrationSpec extends IntegrationSpec {
    @Autowired FilmwebService filmwebService

    @Value('${filmweb.url}')
    String filmwebUrl

    def "shoul get filmweb page"() {
        when:
        Document page = filmwebService.getPage('https://google.com/')

        then:
        page != null
        page.body() != null
    }

    def "should get dvd premieres list"() {
        when:
        DvdPremieresDto premieresList = filmwebService.getDvdPremiersList()

        then:
        premieresList.premieresList.size() > 0
        premieresList.nextPageLink != null
        premieresList.nextPageLink.startsWith(filmwebUrl)
        premieresList.previousPageLink != null
        premieresList.previousPageLink.startsWith(filmwebUrl)
    }
}
