package javafxtableviewaddrows;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FulfillmentCenter {

    private final SimpleStringProperty nameOfMagazine;
    private final SimpleStringProperty place;
    private final SimpleIntegerProperty maxMassOfMagazine;
    List<Item> listOfTheProducts = new ArrayList<>();


    FulfillmentCenter(String name, String place, Integer mass) {
        this.nameOfMagazine = new SimpleStringProperty(name);
        this.place = new SimpleStringProperty(place);
        this.maxMassOfMagazine = new SimpleIntegerProperty(mass);

    }

    public void addMagazineToItem(Item item) {
        item.magazineName = nameOfMagazine;
        item.magazineMass = maxMassOfMagazine;
        item.magazinePlace = place;
    }


    public String getNameOfMagazine() {
        return nameOfMagazine.get();
    }

    public String getPlace() {
        return place.get();
    }

    public int getMaxMassOfMagazine() {
        return maxMassOfMagazine.get();
    }

    public SimpleIntegerProperty maxMassOfMagazineProperty() {
        return maxMassOfMagazine;
    }

    public void setMaxMassOfMagazine(int maxMassOfMagazine) {
        this.maxMassOfMagazine.set(maxMassOfMagazine);
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public SimpleStringProperty nameOfMagazineProperty() {
        return nameOfMagazine;
    }

    public void setNameOfMagazine(String nameOfMagazine) {
        this.nameOfMagazine.set(nameOfMagazine);
    }

    public void removeProduct(Item item) {
        if (item == null) throw new IllegalArgumentException("item");
        if (!listOfTheProducts.contains(item)) {
            System.err.println("Nie można usunąć nieistniejącego produktu.");
            return;
        }

        int index = listOfTheProducts.indexOf(item);
        Item currentItem = (Item) listOfTheProducts.get(index);
        listOfTheProducts.remove(index);
    }

    public void summary() {
        System.out.println();
        printNameInfo();
        printPlaceInfo();
        printMaxMassInfo();

    }

    private void printNameInfo() {
        System.out.println("Nazwa magazynu: " + nameOfMagazine);
    }

    private void printPlaceInfo() {
        System.out.println("Miejscowość: " + place);
    }

    private void printMaxMassInfo() {
        System.out.println("Maksymalne obciążenie magazynu: " + maxMassOfMagazine);
    }


}
