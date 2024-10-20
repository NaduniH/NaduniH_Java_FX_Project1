package Controller.Item;

import dto.Item;
import dto.OrderDetail;
import Util.CRUDUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController1 implements ItemService1 {


    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public Item searchItem(String itemCode) {
        return null;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return false;
    }

    @Override
    public ObservableList<Item> getAllItem() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM Item";

        try {
            ResultSet resultSet = CRUDUtil.execute(SQL);

            while (resultSet.next()) {
                itemObservableList.add(new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }


    public  ObservableList<String> getItemCodes(){
        ObservableList<Item> allItems = getAllItem();
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        allItems.forEach(item -> {
            itemCodeList.add(item.getItemCode());
        });
        return itemCodeList;
    }

    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails){
            try {
                if (!updateStock(orderDetail)){
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;

    }

    public boolean updateStock(OrderDetail orderDetails) throws SQLException {
        return CRUDUtil.execute("UPDATE Item set QtyOnHand=QtyOnHand-? WHERE ItemCode=?",
                orderDetails.getQty(),
                orderDetails.getItemCode()
        );
    }
}
