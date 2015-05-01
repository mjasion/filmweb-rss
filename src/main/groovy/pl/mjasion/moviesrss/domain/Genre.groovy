package pl.mjasion.moviesrss.domain

import groovy.transform.CompileStatic
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@CompileStatic
class Genre {
    @Id
    String name

    String link
}
