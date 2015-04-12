package pl.mjasion.moviesrss.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.domain.repository.GenreRepository
import pl.mjasion.moviesrss.domain.repository.MovieRepository
import pl.mjasion.moviesrss.scheduler.PremieresDownloaderJob

@RestController
@RequestMapping('/admin/')
class StatisticController {

    @Autowired BluerayPremiereRepository bluerayPremiereRepository
    @Autowired GenreRepository genreRepository
    @Autowired MovieRepository movieRepository

    @Autowired PremieresDownloaderJob premieresDownloaderJob

    @RequestMapping('/statistics')
    Map statistics() {
        return getStatistics()
    }

    @RequestMapping('/run')
    Map run() {
        premieresDownloaderJob.execute()
        return getStatistics()
    }

    private Map<String, Long> getStatistics() {
        return [
                genres   : genreRepository.count(),
                movies   : movieRepository.count(),
                premieres: bluerayPremiereRepository.count(),
        ]
    }
}
