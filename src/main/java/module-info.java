module com.danielk.mycontacts {

    requires javafx.fxml;
    requires java.xml;
    requires javafx.controls;
    requires javafx.base;
    exports com.danielk.mycontacts.dataModel;
    exports com.danielk.mycontacts;

    opens com.danielk.mycontacts;

}