package Contacts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Contact_Management extends JFrame implements ActionListener{
   
  JLabel [] labels = new JLabel [3];
  JTextField [] textfields = new JTextField [3];
  JButton insertBtn = new JButton("추가");
  JButton updateBtn = new JButton("수정");
  JButton deleteBtn = new JButton("삭제");
  JTable table;
  JScrollPane scrollbar;
  Font font1, font2;
   
  
   public Contact_Management(){
      
      setTitle("연락처");
      setSize(800,800);
      
      Container b = getContentPane();
      b.setLayout(null);
      
      
      JPanel inputArea = new JPanel();
      
      inputArea.setLayout(null);
      inputArea.setBounds(0, 0, 800, 250);
      inputArea.setBackground(Color.PINK);
      
      font1 = new Font("돋움",Font.ITALIC,30);
      font2 = new Font("돋움",Font.PLAIN,20);
      
      labels[0] = new JLabel("이름");
      labels[0].setBounds(100,5,100,100);
      labels[0].setFont(font1);
      inputArea.add(labels[0]);
      
      textfields[0] = new JTextField();
      textfields[0].setBounds(230,30,300,50);
      textfields[0].setFont(font2);
      inputArea.add(textfields[0]);
      
      labels[1] = new JLabel("휴대폰");
      labels[1].setBounds(100,70,100,100);
      labels[1].setFont(font1);
      inputArea.add(labels[1]);
      
      textfields[1] = new JTextField();
      textfields[1].setBounds(230,95,300,50);
      textfields[1].setFont(font2);
      inputArea.add(textfields[1]);
      
      labels[2] = new JLabel("이메일");
      labels[2].setBounds(100,135,100,100);
      labels[2].setFont(font1);
      inputArea.add(labels[2]);
      
      textfields[2] = new JTextField();
      textfields[2].setBounds(230,160,300,50);
      textfields[2].setFont(font2);
      inputArea.add(textfields[2]);
      
      insertBtn.setBounds(550,30,100,50);
      insertBtn.setFont(font2);
      inputArea.add(insertBtn);
      
      updateBtn.setBounds(550,95,100,50);
      updateBtn.setFont(font2);
      inputArea.add(updateBtn);
      
      deleteBtn.setBounds(550,160,100,50);
      deleteBtn.setFont(font2);
      inputArea.add(deleteBtn);
      
      b.add(inputArea);
      
      
      JPanel dataList = new JPanel();
      
     dataList.setLayout(null);
     dataList.setBackground(Color.LIGHT_GRAY);
     dataList.setBounds(0,250, 800, 650);
     
     String [] colNames = {"이름", "휴대폰", "이메일"};
     Object [][] data;
     
     DefaultTableModel model = new DefaultTableModel(colNames,0);
     table = new JTable(model);
     table.setFont(new Font("돋움",Font.PLAIN,20));
     table.setRowHeight(25);
     scrollbar = new JScrollPane(table);
     scrollbar.setBounds(5,5,775,500);
     dataList.add(scrollbar);
      
     b.add(dataList);
      
     
      setVisible(true);
     
      insertBtn.addActionListener(this);
      updateBtn.addActionListener(this);
      deleteBtn.addActionListener(this);
      
      table.addMouseListener(new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
           
           int row = table.getSelectedRow();
           TableModel model = table.getModel();
           
           textfields[0].setText(model.getValueAt(row,0).toString());
           textfields[1].setText(model.getValueAt(row,1).toString());
           textfields[2].setText(model.getValueAt(row,2).toString());

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
         
      });
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
   // TODO Auto-generated method stub
      Object obj = e.getSource();
      if (obj instanceof JButton) {
         if (e.getSource() == insertBtn) {
           insert(); 
         }
         else if (e.getSource() == updateBtn) {
            update();
         }
         else if (e.getSource() == deleteBtn) {
            delete();
         }
      }
   }
   
   private void insert() {
      
      String [] arr = new String[3];
      arr[0] = textfields[0].getText();
      arr[1] = textfields[1].getText();
      arr[2] = textfields[2].getText();
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.addRow(arr);
      initTextfields();
      
   }

   private void update() {
      
      int row = table.getSelectedRow();
      if (row == -1)
         return;
      
      String [] arr = new String[3];
      arr[0] = textfields[0].getText();
      arr[1] = textfields[1].getText();
      arr[2] = textfields[2].getText();
      
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setValueAt(arr[0], row, 0);
      model.setValueAt(arr[1], row, 1);
      model.setValueAt(arr[2], row, 2);
   }
   
   private void delete() {
      int row = table.getSelectedRow();
      if (row == -1)
         return;
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.removeRow(row);
      initTextfields();
   }
   
   private void initTextfields() {
      textfields[0].setText("");
      textfields[1].setText("");
      textfields[2].setText("");
   }
 
}

