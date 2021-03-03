package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Ospatar {

    DefaultTableModel inputTableModel;
    JTable inputTable;
    JButton button;
    JComboBox<String> selectMethod;

    public Ospatar() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setTitle("Ospatar");
        frame.setLayout(new GridLayout(3, 1));
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocation(0,360);
        frame.getContentPane().setBackground(Color.black);

        inputTableModel = new DefaultTableModel();
        inputTableModel.setRowCount(1);
        inputTableModel.setColumnCount(3);
        inputTableModel.setColumnIdentifiers(new String[]{"data", "numarul mesei", "produse"});
        inputTable = new JTable();
        inputTable.setModel(inputTableModel);

        selectMethod = new JComboBox<String>(new String[]{"Comanda", "Calculeaza Pretul", "Nota de plata"});


        button = new JButton("OK");


        frame.add(selectMethod);
        frame.add(new JScrollPane(inputTable));
        frame.add(button);
        frame.setVisible(true);
   

    }
}
