package repository.custom.impl;

import Util.CRUDUtil;
import entity.CustomerEntity;
import repository.custom.CustomerDao;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customer) {
        System.out.println("Repository : "+customer);

        String SQL =  "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            return CRUDUtil.execute(
                    SQL,
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            );
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public boolean update(CustomerEntity entity, String s) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return List.of();
    }
}
