import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI extends Application{

    protected Stage window;
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
        //First scene
        SceneSetter sm = new SceneSetter(window);
        primaryStage.setScene(sm.firstScene());
        primaryStage.show();
    }

}
