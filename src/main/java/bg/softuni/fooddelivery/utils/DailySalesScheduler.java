package bg.softuni.fooddelivery.utils;

import bg.softuni.fooddelivery.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class DailySalesScheduler {
    private final PromotionService promotionService;
    private final Logger LOGGER = LoggerFactory.getLogger(DailySalesScheduler.class);

    public DailySalesScheduler(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void changePromotions() {

        final DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

        this.promotionService.makePromotions(dayOfWeek);

        LOGGER.info("Promotions changed.");
    }

}
