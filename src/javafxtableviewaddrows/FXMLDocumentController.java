/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtableviewaddrows;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane paneLoadLabel;
    @FXML
    private Pane paneLoadText;
    @FXML
    private Button showAllProducts;
    @FXML
    private TextField filterField;
    @FXML
    private TableView productsTableView;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, String> price;
    @FXML
    private TableColumn<Item, String> quantity;
    @FXML
    private TableColumn<Item, String> magazine;
    @FXML
    private TableView<FulfillmentCenter> magazinesTableView;
    @FXML
    private TableColumn<FulfillmentCenter, String> magazineName;
    @FXML
    private TableColumn<FulfillmentCenter, String> magazinePlace;
    @FXML
    private TableColumn<FulfillmentCenter, String> magazineMass;


    //observable lists to store data
    private final ObservableList<Item> productsList = FXCollections.observableArrayList();
    private final ObservableList<FulfillmentCenter> magazinesList = FXCollections.observableArrayList();

    // list of magazines data
    List<FulfillmentCenter> listOfMagazines = new ArrayList<>(new DataManager().createMagazines());

    Integer newQuantity;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //setting columns in magazines table
        magazineName.setCellValueFactory(new PropertyValueFactory<>("nameOfMagazine"));
        magazinePlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        magazineMass.setCellValueFactory(new PropertyValueFactory<>("maxMassOfMagazine"));


        // setting columns in items table
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        magazine.setCellValueFactory(new PropertyValueFactory<>("magazineName"));

        //creating tables (products, magazines)
        createTables(listOfMagazines);

        // creating a button in magazines table to show products of selected magazine
        addButtonToMagazinesTable();

        // creating a button in products table to buy a product
        addButtonToProductTableToBuyProduct();

        // creating a tooltip in products table to show data from selected row
        addTooltipToColumnCellsInProductsTable(name);

        // creating a button in magazines table to show all products
        addButtonToShowAllProducts();

    }

    // button to show all products
    public void addButtonToShowAllProducts() {

        showAllProducts.setOnAction(event -> {
            productsList.clear();
            for (int i = 0; i < listOfMagazines.size(); i++)
                productsList.addAll(listOfMagazines.get(i).listOfTheProducts);
            filterProductsTable();
            productsTableView.setItems(productsList);
            filterProductsTable();
        });


    }

    // creating magazines table and products table
    public void createTables(List<FulfillmentCenter> list) {
        // magazines table
        magazinesList.addAll(list);
        magazinesTableView.setItems(magazinesList);

        // products table
        for (int i = 0; i < list.size(); i++)
            productsList.addAll(list.get(i).listOfTheProducts);
        filterProductsTable();
    }

    // adding a tooltip to column cells in products table
    private void addTooltipToColumnCellsInProductsTable(TableColumn<Item, String> column) {
        productsTableView.setRowFactory(tv -> new TableRow<Item>() {
            private Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setTooltip(null);
                } else {

                    tooltip.setText("Magazyn: " + item.getMagazineName() + "\n" + "Miejscowość: " + item.getMagazinePlace() + "\n" + "Masa magazynu: " + item.getMagazineMass());
                    setTooltip(tooltip);
                }
            }
        });

    }

    // filtering in products table
    private void filterProductsTable() {
        // Wrapping the ObservableList in a FilteredList
        FilteredList<Item> filteredData = new FilteredList<>(productsList, b -> true);

        // Setting the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, displaying all products

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparing name of every product with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches product's name
                } else
                    return false; // Does not match.
            });
        });

        // Wrapping the FilteredList in a SortedList.
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        //  Binding the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(productsTableView.comparatorProperty());

        // Adding sorted (and filtered) data to the table.
        productsTableView.setItems(sortedData);

    }


    // adding button to show products of selected magazine from magazine table
    private void addButtonToMagazinesTable() {
        TableColumn<FulfillmentCenter, String> colBtn = new TableColumn("Produkty");

        Callback<TableColumn<FulfillmentCenter, String>, TableCell<FulfillmentCenter, String>> cellFactory = new Callback<TableColumn<FulfillmentCenter, String>, TableCell<FulfillmentCenter, String>>() {
            @Override
            public TableCell<FulfillmentCenter, String> call(final TableColumn<FulfillmentCenter, String> param) {
                final TableCell<FulfillmentCenter, String> cell = new TableCell<FulfillmentCenter, String>() {

                    private final Button btn = new Button("Pokaż");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                FulfillmentCenter center = getTableView().getItems().get(getIndex());
                                productsList.clear();
                                productsList.addAll(center.listOfTheProducts);
                                productsTableView.setItems(productsList);
                                filterProductsTable();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        magazinesTableView.getColumns().add(colBtn);

    }

    // adding a button to buy product
    private void addButtonToProductTableToBuyProduct() {
        TableColumn<Item, String> colBtn = new TableColumn("Zakup produktu");

        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory = new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
            @Override
            public TableCell<Item, String> call(final TableColumn<Item, String> param) {
                final TableCell<Item, String> cell = new TableCell<Item, String>() {

                    private final Button btn = new Button("KUP");


                    @Override
                    public void updateItem(String item, boolean empty) {


                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Item product = getTableView().getItems().get(getIndex());
                                Label nameLabel = new Label("Podaj ilość produktu, który chcesz kupić: ");
                                TextField nameField = new TextField();
                                nameField.setPrefWidth(50);

                                Button moveBut = new Button("OK");
                                HBox hbox = new HBox(5);
                                hbox.getChildren().addAll(nameField, moveBut);
                                paneLoadLabel.getChildren().add(nameLabel);
                                paneLoadText.getChildren().add(hbox);


                                moveBut.setOnAction(event2 -> {

                                    int i = Integer.parseInt(nameField.getText());
                                    int newQuantity = product.q - i;
                                    if (i <= product.q) {

                                        product.quantity = new SimpleIntegerProperty(newQuantity);
                                        product.q = product.q - i;
                                        productsTableView.refresh();
                                        if (product.q - i == 0) {
                                            productsList.remove(product);
                                        }

                                        new Alert(Alert.AlertType.INFORMATION, "Produkt został zakupiony!").showAndWait();
                                        paneLoadLabel.getChildren().remove(nameLabel);
                                        paneLoadText.getChildren().remove(hbox);
                                        productsTableView.refresh();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Nie ma takiej ilości produktu w magazynie!").showAndWait();
                                        paneLoadLabel.getChildren().remove(nameLabel);
                                        paneLoadText.getChildren().remove(hbox);

                                    }

                                });

                                nameField.setOnKeyReleased(ev -> {
                                    Item productTwo = getTableView().getItems().get(getIndex());
                                    Integer j = Integer.parseInt(nameField.getText());
                                    newQuantity = productTwo.q - j;
                                    if (ev.getCode() == KeyCode.ENTER) {
                                        if (j <= productTwo.getQuantity()) {
                                            productTwo.quantity = new SimpleIntegerProperty(newQuantity);
                                            productTwo.q = newQuantity;
                                            productsTableView.refresh();
                                            if (productTwo.q - j == 0) {
                                                productsList.remove(productTwo);
                                                productsTableView.refresh();
                                            }

                                            new Alert(Alert.AlertType.INFORMATION, "Produkt został zakupiony!").showAndWait();
                                            paneLoadLabel.getChildren().remove(nameLabel);
                                            paneLoadText.getChildren().remove(hbox);
                                        } else {
                                            new Alert(Alert.AlertType.ERROR, "Nie ma takiej ilości produktu w magazynie!").showAndWait();
                                            paneLoadLabel.getChildren().remove(nameLabel);
                                            paneLoadText.getChildren().remove(hbox);
                                        }


                                    }
                                });


                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        productsTableView.getColumns().add(colBtn);

    }


}






    

