import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;

public class GUI extends Application {

    Button cusLogin;
    Button empLogin;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Movie Tickets");
        cusLogin = new Button();
        cusLogin.setText("Customer Login");
        //empLogin = new Button();
        //empLogin.setText("Employee Login");

        StackPane layout = new StackPane();
        layout.getChildren().add(cusLogin);
        //layout.getChildren().add(empLogin);

        Scene scene = new Scene(layout, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        //ScreenController sc = new ScreenController(scene);
    }



    private class ScreenController {
        private HashMap<String, Pane> screenMap = new HashMap<>();
        private Scene main;

        public ScreenController(Scene main) {
            this.main = main;
        }

        protected void addScreen(String name, Pane pane){
            screenMap.put(name, pane);
        }

        protected void removeScreen(String name){
            screenMap.remove(name);
        }

        protected void activate(String name){
            main.setRoot( screenMap.get(name) );
        }
    }
}
