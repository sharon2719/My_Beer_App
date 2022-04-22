package com.example.mybeerapp.model;

import com.google.gson.annotations.SerializedName;

public class Beer {
    public Beer beer;

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName ("tagline")
    private String tagline;

    @SerializedName ("description")
    private String description;

    @SerializedName ("first_brewed")
    private String firstBrewed;

  public Beer (int id,String name,String tagline,String description,String firstBrewed){
        this.id =id;
        this.name = name;
        this.tagline = tagline;
        this.description = description;
        this.firstBrewed = firstBrewed;
    }
    public Beer() {
        this.id =id;
        this.name = name;
        this.tagline = tagline;
        this.description = description;
        this.firstBrewed = firstBrewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }
}
