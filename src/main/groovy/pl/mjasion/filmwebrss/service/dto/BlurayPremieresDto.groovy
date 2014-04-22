package pl.mjasion.filmwebrss.service.dto

import org.jsoup.select.Elements

class BlurayPremieresDto {
    List<Elements> premieres
    String nextPageLink
    String previousPageLink
}
