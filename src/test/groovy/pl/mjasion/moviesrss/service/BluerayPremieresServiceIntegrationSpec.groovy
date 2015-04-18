package pl.mjasion.moviesrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.moviesrss.IntegrationSpec
import pl.mjasion.moviesrss.domain.BlueRayPremiere
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.domain.repository.GenreRepository
import pl.mjasion.moviesrss.domain.repository.MovieRepository

class BluerayPremieresServiceIntegrationSpec extends IntegrationSpec {
    @Autowired BlurayPremieresService premieresService
    @Autowired BluerayPremiereRepository premiereRepository
    @Autowired GenreRepository genreRepository
    @Autowired MovieRepository movieRepository

    def "should save current blueray premieres"() {
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

    def cleanup() {
        premiereRepository.deleteAll()
        genreRepository.deleteAll()
        movieRepository.deleteAll()
    }
}
