package com.rgt.library;

import com.rgt.library.entity.Book;
import com.rgt.library.entity.CD;
import com.rgt.library.entity.Magazine;
import com.rgt.library.entity.Resource;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

import java.io.*;
import java.util.Optional;

public class LibraryController {
    private ObservableList<Resource> resourceList;
    private TableView<Resource> resourceTable;

    public LibraryController(ObservableList<Resource> resourceList, TableView<Resource> resourceTable) {
        this.resourceList = resourceList;
        this.resourceTable = resourceTable;
    }

    public void addResource(TextField titleTextField, TextField authorTextField, ComboBox<String> resourceTypeComboBox) {
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
    public Optional<Resource> editResource(Resource resource) {
        Dialog<Resource> dialog = new Dialog<>();
        dialog.setTitle("Edit Resource");
        dialog.setHeaderText(null);

        ButtonType updateButtonType = new ButtonType("Update", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create UI components for editing the resource
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        TextField titleTextField = new TextField(resource.getTitle());
        TextField authorTextField = new TextField(resource.getAuthor());
        TextField isbnTextField = new TextField();
        TextField issueDateTextField = new TextField();
        TextField genreTextField = new TextField();

        gridPane.add(new Label("Title:"), 0, 0);
        gridPane.add(titleTextField, 1, 0);
        gridPane.add(new Label("Author:"), 0, 1);
        gridPane.add(authorTextField, 1, 1);
        gridPane.add(new Label("ISBN:"), 0, 2);
        gridPane.add(isbnTextField, 1, 2);
        gridPane.add(new Label("Issue Date:"), 0, 3);
        gridPane.add(issueDateTextField, 1, 3);
        gridPane.add(new Label("Genre:"), 0, 4);
        gridPane.add(genreTextField, 1, 4);

        // Set initial values in the input fields
        if (resource instanceof Book) {
            isbnTextField.setText(((Book) resource).getISBN());
        } else if (resource instanceof Magazine) {
            issueDateTextField.setText(((Magazine) resource).getIssueDate());
        } else if (resource instanceof CD) {
            genreTextField.setText(((CD) resource).getGenre());
        }

        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(titleTextField::requestFocus);

        // Convert the result to the resource object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                String updatedTitle = titleTextField.getText();
                String updatedAuthor = authorTextField.getText();

                // Update the resource properties
                resource.setTitle(updatedTitle);
                resource.setAuthor(updatedAuthor);
                
                if (resource instanceof Book) {
                    ((Book) resource).setISBN(isbnTextField.getText());
                } else if (resource instanceof Magazine) {
                    ((Magazine) resource).setIssueDate(issueDateTextField.getText());
                } else if (resource instanceof CD) {
                    ((CD) resource).setGenre(genreTextField.getText());
                }

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

    public void deleteResource(Resource resource) {
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
}

