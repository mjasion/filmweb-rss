package pl.mjasion.moviesrss.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
@CompoundIndexes([
        @CompoundIndex(name = "name_nameEng", def = "{'name': 1, 'nameEng': -1}", unique = true)
])
class Movie {
    @Id
    String id

    @Indexed
    String name

    String nameEng

    String filmwebLink

}
