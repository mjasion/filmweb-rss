package pl.mjasion.filmwebrss.factory

import org.jsoup.Jsoup
import org.springframework.core.io.ClassPathResource
import pl.mjasion.filmwebrss.service.PremieresDto

class PremieresFactory {
    static File premieresPage = new ClassPathResource('premieresPage.html').getFile()

    static PremieresDto createPremieres(Map = [:]) {
        return new PremieresDto(
                premieres: Jsoup.parse(premieresPage.text).select('ul.editionList li')
        )
    }
}
