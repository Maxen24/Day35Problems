package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<Contacts> addressBookList;

    public AddressBook(List<Contacts> addressBookList) {
        this.addressBookList = addressBookList;
    }

    private void readContacts(Scanner consoleInputReader) {

        System.out.println("Enter the Id : ");
        int Id = consoleInputReader.nextInt();
        System.out.println("Enter the Name : ");
        String Name = consoleInputReader.next();
        System.out.println("Enter the Mobile Number : ");
        String MobileNumber = consoleInputReader.next();
        System.out.println("Enter the City Name : ");
        String City= consoleInputReader.next();
        System.out.println("Enter the Zipcode : ");
        String Zipcode= consoleInputReader.next();

        addressBookList.add(new Contacts(Id, Name, MobileNumber, City, Zipcode));
    }

    private void writeContacts() {
            System.out.println("\nWriting Address Book Roster to Console\n" + addressBookList);
        }

    public List<Contacts> readAddressBookFromDatabase(IOService ioService){
        if (ioService.equals(IOService.DB_IO)){
            this.addressBookList=new AddressBookDBService().readData();
        }
        return this.addressBookList;
    }


    public static void main (String[]args){

        System.out.println("---------- Welcome To Address Book Application ----------\n");
        List<Contacts> addressBookList = null;
        AddressBook addressBook = new AddressBook(addressBookList);
        Scanner consoleInputReader = new Scanner(System.in);

        addressBookList=addressBook.readAddressBookFromDatabase(IOService.DB_IO);
        addressBookList.forEach(System.out::println);

    }

}

