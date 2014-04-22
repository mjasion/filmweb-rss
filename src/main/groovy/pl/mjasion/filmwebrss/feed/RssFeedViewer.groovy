package pl.mjasion.filmwebrss.feed

import com.sun.syndication.feed.rss.Channel
import com.sun.syndication.feed.rss.Content
import com.sun.syndication.feed.rss.Item
import org.springframework.stereotype.Component
import org.springframework.web.servlet.view.feed.AbstractRssFeedView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RssFeedViewer extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.title = 'Premiery DVD/Blu-ray - mjasion.pl'
        feed.link = 'http://filmweb-rss.mjasion.pl'
        feed.description = 'Premiery DVD/Blu-ray - mjasion.pl'
        super.buildFeedMetadata(model, feed, request)
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<PremiereFeedContent> premieres = model.get("premieres")
        List<Item> items = premieres.collect { PremiereFeedContent premiere ->
            new Item(
                    title: premiere.title,
                    link: premiere.url,
                    pubDate: premiere.premiereShopdate,
                    content: new Content(value: premiere.summary)
            )
        }
        return items
    }

}
