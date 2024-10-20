package repository.custom.impl;

import Util.CRUDUtil;
import dto.Customer;
import dto.Item;
import entity.ItemEntity;
import repository.custom.ItemDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity item) {
        System.out.println("Item Repository "+item);

        String SQl = "INSERT INTO Item VALUES(?,?,?,?,?)";
        try {
            return CRUDUtil.execute(
                    SQl,
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQty()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(ItemEntity item, String s) {
        String SQL = "UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?";
        try {
            return CRUDUtil.execute(
                    SQL,
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQty(),
                    item.getItemCode());
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public boolean delete(String itemCode) {
        String SQL = "DELETE FROM Item WHERE ItemCode=?";
        try {
            return CRUDUtil.execute(SQL,itemCode);
        } catch (SQLException e) {

        }
        return false;
    }


    @Override
    public Object search(String itemCode) {
        String SQL = "SELECT * FROM Item WHERE ItemCode=?";
        ResultSet resultSet = null;
        try {
            resultSet = CRUDUtil.execute(SQL, itemCode);
            resultSet.next();
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            );
        } catch (SQLException e) {

        }
        return null;

    }

//    @Override
//    public Item itemSearch(Object itemCode) {
//        String SQL = "SELECT * FROM Item WHERE ItemCode=?";
//        ResultSet resultSet = null;
//        try {
//            resultSet = CRUDUtil.execute(SQL, itemCode);
//            resultSet.next();
//            return new Item(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4),
//                    resultSet.getInt(5)
//            );
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }

    @Override
    public List<ItemEntity> findAll() {
        return List.of();
    }
}
