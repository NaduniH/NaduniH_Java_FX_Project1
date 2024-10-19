package Controller.Item;

import dto.Item;
import javafx.collections.ObservableList;

public interface ItemService1 {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    Item searchItem(String itemCode);
    boolean deleteItem(String itemCode);
    ObservableList<Item> getAllItem();
}
