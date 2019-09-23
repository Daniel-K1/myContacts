package com.danielk.mycontacts;

import com.danielk.mycontacts.dataModel.Contact;
import com.danielk.mycontacts.dataModel.ContactData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    TableView<Contact> contactsTable;

    @FXML
    MenuItem addNewConactMenuItem;

    @FXML
    BorderPane mainWindow;

    private ContactData data;

    private final static Logger LOG= LogManager.getLogger();

    public void initialize() {

        data = new ContactData();
        data.loadContacts();
        contactsTable.setItems(data.getContacts());

//        contactsTable = new TableView<>();
//        contactsTable.getColumns().add(new TableColumn<>());


    }

    @FXML
    public void openNewItemDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Add new contact");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/addNewDialogWindow.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            LOG.error("Loading failed"+e);
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            AddNewDialogController dialogController = loader.getController();
            Contact newContact = dialogController.getNewContact();
            data.addContact(newContact);
            data.saveContacts();

        }
    }

    @FXML
    public void onExit() {
        System.exit(0);
    }

    @FXML
    public void openEditDialog() {
        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();

        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select contact to be edited");
            alert.showAndWait();
        }

        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Edit contact");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewDialogWindow.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            LOG.error("Loading failed: "+e);
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        AddNewDialogController dialogController = loader.getController();
        dialogController.editContact(selectedContact);


        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            dialogController.updateContact(selectedContact);
            data.saveContacts();

        }

    }

    public void deleteSelectedContact() {

        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();


        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select contact to be deleted");
            alert.showAndWait();
        }else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please confirm");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete contact: "+selectedContact.getFirstName()
                    +" "+selectedContact.getLastName()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get()==ButtonType.OK){
                data.deleteContact(selectedContact);
                data.saveContacts();
            }
        }


    }
}
