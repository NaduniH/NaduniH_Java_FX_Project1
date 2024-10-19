package service;

import Util.ServiceType;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;

public class ServiceFactory {

    private static ServiceFactory factory;

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return factory != null? factory: (factory = new ServiceFactory());

    }

    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:return (T) new CustomerServiceImpl();
            case ITEM:return (T) new ItemServiceImpl();
        }
        return null;
    }
}
