package javafxtableviewaddrows;

import java.util.ArrayList;
import java.util.List;

public class DataManager {


    public List<FulfillmentCenter> createMagazines() {
        Item item1 = new Item("książka", 23.50, 12, 3);
        Item item2 = new Item("plecak", 120.30, 30, 5);
        Item item3 = new Item("skakanka", 15.40, 2, 31);
        Item item4 = new Item("okno", 200.34, 45, 5);
        Item item5 = new Item("drzwi", 300.3, 300, 3);
        Item item6 = new Item("książka", 23.50, 12, 3);
        Item item7 = new Item("lustro", 100.30, 3, 3);
        Item item8 = new Item("zegar", 50.30, 2, 6);
        Item item9 = new Item("kubek", 5.5, 1, 7);

        // first magazine
        FulfillmentCenter magazine1 = new FulfillmentCenter("Magazynowo", "Kraków", 230);
        magazine1.listOfTheProducts.add(item1);
        magazine1.addMagazineToItem(item1);
        magazine1.listOfTheProducts.add(item2);
        magazine1.addMagazineToItem(item2);

        // second magazine
        FulfillmentCenter magazine2 = new FulfillmentCenter("Jenny", "Warszawa", 340);
        magazine2.listOfTheProducts.add(item3);
        magazine2.addMagazineToItem(item3);
        magazine2.listOfTheProducts.add(item4);
        magazine2.addMagazineToItem(item4);
        magazine2.listOfTheProducts.add(item5);
        magazine2.addMagazineToItem(item5);
        magazine2.listOfTheProducts.add(item6);
        magazine2.addMagazineToItem(item6);


        // third magazine
        FulfillmentCenter magazine3 = new FulfillmentCenter("Mały Magazyn", "Lublin", 500);
        magazine3.listOfTheProducts.add(item7);
        magazine3.addMagazineToItem(item7);
        magazine3.listOfTheProducts.add(item8);
        magazine3.addMagazineToItem(item8);

        // fourth magazine
        FulfillmentCenter magazine4 = new FulfillmentCenter("Książkowo", "Poznań", 700);
        magazine4.listOfTheProducts.add(item9);
        magazine4.addMagazineToItem(item9);

        List<FulfillmentCenter> listOfMagazines = new ArrayList<>();
        listOfMagazines.add(magazine1);
        listOfMagazines.add(magazine2);
        listOfMagazines.add(magazine3);
        listOfMagazines.add(magazine4);

        return listOfMagazines;

    }


}
