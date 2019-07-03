package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String[] args) throws SQLException {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport","postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
          Customer customer = new Customer();
          customer.setFirstName("tom");
          customer.setLastName("buchka");
          customer.setEmail("tom@gmail.com");
          customer.setAddress("123 address");
          customer.setCity("new jersey");
          customer.setState("VA");
          customer.setPhone("(123) 123-1234");
          customer.setZipCode("12345");

          Customer dbcustomer = customerDAO.create(customer);
            System.out.println(dbcustomer);
            dbcustomer = customerDAO.findById(dbcustomer.getId());
            System.out.println(dbcustomer);
            dbcustomer.setEmail("john.adam@wh.go");

            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
