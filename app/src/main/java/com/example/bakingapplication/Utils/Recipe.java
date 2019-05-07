package com.example.bakingapplication.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {

    private String name, image;
    private Long id, servings;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public Recipe(String name, String image, Long id, Long servings, List<Ingredients> ingredients, List<Steps> steps) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServings() {
        return servings;
    }

    public void setServings(Long servings) {
        this.servings = servings;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id +
                ", servings=" + servings +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
