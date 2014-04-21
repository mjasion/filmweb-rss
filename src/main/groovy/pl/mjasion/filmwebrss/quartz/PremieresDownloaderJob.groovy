package pl.mjasion.filmwebrss.quartz

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import pl.mjasion.filmwebrss.service.PremieresService

@Slf4j
@Component
class PremieresDownloaderJob {
    @Autowired PremieresService premieresService

    @Scheduled(cron = '${filmweb.cron.premieres}')
    void execute() {
        log.info('Saving premieres - START')
        premieresService.saveCurrentPremieres()
        log.info('Saving premieres - FINISH')
    }
}
