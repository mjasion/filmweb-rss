package pl.mjasion.filmwebrss.service

import org.jsoup.select.Elements

class DvdPremieresDto {
    List<Elements> premieresList
    String nextPageLink
    String previousPageLink
}
