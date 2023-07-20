package com.rgt.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.test.entity.Book;
import com.rgt.test.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * Add a new book to the system.
	 *
	 * @param book The book object to be added.
	 * @return The newly added Book object.
	 */
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	/**
	 * Get a book by its ID.
	 *
	 * @param id The ID of the book to retrieve.
	 * @return The Book object corresponding to the given ID.
	 * @throws IllegalArgumentException If the book with the given ID is not found.
	 */
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.getBookById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
	}
	
	/**
	 * Update an existing book in the system.
	 *
	 * @param id          The ID of the book to be updated.
	 * @param updatedBook The updated Book object.
	 * @return The updated Book object.
	 */
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
		return bookService.updateBook(id, updatedBook);
	}

	/**
	 * Delete a book by its ID.
	 *
	 * @param id The ID of the book to be deleted.
	 */
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	/**
	 * Search for books by name.
	 *
	 * @param name The name to be used for searching books.
	 * @return A list of Book objects matching the search criteria.
	 */
	@GetMapping("/search")
	public Iterable<Book> searchBooks(@RequestParam("name") String name) {
		return bookService.searchBooks(name);
	}

	/**
	 * Get a paginated list of all books.
	 *
	 * @param pageNumber The page number to retrieve (default: 1).
	 * @param pageSize   The number of books per page (default: 10).
	 * @return A Page containing the requested books.
	 */
	@GetMapping
	public Page<Book> getAllBooks(
			@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return bookService.getAll(pageable);
	}

	/**
	 * Get a list of active books.
	 *
	 * @return A list of Book objects that are marked as active.
	 */
	@GetMapping("/active")
	public List<Book> getActiveBooks() {
		return bookService.getActiveBooks();
	}
}