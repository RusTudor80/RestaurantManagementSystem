package Business;

import java.util.Date;

public interface RestaurantProcessing {

   
    void addMenuItem(String name, String item);

    void editMenuItem(String name, String item);

    void deleteMenuItem(String name);

    String createOrder(Date date, Integer table, String items);

    Double computeOrderPrice(Integer id);

    void generateOrderBill(Integer id);
}
