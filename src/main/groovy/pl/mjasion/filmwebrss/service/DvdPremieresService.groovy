package pl.mjasion.filmwebrss.service

import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DvdPremieresService {
    @Autowired FilmwebService filmwebService

    @Value('${filmweb.url}')
    String filmwebUrl


    List<Map> saveCurrentDvdPremieres() {
        DvdPremieresDto premieres = filmwebService.getDvdPremiersList()
        return convertPremieresList(premieres.premieresList)
    }

    List convertPremieresList(List<Elements> premieresList) {
        return premieresList.collect { Element e ->
            [
                    title: e.select('a.fNoImg0').attr('title'),
                    link: "${filmwebUrl}${e.select('a.fNoImg0').attr('href')}"

            ]

        }
    }
}
