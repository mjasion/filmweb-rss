package pl.mjasion.filmwebrss.factory

import org.jsoup.Jsoup
import org.springframework.core.io.ClassPathResource
import pl.mjasion.filmwebrss.service.DvdPremieresDto

class DvdPremieresFactory {
    static File dvdPremieresPage = new ClassPathResource('dvdPremieresPage.html').getFile()

    static DvdPremieresDto createDvdPremieres(Map = [:]) {
        return new DvdPremieresDto(
                premieresList: Jsoup.parse(dvdPremieresPage.text).select('ul.editionList li')
        )
    }
}
