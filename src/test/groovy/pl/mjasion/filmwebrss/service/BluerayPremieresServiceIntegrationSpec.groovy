package pl.mjasion.filmwebrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.filmwebrss.IntegrationSpec
import pl.mjasion.filmwebrss.domain.BlueRayPremiere
import pl.mjasion.filmwebrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.filmwebrss.domain.repository.GenreRepository

class BluerayPremieresServiceIntegrationSpec extends IntegrationSpec {
    @Autowired BlurayPremieresService premieresService
    @Autowired BluerayPremiereRepository premiereRepository
    @Autowired GenreRepository genreRepository

    def setup() {
        premiereRepository.deleteAll()
    }

    def "should save current blueray premieres"() {
        given:
        genreRepository.deleteAll()

        when:
        premieresService.saveCurrentPremieres()

        then:
        List<BlueRayPremiere> premieres = premiereRepository.findAll()
        premieres.size() == 2
    }

    def "should not duplicate premieres"() {
        given:
        premieresService.saveCurrentPremieres()

        when:
        premieresService.saveCurrentPremieres()

        then:
        premiereRepository.count() == 2
    }
}
