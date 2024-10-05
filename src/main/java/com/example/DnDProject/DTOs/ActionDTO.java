package com.example.DnDProject.DTOs;

public class ActionDTO {
    private String name;
    private String info;
    private String legend;


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", legend='" + legend + '\'' +
                '}';
    }
}

