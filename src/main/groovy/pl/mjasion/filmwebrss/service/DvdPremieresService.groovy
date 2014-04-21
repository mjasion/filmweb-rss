package pl.mjasion.filmwebrss.service

import com.google.common.annotations.VisibleForTesting
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.mjasion.filmwebrss.domain.Genre
import pl.mjasion.filmwebrss.domain.Movie
import pl.mjasion.filmwebrss.domain.Premiere
import pl.mjasion.filmwebrss.domain.repository.GenreRepository
import pl.mjasion.filmwebrss.domain.repository.MovieRepository
import pl.mjasion.filmwebrss.domain.repository.PremiereRepository

@Service
class DvdPremieresService {
    @Autowired FilmwebService filmwebService
    @Autowired PremiereRepository premiereRepository
    @Autowired MovieRepository movieRepository
    @Autowired GenreRepository genreRepository

    @Value('${filmweb.url}')
    private String filmwebUrl


    List<Premiere> saveCurrentDvdPremieres() {
        DvdPremieresDto premieresDto = filmwebService.getDvdPremiersDto()
        return saveDvdPremiereDtos(premieresDto)
    }

    ArrayList<Premiere> saveDvdPremiereDtos(DvdPremieresDto premieresDto) {
        List premieres = convertPremieres(premieresDto.premieres)
        premiereRepository.save(premieres)
        return premieres.unique(false) { Premiere premiere -> premiere.movie.name }
    }

    List convertPremieres(List<Elements> premieres) {
        return premieres.collect { Element element ->
            return new Premiere(
                    movie: getMovie(element),
                    premiereShopdate: parsePremiereShopdate(element.select('span.premiereShopdate').attr('title')),
                    storageMedia: element.select('span.storageMedia').text(),
                    dateAdded: new Date(),
                    genres: getGenres(element)
            )
        }
    }

    private Movie getMovie(Element element) {
        Elements movieElement = element.select('h3.premiereFilm a')
        Movie movie = movieRepository.findByName(movieElement.text())
        if (movie == null) {
            movie = new Movie(
                    name: movieElement.text(),
                    filmwebLink: "${filmwebUrl}${movieElement.attr('href')}"
            )
            movieRepository.save(movie)
        }
        return movie
    }

    @VisibleForTesting
    private Date parsePremiereShopdate(String premiereShopDate) {
        return new Date().parse('dd/MM/yyyy HH:mm', premiereShopDate)
    }

    // TODO create converter http://www.javacodegeeks.com/2013/11/spring-data-mongodb-cascade-save-on-dbref-objects.html
    private List<Genre> getGenres(Element element) {
        List<Genre> genres = element.select('li > a:not([class])').collect {
            new Genre(
                    name: it.text(),
                    link: "$filmwebUrl${it.attr('href')}"
            )
        }
        genreRepository.save(genres)
        return genres
    }
}
