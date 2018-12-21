package Appointments;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class Appointments_Management extends JFrame implements ActionListener {
	  
	  DefaultTableModel model;
	  JLabel [] labels = new JLabel [3];
	  JTextField [] textfields = new JTextField [3];
	  JButton insertBtn = new JButton("�߰�");
	  JButton updateBtn = new JButton("����");
	  JButton deleteBtn = new JButton("����");
	  JTable table;
	  JScrollPane scrollbar;
	  Font font1, font2;
	   
	  Connection conn;
	  Statement stmt;
	  ResultSet rs;
	  
	  public Appointments_Management() {
		  
		  enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		  
		  setTitle("��� ����");
	      setSize(800,800);
	      
	      Container b = getContentPane();
	      b.setLayout(null);
	      
	      
	      JPanel inputArea = new JPanel();
	      
	      inputArea.setLayout(null);
	      inputArea.setBounds(0, 0, 800, 250);
	      inputArea.setBackground(Color.PINK);
	      
	      font1 = new Font("����",Font.ITALIC,20);
	      font2 = new Font("����",Font.PLAIN,20);
	      
	      labels[0] = new JLabel("��ӳ�¥");
	      labels[0].setBounds(100,5,100,100);
	      labels[0].setFont(font1);
	      inputArea.add(labels[0]);
	      
	      textfields[0] = new JTextField();
	      textfields[0].setBounds(230,30,300,50);
	      textfields[0].setFont(font2);
	      inputArea.add(textfields[0]);
	      
	      labels[1] = new JLabel("��ӻ��");
	      labels[1].setBounds(100,70,100,100);
	      labels[1].setFont(font1);
	      inputArea.add(labels[1]);
	      
	      textfields[1] = new JTextField();
	      textfields[1].setBounds(230,95,300,50);
	      textfields[1].setFont(font2);
	      inputArea.add(textfields[1]);
	      
	      labels[2] = new JLabel("������");
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
	     
	     String [] colNames = {"No.", "��ӳ�¥", "��ӻ��", "������"};
	     Object [][] data = new String[0][4];
	     
	     model = new DefaultTableModel(data,colNames);
	     table = new JTable(model);
	     table.setFont(new Font("����",Font.PLAIN,20));
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
	           
	           textfields[0].setText(model.getValueAt(row,1).toString());
	           textfields[1].setText(model.getValueAt(row,2).toString());
	           textfields[2].setText(model.getValueAt(row,3).toString());

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
	      
	      if(!this.connProcess()){		
	    	  System.out.println("������ ���� �����Ͽ����ϴ�.");
	    	  return;
	      } else {
	    	  this.sqlQuery("SELECT * FROM appoint_tbl");
	    	  System.out.println("������ �ҷ��ɴϴ�.");
	      }
	   }
	  
	  @Override
	   public void actionPerformed(ActionEvent e) {
	   // TODO Auto-generated method stub
	      Object obj = e.getSource();
	      
	      if (obj instanceof JButton) {
	         if (e.getSource() == insertBtn) {
	        	 
	        	 nullToSpace();
	        	 
	        	 String sql = "INSERT INTO appoint_tbl(date,people,place) VALUES("+ 
	        			 		"'" + this.textfields[0].getText() + "'," +
	        			 		"'" + this.textfields[1].getText() + "'," +
	        			 		"'" + this.textfields[2].getText() + "')";
	        	 if (sqlUpdate(sql)) {
	        		 sqlQuery("SELECT * FROM appoint_tbl");
	        		 System.out.println("������ �߰� ����");
	        	 }else {
	        		 System.out.println("������ �߰� ����");
	        	 }
	        	 
	           insert(); 
	           
	         }
	         
	         else if (e.getSource() == updateBtn) {
	            
	        	 nullToSpace();
	        	 
	        	 int row = table.getSelectedRow();
	        	 if ( row < 0 ) {
	        		 System.out.println("������ ����� �����ϼ���.");
	        		 return;
	        	 }
	        	 //������ row���� 0��
	        	 String key_id = (String)model.getValueAt(row, 0);
	        	 System.out.println(key_id);
	        	 String sql = "UPDATE appoint_tbl SET " +
	        			 		"date" + "=" + "'" + 
	        			 		this.textfields[0].getText() + "'," +
	        			 		"people" + "=" + "'" + 
	        			 		this.textfields[1].getText() + "'," +
	        			 		"place" + "=" + "'" + 
	        			 		this.textfields[2].getText() + "' " +
	        			 		"WHERE id = " + key_id + "";
	        	 if (sqlUpdate(sql)) {
	        		 sqlQuery("SELECT * FROM appoint_tbl");
	        		 System.out.println("������ ���� ����");
	        	 }else {
	        		 System.out.println("������ ���� ����");
	        	 }
	        	 
	        	 update();
	         }
	         else if (e.getSource() == deleteBtn) {
	           
	        	 int row = table.getSelectedRow();
	        	 if ( row < 0 ) {
	        		 System.out.println("������ ����� �����ϼ���.");
	        		 return;
	        	 }
	        	 String key_id = (String)model.getValueAt(row, 0);
	        	 String sql = "DELETE FROM appoint_tbl WHERE id = " + key_id + "";
	        	 if (sqlUpdate(sql)) {
	        		 sqlQuery("SELECT * FROM appoint_tbl");
	        		 System.out.println("������ ���� ����");
	        	 }else {
	        		 System.out.println("������ ���� ����");
	        	 }
	        	 
	        	 delete();
	         }
	      }
	   }
	   
	   private void insert() {
	      initTextfields();
	   }

	   private void update() {
	      
	      int row = table.getSelectedRow();
	      if (row == -1)
	         return;
	      
	      String [] arr = new String[4];
	      arr[0] = row + "";
	      arr[1] = textfields[1].getText();
	      arr[2] = textfields[2].getText();
	      arr[3] = textfields[3].getText();
	      
	      DefaultTableModel model = (DefaultTableModel) table.getModel();
	      model.setValueAt(arr[0], row, 0);
	      model.setValueAt(arr[1], row, 1);
	      model.setValueAt(arr[2], row, 2);
	      model.setValueAt(arr[3], row, 3);
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
	  
	   private void nullToSpace() {
	       if (textfields[0].getText() == null ) {
	    	   textfields[0].setText(" ");
	       }else if (textfields[1].getText() == null){
	    	   textfields[1].setText(" ");
	       }else if (textfields[2].getText() == null){
	    	   textfields[2].setText(" ");
	       }
	   }
	   
	   public boolean connProcess() {  // sql ���� �޼ҵ�
		   
		   String dbUrl ="jdbc:mysql://127.0.0.1/schema?allowPublicKeyRetrieval=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
			  String user = "sohyun";
		      String pass = "cute94";
		      
		      conn = null;
		      stmt = null;
		      
		      try{
		    	  Class.forName("com.mysql.cj.jdbc.Driver");
		    	  System.out.println("����̹� �˻� ����!");
		    	  conn = (Connection)DriverManager.getConnection(dbUrl, user, pass);
		    	  System.out.println("���� ����!");
		      }catch (ClassNotFoundException e) {
		    	  System.out.println("����̹� �˻� ����!");
		    	  e.printStackTrace();
		    	  return false;
		      }catch (SQLException e) {
		    	  e.printStackTrace();
		    	  return false;
		      }
		      return true;
	   }   
	   
	   public void sqlQuery(String sql){ // SQL������ + ����¾��� + SELECT�� ����޼ҵ�
		   Statement stmt = null;
		   try {
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery(sql);
			   model.setNumRows(0);
			   while(rs.next()){
				   String data [] = {
					   rs.getInt("id")+"",
					   rs.getString("date"),
					   rs.getString("people"),
					   rs.getString("place")
				   	};
				   model.addRow(data);
			   }
		   }catch(Exception e){
			   System.out.println("������ select���� : " + e.getMessage());
		   }finally {
			   try{
				   stmt.close();
			   }catch(Exception e){}
		   }
	   }  
	   public boolean sqlUpdate(String sql) {  // SQL������ (����¾��� delete, update����)
		   Statement stmt = null;
		   try {
			   stmt = conn.createStatement();
			   int i = stmt.executeUpdate(sql);
			   System.out.println(i+"���� ������ ������Ʈ ����");
			   return true;
		   }catch(Exception e) {
			   System.out.println("������ ������Ʈ ���� : " + e.getMessage());
			   return false;
		   }finally{
			   try{
				   stmt.close();
			   }catch(Exception e) {}
		   }
	   }

	   protected void processWindowEvent(WindowEvent e) { // ������â ���� ���� �����
		   super.processWindowEvent(e);
		   if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			   try {
				   if(conn != null) {
				   	   conn.close();
				   }
			   }catch(Exception e1) {}
			   this.setVisible(false);
		   }
	   }
}