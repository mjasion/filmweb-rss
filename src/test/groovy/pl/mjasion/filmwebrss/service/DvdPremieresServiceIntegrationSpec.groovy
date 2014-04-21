package pl.mjasion.filmwebrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.filmwebrss.IntegrationSpec
import pl.mjasion.filmwebrss.domain.Premiere
import pl.mjasion.filmwebrss.domain.repository.PremiereRepository

class DvdPremieresServiceIntegrationSpec extends IntegrationSpec {
    @Autowired DvdPremieresService dvdPremieresService
    @Autowired PremiereRepository premiereRepository

    def setup() {
        premiereRepository.deleteAll()
    }

    def "should save current dvd premieres"() {
        given:
        int premieresCount = 15

        when:
        List<Premiere> premieres = dvdPremieresService.saveCurrentDvdPremieres()

        then:
        premiereRepository.count() == premieresCount
        def premieresFromDb = premieres.findAll()
        premieresFromDb*.genres.flatten() as Set == premieres*.genres.flatten() as Set
    }
}
