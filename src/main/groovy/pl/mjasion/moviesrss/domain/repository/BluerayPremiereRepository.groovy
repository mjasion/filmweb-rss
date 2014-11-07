package pl.mjasion.moviesrss.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository
import pl.mjasion.moviesrss.domain.BlueRayPremiere
import pl.mjasion.moviesrss.domain.Movie

interface BluerayPremiereRepository extends PagingAndSortingRepository<BlueRayPremiere, String> {

    BlueRayPremiere findByMovieAndStorageMedia(Movie movie, String storageMedia)

}
