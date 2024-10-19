package service.custom;

import dto.Item;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ItemService extends SuperService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    Item searchItem(String itemCode);
    boolean deleteItem(String itemCode);
    ObservableList<Item> getAllItem();
}
