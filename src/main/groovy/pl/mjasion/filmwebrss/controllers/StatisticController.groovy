package pl.mjasion.filmwebrss.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.filmwebrss.domain.repository.GenreRepository
import pl.mjasion.filmwebrss.domain.repository.MovieRepository
import pl.mjasion.filmwebrss.domain.repository.PremiereRepository

@RestController
@RequestMapping('/statistics')
class StatisticController {

    @Autowired PremiereRepository premiereRepository
    @Autowired GenreRepository genreRepository
    @Autowired MovieRepository movieRepository

    @RequestMapping
    Map statistics() {
        return [
                genres   : genreRepository.count(),
                movies   : movieRepository.count(),
                premieres: premiereRepository.count(),
        ]
    }
}
