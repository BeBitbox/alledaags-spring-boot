package be.bitbox.alledaags.core;

import java.time.LocalDate;
import java.util.List;

public interface DailyItemRepository {

    List<DailyItem> getDailyItems();

    void save(DailyItem dailyItem, LocalDate localDate);
}
