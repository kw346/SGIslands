package sg.edu.rp.c346.id20022735.sgislands;

import java.io.Serializable;

public class Island implements Serializable {
    private int id;
    private String name;
    private String description;
    private int distance;
    private int stars;

    public Island(int id, String name, String description, int distance, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.stars = stars;
    }

    public Island(String name, String description, int distance, int stars) {
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.stars = stars;
    }

    public int getId() {
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
