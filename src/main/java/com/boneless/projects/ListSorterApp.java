package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ListSorterApp extends JFrame {
    private ArrayList<String> items;
    private JList<String> itemsList;
    private DefaultListModel<String> listModel;

    public ListSorterApp() {
        setTitle("List Sorter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize the ArrayList and populate it with some items
        items = new ArrayList<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Orange");
        items.add("Grape");
        items.add("Pear");

        // Create the list model and JList
        listModel = new DefaultListModel<>();
        updateListModel();
        itemsList = new JList<>(listModel);

        // Add the JList to the content pane
        JScrollPane scrollPane = new JScrollPane(itemsList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a sort button
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> sortList());
        getContentPane().add(sortButton, BorderLayout.SOUTH);
    }

    private void updateListModel() {
        listModel.clear();
        for (String item : items) {
            listModel.addElement(item);
        }
    }

    /**
     * TODO: Implement a method to sort the items ArrayList in alphabetical order
     * using an appropriate sorting algorithm and the compareTo() method.
     */
    private void sortList() {
        for(int i = 0;i < items.size() - 1;i++){
            for(int k = 0;k < items.size() - i - 1;k++){
                String obj1 = items.get(k);
                String obj2 = items.get(k + 1);

                if(obj1.compareToIgnoreCase(obj2) > 0){
                    items.set(k, obj2);
                    items.set(k + 1, obj1);
                }
            }
        }
        updateListModel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListSorterApp app = new ListSorterApp();
            app.setVisible(true);
        });
    }
}