/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zooregistergui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author USER
 */
public class SearchSpeciesRecord implements ActionListener{
     
    JFrame frame1;//creating object of first JFrame
    JLabel nameLabel;//creating object of JLabel
    JTextField nameTextField;//creating object of JTextfield
    JButton searchButton;//creating object of JButton
    
    JFrame frame2;//creating object of second JFrame
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    Connection connection;//Creating object of Connection class
    Statement statement;//Creating object of Statement class
    int flag = 0;
 
 
    SearchSpeciesRecord() {
 
        frame1 = new JFrame();
        frame1.setTitle("Search Species");//setting the title of first JFrame
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//setting default close operation
        GridBagLayout bagLayout = new GridBagLayout();//creating object of GridBagLayout
        GridBagConstraints bagConstraints = new GridBagConstraints();//creating object of GridBagConstratints
        frame1.setSize(500, 300);//setting the size of first JFrame
        frame1.setLayout(bagLayout);//setting the layout to GridBagLayout of first JFrame
 
        bagConstraints.insets = new Insets(15, 40, 0, 0);//Setting the padding between the components and neighboring components
 
      //Setting the property of JLabel and adding it to the first JFrame
        nameLabel = new JLabel("Species Name");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame1.add(nameLabel, bagConstraints);
 
      //Setting the property of JTextfield and adding it to the first JFrame
        nameTextField = new JTextField(15);
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        frame1.add(nameTextField, bagConstraints);
 
      //Setting the property of JButton(Click Search) and adding it to the first JFrame
        searchButton = new JButton("Click Search");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.ipadx = 60;
        frame1.add(searchButton, bagConstraints);
 
 
        //adding ActionListener to button
        searchButton.addActionListener(this);
        
 
 
        frame1.setVisible(true);//Setting the visibility of First JFrame
        frame1.validate();//Performing relayout of the First JFrame
 
 
    }
 
    public static void main(String[] args) {
        new SearchSpeciesRecord();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == searchButton) {
 
            String animalName = nameTextField.getText();//getting text of animal name text field and storing it in a String variable
            frameSecond(animalName);//passing the text value to frameSecond method
 
        }
 
    }
 
 
    public void frameSecond(String speciesName) {
    
     //setting the properties of second JFrame
        frame2 = new JFrame("Species Results Table");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(400, 400);
 
        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("Species ID");
        defaultTableModel.addColumn("Species Name");

        try {
            
            String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String sourceURL = " jdbc:sqlserver://localhost:1433;databaseName=ZooRegister";
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ZooRegister");//Creating connection with database
       
            statement = connection.createStatement();//creating statement object
            String query = "SELECT * FROM Species WHERE speciesName=?";//Storing SQL query in A string variable
            ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet
 
            while (resultSet.next()) {
            
             //Retrieving details from the database and storing it in the String variables
                int spID = resultSet.getInt("speciesID");
                String name = resultSet.getString("speciesName");
                
                if (speciesName.equalsIgnoreCase(name)) {
                    flag = 1;
                    
                    defaultTableModel.addRow(new Object[]{spID, name});//Adding row in Table
                    frame2.setVisible(true);//Setting the visibility of second Frame
                    frame2.validate();
                    break;
                }
 
            }
 
            if (flag == 0) {
                JOptionPane.showMessageDialog(null, "No Such Species Found");//When invalid species is entered
            }
 
 
        } catch (SQLException throwables) {
        }
 
 
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    

