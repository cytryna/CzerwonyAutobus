package com.diligentia.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private String url;
    @Enumerated(EnumType.STRING)
    private Meal mealCategory = Meal.DINNER;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Meal getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(Meal mealCategory) {
        this.mealCategory = mealCategory;
    }

    @Override
    public String toString() {
        return name;
    }
}