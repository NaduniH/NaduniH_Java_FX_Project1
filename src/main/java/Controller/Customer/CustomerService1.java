package Controller.Customer;

import dto.Customer;
import javafx.collections.ObservableList;

public interface CustomerService1 {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
    boolean deleteCustomer(String id);
    ObservableList<Customer> getAllCustomers();


}
