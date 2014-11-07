package pl.mjasion.moviesrss.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.moviesrss.domain.repository.BluerayPremiereRepository
import pl.mjasion.moviesrss.service.BlurayPremieresService

@RequestMapping('/premieres')
@RestController
class PremiereController {

    @Autowired BlurayPremieresService premieresService
    @Autowired BluerayPremiereRepository premiereRepository

    @RequestMapping('update/current')
    Map current() {
        premieresService.saveCurrentPremieres()
        return [totalPremieres: premiereRepository.count()]
    }
}
