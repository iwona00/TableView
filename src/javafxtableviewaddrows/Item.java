package javafxtableviewaddrows;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Item implements Comparable<Item> {

    public final SimpleStringProperty name;
    public final SimpleDoubleProperty price;
    public final SimpleIntegerProperty mass;
    public SimpleIntegerProperty quantity;
    public SimpleStringProperty magazineName;
    public SimpleIntegerProperty magazineMass;
    public SimpleStringProperty magazinePlace;
    public int q;


    Item(String name, Double price, Integer mass, Integer quantity) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.mass = new SimpleIntegerProperty(mass);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.magazineName = new SimpleStringProperty("");
        this.q = quantity;
    }

    public String getMagazinePlace() {
        return magazinePlace.get();
    }

    public SimpleStringProperty magazinePlaceProperty() {
        return magazinePlace;
    }

    public void setMagazinePlace(String magazinePlace) {
        this.magazinePlace.set(magazinePlace);
    }

    public int getMagazineMass() {
        return magazineMass.get();
    }

    public SimpleIntegerProperty magazineMassProperty() {
        return magazineMass;
    }

    public void setMagazineMass(int magazineMass) {
        this.magazineMass.set(magazineMass);
    }

    public String getMagazineName() {
        return magazineName.get();
    }

    public SimpleStringProperty magazineNameProperty() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName.set(magazineName);
    }

    public void getMagazine(String magazine) {

    }


    public int getMass() {
        return mass.get();
    }

    public SimpleIntegerProperty massProperty() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass.set(mass);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public void summary() {
        System.out.println();
        printNameInfo();
        printPriceInfo();
        printMassInfo();
        printQuantityInfo();
    }


    private void printNameInfo() {
        System.out.println("Nazwa towaru: " + name);
    }

    private void printPriceInfo() {
        System.out.println("Cena towaru: " + price);
    }

    private void printMassInfo() {
        System.out.println("Masa towaru: " + mass);
    }

    private void printQuantityInfo() {
        System.out.println("Ilość towaru: " + quantity);
    }


    @Override
    public int compareTo(Item item) {
        return 0;
    }
}
