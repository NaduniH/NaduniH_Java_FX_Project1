package service.custom.impl;

import Util.DaoType;
import dto.Customer;
import entity.CustomerEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {
        System.out.println("Service :"+customer);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        return customerDao.save(entity);

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        System.out.println("Update :"+customer);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        return customerDao.update(entity, customer.getId());
    }

    @Override
    public Customer searchCustomer(String id) {
        System.out.println("Search :"+id);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return (Customer) customerDao.search(id);
    }

    @Override
    public boolean deleteCustomer(String id ) {
        System.out.println("Delete :"+id);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.delete(id);
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }
}
