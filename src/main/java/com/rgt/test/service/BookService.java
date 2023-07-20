package com.rgt.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rgt.test.entity.Book;
import com.rgt.test.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Add a new book to the database.
	 *
	 * @param book The book object to be added.
	 * @return The newly added book object.
	 */
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Get a book by its unique ID.
	 *
	 * @param id The ID of the book to retrieve.
	 * @return An Optional containing the book, or empty if not found.
	 */
	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}

	/**
	 * Get all books stored in the database.
	 *
	 * @return A list of all books.
	 */
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	/**
	 * Update an existing book in the database.
	 *
	 * @param id   The ID of the book to be updated.
	 * @param book The updated book object.
	 * @return The updated book object.
	 */
	public Book updateBook(Long id, Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Delete a book from the database by its ID.
	 *
	 * @param id The ID of the book to be deleted.
	 */
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	/**
	 * Search for books containing the given name (case-insensitive).
	 *
	 * @param name The name to search for in book titles.
	 * @return An iterable of books that match the search criteria.
	 */
	public Iterable<Book> searchBooks(String name) {
		return bookRepository.findByTitleContainingIgnoreCase(name);
	}

	/**
	 * Get a list of all active books in the database.
	 *
	 * @return A list of active books.
	 */
	public List<Book> getActiveBooks() {
		Iterable<Book> allBooks = bookRepository.findAll();

		List<Book> activeBooks = new ArrayList<>();
		for (Book book : allBooks) {
			if (book.isActive()) {
				activeBooks.add(book);
			}
		}

		return activeBooks;
	}
	/**
	 * Get the total number of records (books) in the database.
	 *
	 * @return The total number of records.
	 */
	public long getTotalRecords() {
		long totalRecords = bookRepository.count();
		return totalRecords;
	}

	/**
	 * Get a paginated list of all books from the database.
	 *
	 * @param pageable The pageable object defining pagination options.
	 * @return A Page object containing the requested books.
	 */
	public Page<Book> getAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
}