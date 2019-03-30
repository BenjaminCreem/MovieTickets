import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class GUI extends Application{

    private Stage window;
    private Scene thisScene;
    private Scene customerScene;
    private Scene employeeScene;
    private Button cusLogin;
    private Button empLogin;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Main Window
        window = primaryStage;
        window.setTitle("Movie Tickets");

        //Set possible next scenes depending on user input
        SceneSetter sm = new SceneSetter(window);
        customerScene = sm.getCustomerScene();
        employeeScene = sm.getEmployeeScene();

        //Info label
        Label info = new Label("Welcome to Movie Tickets1");

        //Login Buttons
        cusLogin = new Button();
        cusLogin.setText("Customer Login");
        cusLogin.getStyleClass().add("button");
        cusLogin.setId("cusLogin");
        cusLogin.setOnAction(e -> window.setScene(customerScene)); //Lambda expression


        empLogin = new Button();
        empLogin.setText("Employee Login");
        empLogin.getStyleClass().add("button");
        empLogin.setId("empLogin");
        empLogin.setOnAction(e -> window.setScene(employeeScene));


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(info, empLogin, cusLogin);

        thisScene = new Scene(layout, 1280, 720);
        thisScene.getStylesheets().add("styles.css");
        primaryStage.setScene(thisScene);
        primaryStage.show();
    }
}
