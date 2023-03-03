package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu")
public class MenuEntity extends BaseEntity {

    @OneToMany
    private Set<FoodEntity> foods;

    @OneToMany
    private Set<DrinkEntity> drinks;

    public MenuEntity() {
        this.foods = new HashSet<>();
        this.drinks=new HashSet<>();
    }

    public Set<FoodEntity> getFoods() {
        return foods;
    }

    public MenuEntity setFoods(Set<FoodEntity> foods) {
        this.foods = foods;
        return this;
    }

    public Set<DrinkEntity> getDrinks() {
        return drinks;
    }

    public MenuEntity setDrinks(Set<DrinkEntity> drinks) {
        this.drinks = drinks;
        return this;
    }
}
