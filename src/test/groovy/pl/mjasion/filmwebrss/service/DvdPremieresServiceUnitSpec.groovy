package pl.mjasion.filmwebrss.service

import org.joda.time.DateTime
import org.jsoup.select.Elements
import pl.mjasion.filmwebrss.domain.premiere.PremiereRepository
import spock.lang.Specification

import static pl.mjasion.filmwebrss.factory.DvdPremieresFactory.createDvdPremieres

class DvdPremieresServiceUnitSpec extends Specification {
    String filmwebUrl = 'http://filmweb.pl'
    DvdPremieresDto premieres = createDvdPremieres()
    FilmwebService filmwebService = Mock()
    PremiereRepository premiereRepository = Mock()
    DvdPremieresService dvdPremieresService = new DvdPremieresService(
            filmwebUrl: filmwebUrl,
            filmwebService: filmwebService,
            premiereRepository: premiereRepository
    )

    def "should save current dvd premieres"() {
        when:
        dvdPremieresService.saveCurrentDvdPremieres()

        then:
        1 * filmwebService.getDvdPremiersDto() >> premieres
        1 * premiereRepository.save(_)
    }

    def "should convert premieres list"() {
        given:
        List<Elements> premieres = this.premieres.premieres

        when:
        def convertedPremieres = dvdPremieresService.convertPremieres(premieres)

        then:
        convertedPremieres != null

        convertedPremieres*.name == premieres*.select('a.fNoImg0')*.attr('title')
        convertedPremieres*.storageMedia == premieres*.select('span.storageMedia').flatten()*.text()
        convertedPremieres*.link == premieres.collect {
            "${filmwebUrl}${it.select('h3.premiereFilm a').attr('href')}"
        }
    }

    def "should parse premiere shop date"() {
        given:
        Date date = new DateTime()
                .withMillisOfDay(0)
                .withYear(2014)
                .withMonthOfYear(4)
                .withDayOfMonth(21)
                .withHourOfDay(12)
                .withMinuteOfHour(30).toDate()

        when:
        def parsedDate = dvdPremieresService.parsePremiereShopdate('21/04/2014 12:30')

        then:
        parsedDate == date
    }
}
