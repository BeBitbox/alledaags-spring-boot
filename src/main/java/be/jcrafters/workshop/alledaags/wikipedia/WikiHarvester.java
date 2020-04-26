package be.jcrafters.workshop.alledaags.wikipedia;

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

@Component
public class WikiHarvester implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiHarvester.class);
    public static final String TITLE = "Weetje";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var url = wikiURLFor(LocalDate.now());
            var document = Jsoup.connect(url).get();
            var text = document.select("ul li")
                    .stream()
                    .filter(element -> element.text().startsWith("...") && element.html().contains("<b>"))
                    .map(Element::text)
                    .findAny()
                    .orElseThrow();

            var imageURL = document.select("a[href^=/wiki/Bestand]")
                    .stream()
                    .map(element -> element.attr("href"))
                    .findAny()
                    .orElseThrow();
            String href = "https:" + Jsoup.connect("https://nl.wikipedia.org" + imageURL).get()
                    .select("a[href^=//upload.wikimedia.org]")
                    .attr("href");

            return Optional.of(aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(url)
                    .withCreditName("Wikipedia")
                    .withImage(href)
                    .withSpecific(text)
                    .build());
        } catch (Exception exception) {
            LOGGER.error("Eror retrieving dailyItem on Wikipedia for {}", localDate, exception);
            return Optional.empty();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    static String wikiURLFor(LocalDate localDate) {
        return String.format("https://nl.wikipedia.org/wiki/Sjabloon:Hoofdpagina_-_wist_je_dat_%d_%s",
                localDate.getDayOfMonth(),
                translateMonth(localDate.getMonth()));
    }

    private static String translateMonth(Month month) {
        switch (month) {
            case JANUARY:
                return "januari";
            case FEBRUARY:
                return "februari";
            case MARCH:
                return "march";
            case APRIL:
                return "april";
            case MAY:
                return "mei";
            case JUNE:
                return "juni";
            case JULY:
                return "juli";
            case AUGUST:
                return "augustus";
            case SEPTEMBER:
                return "september";
            case OCTOBER:
                return "oktober";
            case NOVEMBER:
                return "november";
            case DECEMBER:
                return "december";
            default:
                throw new IllegalArgumentException("unknown month: " + month);
        }
    }
}
