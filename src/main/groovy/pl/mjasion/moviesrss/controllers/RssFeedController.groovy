package pl.mjasion.moviesrss.controllers

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import pl.mjasion.moviesrss.domain.BlueRayPremiere
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.feed.PremiereFeedContent

import static org.springframework.data.domain.Sort.Direction.DESC

@Controller
@RequestMapping('/rss')
@CompileStatic
class RssFeedController {

    @Autowired BluerayPremiereRepository premiereRepository

    @RequestMapping('blueray/premieres')
    ModelAndView dvdPremieres() {
        List<BlueRayPremiere> premieres = premiereRepository.findAll(new PageRequest(0, 50, DESC, 'premiereShopdate')).content
        List items = premieres.collect {
            return new PremiereFeedContent(
                    title: it.movie.name,
                    url: it.movie.filmwebLink,
                    premiereShopdate: it.premiereShopdate,
                    summary: it.genres*.name.join(', ')
            )
        }
        ModelAndView modelAndView = new ModelAndView()
        modelAndView.setViewName('blurayRssFeedViewer')
        modelAndView.addObject('premieres', items)
        return modelAndView
    }
}

