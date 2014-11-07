package pl.mjasion.moviesrss.domain.repository

import org.springframework.data.repository.CrudRepository
import pl.mjasion.moviesrss.domain.Genre

interface GenreRepository extends CrudRepository<Genre, String> {
}
