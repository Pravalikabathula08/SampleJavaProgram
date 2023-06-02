package com.rgt.samples;

import java.util.Scanner;

public class LibrarySystem {

	public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        Library library = new Library();
		        int choice;
		        while (true) {
		            System.out.println("Library Management System");
		            System.out.println("1. Add Book");
		            System.out.println("2. Add Patron");
		            System.out.println("3. Borrow Book");
		            System.out.println("4. Return Book");
		            System.out.println("5. Exit");
		            System.out.print("Choose an option: ");
		            choice = scanner.nextInt();
		            scanner.nextLine();
		            switch (choice) {
		                case 1:
		                    System.out.print("Enter book title: ");
		                    String bookTitle = scanner.nextLine();
		                    System.out.print("Enter author name: ");
		                    String author = scanner.nextLine();
		                    library.addBook(bookTitle, author);
		                    break;
		                case 2:
		                    System.out.print("Enter patron name: ");
		                    String patronName = scanner.nextLine();
		                    library.addPatron(patronName);
		                    break;
		                case 3:
		                    System.out.print("Enter patron name: ");
		                    patronName = scanner.nextLine();
		                    System.out.print("Enter book title: ");
		                    bookTitle = scanner.nextLine();
		                    library.borrowBook(patronName, bookTitle);
		                    break;
		                case 4:
		                    System.out.print("Enter patron name: ");
		                    patronName = scanner.nextLine();
		                    System.out.print("Enter book title: ");
		                    bookTitle = scanner.nextLine();
		                    library.returnBook(patronName, bookTitle);
		                    break;
		                case 5:
		                    System.out.println("Goodbye!");
		                    scanner.close();
		                    System.exit(0);
		                    break;
		                default:
		                    System.out.println("Invalid choice, Please try again...");
		            }
		            System.out.println("...closed....");
		        }
		    }
	}


