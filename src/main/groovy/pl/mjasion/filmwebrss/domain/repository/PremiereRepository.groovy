package pl.mjasion.filmwebrss.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository
import pl.mjasion.filmwebrss.domain.Movie
import pl.mjasion.filmwebrss.domain.Premiere

interface PremiereRepository extends PagingAndSortingRepository<Premiere, String> {

    Premiere findByMovieAndStorageMedia(Movie movie, String storageMedia)

}
