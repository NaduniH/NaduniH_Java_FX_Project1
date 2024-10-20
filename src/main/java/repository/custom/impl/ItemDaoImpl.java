package repository.custom.impl;

import Util.CRUDUtil;
import dto.Customer;
import entity.ItemEntity;
import repository.custom.ItemDao;

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
    public boolean update(ItemEntity entity, String s) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public List<ItemEntity> findAll() {
        return List.of();
    }
}
