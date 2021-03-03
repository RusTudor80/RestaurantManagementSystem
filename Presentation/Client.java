package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Client {
    DefaultTableModel inputTableModel;
    JTable inputTable;
    JButton button;
    JComboBox<String> selectMethod;

    public Client() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setTitle("Client");
        frame.setLayout(new GridLayout(3, 1));
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocation(0, 0);
        frame.getContentPane().setBackground(Color.black);

        inputTableModel = new DefaultTableModel();
        inputTableModel.setRowCount(1);
        inputTableModel.setColumnCount(3);
        inputTableModel.setColumnIdentifiers(new String[]{"produs", "pretul sau compozitia"});
        inputTable = new JTable();
        inputTable.setModel(inputTableModel);

        selectMethod = new JComboBox<String>(new String[]{"adauga produs", "editeaza produsul", "sterge produsul"});


        button = new JButton("OK");


        frame.add(selectMethod);
        frame.add(new JScrollPane(inputTable));
        frame.add(button);
        frame.setVisible(true);

    }
}
