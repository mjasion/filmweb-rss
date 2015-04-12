package pl.mjasion.moviesrss.service.dto

import groovy.transform.CompileStatic
import org.jsoup.select.Elements

@CompileStatic
class BlurayPremieresDto {
    List<Elements> premieres
    String nextPageLink
    String previousPageLink
}
