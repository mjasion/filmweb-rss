package pl.mjasion.moviesrss.factory

import groovy.transform.CompileStatic
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.core.io.ClassPathResource
import pl.mjasion.moviesrss.service.dto.BlurayPremieresDto

@CompileStatic
class PremieresFactory {
    static File bluerayPremieresPage = new ClassPathResource('bluerayPremieresPage.html').getFile()
    static File cinemaPremieresPage = new ClassPathResource('cinemaPremieresPage.html').getFile()

    static BlurayPremieresDto createBlueRayPremieres() {
        return new BlurayPremieresDto(
                premieres: Jsoup.parse(bluerayPremieresPage.text).select('ul.editionList li')
        )
    }

    static BlurayPremieresDto createCinemaPremieres() {
        Document document = Jsoup.parse(cinemaPremieresPage.text)
        List<Element> currentMonthPremieresDates = document.select('ul.dateSelect li.on')
        String monthOfPremiere = getMonthOfPremiereDateString(currentMonthPremieresDates.first())
        List<String> daysOfPremieresInMonth = getDaysOfPremieresInMonth(currentMonthPremieresDates.last())
        List<Element> premieresPageBoxes = document.select('div#premieresList div[id^="day-"]')
        return null
    }

    static String getMonthOfPremiereDateString(Element element) {
        element.select('a').attr('href').replace('/premiere/', '')
    }

    static List<String> getDaysOfPremieresInMonth(Element element) {
        return element.select('a')*.attr('href')*.replace('#d-', 'day-a')
    }

    public static void main(String[] args) {
        createCinemaPremieres()
    }
}
