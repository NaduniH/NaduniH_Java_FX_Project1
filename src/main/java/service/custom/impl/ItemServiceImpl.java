package service.custom.impl;

import Util.DaoType;
import dto.Item;
import entity.CustomerEntity;
import entity.ItemEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import repository.custom.ItemDao;
import service.custom.ItemService;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        System.out.println("Item Service :"+item);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.save(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public boolean updateItem(Item item) {
        System.out.println("Update :"+item);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);
        return itemDao.update(entity, item.getItemCode());
    }

    @Override
    public Item searchItem(String itemCode) {
        System.out.println("Search :"+itemCode);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return (Item) itemDao.search(itemCode);
    }

    @Override
    public boolean deleteItem(String itemCode) {
        System.out.println("Delete :"+itemCode);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.delete(itemCode);
    }

    @Override
    public ObservableList<Item> getAllItem() {
        return null;
    }
}
