package pl.mjasion.filmwebrss.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import pl.mjasion.filmwebrss.domain.BlueRayPremiere
import pl.mjasion.filmwebrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.filmwebrss.feed.PremiereFeedContent

import static org.springframework.data.domain.Sort.Direction.DESC

@Controller
@RequestMapping('/rss')
class RssFeedController {

    @Autowired
    BluerayPremiereRepository premiereRepository

    @RequestMapping('blueray/premieres')
    ModelAndView dvdPremieres() {
        List<BlueRayPremiere> premieres = premiereRepository.findAll(new PageRequest(0, 20, DESC, 'premiereShopdate')).content
        List items = premieres.collect {

            return new PremiereFeedContent(
                    title: "${it.storageMedia} - $it.movie.name",
                    url: it.movie.filmwebLink,
                    premiereShopdate: it.premiereShopdate,
                    summary: it.genres*.name.join(', ')
            )
        }

        ModelAndView modelAndView = new ModelAndView('rssFeedViewer');
        modelAndView.addObject('premieres', items)
        return modelAndView
    }
}

