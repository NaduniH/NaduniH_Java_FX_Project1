package repository;

import Util.DaoType;
import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.ItemDaoImpl;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory(){}

    public <T extends SuperDao>T getDaoType(DaoType daoType){
        switch (daoType){
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();

        }
        return null;
    }

    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance = new DaoFactory());
    }
}
