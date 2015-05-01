package pl.mjasion.moviesrss.factory

import groovy.transform.CompileStatic
import org.jsoup.Jsoup
import org.springframework.core.io.ClassPathResource
import pl.mjasion.moviesrss.service.dto.BlurayPremieresDto

@CompileStatic
class PremieresFactory {
    static File premieresPage = new ClassPathResource('premieresPage.html').getFile()

    static BlurayPremieresDto createPremieres(Map = [:]) {
        return new BlurayPremieresDto(
                premieres: Jsoup.parse(premieresPage.text).select('ul.editionList li')
        )
    }
}
