package pl.mjasion.filmwebrss.domain.repository

import org.springframework.data.repository.CrudRepository
import pl.mjasion.filmwebrss.domain.Movie

interface MovieRepository extends CrudRepository<Movie, String> {

    Movie findByName(String name);

}
