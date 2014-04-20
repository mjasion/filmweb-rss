package pl.mjasion.filmwebrss.service

import com.google.common.annotations.VisibleForTesting
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FilmwebService {

    @Value('${filmweb.url.dvd.premieres}')
    String dvdPremieresUrl

    @Value('${filmweb.url}')
    String filmwebUrl

    @Value('${user.agent}')
    private String userAgent

    DvdPremieresDto getDvdPremiersList() {
        Document page = getPage(dvdPremieresUrl)
        return new DvdPremieresDto(
                premieresList : page.select('ul.editionList li'),
                previousPageLink: "${filmwebUrl}${page.select('div.sortCont a.prev').attr('href')}",
                nextPageLink: "${filmwebUrl}${page.select('div.sortCont a.next').attr('href')}",
        )
    }

    @VisibleForTesting
    private Document getPage(String url) {
        return Jsoup.connect(url).userAgent(userAgent).get()
    }


}
