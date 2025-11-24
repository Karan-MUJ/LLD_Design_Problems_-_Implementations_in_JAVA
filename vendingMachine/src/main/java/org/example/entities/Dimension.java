package org.example.entities;

public class Dimension {
    private final Double length, width, height;
    public Dimension(Double length, Double width, Double height) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public Double getLength() { return length; }
    public Double getWidth() { return width; }
    public Double getHeight() { return height; }

    Boolean fitsIn(Dimension other) {
        return this.length <= other.length &&
               this.width  <= other.width  &&
               this.height <= other.height;
    }
}
