package uk.ac.tees.aad.W9513634;

public class Exercises {

    String name;

    String Calories;

    String image;

    public Exercises(String name, String calories, String image) {
        this.name = name;
        this.Calories = calories;
        this.image = image;
    }

    public Exercises() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        this.Calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "name='" + name + '\'' +
                ", calories='" + Calories + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
