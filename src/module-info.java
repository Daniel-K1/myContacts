module myContacts {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;
    exports com.myContacts.dataModel;

    opens com.myContacts;

}

