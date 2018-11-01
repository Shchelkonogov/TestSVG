package ru.tn.testSVG.model;

public class MnemonicData {

    private String name, color, data;

    public MnemonicData(String name, String color, String data) {
        this.name = name;
        this.color = color;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
