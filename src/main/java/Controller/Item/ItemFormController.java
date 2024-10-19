package Controller.Item;

import Util.ServiceType;
import dto.Item;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.ItemService;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblItemForm;

    @FXML
    private TableView tblItems;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    ItemService1 ItemController = new ItemController1();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );

        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        if (itemService.addItem(item)) {
            new Alert(Alert.AlertType.INFORMATION, "Item Added...").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item Not Added...").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(ItemController.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted!!").show();
            loadTable();
            //clearText();
        } else {
            new Alert(Alert.AlertType.ERROR,"Item Not Deleted!!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Item item = ItemController.searchItem(txtItemCode.getText());
        setValueToText(item);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        if(ItemController.updateItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated!!").show();
            loadTable();
            //clearText();
        } else{
            new Alert(Alert.AlertType.ERROR,"Item Not Updated!!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadTable();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, Item, itemValues) ->{
                    if(itemValues!=null){
                        setValueToText((Item) itemValues);
                    }
                }
        );

    }
    private void loadTable() {
        ObservableList<Item> items = ItemController.getAllItem();
        tblItems.setItems(items);
    }

    private void setValueToText(Item item) {
        txtItemCode.setText(item.getItemCode());
        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtUnitPrice.setText(item.getUnitPrice().toString());
        txtQty.setText(item.getQty().toString());
    }
}
