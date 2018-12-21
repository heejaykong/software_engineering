package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class Test {

//AppointmentTest
   @Test
   void testSqlUpdate() {
      assertTrue(Appointments_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   @Test
   void testConnprocess() {
      assertTrue(Appointments_Management.connprocess());
   }

//ContactsTest
   @Test
   void testSqlUpdate() {
      assertTrue(Contact_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   @Test
   void testConnprocess() {
      assertTrue(Contact_Management.connprocess());
   }

//ToDoTest
   @Test
   void testSqlUpdate() {
      assertTrue(ToDo_Management.sqlUpdate("INSERT INTO appoint_tbl(date,people,place) VALUES('상대방','0109283722','1288428392')");
   }
   
   @Test
   void testConnprocess() {
      assertTrue(ToDo_Management.connprocess());
   }
}