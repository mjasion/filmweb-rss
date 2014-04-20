package pl.mjasion.filmwebrss.service

import org.jsoup.select.Elements

class DvdPremieresDto {
    List<Elements> premieres
    String nextPageLink
    String previousPageLink
}
