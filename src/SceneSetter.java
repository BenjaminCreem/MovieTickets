import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;


public class SceneSetter {

    private Stage window;

    public SceneSetter(Stage w){
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
        GridPane.setConstraints(passLogin, 0, 1);
        //Password Input
        PasswordField passInputLogin = new PasswordField();
        passInputLogin.setPromptText("password");
        GridPane.setConstraints(passInputLogin, 1, 1);
        //Login Button - Need to set onclick listener to verify from account manager and proceed to next screen
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");
        //loginButton.setOnAction(e -> window.setScene()); Need to add checks to this
        GridPane.setConstraints(loginButton, 1, 2);

        //Add all to grid
        grid.getChildren().addAll(emailLogin, emailInputLogin, passLogin, passInputLogin, loginButton);

        //Add Login Grid to VBox
        mainLayout.getChildren().add(grid);

        //Sign up Stuff
        Label signupLabel = new Label("Sign up");
        mainLayout.getChildren().add(signupLabel);

        //Signup Grid
        GridPane signup = new GridPane();
        signup.setPadding(new Insets(100, 100, 100 ,100)); //Border to window
        signup.setVgap(10); //Set vertical spacing
        signup.setHgap(10); //Set horizontal spacing
        signup.setAlignment(Pos.CENTER);


        //Signup email
        Label emailSignup = new Label("Email: ");
        GridPane.setConstraints(emailSignup, 0, 0);
        //Email input
        TextField emailInputSignup = new TextField();
        emailInputSignup.setPromptText("email");
        GridPane.setConstraints(emailInputSignup, 1, 0);

        //Signup Password
        Label passwordSignup = new Label("Password:");
        GridPane.setConstraints(passwordSignup, 0, 1);
        //Signup password input
        PasswordField passwordSignupInput = new PasswordField();
        passwordSignupInput.setPromptText("password");
        GridPane.setConstraints(passwordSignupInput, 1, 1);

        //Signup confirmPassword
        Label confPassSignup = new Label("Confirm Password:");
        GridPane.setConstraints(confPassSignup, 0, 2);
        //Signup confirmPassword input
        PasswordField confPassInput = new PasswordField();
        confPassInput.setPromptText("confirm password");
        GridPane.setConstraints(confPassInput, 1, 2);

        //Credit card num input
        Label creditCardNum = new Label("Credit Card Number: ");
        GridPane.setConstraints(creditCardNum, 0 ,3);
        //Credit card num input
        TextField creditInput = new TextField();
        creditInput.setPromptText("credit card number");
        GridPane.setConstraints(creditInput, 1, 3);

        //Credit card exp date
        Label creditCardDate = new Label("Credit Card Exp Date: ");
        GridPane.setConstraints(creditCardDate, 0, 4);
        //Credit card exp date input
        DatePicker dp = new DatePicker();
        GridPane.setConstraints(dp, 1, 4);

        //Credit card security code
        Label secCode = new Label("Security Code: ");
        GridPane.setConstraints(secCode, 0, 5);
        //Credit card security code input
        TextField secCodeInput = new TextField();
        secCodeInput.setPromptText("security code");
        GridPane.setConstraints(secCodeInput, 1, 5);

        //Credit card zip code
        Label zipCode = new Label("Zip Code: ");
        GridPane.setConstraints(zipCode, 0, 6);
        //Zip code input
        TextField zipCodeInput = new TextField();
        zipCodeInput.setPromptText("zip code");
        GridPane.setConstraints(zipCodeInput, 1, 6);

        //Add all to signup grid
        signup.getChildren().addAll(emailSignup, emailInputSignup, passwordSignup, passwordSignupInput, confPassSignup, confPassInput,
                creditCardNum, creditInput, creditCardDate, dp, secCode, secCodeInput, zipCode, zipCodeInput);

        //Add Signup grid to VBox
        mainLayout.getChildren().add(signup);


        Scene scene = new Scene(mainLayout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    //public Scene getTheaterScene(){
    //}
}
