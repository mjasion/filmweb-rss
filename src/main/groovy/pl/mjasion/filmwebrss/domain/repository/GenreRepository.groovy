package pl.mjasion.filmwebrss.domain.repository

import org.springframework.data.repository.CrudRepository
import pl.mjasion.filmwebrss.domain.Genre

interface GenreRepository extends CrudRepository<Genre, String> {
}
