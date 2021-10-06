package be.bitbox.alledaags.core;

import java.time.LocalDate;
import java.util.Optional;

public interface Harvester {

    Optional<DailyItem> harvest(LocalDate localDate);

    String getTitle();
}
