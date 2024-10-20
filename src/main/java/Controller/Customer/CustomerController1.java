package Controller.Customer;

import dto.Customer;
import Util.CRUDUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController1 implements CustomerService1 {


    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        String SQL = "select * from Customer";
        try {
            ResultSet resultSet= CRUDUtil.execute(SQL);
            while(resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("custID"),
                        resultSet.getString("custTitle"),
                        resultSet.getString("custName"),
                        resultSet.getDate("dob").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("custAddress"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postalCode")
                );
                customerObservableList.add(customer);
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  ObservableList<String> getCustomerIds(){
        ObservableList<Customer> allCustomers = getAllCustomers();
        ObservableList<String> idList = FXCollections.observableArrayList();

        allCustomers.forEach(customer -> {
            idList.add(customer.getId());
        });
        return idList;
    }
}
