package pl.mjasion.filmwebrss.service

import spock.lang.Specification

import static pl.mjasion.filmwebrss.factory.DvdPremieresFactory.createDvdPremieres

class DvdPremieresServiceTest extends Specification {
    String filmwebUrl = 'http://filmweb.pl'
    DvdPremieresDto premieres = createDvdPremieres()
    FilmwebService filmwebService = Mock()
    DvdPremieresService dvdPremieresService = new DvdPremieresService(
            filmwebUrl: filmwebUrl,
            filmwebService: filmwebService
    )

    def "should save current dvd premieres"() {
        when:
        dvdPremieresService.saveCurrentDvdPremieres()

        then:
        1 * filmwebService.getDvdPremiersList() >> premieres
    }

    def "should convert premieres list"() {
        when:
        def convertedPremieresList = dvdPremieresService.convertPremieresList(premieres.premieresList)

        then:
        convertedPremieresList != null
    }
}
