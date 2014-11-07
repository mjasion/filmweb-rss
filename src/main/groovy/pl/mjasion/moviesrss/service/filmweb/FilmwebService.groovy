package pl.mjasion.moviesrss.service.filmweb

import com.google.common.annotations.VisibleForTesting
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.mjasion.moviesrss.service.dto.BlurayPremieresDto

@Service
class FilmwebService {

    @Value('${filmweb.url.premieres.bluray}')
    private String blurayPremieresUrl

    @Value('${filmweb.url}')
    private String filmwebUrl

    @Value('${user.agent}')
    private String userAgent

    BlurayPremieresDto getPremiersDto() {
        Document page = getPage(blurayPremieresUrl)
        return new BlurayPremieresDto(
                premieres: page.select('ul.editionList li'),
                previousPageLink: "${filmwebUrl}${page.select('div.sortCont a.prev').attr('href')}",
                nextPageLink: "${filmwebUrl}${page.select('div.sortCont a.next').attr('href')}",
        )
    }

    @VisibleForTesting
    private Document getPage(String url) {
        return Jsoup.connect(url).userAgent(userAgent).get()
    }


}
