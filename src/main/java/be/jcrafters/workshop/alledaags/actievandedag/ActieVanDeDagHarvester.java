package be.jcrafters.workshop.alledaags.actievandedag;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static be.jcrafters.workshop.alledaags.core.DailyItem.DailyItemBuilder.aDailyItem;

@Component
public class ActieVanDeDagHarvester implements Harvester {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActieVanDeDagHarvester.class);
    public static final String TITLE = "Actie van de dag!";
    public static final String URL = "https://www.actievandedag.be";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var document = Jsoup.connect(URL).get();
            var text = document.select("div.deal.maindeal")
                    .stream()
                    .map(Element::text)
                    .findAny()
                    .orElseThrow();

            var imageURL = "https://www.htc-opleidingen.nl/wp/wp-content/uploads/actiebutton.png";

            return Optional.of(aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(URL)
                    .withCreditName("actievandedag")
                    .withImage(imageURL)
                    .withSpecific(text)
                    .build());
        } catch (Exception exception) {
            LOGGER.error("Error retrieving dailyItem on actievandedag.be for {}", localDate, exception);
            return Optional.empty();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
