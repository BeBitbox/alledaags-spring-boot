package be.jcrafters.workshop.alledaags.wikipedia;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class WikiHarvesterTest {

    @Test
    void testURL_Creation() {
        assertThat(WikiHarvester.wikiURLFor(LocalDate.of(2010, Month.JANUARY, 5)))
                .isEqualTo("https://nl.wikipedia.org/wiki/Sjabloon:Hoofdpagina_-_wist_je_dat_5_januari");
        assertThat(WikiHarvester.wikiURLFor(LocalDate.of(2020, Month.AUGUST, 25)))
                .isEqualTo("https://nl.wikipedia.org/wiki/Sjabloon:Hoofdpagina_-_wist_je_dat_25_augustus");
    }
}