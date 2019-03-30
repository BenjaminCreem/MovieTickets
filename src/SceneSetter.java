import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;


public class SceneSetter {

    Window window;

    public SceneSetter(Window w){
        window = w;
    }

    public Scene getLoginScene(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(-75.0); //Make everything tighter
        Label loginLabel = new Label("Login");
        mainLayout.getChildren().add(loginLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100 ,100)); //Border to window
        grid.setVgap(10); //Set vertical spacing
        grid.setHgap(10); //Set horizontal spacing
        grid.setAlignment(Pos.CENTER);

        //Login stuff
        //Email Label
        Label emailLogin = new Label("Email: ");
        GridPane.setConstraints(emailLogin, 0, 0);
        //Email Input
        TextField emailInputLogin = new TextField();
        emailInputLogin.setPromptText("email");
        GridPane.setConstraints(emailInputLogin, 1, 0);
        //Pass Label
        Label passLogin = new Label("Password: ");
        GridPane.setConstraints(emailLogin, 0, 1);
        //Password Input
        PasswordField passInputLogin = new PasswordField();
        passInputLogin.setPromptText("password");
        GridPane.setConstraints(passInputLogin, 1, 1);
        //Login Button - Need to set onclick listener to verify from account manager and proceed to next screen
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");
        GridPane.setConstraints(loginButton, 1, 2);

        //Add all to grid
        grid.getChildren().addAll(emailLogin, emailInputLogin, passLogin, passInputLogin, loginButton);

        //Add Login Grid to VBox
        mainLayout.getChildren().add(grid);

        //Sign up Stuff
        Label signupLabel = new Label("Sign up");
        mainLayout.getChildren().add(signupLabel);

        Scene scene = new Scene(mainLayout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    //public Scene getTheaterScene(){
    //}
}
