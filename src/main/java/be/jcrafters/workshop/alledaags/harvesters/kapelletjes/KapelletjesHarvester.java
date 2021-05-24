package be.jcrafters.workshop.alledaags.harvesters.kapelletjes;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.Harvester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static be.jcrafters.workshop.alledaags.core.DailyItem.DailyItemBuilder.aDailyItem;
import static java.util.stream.Collectors.toList;

@Component
public class KapelletjesHarvester implements Harvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(KapelletjesHarvester.class);
    private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d+");
    private static final Random random = new Random();

    public static final String TITLE = "Kappeletje";
    public static final String BASE_URL = "http://kapelletjesinvlaanderen.be/";
    public static final String URL = "http://kapelletjesinvlaanderen.be/html/oost-vlaanderen.html";

    @Override
    public Optional<DailyItem> harvest(LocalDate localDate) {
        try {
            var document = Jsoup.connect(URL).get();
            var fotoPageURLS = document.select("td")
                    .stream()
                    .filter(element -> element.childrenSize() == 1)
                    .map(element -> element.child(0))               // <p>
                    .filter(element -> element.childrenSize() == 1)
                    .map(element -> element.child(0))               // <span>
                    .filter(element -> element.childrenSize() == 1)
                    .map(element -> element.child(0))               // <a>
                    .filter(a -> INTEGER_PATTERN.matcher(a.text()).matches())
                    .map(element -> element.attr("href"))
                    .collect(toList());
            var selectedFotoPage = BASE_URL + fotoPageURLS.get(random.nextInt(fotoPageURLS.size()));

            var imageURLS = Jsoup.connect(selectedFotoPage).get()
                    .select("img[height=\"112\"]")
                    .stream()
                    .map(element -> element.attr("src"))
                    .collect(toList());
            var imageURL = BASE_URL + imageURLS.get(random.nextInt(imageURLS.size()));

            return Optional.of(aDailyItem()
                    .withTitle(TITLE)
                    .withCredit(URL)
                    .withCreditName("Kapelletjes in Vlaanderen")
                    .withImage(imageURL)
                    .withSpecific("Oost-Vlaanderen")
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
