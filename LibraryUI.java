package com.rgt.library;

import com.rgt.library.entity.Book;
import com.rgt.library.entity.CD;
import com.rgt.library.entity.Magazine;
import com.rgt.library.entity.Resource;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryUI extends Application {
    private TextField titleTextField;
    private TextField authorTextField;
    private ComboBox<String> resourceTypeComboBox;
    private Button addButton;
    private TableView<Resource> resourceTable;
    private ObservableList<Resource> resourceList;
    private LibraryController libraryController;

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

        // Initialize resource list and table
        resourceList = FXCollections.observableArrayList();
        resourceTable = new TableView<>();
        resourceTable.setItems(resourceList);

        // Create library controller
        libraryController = new LibraryController(resourceList, resourceTable);

        // Set column properties for the resource table
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

        // Add button click event handler
        addButton.setOnAction(e -> libraryController.addResource(titleTextField, authorTextField, resourceTypeComboBox));
        
           // Add an "Edit" button to the table view
     		TableColumn<Resource, Void> editColumn = new TableColumn<>("Edit");
     		editColumn.setCellFactory(param -> new TableCell<Resource, Void>() {
     			private final Button editButton = new Button("Edit");

     			{
     				editButton.setOnAction(event -> {
     					Resource resource = (Resource) getTableRow().getItem();
     					if (resource != null) {
     						libraryController.editResource(resource);
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
     					Resource resource = (Resource) getTableRow().getItem();
     					if (resource != null) {
     						libraryController.deleteResource(resource);
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
        gridPane.add(titleLabel, 0, 0);
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

    public static void main(String[] args) {
        launch(args);
    }
}
