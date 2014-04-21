package pl.mjasion.filmwebrss.service

import org.jsoup.select.Elements

class PremieresDto {
    List<Elements> premieres
    String nextPageLink
    String previousPageLink
}
