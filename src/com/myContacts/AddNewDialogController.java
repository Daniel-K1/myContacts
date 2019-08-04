package com.myContacts;

import com.myContacts.dataModel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNewDialogController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField notesField;

    public Contact getNewContact(){

        String firstName=firstNameField.getText();
        String lastName=lastNameField.getText();
        String phoneNumber=phoneNumberField.getText();
        String notes=notesField.getText();

        return new Contact(firstName,lastName,phoneNumber,notes);
    }


    public void editContact(Contact editedContact){

        firstNameField.setText(editedContact.getFirstName());
        lastNameField.setText((editedContact.getLastName()));
        phoneNumberField.setText((editedContact.getPhoneNumber()));
        notesField.setText(editedContact.getNotes());
    }

    public void updateContact(Contact selectedContact) {

        selectedContact.setFirstName(firstNameField.getText());
        selectedContact.setLastName(lastNameField.getText());
        selectedContact.setPhoneNumber(phoneNumberField.getText());
        selectedContact.setNotes(notesField.getText());
    }
}
