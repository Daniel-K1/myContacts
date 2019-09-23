module com.danielk.mycontacts {

    requires javafx.fxml;
    requires java.xml;
    requires javafx.controls;
    requires javafx.base;
    requires org.apache.logging.log4j;
    exports com.danielk.mycontacts.dataModel;
    exports com.danielk.mycontacts;

    opens com.danielk.mycontacts;

}