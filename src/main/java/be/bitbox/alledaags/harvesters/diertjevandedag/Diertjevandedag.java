package be.bitbox.alledaags.harvesters.diertjevandedag;

import be.bitbox.alledaags.core.DailyItem;
import be.bitbox.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Diertjevandedag implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(Diertjevandedag.class);

    public static final String URL = "https://diertjevandedag.be/";
    public static final String TITLE = "Dier";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var document = Jsoup.connect(URL).get();
            var text = document.select("#content a")
                    .stream()
                    .filter(element -> element.hasText() && element.attr("href").contains("diertjevandedag.be"))
                    .map(Element::text)
                    .findAny()
                    .orElseThrow();

            var imageURL = document.select("#content a img")
                    .stream()
                    .map(element -> element.attr("src"))
                    .filter(src -> src.contains("diertjevandedag.be") && !src.contains("facebook"))
                    .findAny()
                    .orElseThrow();

            return Optional.of(DailyItem.DailyItemBuilder.aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(URL)
                    .withCreditName("Diertje van de Dag")
                    .withImage(imageURL)
                    .withSpecific(text)
                    .build());
        } catch (Exception exception) {
            LOGGER.error("Error retrieving dailyItem on {} for {}", TITLE, localDate, exception);
            return Optional.empty();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
