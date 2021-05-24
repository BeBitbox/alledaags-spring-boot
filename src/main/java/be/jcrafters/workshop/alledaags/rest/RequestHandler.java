package be.jcrafters.workshop.alledaags.rest;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.DailyItemRepository;
import be.jcrafters.workshop.alledaags.harvesters.actievandedag.ActieVanDeDagHarvester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandler.class);

    private final DailyItemRepository dailyItemRepository;

    public RequestHandler(DailyItemRepository dailyItemRepository) {
        this.dailyItemRepository = dailyItemRepository;
    }

    @GetMapping("/api/daily-items")
    public List<DailyItem> dailyItems() {
        LOGGER.info("[REQUEST-HANDLER] dailyItems");
        return dailyItemRepository.getDailyItems();
    }
}
