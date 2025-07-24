package com.example.DnDProject.DTOs.Search;

public class SpellSearchRequest {
    private String name;
    private String level;
    private String type;
    private String charClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlevel() {
        return level;
    }

    public void setlevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getcharClass() {
        return charClass;
    }

    public void setcharClass(String charClass) {
        this.charClass = charClass;
    }
}
