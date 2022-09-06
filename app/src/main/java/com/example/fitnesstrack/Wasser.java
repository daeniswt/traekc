package com.example.fitnesstrack;

public class Wasser {

    public int id;
    private int valueeing;

    public Wasser(int id, int valueeing) {
    this.id=id;
    this.valueeing=valueeing;
    }

    public Wasser() {

    }

    public int getValueeing() {
        return valueeing;
    }

    @Override
    public String toString() {
        return "Wasser{" +
                "valueeing=" + valueeing +
                '}';
    }
}
