package pl.mjasion.filmwebrss.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository
import pl.mjasion.filmwebrss.domain.BlueRayPremiere
import pl.mjasion.filmwebrss.domain.Movie

interface BluerayPremiereRepository extends PagingAndSortingRepository<BlueRayPremiere, String> {

    BlueRayPremiere findByMovieAndStorageMedia(Movie movie, String storageMedia)

}
