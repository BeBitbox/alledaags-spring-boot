package be.bitbox.alledaags.harvesters.onroerenderfgoed;

import be.bitbox.alledaags.core.DailyItem;
import be.bitbox.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Component
public class OnroerendErfgoed implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnroerendErfgoed.class);

    public static final String OVERVIEW_URL = "https://inventaris.onroerenderfgoed.be/erfgoedobjecten?pagina=%d&sort=id";
    public static final String BASE_URL = "https://inventaris.onroerenderfgoed.be/";
    public static final String TITLE = "Onroerend erfgoed";
    private final Random random;
    private final RestTemplate restTemplate;

    public OnroerendErfgoed() {
        this.restTemplate = new RestTemplate();
        this.random = new Random();
    }

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var url = getUrl();
            var document = Jsoup.connect(url).get();
            var childIndex = random.nextInt(10) + 2; // first one is header / second one is pagination
            LOGGER.info("looking for {} on URL {}, item {}", TITLE, url, childIndex);
            var article = document.select(".result-container").get(0).child(childIndex);
            var articleTitle = article.select("h3").text();
            var text = article.select(".img-container p").html().split("<br>")[1];
            var specific = "<b>" + articleTitle + "</b><br>" + text;

            var creditURL = BASE_URL + article.select("a").attr("href");

            return Optional.of(DailyItem.DailyItemBuilder.aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(creditURL)
                    .withCreditName("Inventaris onroerend vastgoed")
                    .withImage(BeeldBank.getImageSrc(restTemplate, article.html()))
                    .withSpecific(specific)
                    .build());
        } catch (Exception exception) {
            LOGGER.error("Eror retrieving dailyItem on {} for {}", TITLE, localDate, exception);
            return Optional.empty();
        }
    }

    private String getUrl() {
        var page = random.nextInt(9285) + 1; //92845 results when code written
        return String.format(OVERVIEW_URL, page);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
