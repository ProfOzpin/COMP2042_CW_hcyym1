module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.game;
    opens com.example.demo.game to javafx.fxml;
    exports com.example.demo.user;
    opens com.example.demo.user to javafx.fxml;
}