package pl.mjasion.filmwebrss.domain.premiere

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Premiere {
    @Id
    String name

    @Indexed
    Date premiereShopdate

    String link

    String storageMedia

    Date dateAdded

}
