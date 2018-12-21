package Menu;

import Contacts.*;
import ToDo.*;
import Appointments.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu extends JFrame {
   
   private Contact_Management contacts;
   private Appointments_Management appoints;
   private ToDo_Management todo;
   // private TODO
   
   private JButton [] menuBtn = new JButton [3];
   private Font font;
   
   public Menu () {
      setTitle("Menu");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500,500);
      Container a = getContentPane();
      GridLayout grid = new GridLayout(3,1,20,20);
      a.setLayout(grid);
      a.setBackground(Color.WHITE);
      
      font = new Font("돋움", Font.BOLD, 30);
      
      menuBtn[0] = new JButton("연락처");
      menuBtn[0].setSize(200,40);
      menuBtn[0].setFont(font);
      a.add(menuBtn[0]);
      
      menuBtn[0].addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            contacts = new Contact_Management();   
         }
      });
      
      menuBtn[1] = new JButton("약속 관리");
      menuBtn[1].setSize(200,40);
      menuBtn[1].setFont(font);
      a.add(menuBtn[1]);
      
      menuBtn[1].addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
             appoints = new Appointments_Management();  
          }
       });
      
      menuBtn[2] = new JButton("To-Do");
      menuBtn[2].setSize(200,40);
      menuBtn[2].setFont(font);
      a.add(menuBtn[2]);
      
      menuBtn[2].addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
             todo = new ToDo_Management();  
          }
       });
      
      setVisible(true);
   }
   
   public static void main(String[] args) {
      new Menu();
      
   }

}