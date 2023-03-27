package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import static bg.softuni.fooddelivery.constants.Messages.*;

@Service
public class PromotionService {

    private final ProductRepository productRepository;
    @Autowired
    public PromotionService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void makePromotions(DayOfWeek dayOfWeek) {

        ProductEntity margherita = this.productRepository.findByName(MARGHERITA);
        ProductEntity chickenBurger = this.productRepository.findByName(CHICKEN_BURGER);
        ProductEntity mrCheesy = this.productRepository.findByName(MR_CHEESY);
        ProductEntity satoshi = this.productRepository.findByName(SATOSHI);
        ProductEntity blackAngus = this.productRepository.findByName(BLACK_ANGUS);
        ProductEntity ranchFries = this.productRepository.findByName(RANCH_FRIES);
        ProductEntity carbonara = this.productRepository.findByName(CARBONARA);
        ProductEntity brownie = this.productRepository.findByName(BROWNIE);
        ProductEntity mousse = this.productRepository.findByName(CHOCOLATE_MOUSSE);
        ProductEntity doughnuts = this.productRepository.findByName(DOUGHNUTS);

        switch (dayOfWeek) {
            case MONDAY -> {
                monday(margherita, mousse, doughnuts);
            }
            case TUESDAY -> {
                tuesday(margherita, chickenBurger);
            }
            case WEDNESDAY -> {
                wednesday(chickenBurger, mrCheesy);
            }
            case THURSDAY -> {
                thursday(mrCheesy, satoshi);
            }
            case FRIDAY -> {
                friday(satoshi, blackAngus, ranchFries);
            }
            case SATURDAY -> {
                saturday(blackAngus, ranchFries, carbonara, brownie);
            }
            case SUNDAY -> {
                sunday(carbonara, brownie, mousse, doughnuts);
            }
        }

    }

    private void sunday(ProductEntity carbonara,
                        ProductEntity brownie,
                        ProductEntity mousse,
                        ProductEntity doughnuts) {

        carbonara.setPrice(BigDecimal.valueOf(9.49));
        this.productRepository.save(carbonara);

        brownie.setPrice(BigDecimal.valueOf(4.59));
        this.productRepository.save(brownie);

        mousse.setPrice(BigDecimal.valueOf(2.99));
        this.productRepository.save(mousse);

        doughnuts.setPrice(BigDecimal.valueOf(1.49));
        this.productRepository.save(doughnuts);
    }

    private void saturday(ProductEntity blackAngus,
                          ProductEntity ranchFries,
                          ProductEntity carbonara,
                          ProductEntity brownie) {

        blackAngus.setPrice(BigDecimal.valueOf(10.99));
        this.productRepository.save(blackAngus);

        ranchFries.setPrice(BigDecimal.valueOf(5.99));
        this.productRepository.save(ranchFries);

        carbonara.setPrice(BigDecimal.valueOf(7.99));
        this.productRepository.save(carbonara);

        brownie.setPrice(BigDecimal.valueOf(2.99));
        this.productRepository.save(brownie);
    }

    private void friday(ProductEntity satoshi,
                        ProductEntity blackAngus,
                        ProductEntity ranchFries) {

        satoshi.setPrice(BigDecimal.valueOf(12.99));
        this.productRepository.save(satoshi);

        blackAngus.setPrice(BigDecimal.valueOf(8.99));
        this.productRepository.save(blackAngus);

        ranchFries.setPrice(BigDecimal.valueOf(3.99));
        this.productRepository.save(ranchFries);
    }

    private void thursday(ProductEntity mrCheesy,
                          ProductEntity satoshi) {

        mrCheesy.setPrice(BigDecimal.valueOf(12.99));
        this.productRepository.save(mrCheesy);

        satoshi.setPrice(BigDecimal.valueOf(12.99));
        this.productRepository.save(satoshi);
    }

    private void wednesday(ProductEntity chickenBurger,
                           ProductEntity mrCheesy) {

        chickenBurger.setPrice(BigDecimal.valueOf(10.99));
        this.productRepository.save(chickenBurger);

        mrCheesy.setPrice(BigDecimal.valueOf(10.99));
        this.productRepository.save(mrCheesy);
    }

    private void tuesday(ProductEntity margherita,
                         ProductEntity chickenBurger) {

        margherita.setPrice(BigDecimal.valueOf(7.99));
        this.productRepository.save(margherita);

        chickenBurger.setPrice(BigDecimal.valueOf(9.49));
        this.productRepository.save(chickenBurger);
    }

    private void monday(ProductEntity margherita,
                        ProductEntity mousse,
                        ProductEntity doughnuts) {

        mousse.setPrice(BigDecimal.valueOf(3.99));
        this.productRepository.save(mousse);

        doughnuts.setPrice(BigDecimal.valueOf(1.79));
        this.productRepository.save(doughnuts);

        margherita.setPrice(BigDecimal.valueOf(6.99));
        this.productRepository.save(margherita);
    }


}
