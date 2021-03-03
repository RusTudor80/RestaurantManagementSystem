package Presentation;


import Business.Restaurant;

import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Observer {



    public static void main(String[] args) {

        Restaurant restaurantAux = new Restaurant();
        try {
            FileInputStream file = new FileInputStream("restaurant");
            ObjectInputStream in = new ObjectInputStream(file);
            restaurantAux = (Restaurant)in.readObject(); 
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        Restaurant restaurant = restaurantAux;
        Client a = new Client();
        Ospatar b = new Ospatar();
        Bucatar c = new Bucatar();

        //ospatar

        b.selectMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    for (int i = 0; i < b.inputTable.getRowCount(); i++) {
                        for (int j = 0; j < b.inputTable.getColumnCount(); j++) {
                            b.inputTable.setValueAt("", i, j);
                        }
                    }

                    Vector<String> parameters = new Vector<>();
                    switch(Objects.requireNonNull(b.selectMethod.getSelectedItem()).toString()) {
                        case "Comanda": {
                            try {
                                for (Parameter p : Restaurant.class.getDeclaredMethod(b.selectMethod.getSelectedItem().toString(), Date.class, Integer.class, String.class).getParameters()) {
                                    parameters.add(p.getName());
                                }
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                                return;
                            }
                            break;
                        }
                        case "Calculeaza Pretul":
                        case "Nota de plata": {
                            try {
                                for (Parameter p : Restaurant.class.getDeclaredMethod(b.selectMethod.getSelectedItem().toString(), Integer.class).getParameters()) {
                                    parameters.add(p.getName());
                                }
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                                return;
                            }
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    b.inputTableModel.setRowCount(1);
                    b.inputTableModel.setColumnCount(parameters.size());
                    b.inputTableModel.setColumnIdentifiers(parameters);
                    b.inputTable.setModel(b.inputTableModel);
                }
            }
        });

        b.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(Objects.requireNonNull(b.selectMethod.getSelectedItem()).toString()) {
                    case "Comanda": {
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String compositeItems = restaurant.createOrder(dateFormat.parse(b.inputTable.getValueAt(0, 0).toString()), Integer.parseInt(b.inputTable.getValueAt(0, 1).toString()), b.inputTable.getValueAt(0, 2).toString());
                            if(!compositeItems.isEmpty()) {
                                c.popUp(compositeItems);
                            }
                            System.out.println("Comanda a fost adaugata cu success!");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    case "Calculeaza Pretul": {
                        System.out.println(restaurant.computeOrderPrice(Integer.parseInt(b.inputTable.getValueAt(0, 0).toString())));
                        break;
                    }
                    case "Nota de plata": {
                        restaurant.generateOrderBill(Integer.parseInt(b.inputTable.getValueAt(0, 0).toString()));
                        break;
                    }
                }
            }
        });


        //bucatar

        c.frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                FileOutputStream file = null;
                try {
                    file = new FileOutputStream("restaurant");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(restaurant);
                    out.close();
                    file.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        
        
        //client


        a.selectMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    for (int i = 0; i < a.inputTable.getRowCount(); i++) {
                        for (int j = 0; j < a.inputTable.getColumnCount(); j++) {
                            a.inputTable.setValueAt("", i, j);
                        }
                    }

                    Vector<String> parameters = new Vector<>();
                    switch(Objects.requireNonNull(a.selectMethod.getSelectedItem()).toString()) {
                        case "adauga produs":
                        case "editeaza produsul": {
                            try {
                                for (Parameter p : Restaurant.class.getDeclaredMethod(a.selectMethod.getSelectedItem().toString(), String.class, String.class).getParameters()) {
                                    parameters.add(p.getName());
                                }
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                                return;
                            }
                            break;
                        }
                        case "sterge produsul": {
                            try {
                                for (Parameter p : Restaurant.class.getDeclaredMethod(a.selectMethod.getSelectedItem().toString(), String.class).getParameters()) {
                                    parameters.add(p.getName());
                                }
                            } catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                                return;
                            }
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    a.inputTableModel.setRowCount(1);
                    a.inputTableModel.setColumnCount(parameters.size());
                    a.inputTableModel.setColumnIdentifiers(parameters);
                    a.inputTable.setModel(a.inputTableModel);
                }
            }
        });

        a.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(Objects.requireNonNull(a.selectMethod.getSelectedItem()).toString()) {
                    case "adauga produs": {
                        restaurant.addMenuItem(a.inputTable.getValueAt(0, 0).toString(), a.inputTable.getValueAt(0, 1).toString());
                        System.out.println(restaurant.menu.get(a.inputTable.getValueAt(0, 0).toString()));
                        break;
                    }
                    case "editeaza produsul": {
                        System.out.print("Produsul" + a.inputTable.getValueAt(0, 0).toString() + " a fost schimbat " + restaurant.menu.get(a.inputTable.getValueAt(0, 0).toString()));
                        restaurant.editMenuItem(a.inputTable.getValueAt(0, 0).toString(), a.inputTable.getValueAt(0, 1).toString());
                        System.out.println(" in  " + restaurant.menu.get(a.inputTable.getValueAt(0, 0).toString()) + " ");
                        break;
                    }
                    case "sterge produsul": {
                        System.out.print("Meniul a fost  " + restaurant.menu.size());
                        restaurant.deleteMenuItem(a.inputTable.getValueAt(0, 0).toString());
                        System.out.println(" iar acum are " + restaurant.menu.size() + ".");
                        break;
                    }
                }
            }
        });
    }
}
