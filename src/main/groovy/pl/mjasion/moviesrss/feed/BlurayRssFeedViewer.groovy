package pl.mjasion.moviesrss.feed

import com.sun.syndication.feed.rss.Channel
import com.sun.syndication.feed.rss.Content
import com.sun.syndication.feed.rss.Item
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.view.feed.AbstractRssFeedView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BlurayRssFeedViewer extends AbstractRssFeedView {

    @Value('${filmweb.url.premieres.bluray}')
    private String blurayPremieresUrl

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.title = 'Premiery Blu-ray - mjasion.pl'
        feed.link = blurayPremieresUrl
        feed.description = 'Premiery Blu-ray - mjasion.pl'
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
