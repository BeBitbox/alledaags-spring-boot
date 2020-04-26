package be.jcrafters.workshop.alledaags.core;

import java.time.LocalDate;
import java.util.Optional;

public interface Harvester {

    Optional<DailyItem> harvest(LocalDate localDate);

    String getTitle();
}
