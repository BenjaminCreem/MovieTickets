import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneSetter {

    public SceneSetter(){

    }

    public Scene getCustomerScene(){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    public Scene getEmployeeScene(){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}