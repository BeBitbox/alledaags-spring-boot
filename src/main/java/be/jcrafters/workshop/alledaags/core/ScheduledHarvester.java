package be.jcrafters.workshop.alledaags.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@EnableScheduling
public class ScheduledHarvester {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledHarvester.class);

    private final List<Harvester> harvesters;
    private final DailyItemRepository dailyItemRepository;

    public ScheduledHarvester(List<Harvester> harvesters, DailyItemRepository dailyItemRepository) {
        this.harvesters = harvesters;
        this.dailyItemRepository = dailyItemRepository;
    }

    @Scheduled(fixedDelay = 15 * 60 * 1000) //15 minutes
    public void harvest() {
        var dailyItemsTitles = dailyItemRepository.getDailyItems()
                .stream()
                .map(DailyItem::getTitle)
                .collect(toList());
        harvesters.stream()
                .filter(harvester -> !dailyItemsTitles.contains(harvester.getTitle()))
                .forEach(harvester -> {
                    var today = LocalDate.now();
                    LOGGER.info("harvest {} for date {}", harvester.getTitle(), today);
                    harvester.harvest(today).ifPresent(dailyItem -> {
                        LOGGER.info("harvest done {}", dailyItem);
                        dailyItemRepository.save(dailyItem, today);
                    });
                });
    }
}
