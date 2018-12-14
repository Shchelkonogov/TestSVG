package ru.tn.testSVG.model;

/**
 * Модель данных для отображения на мнемосхеме
 */
public class MnemonicData {

    private String name, color, data, title;

    /**
     * Конструктор
     * @param name имя элемента
     * @param color цвет элемента
     * @param data значение элемента
     * @param title всплывающая подсказка элемента
     */
    public MnemonicData(String name, String color, String data, String title) {
        this.name = name;
        this.color = color;
        this.data = data;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
