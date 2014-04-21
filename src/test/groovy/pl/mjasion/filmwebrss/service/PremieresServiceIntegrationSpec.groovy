package pl.mjasion.filmwebrss.service

import org.springframework.beans.factory.annotation.Autowired
import pl.mjasion.filmwebrss.IntegrationSpec
import pl.mjasion.filmwebrss.domain.Genre
import pl.mjasion.filmwebrss.domain.Premiere
import pl.mjasion.filmwebrss.domain.repository.GenreRepository
import pl.mjasion.filmwebrss.domain.repository.PremiereRepository

class PremieresServiceIntegrationSpec extends IntegrationSpec {
    @Autowired PremieresService premieresService
    @Autowired PremiereRepository premiereRepository
    @Autowired GenreRepository genreRepository

    def setup() {
        premiereRepository.deleteAll()
    }

    def "should save current premieres"() {
        given:
        int expectedPremieresCount = 15
        genreRepository.deleteAll()

        when:
        premieresService.saveCurrentPremieres()

        then:
        List<Premiere> premieres = premiereRepository.findAll()
        premieres.size() == expectedPremieresCount
        List<Genre> genres = premieres*.genres.flatten().unique(false) { Genre genre -> genre.name }
        genreRepository.count() == genres.size()
    }

    def "should not duplicate premieres"() {
        given:
        int expectedPremieresCount = 15
        premieresService.saveCurrentPremieres()

        when:
        premieresService.saveCurrentPremieres()

        then:
        premiereRepository.count() == expectedPremieresCount
    }
}
