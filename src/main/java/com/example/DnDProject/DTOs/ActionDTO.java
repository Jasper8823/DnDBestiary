package com.example.DnDProject.DTOs;

public class ActionDTO {
    private String name;
    private String info;
    private boolean legend;


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

    public boolean getLegend() {
        return legend;
    }

    public void setLegend(boolean legend) {
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

