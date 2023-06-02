package com.rgt.samples;

public class Library {

	private static final int MAX_BOOKS = 5;
	private static final int MAX_PATRONS = 5;
	private static Book[] books = new Book[MAX_BOOKS];
	private static Patron[] patrons = new Patron[MAX_PATRONS];
	private static int numBooks = 0;
	private static int numPatrons = 0;

	public void addBook(String title, String author) {
		if (numBooks >= MAX_BOOKS) {
			System.out.println("Maximum number of books reached");
			return;
		}
		Book book = new Book(title, author);
		books[numBooks] = book;
		numBooks++;
		System.out.println("Book added successfully...");
	}

	public void addPatron(String name) {
		if (numPatrons >= MAX_PATRONS) {
			System.out.println("Maximum number of patrons reached");
			return;
		}
		Patron patron = new Patron(name);
		patrons[numPatrons] = patron;
		numPatrons++;
		System.out.println("Patron added successfully...");
	}

	public void borrowBook(String patronName, String bookTitle) {
		Patron patron = findPatronByName(patronName);
		Book book = findBookByTitle(bookTitle);
		if (book != null && patron != null) {
			if (!book.isAvalilable()) {
				System.out.println("Book is already borrowed....");
			} else {
				patron.borrowBook(book);
				book.setAvalilable(false);
				System.out.println("Book borrowed successfully...");
			}
		}else
		{
			System.out.println("Book and patron is not available");
		}
	}
	public void returnBook(String patronName, String bookTitle) {
		Patron patron = findPatronByName(patronName);
		Book book = findBookByTitle(bookTitle);
		if (patron == null) {
			System.out.println("Invalid patron name");
			return;
		}
		if (book == null) {
			System.out.println("Book does not exist");
			return;
		}
		if (patron.getBorrowedBook()!=null) {
			String borrowedBook = patron.getBorrowedBook().getTitle();
			if (borrowedBook.equals(borrowedBook)) {
				if (book.isAvalilable()) {
					System.out.println("Book is Not avalilable..");
					return;
				}else {
					patron.returnBook(book);
					patron.setBorrowedBook(null);
					book.setAvalilable(true);
					System.out.println("Book returned successfully...");
					return;
				}
			}else {
				System.out.println("Book is not borrowed by the patron");
				return;
			}
		}else {
			System.out.println("patron has no book to return");
			return;
		}
	}

	private Patron findPatronByName(String name) {
		for (int i = 0; i < numPatrons; i++) {
			if (patrons[i].getName().equals(name)) {
				return patrons[i];
			}
		}
		return null;
	}

	private Book findBookByTitle(String title) {
		for (int i = 0; i < numBooks; i++) {
			if (books[i].getTitle().equals(title)) {
				return books[i];
			}
		}
		return null;
	}
}
