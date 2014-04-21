package pl.mjasion.filmwebrss.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Genre {
    @Id
    String name

    String link
}
