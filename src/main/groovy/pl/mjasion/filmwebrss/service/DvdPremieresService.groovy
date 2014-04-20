package pl.mjasion.filmwebrss.service

import com.google.common.annotations.VisibleForTesting
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.mjasion.filmwebrss.domain.premiere.Premiere
import pl.mjasion.filmwebrss.domain.premiere.PremiereRepository

@Service
class DvdPremieresService {
    @Autowired FilmwebService filmwebService
    @Autowired PremiereRepository premiereRepository

    @Value('${filmweb.url}')
    private String filmwebUrl


    List<Premiere> saveCurrentDvdPremieres() {
        DvdPremieresDto premieresDto = filmwebService.getDvdPremiersDto()
        List premieres = convertPremieres(premieresDto.premieres)
        premiereRepository.save(premieres)
        return premieres
    }

    List convertPremieres(List<Elements> premieres) {
        return premieres.collect { Element e ->
            new Premiere(
                    name: e.select('a.fNoImg0').attr('title'),
                    link: "${filmwebUrl}${e.select('h3.premiereFilm a').attr('href')}",
                    premiereShopdate: parsePremiereShopdate(e.select('span.premiereShopdate').attr('title')),
                    storageMedia: e.select('span.storageMedia').text(),
                    dateAdded: new Date(),
            )
        }
    }

    @VisibleForTesting
    private Date parsePremiereShopdate(String premiereShopDate) {
        return new Date().parse('dd/MM/yyyy HH:mm', premiereShopDate)
    }
}
