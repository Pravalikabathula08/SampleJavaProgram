package com.rgt.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import com.rgt.library.entity.Book;
import com.rgt.library.entity.CD;
import com.rgt.library.entity.Magazine;
import com.rgt.library.entity.Resource;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryManagmentSystem extends Application {

	private TextField titleTextField;
	private TextField authorTextField;
	private ComboBox<String> resourceTypeComboBox;
	private Button addButton;
	private TableView<Resource> resourceTable;
	private ObservableList<Resource> resourceList;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Library Management System");

		// Create UI components
		Label titleLabel = new Label("Title:");
		Label authorLabel = new Label("Author:");
		Label resourceTypeLabel = new Label("Resource Type:");
		titleTextField = new TextField();
		authorTextField = new TextField();
		resourceTypeComboBox = new ComboBox<>();
		resourceTypeComboBox.getItems().addAll("Book", "Magazine", "CD");
		addButton = new Button("Add");

		// Add button click event handler
		addButton.setOnAction(e -> addResource());

		// Create resource table
		resourceTable = new TableView<>();
		resourceList = FXCollections.observableArrayList();
		resourceTable.setItems(resourceList);

		TableColumn<Resource, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Resource, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Resource, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

		TableColumn<Resource, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setCellValueFactory(cellData -> {
			Resource resource = cellData.getValue();
			if (resource instanceof Book) {
				return new SimpleStringProperty(((Book) resource).getISBN());
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Resource, String> issueDateColumn = new TableColumn<>("Issue Date");
		issueDateColumn.setCellValueFactory(cellData -> {
			Resource resource = cellData.getValue();
			if (resource instanceof Magazine) {
				return new SimpleStringProperty(((Magazine) resource).getIssueDate());
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Resource, String> genreColumn = new TableColumn<>("Genre");
		genreColumn.setCellValueFactory(cellData -> {
			Resource resource = cellData.getValue();
			if (resource instanceof CD) {
				return new SimpleStringProperty(((CD) resource).getGenre());
			} else {
				return new SimpleStringProperty("");
			}
		});

		resourceTable.getColumns().add(titleColumn);
		resourceTable.getColumns().add(authorColumn);
		resourceTable.getColumns().add(typeColumn);

		// Update column order for Book, Magazine, CD
		resourceTable.getColumns().add(isbnColumn);
		resourceTable.getColumns().add(issueDateColumn);
		resourceTable.getColumns().add(genreColumn);

		// Add an "Edit" button to the table view
		TableColumn<Resource, Void> editColumn = new TableColumn<>("Edit");
		editColumn.setCellFactory(param -> new TableCell<Resource, Void>() {
			private final Button editButton = new Button("Edit");

			{
				editButton.setOnAction(event -> {
					Resource resource = getTableRow().getItem();
					if (resource != null) {
						editResource(resource);
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(editButton);
				}
			}
		});

		// Add a "Delete" button to the table view
		TableColumn<Resource, Void> deleteColumn = new TableColumn<>("Delete");
		deleteColumn.setCellFactory(param -> new TableCell<Resource, Void>() {
			private final Button deleteButton = new Button("Delete");

			{
				deleteButton.setOnAction(event -> {
					Resource resource = getTableRow().getItem();
					if (resource != null) {
						deleteResource(resource);
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteButton);
				}
			}
		});
		resourceTable.getColumns().add(editColumn);
		resourceTable.getColumns().add(deleteColumn);

		// Create a grid pane and add components
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(titleLabel, 0, 0);// titleLabel at row 0, column 0
		gridPane.add(titleTextField, 1, 0);
		gridPane.add(authorLabel, 0, 1);
		gridPane.add(authorTextField, 1, 1);
		gridPane.add(resourceTypeLabel, 0, 2);
		gridPane.add(resourceTypeComboBox, 1, 2);
		gridPane.add(addButton, 1, 3);
		gridPane.add(resourceTable, 0, 4, 2, 1);

		// Create a scene and set it on the stage
		Scene scene = new Scene(gridPane, 700, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void addResource() {
		String title = titleTextField.getText();
		String author = authorTextField.getText();
		String resourceType = resourceTypeComboBox.getValue();

		if (title.isEmpty() || author.isEmpty() || resourceType.isEmpty()) {
			System.out.println("Error: Title, Author, and Resource Type fields cannot be empty.");
			return;
		}

		// Create resource based on the selected resource type
		Resource resource;
		switch (resourceType) {
		case "Book":
			String isbn = showInputDialog("ISBN:");
			resource = new Book(title, author, isbn);
			break;
		case "Magazine":
			String issueDate = showInputDialog("Issue Date:");
			resource = new Magazine(title, author, issueDate);
			break;
		case "CD":
			String genre = showInputDialog("Genre:");
			resource = new CD(title, author, genre);
			break;
		default:
			System.out.println("Error: Unknown resource type.");
			return;
		}

		// Save the resource data to a file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt", true))) {
			writer.write(resource.toString()); // Write resource details in CSV format
			writer.newLine();
			writer.flush();
			System.out.println("Resource added successfully!");

			// Add the resource to the table view
			resourceList.add(resource);
		} catch (IOException e) {
			System.out.println("Error: Failed to add resource.");
			e.printStackTrace();
		}

		// Clear the text fields
		titleTextField.clear();
		authorTextField.clear();
		resourceTypeComboBox.getSelectionModel().clearSelection();
	}

	private String showInputDialog(String prompt) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Input");
		dialog.setHeaderText(null);
		dialog.setContentText(prompt);
		Optional<String> result = dialog.showAndWait();
		return result.orElse("");
	}

	private Optional<Resource> editResource(Resource resource) {
		Dialog<Resource> dialog = new Dialog<>();
		dialog.setTitle("Edit Resource");
		dialog.setHeaderText(null);

		// Create the dialog buttons
		ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

		// Create UI components for editing the resource
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));

		TextField titleTextField = new TextField(resource.getTitle());
		TextField authorTextField = new TextField(resource.getAuthor());

		gridPane.add(new Label("Title:"), 0, 0);
		gridPane.add(titleTextField, 1, 0);
		gridPane.add(new Label("Author:"), 0, 1);
		gridPane.add(authorTextField, 1, 1);

		dialog.getDialogPane().setContent(gridPane);

		// Request focus on the title text field by default
		Platform.runLater(titleTextField::requestFocus);

		// Convert the result to the resource object when the update button is clicked
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == updateButtonType) {
				String updatedTitle = titleTextField.getText();
				String updatedAuthor = authorTextField.getText();

				// Update the resource properties
				resource.setTitle(updatedTitle);
				resource.setAuthor(updatedAuthor);

				// Update the resource in the file
				updateResourceFromFile(resource);

				// Update the resource in the table view
				resourceTable.refresh();

				String successMessage = "The resource was edited successfully.";
				System.out.println(successMessage);
				showInformationDialog("Resource Updated", "The resource was edited successfully.");
				return resource;
			}
			return null;
		});

		return dialog.showAndWait();
	}

	private void showInformationDialog(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void deleteResource(Resource resource) {
		Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationDialog.setTitle("Delete Resource");
		confirmationDialog.setHeaderText(null);
		confirmationDialog.setContentText("Are you sure you want to delete this resource?");

		Optional<ButtonType> result = confirmationDialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			resourceList.remove(resource);
			deleteResourceFromFile(resource);
			System.out.println("Resource deleted successfully!");
		}
	}

	@SuppressWarnings("unused")
	private void loadResources() {
		try (BufferedReader reader = new BufferedReader(new FileReader("library_data.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length >= 4) {
					String resourceType = data[0];
					String title = data[1];
					String author = data[2];
					String additionalData = data[3];
					Resource resource;

					switch (resourceType) {
					case "Book":
						resource = new Book(title, author, additionalData);
						break;
					case "Magazine":
						resource = new Magazine(title, author, additionalData);
						break;
					case "CD":
						resource = new CD(title, author, additionalData);
						break;
					default:
						System.out.println("Warning: Unknown resource type found in the data file.");
						continue;
					}

					resourceList.add(resource);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: Failed to load resources.");
			e.printStackTrace();
		}
	}

	private void updateResourceFromFile(Resource resource) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt"))) {
			for (Resource r : resourceList) {
				writer.write(r.toString());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			System.out.println("Error: Failed to update resources.");
			e.printStackTrace();
		}
	}

	private void deleteResourceFromFile(Resource resource) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt"))) {
			for (Resource r : resourceList) {
				if (!r.equals(resource)) {
					writer.write(r.toString());
					writer.newLine();
				}
			}
			writer.flush();
		} catch (IOException e) {
			System.out.println("Error: Failed to delete resource.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
