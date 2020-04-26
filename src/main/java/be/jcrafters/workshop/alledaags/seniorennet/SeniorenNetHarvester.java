package be.jcrafters.workshop.alledaags.seniorennet;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static be.jcrafters.workshop.alledaags.core.DailyItem.DailyItemBuilder.aDailyItem;

//@Component
public class SeniorenNetHarvester implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeniorenNetHarvester.class);
    public static final String TITLE = "Mopje";
    public static final String URL = "https://www.seniorennet.be/Pages/Moppen/mop.php";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var document = Jsoup.connect(URL).get();
            var text = document.select("div.weetje-msg")
                    .stream()
                    .map(Element::text)
                    .findAny()
                    .orElseThrow();

            var imageURL = "https://upload.wikimedia.org/wikipedia/commons/1/18/Utrecht_Moreelse_Democrite.JPG";

            return Optional.of(aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(URL)
                    .withCreditName("SeniorenNet")
                    .withImage(imageURL)
                    .withSpecific(text)
                    .build());
        } catch (Exception exception) {
            LOGGER.error("Eror retrieving dailyItem on SeniorenNet for {}", localDate, exception);
            return Optional.empty();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
