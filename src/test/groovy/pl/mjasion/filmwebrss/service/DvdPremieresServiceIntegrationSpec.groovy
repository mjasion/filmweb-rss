package pl.mjasion.filmwebrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.filmwebrss.IntegrationSpec
import pl.mjasion.filmwebrss.domain.premiere.Premiere
import pl.mjasion.filmwebrss.domain.premiere.PremiereRepository

class DvdPremieresServiceIntegrationSpec extends IntegrationSpec {
    @Autowired DvdPremieresService dvdPremieresService
    @Autowired PremiereRepository premiereRepository

    def setup() {
        premiereRepository.deleteAll()
    }

    def "should save current dvd premieres"() {
        given:
        int premieresCount = 13 // http://www.filmweb.pl/dvd/premieres/2014-03-24

        when:
        List<Premiere> premieres = dvdPremieresService.saveCurrentDvdPremieres()

        then:
        premiereRepository.count() == premieresCount
    }
}
