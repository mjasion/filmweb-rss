package pl.mjasion.filmwebrss.factory

import org.jsoup.Jsoup
import org.springframework.core.io.ClassPathResource
import pl.mjasion.filmwebrss.service.dto.BlurayPremieresDto

class PremieresFactory {
    static File premieresPage = new ClassPathResource('premieresPage.html').getFile()

    static BlurayPremieresDto createPremieres(Map = [:]) {
        return new BlurayPremieresDto(
                premieres: Jsoup.parse(premieresPage.text).select('ul.editionList li')
        )
    }
}
