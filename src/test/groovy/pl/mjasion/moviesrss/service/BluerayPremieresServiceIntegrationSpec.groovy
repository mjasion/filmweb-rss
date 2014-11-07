package pl.mjasion.moviesrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.moviesrss.IntegrationSpec
import pl.mjasion.moviesrss.domain.BlueRayPremiere
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.domain.repository.GenreRepository

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
