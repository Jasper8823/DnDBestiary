package com.example.DnDProject.DTOs.Calculator;

public class CharacterDTO {
    private Integer id;
    private Integer count;
    private Integer level;
    private String className;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
