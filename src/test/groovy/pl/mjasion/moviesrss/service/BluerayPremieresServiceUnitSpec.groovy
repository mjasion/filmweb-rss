package pl.mjasion.moviesrss.service

import org.joda.time.DateTime
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pl.mjasion.moviesrss.domain.Movie
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.domain.repository.GenreRepository
import pl.mjasion.moviesrss.domain.repository.MovieRepository
import pl.mjasion.moviesrss.service.dto.BlurayPremieresDto
import pl.mjasion.moviesrss.service.filmweb.FilmwebService
import spock.lang.Specification

import static pl.mjasion.moviesrss.factory.PremieresFactory.createBlueRayPremieres

class BluerayPremieresServiceUnitSpec extends Specification {
    String filmwebUrl = 'http://filmweb.pl'
    BlurayPremieresDto premieres = createBlueRayPremieres()
    FilmwebService filmwebService = Mock()
    BluerayPremiereRepository premiereRepository = Mock()
    GenreRepository genreRepository = Stub()
    MovieRepository movieRepository = Mock()

    BlurayPremieresService premieresService = new BlurayPremieresService(
            filmwebUrl: filmwebUrl,
            filmwebService: filmwebService,
            premiereRepository: premiereRepository,
            genreRepository: genreRepository,
            movieRepository: movieRepository
    )

    def "should save current premieres"() {
        when:
        premieresService.saveCurrentPremieres()

        then:
        1 * filmwebService.getPremiersDto() >> premieres
        1 * premiereRepository.save(_ as List)
    }

    def "should convert premieres list"() {
        given:
        List<Elements> premieres = this.premieres.premieres
        Element firstPremiere = premieres.first()
        movieRepository.findByName(_) >> new Movie(name: firstPremiere.select('h3.premiereFilm a').text())

        when:
        def convertedPremieres = premieresService.convertPremieres(premieres)

        then:
        convertedPremieres != null
        convertedPremieres.first().movie.name == firstPremiere.select('h3.premiereFilm a').text()
        convertedPremieres*.storageMedia == premieres*.select('span.storageMedia').flatten()*.text()
    }

    def "should parse premiere shop date"() {
        given:
        Date date = new DateTime()
                .withMillisOfDay(0)
                .withYear(2014)
                .withMonthOfYear(4)
                .withDayOfMonth(21)
                .withMillisOfDay(0).toDate()

        when:
        def parsedDate = premieresService.parsePremiereShopdate('21/04/2014')

        then:
        parsedDate == date
    }
}
