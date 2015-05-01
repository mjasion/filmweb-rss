package pl.mjasion.moviesrss.service.dto

import groovy.transform.CompileStatic
import org.jsoup.nodes.Element

@CompileStatic
class BlurayPremieresDto {
    List<Element> premieres
    String nextPageLink
    String previousPageLink
}
