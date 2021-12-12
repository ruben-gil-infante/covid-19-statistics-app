package com.ruben.covid_19_statistics_app.uicomponents.ListWithFinder.model;

public class ListWithFinderItem {

    private String text;
    private int originalPosition;

    public ListWithFinderItem() {}

    public ListWithFinderItem(String text, int originalPosition) {
        this.text = text;
        this.originalPosition = originalPosition;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOriginalPosition() {
        return this.originalPosition;
    }

    public void setOriginalPosition(int originalPosition) {
        this.originalPosition = originalPosition;
    }

    public static ListWithFinderItem Build(String text, int originalPosition) {
        ListWithFinderItem item = new ListWithFinderItem();
        item.setText(text);
        item.setOriginalPosition(originalPosition);
        return item;
    }

    @Override
    public String toString() {
        return "ListWithFinderItem{" +
                "text='" + text + '\'' +
                ", originalPosition=" + originalPosition +
                '}';
    }
}
