package pl.mjasion.moviesrss.domain

import groovy.transform.CompileStatic
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
@CompileStatic
class BlueRayPremiere {
    @Id
    String id

    @DBRef
    Movie movie

    @Indexed
    Date premiereShopdate

    String storageMedia

    @DBRef
    List<Genre> genres

    Date dateAdded
}
