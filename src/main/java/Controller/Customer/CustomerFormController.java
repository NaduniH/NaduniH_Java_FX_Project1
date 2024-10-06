package Controller.Customer;

import Model.Customer;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private JFXComboBox <String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCusID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    CustomerService CustomerController = new CustomerController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String>customerTitleList = FXCollections.observableArrayList();
        customerTitleList.add("Mr");
        customerTitleList.add("Mrs");
        customerTitleList.add("Miss");
        customerTitleList.add("Ms");
        cmbTitle.setItems(customerTitleList);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

        loadTable();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, newValue) ->{
            if(newValue!=null){
                setValueToText(newValue);
            }
        });

    }

    private void setValueToText(Customer newValue) {
        txtCusID.setText(newValue.getId());
        txtName.setText(newValue.getName());
        cmbTitle.setValue(newValue.getTitle());
        txtProvince.setText(newValue.getProvince());
        txtPostalCode.setText(newValue.getPostalCode());
        txtSalary.setText(newValue.getSalary().toString());
        dateDOB.setValue(newValue.getDob());
        txtAddress.setText(newValue.getAddress());
        txtCity.setText(newValue.getCity());

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Customer customer = new Customer(
                txtCusID.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                dateDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        if(CustomerController.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfully").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(CustomerController.deleteCustomer(txtCusID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully!!").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR,"Not Deleted!!").show();
        }

    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setValueToText(CustomerController.searchCustomer(txtCusID.getText()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCusID.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                dateDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()

        );
        if(CustomerController.updateCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successfully!!").show();
            loadTable();

        } else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Updated!!").show();
        }
    }

    private void loadTable(){
        ObservableList<Customer> customers = CustomerController.getAllCustomers();
        tblCustomers.setItems(customers);
    }
}
