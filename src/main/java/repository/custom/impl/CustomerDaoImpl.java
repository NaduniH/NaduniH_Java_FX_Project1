package repository.custom.impl;

import Util.CRUDUtil;
import dto.Customer;
import entity.CustomerEntity;
import repository.custom.CustomerDao;

import java.sql.ResultSet;
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
    public boolean update(CustomerEntity customer, String s) {

        String SQL = "UPDATE Customer SET CustName=?,CustTitle=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";
        try {
            return CRUDUtil.execute(
                    SQL,
                    customer.getName(),
                    customer.getTitle(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode(),
                    customer.getId()
            );
        } catch (SQLException e) {

        }
        return false;

    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM Customer WHERE CustID =?";

        try {
            return CRUDUtil.execute(SQL,id);
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public Customer search(String id) {
        String SQL = "SELECT * FROM Customer WHERE CustID=?";
        ResultSet resultSet = null;
        try {
            resultSet = CRUDUtil.execute(SQL,id);
            resultSet.next();
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CustomerEntity> findAll() {
        return List.of();
    }
}
