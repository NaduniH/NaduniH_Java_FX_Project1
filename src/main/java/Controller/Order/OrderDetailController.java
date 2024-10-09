package Controller.Order;

import Model.OrderDetail;
import Util.CRUDUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public static boolean addOrderDetail(List<OrderDetail> orderDetails) throws SQLException {
        for (OrderDetail orderDetail : orderDetails){
            boolean isOrderDetailAdd = addOrderDetail(orderDetail);
            if (!isOrderDetailAdd){
                return false;
            }
        }
        return true;
    }

    public static boolean addOrderDetail(OrderDetail orderDetail) throws SQLException {
        return CRUDUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?)",
                orderDetail.getOrderID(),
                orderDetail.getItemCode(),
                orderDetail.getQty(),
                orderDetail.getDiscount()
        );
    }


}
