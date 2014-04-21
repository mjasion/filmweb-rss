package pl.mjasion.filmwebrss.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.filmwebrss.domain.repository.PremiereRepository
import pl.mjasion.filmwebrss.service.PremieresService

@RequestMapping('/premieres')
@RestController
class PremiereController {

    @Autowired PremieresService premieresService
    @Autowired PremiereRepository premiereRepository

    @RequestMapping('update/current')
    Map current() {
        premieresService.saveCurrentPremieres()
        return [totalPremieres: premiereRepository.count()]
    }
}
