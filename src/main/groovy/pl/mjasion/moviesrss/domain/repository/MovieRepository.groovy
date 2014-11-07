package pl.mjasion.moviesrss.domain.repository

import org.springframework.data.repository.CrudRepository
import pl.mjasion.moviesrss.domain.Movie

interface MovieRepository extends CrudRepository<Movie, String> {

    Movie findByName(String name);

}
