package pl.mjasion.filmwebrss.domain.repository

import org.springframework.data.repository.CrudRepository
import pl.mjasion.filmwebrss.domain.Premiere

interface PremiereRepository extends CrudRepository<Premiere, String> {
}