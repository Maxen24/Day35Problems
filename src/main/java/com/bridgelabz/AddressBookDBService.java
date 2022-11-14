package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private Connection getConnection() throws SQLException{
        String jdbcUrl="jdbc:mysql://127.0.0.1:3306/AddressBook?useSSL-false";
        String username= "root";
        String password= "Cool@2197";
        Connection connect;

        System.out.println("Connecting to Database"+jdbcUrl);
        connect= DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connection Successfully"+connect);
        return connect;
    }

    public List<Contacts> readData(){
        String sqlStatement= "SELECT Id, Name, MobileNumber, City, Zipcode from AddressBook; ";
        List<Contacts>addressBookList=new ArrayList<>();

        try(Connection connect=getConnection()){
            Statement statement=connect.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlStatement);

            while(resultSet.next()){
                int id= resultSet.getInt("Id");
                String name=resultSet.getString("Name");
                String MobileNumber=resultSet.getString("MobileNumber");
                String City= resultSet.getString("City");
                String Zipcode=resultSet.getString("Zipcode");
                addressBookList.add(new Contacts(id, name,MobileNumber, City, Zipcode));
            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return addressBookList;
    }
}
