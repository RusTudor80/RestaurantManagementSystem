package Business;

import Business.Menu.Produs;
import Business.Menu.CompositeProduct;
import Business.Menu.MenuItem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Restaurant implements RestaurantProcessing, Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer orderID = 0;
    public HashMap<String, MenuItem> menu;
    public HashMap<Order, ArrayList<MenuItem>> orders;

    public Restaurant() {
        menu = new HashMap<>();
        orders = new HashMap<>();
    }

    @Override
    public void addMenuItem(String name, String priceOrComposition) {
        priceOrComposition = priceOrComposition.replaceAll(" ", "");
        String[] separateItem = priceOrComposition.split(",");

        if(separateItem.length == 0) {
            System.out.println("Invalid");
        } else if(separateItem.length == 1) {
            menu.put(name, new Produs(Double.parseDouble(separateItem[0])));
        } else {
            ArrayList<MenuItem> components = new ArrayList<>();
            for(String i : separateItem) {
                if(menu.containsKey(i)) {
                    components.add(menu.get(i));
                }
            }
            menu.put(name, new CompositeProduct(components));
        }
    }

    @Override
    public void editMenuItem(String name, String item){
        item = item.replaceAll(" ", "");
        String[] separateItem = item.split(",");
        if(separateItem.length == 0) {
            System.out.println("Invalid");
        } else if(separateItem.length == 1) {
            ((Produs) menu.get(name)).setPrice(Double.parseDouble(separateItem[0]));
        } else {
            ArrayList<MenuItem> newComponents = new ArrayList<>();
            for(String i : separateItem) {
                if(menu.containsKey(i)) {
                    newComponents.add(menu.get(i));
                }
            }
            ((CompositeProduct) menu.get(name)).setComposition(newComponents);
        }
    }

    @Override
    public void deleteMenuItem(String name) {
        menu.remove(name);
    }

    @Override
    public String createOrder(Date date, Integer table, String items) {
        StringBuilder isComposite = new StringBuilder();
        items = items.replaceAll(" ", "");
        String [] separateItems = items.split(",");
        ArrayList<MenuItem> orderList = new ArrayList<>();
        for(String i : separateItems) {
            if(menu.containsKey(i)) {
                if(menu.get(i) instanceof CompositeProduct) {
                    isComposite.append(i).append(" ");
                }
                orderList.add(menu.get(i));
            }
        }
        orders.put(new Order(orderID++, date, table), orderList);

        return isComposite.toString();
    }


    @Override
    public Double computeOrderPrice(Integer id) {
        Double price = 0d;

        for(Order o : orders.keySet()) {
            if (o.getId().equals(id)) {
                for (MenuItem i : orders.get(o)) {
                    price += i.computePrice();
                }
                break;
            }
        }

        return price;
    }
    @Override
    public void generateOrderBill(Integer id) {
        try {
            PrintWriter bill = new PrintWriter("Nota de plata" + id + ".txt", "UTF-8");
            bill.println("Totalul dumneavoastra: " + computeOrderPrice(id) + ".");
            bill.println("Multumim si va mai asteptam!");
            bill.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
