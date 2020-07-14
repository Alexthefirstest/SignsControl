package by.epam.orders;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.factory.ServiceFactory;

import java.util.Arrays;

public class ClassForTestingLALALA {

    public static void main(String[] args) throws ServiceException {
       for (MapPoint$Orders mo : ServiceFactory.getINSTANCE().getOrdersControlService().getExecutedOrdersMapPoint()){
           System.out.println(mo);
        }
    }

}
