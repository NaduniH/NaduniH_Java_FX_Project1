package Controller.Order;

import Controller.Customer.CustomerController1;
import Controller.Item.ItemController1;
import DB.DBConnection;
import dto.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CartTm> tblOrders;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtItemDescription;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    ObservableList<CartTm> cart = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        Double unitPrice =  Double.parseDouble(txtUnitPrice.getText());
        Integer qty = Integer.parseInt(txtQTY.getText());

        Double total = unitPrice * qty;

        cart.add(
                new CartTm(
                cmbItemCode.getValue(),
                txtItemDescription.getText(),
                qty,
                unitPrice,
                total
        ));

        tblOrders.setItems(cart);
        calNetTotal();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderID = txtCustomerID.getText();
        String customerId = cmbCustomerId.getValue();
        LocalDate now = LocalDate.now();
        ArrayList<OrderDetail>orderDetails = new ArrayList<>();

        for (CartTm cartTm : cart){
            String itemCode = cartTm.getItemCode();
            Integer qty = cartTm.getQty();
            orderDetails.add(new OrderDetail(orderID,itemCode,qty,0.0));
        }

        try {
            if(new OrderController().placeOrder(new Order(orderID,now,customerId,orderDetails))) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed...").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Order Not Placed...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText("Date : " + f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(
                    "Time : " + now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((ObservableValue,s,newValue) -> {
            loadItemData(newValue);
        });

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((ObservableValue,s,newValue) -> {
            loadCustomerData(newValue);
        });

        loadDateAndTime();
        loadCustomerIds();
        loadItemCodes();

    }

    private void loadCustomerIds(){
        cmbCustomerId.setItems(new CustomerController1().getCustomerIds());
    }

    private void loadItemCodes(){
        cmbItemCode.setItems(new ItemController1().getItemCodes());
    }

    public  void loadItemData(String itemCode){
        Item item = new ItemController1().searchItem(itemCode);

        txtItemDescription.setText(item.getDescription());
        txtStock.setText(item.getQty().toString());
        txtUnitPrice.setText(item.getUnitPrice().toString());
    }

    public  void loadCustomerData(String customerID){
        Customer customer = new CustomerController1().searchCustomer(customerID);

        txtName.setText(customer.getName());
        txtCity.setText(customer.getCity());
        txtSalary.setText(customer.getSalary().toString());
    }

    private void calNetTotal(){
        Double total = 0.0;

        for (CartTm cartTm : cart){
            total += cartTm.getTotal();

        }
        lblNetTotal.setText(total.toString());

    }
    public void btnCommitOnAction(ActionEvent actionEvent) throws SQLException {
        DBConnection.getInstance().getConnection().commit();
    }

}
