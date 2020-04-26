package be.jcrafters.workshop.alledaags.rest;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.DailyItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestHandler {

    private final DailyItemRepository dailyItemRepository;

    public RequestHandler(DailyItemRepository dailyItemRepository) {
        this.dailyItemRepository = dailyItemRepository;
    }

    @GetMapping("/api/daily-items")
    public List<DailyItem> dailyItems() {
        return dailyItemRepository.getDailyItems();
    }
}
