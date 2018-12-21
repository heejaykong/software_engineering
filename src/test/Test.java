package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class Test {

//AppointmentTest
   @Test
   public void testSqlUpdate() throws Exception{
      assertTrue(Appointments_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   @Test
   public void testConnprocess() throws Exception{
      assertTrue(Appointments_Management.connprocess());
   }

//ContactsTest
   @Test
   public void testSqlUpdate() throws Exception{
      assertTrue(Contact_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   @Test
   public void testConnprocess() throws Exception{
      assertTrue(Contact_Management.connprocess());
   }

//ToDoTest
   @Test
   public void testSqlUpdate() throws Exception{
      assertTrue(ToDo_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   
   @Test
   public void testConnprocess() throws Exception{
      assertTrue(ToDo_Management.connprocess());
   }
}