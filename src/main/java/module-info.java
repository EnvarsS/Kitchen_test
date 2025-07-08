module com.envy.kitchen_test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.controlsfx.controls;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    exports com.envy.kitchen_test;
    exports com.envy.kitchen_test.Controller;
    exports com.envy.kitchen_test.Model;
    opens com.envy.kitchen_test to javafx.fxml;
    opens com.envy.kitchen_test.Controller to javafx.fxml;
}