package be.bitbox.alledaags.harvesters.meteo;

import be.bitbox.alledaags.core.DailyItem;
import be.bitbox.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Meteo implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(Meteo.class);

    public static final String URL = "https://www.meteo.be/nl/gent";
    public static final String TITLE = "Het weer";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var document = Jsoup.connect(URL).get();
            var weer = document.select("[daytype='D1']")
                    .get(0);

            var imageURL = String.format("https://www.meteo.be/frontend/img/icons/weather/small/%s.svg",
                    weer.attr("typefrom"));

            var text = String.format("Min %s° - Max %s°<br>Neerslagkans: %s procent<br>Wind: %s Beaufort",
                    weer.attr("mintemp"),
                    weer.attr("maxtemp"),
                    weer.attr("humiditypct"),
                    weer.attr("windspeedbft"));

            return Optional.of(DailyItem.DailyItemBuilder.aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(URL)
                    .withCreditName("Koninklijk Meteorologisch Instituut")
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
