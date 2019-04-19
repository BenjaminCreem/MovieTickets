import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SceneSetter {

    private Stage window;
    private User loggedInUser;
    private String customerOrEmployee;

    AccountManager am;
    TheaterManager tm;
    PaymentManager pm;


    //Used for screen where user inputs their seats
    private int numberSeatsSelected;

    public SceneSetter(Stage w){
        window = w;
        numberSeatsSelected = 0;
        am = new AccountManager();
        tm = new TheaterManager();
        pm = new PaymentManager();
    }

    public Scene firstScene(){
        Scene customerScene = getLoginScene("customer");
        Scene employeeScene = getLoginScene("employee");

        //Info label
        Label info = new Label("Welcome to Movie Tickets!");

        //Login Buttons
        Button cusLogin = new Button();
        cusLogin.setText("Customer Login");
        cusLogin.getStyleClass().add("button");
        cusLogin.setId("cusLogin");
        cusLogin.setOnAction(e -> window.setScene(customerScene)); //Lambda expression


        Button empLogin = new Button();
        empLogin.setText("Employee Login");
        empLogin.getStyleClass().add("button");
        empLogin.setId("empLogin");
        empLogin.setOnAction(e -> window.setScene(employeeScene));


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(info, cusLogin, empLogin);
        Scene scene = new Scene(layout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    public Scene getLoginScene(String cusOrEmp){
        customerOrEmployee = cusOrEmp;
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(-50.0); //Make everything tighter


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
        GridPane.setConstraints(loginButton, 1, 2);

        //Add all to grid
        grid.getChildren().addAll(emailLogin, emailInputLogin, passLogin, passInputLogin, loginButton);

        //Add Login Grid to VBox
        mainLayout.getChildren().add(grid);

        //Sign up Stuff - ONLY IF USER IS A CUSTOMER
        if(customerOrEmployee.equals("customer")) {
            loginButton.setOnAction(e -> loginClickedCustomer(emailInputLogin, passInputLogin));
            Label signupLabel = new Label("Sign up");
            mainLayout.getChildren().add(signupLabel);

            //Signup Grid
            GridPane signup = new GridPane();
            signup.setPadding(new Insets(100, 100, 100, 100)); //Border to window
            signup.setVgap(10); //Set vertical spacing
            signup.setHgap(10); //Set horizontal spacing
            signup.setAlignment(Pos.CENTER);

            //Signup name
            Label nameSignup = new Label("Name: ");
            GridPane.setConstraints(nameSignup, 0, 0);
            //Name input
            TextField nameInputSignup = new TextField();
            nameInputSignup.setPromptText("name");
            GridPane.setConstraints(nameInputSignup, 1, 0);

            //Signup email
            Label emailSignup = new Label("Email: ");
            GridPane.setConstraints(emailSignup, 0, 1);
            //Email input
            TextField emailInputSignup = new TextField();
            emailInputSignup.setPromptText("email");
            GridPane.setConstraints(emailInputSignup, 1, 1);

            //Signup Password
            Label passwordSignup = new Label("Password:");
            GridPane.setConstraints(passwordSignup, 0, 2);
            //Signup password input
            PasswordField passwordSignupInput = new PasswordField();
            passwordSignupInput.setPromptText("password");
            GridPane.setConstraints(passwordSignupInput, 1, 2);

            //Signup confirmPassword
            Label confPassSignup = new Label("Confirm Password:");
            GridPane.setConstraints(confPassSignup, 0, 3);
            //Signup confirmPassword input
            PasswordField confPassInput = new PasswordField();
            confPassInput.setPromptText("confirm password");
            GridPane.setConstraints(confPassInput, 1, 3);

            //Credit card num input
            Label creditCardNum = new Label("Credit Card Number: ");
            GridPane.setConstraints(creditCardNum, 0, 4);
            //Credit card num input
            TextField creditInput = new TextField();
            creditInput.setPromptText("credit card number");
            GridPane.setConstraints(creditInput, 1, 4);

            //Credit card exp date
            Label creditCardDate = new Label("Credit Card Exp Date: ");
            GridPane.setConstraints(creditCardDate, 0, 5);
            //Credit card exp date input
            DatePicker dp = new DatePicker();
            GridPane.setConstraints(dp, 1, 5);

            //Credit card security code
            Label secCode = new Label("Security Code: ");
            GridPane.setConstraints(secCode, 0, 6);
            //Credit card security code input
            TextField secCodeInput = new TextField();
            secCodeInput.setPromptText("security code");
            GridPane.setConstraints(secCodeInput, 1, 6);

            //Credit card zip code
            Label zipCode = new Label("Zip Code: ");
            GridPane.setConstraints(zipCode, 0, 7);
            //Zip code input
            TextField zipCodeInput = new TextField();
            zipCodeInput.setPromptText("zip code");
            GridPane.setConstraints(zipCodeInput, 1, 7);

            //Add all to signup grid
            signup.getChildren().addAll(nameSignup, nameInputSignup, emailSignup, emailInputSignup, passwordSignup, passwordSignupInput, confPassSignup, confPassInput,
                    creditCardNum, creditInput, creditCardDate, dp, secCode, secCodeInput, zipCode, zipCodeInput);

            //Signup button
            Button signupButton = new Button("Sign up");
            signupButton.getStyleClass().add("button");
            signupButton.setOnAction(e -> signupClicked(nameInputSignup, emailInputSignup, passwordSignupInput, confPassInput, creditInput, dp, secCodeInput, zipCodeInput));
            GridPane.setConstraints(signupButton, 1, 8);
            signup.getChildren().add(signupButton);

            //Add Signup grid to VBox
            mainLayout.getChildren().add(signup);
        }
        else
        {
            loginButton.setOnAction(e -> loginClickedEmployee(emailInputLogin, passInputLogin));
        }
        //Add back button
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> window.setScene(firstScene()));
        mainLayout.getChildren().add(backButton);


        Scene scene = new Scene(mainLayout, 1280, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    private void loginClickedCustomer(TextField email, TextField password){
        if(am.customerSignin(email.getText(), password.getText())){ //Correct username and password
            //Go to movie selection screen
            loggedInUser = am.getPaymentInfo(email.getText());
            window.setScene(getTheaterScene());
        }
    }

    private void loginClickedEmployee(TextField email, TextField password){
        if(am.employeeSignin(email.getText(), password.getText())){ //Correct username and password
            //Go to movie selection screen
            loggedInUser = am.getEmployee(email.getText());
            window.setScene(getTheaterScene());
        }
    }


    private void signupClicked(TextField name, TextField email, PasswordField pass, PasswordField confPass, TextField creditNum,
                               DatePicker dp, TextField secCode, TextField zipCode)
    {
        //Convert LocalDate object from dp to Date object because createCustomerAccount inside of AccountManager needs a Date object
        LocalDate ld = dp.getValue();
        java.sql.Date sqlDate = java.sql.Date.valueOf(ld.toString());
        if(am.createCustomerAccount(name.getText(), email.getText(), pass.getText(), confPass.getText(), creditNum.getText(),
                sqlDate, secCode.getText(), zipCode.getText())){
            //Signup successful, go to movie selection screen
            window.setScene(getTheaterScene());
        }
        else
        {
            System.out.println("Error creating account");
        }
    }


    public Scene getTheaterScene(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        Label loginLabel = new Label("All Movies For Today");
        mainLayout.getChildren().add(loginLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100 ,100)); //Border to window
        grid.setVgap(10); //Set vertical spacing
        grid.setHgap(10); //Set horizontal spacing
        grid.setAlignment(Pos.CENTER);

        ArrayList<String> movies = tm.getMovieNames();
        for(int i = 0; i < movies.size(); i++){
            Label movieNameLabel = new Label(movies.get(i));
            GridPane.setConstraints(movieNameLabel, 0, i);
            grid.getChildren().add(movieNameLabel);

            ArrayList<Showing> showings = tm.getShowings(movies.get(i));
            for(int j = 0; j < showings.size(); j++){
                final int movieID = j;

                UtilityMethods um = new UtilityMethods();
                Hyperlink theaterLink = new Hyperlink(um.formatTime(showings.get(movieID).showtime));
                theaterLink.setOnAction(e -> window.setScene(getSeatSelectionScreen(showings.get(movieID).id)));

                GridPane.setConstraints(theaterLink, j+1, i);
                grid.getChildren().add(theaterLink);
            }
        }


        mainLayout.getChildren().add(grid);

        //Bottom nav bar for buttons. Only has 1 for customer but has multiple for manager or employee
        HBox bottomNavBar = new HBox();
        bottomNavBar.setAlignment(Pos.CENTER);
        bottomNavBar.setSpacing(10.0);
        Button backButton = new Button("Logout");
        backButton.setOnAction(e -> window.setScene(firstScene()));
        bottomNavBar.getChildren().add(backButton);
        mainLayout.getChildren().add(bottomNavBar);

        //Additional Stuff if user is employee
        if(loggedInUser instanceof Employee){
            Employee emp = (Employee) loggedInUser;
            Button snackPurchaseButton = new Button("Snack Purchases");
            snackPurchaseButton.setOnAction(e -> window.setScene(snackPurchaseScene()));
            bottomNavBar.getChildren().add(snackPurchaseButton);
            //Additional Stuff if user is manager
            if(emp.isManager()){
                Button addEmployeeButton = new Button("Add Employee");
                addEmployeeButton.setOnAction(e -> window.setScene(addEmployeeScene()));
                Button revenueButton = new Button("View Revenue Data");
                revenueButton.setOnAction(e -> window.setScene(revenueScene()));
                Button movieManagerButton = new Button("Movie Manager");
                movieManagerButton.setOnAction(e -> window.setScene(movieManager()));
                bottomNavBar.getChildren().addAll(addEmployeeButton, revenueButton, movieManagerButton);
            }
        }


        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    public Scene movieManager(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(10.0);
        HBox moviesVsAddMovieTitles = new HBox();
        moviesVsAddMovieTitles.setSpacing(300.0);
        moviesVsAddMovieTitles.setAlignment(Pos.CENTER);


        Label addMovie = new Label("Add Movie");
        moviesVsAddMovieTitles.getChildren().add(addMovie);
        Label removeMovies = new Label("Remove Movies");
        moviesVsAddMovieTitles.getChildren().add(removeMovies);
        mainLayout.getChildren().add(moviesVsAddMovieTitles);

        HBox actualForms = new HBox();
        actualForms.setSpacing(100.0);
        actualForms.setAlignment(Pos.CENTER);

        GridPane addMovieGrid = new GridPane();
        addMovieGrid.setAlignment(Pos.CENTER);
        addMovieGrid.setHgap(10.0);
        addMovieGrid.setVgap(10.0);

        //Movie name
        Label movieNameLabel = new Label("Movie Name: ");
        GridPane.setConstraints(movieNameLabel, 0, 0);
        TextField movieName = new TextField();
        movieName.setPromptText("movie name");
        GridPane.setConstraints(movieName, 1, 0);

        //Theater Number
        Label theaterNumber = new Label("Theater Number");
        GridPane.setConstraints(theaterNumber, 0, 1);
        ObservableList<Integer> theaterNumbers = FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );
        final ComboBox theaterNumberDropDown = new ComboBox(theaterNumbers);
        theaterNumberDropDown.getSelectionModel().selectFirst();
        theaterNumberDropDown.setPrefWidth(175.0);
        GridPane.setConstraints(theaterNumberDropDown, 1, 1);

        //Time
        Label timeLabel = new Label("Time: ");
        GridPane.setConstraints(timeLabel, 0, 2);
        TextField time = new TextField();
        time.setPromptText("24hr time (1230)");
        GridPane.setConstraints(time, 1, 2);

        //Button to add movie
        Button addMovieBtn = new Button("Add Movie");
        GridPane.setConstraints(addMovieBtn, 0, 3);
        addMovieBtn.setOnAction(e -> addMovieBtnClicked(movieName.getText(), (int)theaterNumberDropDown.getValue(), time.getText()));

        addMovieGrid.getChildren().addAll(movieNameLabel, movieName, theaterNumber, theaterNumberDropDown, timeLabel, time, addMovieBtn);

        //Remove showing grid
        GridPane removeShowingGrid = new GridPane();
        removeMovies.setAlignment(Pos.CENTER);
        removeShowingGrid.setVgap(10.0);
        removeShowingGrid.setHgap(10.0);
        ArrayList<String> movies = tm.getMovieNames();
        for(int i = 0; i < movies.size(); i++){
            Label movieNameLabelRemove = new Label(movies.get(i));
            GridPane.setConstraints(movieNameLabelRemove, 0, i);
            removeShowingGrid.getChildren().add(movieNameLabelRemove);

            ArrayList<Showing> showings = tm.getShowings(movies.get(i));
            for(int j = 0; j < showings.size(); j++){
                final int movieID = j;

                UtilityMethods um = new UtilityMethods();
                Hyperlink theaterLink = new Hyperlink(um.formatTime(showings.get(movieID).showtime));
                theaterLink.setOnAction(e -> movieRemovedClicked(showings.get(movieID).id));

                GridPane.setConstraints(theaterLink, j+1, i);
                removeShowingGrid.getChildren().add(theaterLink);
            }
        }

        actualForms.getChildren().addAll(addMovieGrid, removeShowingGrid);
        mainLayout.getChildren().add(actualForms);

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(getTheaterScene()));
        mainLayout.getChildren().add(back);


        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    private void movieRemovedClicked(int id){
        tm.removeMovie(id);
        window.setScene(movieManager());
    }

    private void addMovieBtnClicked(String movieName, int theaterNumber, String time){
        if(tm.insertIntoMovies(movieName, theaterNumber, time)){
            window.setScene(movieManager());
        }
    }

    public Scene snackPurchaseScene(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(0.0);

        Label mainLabel = new Label("Purchase Snacks");
        mainLayout.getChildren().add(mainLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100, 100)); //Border to window
        grid.setVgap(10); //Set vertical spacing
        grid.setHgap(10); //Set horizontal spacing
        grid.setAlignment(Pos.CENTER);

        //Popcorn
        Label popcornLabel = new Label("Popcorn: ");
        GridPane.setConstraints(popcornLabel, 0, 0);
        grid.getChildren().add(popcornLabel);
        //Popcorn - drop down
        ObservableList<Integer> options = FXCollections.observableArrayList(
                0, 1, 2, 3, 4, 5, 6, 7, 8
        );
        final ComboBox popcornDropDown = new ComboBox(options);
        popcornDropDown.getSelectionModel().selectFirst();
        popcornDropDown.setPrefWidth(175.0);
        GridPane.setConstraints(popcornDropDown, 1, 0);
        grid.getChildren().add(popcornDropDown);

        //Drinks
        Label drinkLabel = new Label("Drink(s): ");
        GridPane.setConstraints(drinkLabel, 0, 1);
        grid.getChildren().add(drinkLabel);

        final ComboBox drinkDropDown = new ComboBox(options);
        drinkDropDown.getSelectionModel().selectFirst();
        drinkDropDown.setPrefWidth(175.0);
        GridPane.setConstraints(drinkDropDown, 1, 1);
        grid.getChildren().add(drinkDropDown);

        //Candy
        Label candyLabel = new Label("Candy: ");
        GridPane.setConstraints(candyLabel, 0, 2);
        grid.getChildren().add(candyLabel);

        final ComboBox candyDropDown = new ComboBox(options);
        candyDropDown.getSelectionModel().selectFirst();
        candyDropDown.setPrefWidth(175.0);
        GridPane.setConstraints(candyDropDown, 1, 2);
        grid.getChildren().add(candyDropDown);

        //Name on Credit Card - label
        Label nameOnCardLabel = new Label("Name on Credit Card: ");
        GridPane.setConstraints(nameOnCardLabel, 0, 3);
        grid.getChildren().add(nameOnCardLabel);
        //Name on Credit Card - input
        TextField nameInput = new TextField();
        nameInput.setPromptText("name on credit card");
        nameInput.setText(loggedInUser.name());
        GridPane.setConstraints(nameInput, 1, 3);
        grid.getChildren().add(nameInput);

        //Credit Card Number - label
        Label creditNumLabel = new Label("Credit Card Number: ");
        GridPane.setConstraints(creditNumLabel, 0, 4);
        grid.getChildren().add(creditNumLabel);
        //Credit Card Number - input
        TextField cardNumberInput = new TextField();
        cardNumberInput.setPromptText("credit card number");
        cardNumberInput.setText(loggedInUser.creditNum());
        GridPane.setConstraints(cardNumberInput, 1, 4);
        grid.getChildren().add(cardNumberInput);

        //Credit Card Expiration Date - Label
        Label expDateLabel = new Label("Credit Card Expiration Date");
        GridPane.setConstraints(expDateLabel, 0, 5);
        grid.getChildren().add(expDateLabel);
        //Credit Card Expiration Date - input
        DatePicker dp = new DatePicker(loggedInUser.creditExpDate());
        GridPane.setConstraints(dp, 1 , 5);
        grid.getChildren().add(dp);

        //Credit Card Security Code
        Label securityCodeLabel = new Label("Credit Card Security Code");
        GridPane.setConstraints(securityCodeLabel, 0, 6);
        grid.getChildren().add(securityCodeLabel);
        //Credit Card Security Code - input
        TextField secCodeInput = new TextField();
        secCodeInput.setPromptText("security code");
        secCodeInput.setText(loggedInUser.secCode());
        GridPane.setConstraints(secCodeInput, 1, 6);
        grid.getChildren().add(secCodeInput);

        //Zip Code - label
        Label zipCodeLabel = new Label("Zip Code: ");
        GridPane.setConstraints(zipCodeLabel, 0, 7);
        grid.getChildren().add(zipCodeLabel);
        //Zip Code - input
        TextField zipCodeInput = new TextField();
        zipCodeInput.setPromptText("zip code");
        zipCodeInput.setText(loggedInUser.zipCode());
        GridPane.setConstraints(zipCodeInput, 1, 7);
        grid.getChildren().add(zipCodeInput);

        mainLayout.getChildren().add(grid);

        //Bottom navigation buttons
        HBox bottomButtons = new HBox();
        Button pay = new Button("Complete Purchase");
        Button back = new Button("Previous Screen");
        back.setOnAction(e -> window.setScene(getTheaterScene()));
        pay.setOnAction(e -> snackPaymentClicked(nameInput, cardNumberInput, dp, secCodeInput, zipCodeInput,
                popcornDropDown.getValue().toString(), drinkDropDown.getValue().toString(), candyDropDown.getValue().toString()));

        //If user is employee they can pay in cash
        if(loggedInUser instanceof Employee){
            Button cashButton = new Button("Payed in Cash");
            cashButton.setOnAction(e -> snackPaymentClickedCash(popcornDropDown.getValue().toString(), drinkDropDown.getValue().toString(), candyDropDown.getValue().toString()));
            bottomButtons.getChildren().add(cashButton);
        }

        bottomButtons.getChildren().addAll(pay, back);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(10.0);
        mainLayout.getChildren().add(bottomButtons);

        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    public Scene revenueScene(){
        VBox mainLayout = new VBox();
        mainLayout.setSpacing(10.0);
        mainLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(mainLayout, 1280, 720);
        Label titleOfPage = new Label("Revenue");
        mainLayout.getChildren().addAll(titleOfPage);

        mainLayout.getChildren().add(pm.getPaymentData());

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(getTheaterScene()));
        mainLayout.getChildren().addAll(back);


        return scene;
    }

    public Scene addEmployeeScene(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(0.0);

        Label mainLabel = new Label("Create Employee Account");
        mainLayout.getChildren().add(mainLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100, 100)); //Border to window
        grid.setVgap(10); //Set vertical spacing
        grid.setHgap(10); //Set horizontal spacing
        grid.setAlignment(Pos.CENTER);

        //Employee email
        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 0);
        TextField emailInput = new TextField();
        emailInput.setPromptText("employee email");
        GridPane.setConstraints(emailInput, 1, 0);
        grid.getChildren().addAll(emailLabel, emailInput);

        //Employee password
        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("employee password");
        GridPane.setConstraints(passwordInput, 1, 1);
        grid.getChildren().addAll(passwordLabel, passwordInput);

        //Employee confirm password
        Label confirmPassLabel = new Label("Confirm Password: ");
        GridPane.setConstraints(confirmPassLabel, 0, 2);
        PasswordField confirmPasswordInput = new PasswordField();
        confirmPasswordInput.setPromptText("confirm password");
        GridPane.setConstraints(confirmPasswordInput, 1, 2);
        grid.getChildren().addAll(confirmPassLabel, confirmPasswordInput);

        //Employee is manager toggle
        Label isManagerLabel = new Label("Manager: ");
        GridPane.setConstraints(isManagerLabel, 0, 3);
        CheckBox isManagerCheck = new CheckBox();
        isManagerCheck.setSelected(false);
        GridPane.setConstraints(isManagerCheck, 1, 3);
        grid.getChildren().addAll(isManagerLabel, isManagerCheck);

        mainLayout.getChildren().add(grid);

        //Back and add buttons
        HBox bottomNavBar = new HBox();
        bottomNavBar.setAlignment(Pos.CENTER);
        bottomNavBar.setSpacing(10.0);
        Button addEmployee = new Button("Add Employee");
        addEmployee.setOnAction(e -> addEmpButtonClicked(emailInput, passwordInput, confirmPasswordInput, isManagerCheck));
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(getTheaterScene()));
        bottomNavBar.getChildren().addAll(addEmployee, back);

        mainLayout.getChildren().add(bottomNavBar);


        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    public void addEmpButtonClicked(TextField email, TextField pass, TextField confPass, CheckBox manager){
        if(email.getText() != null && !email.getText().trim().isEmpty() && pass.getText() != null && !pass.getText().trim().isEmpty() && confPass.getText() != null && !confPass.getText().trim().isEmpty()) {
            if (am.createEmployeeAccount(email.getText(), pass.getText(), confPass.getText(), manager.isSelected())) {
                window.setScene(getTheaterScene());
            }
        }
    }

    public Scene getSeatSelectionScreen(int movieId){
        VBox mainLayout = new VBox();

        UtilityMethods um = new UtilityMethods();
        mainLayout.setAlignment(Pos.CENTER);
        Showing thisMovie = new Showing(movieId, tm);

        //Header
        Label movieName = new Label(thisMovie.movieName);
        Label startTime = new Label("Start Time: " + um.formatTime(thisMovie.showtime));
        Label theaterNumber = new Label("Theater: " + thisMovie.theaterNumber);
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setSpacing(500.00);
        header.getChildren().addAll(movieName, startTime, theaterNumber);
        mainLayout.getChildren().addAll(header);

        //This HBox is used for separating the user info and seat selection
        HBox splitScreen = new HBox();
        splitScreen.setSpacing(150.0);

        //Left side of screen - the credit card info and email and stuff
        GridPane userVals = new GridPane();
        userVals.setPadding(new Insets(100, 100, 100, 100));
        userVals.setVgap(10.0);
        userVals.setHgap(10.0);
        userVals.setAlignment(Pos.CENTER);

        //Number of seats - label
        Label numSeatSelectLabel = new Label("Select Number of Seats: ");
        GridPane.setConstraints(numSeatSelectLabel, 0, 0);
        userVals.getChildren().add(numSeatSelectLabel);
        //Number of seats - drop down
        ObservableList<Integer> options = FXCollections.observableArrayList(
            1, 2, 3, 4, 5, 6, 7, 8
        );
        final ComboBox numSeatsDropDown = new ComboBox(options);
        numSeatsDropDown.getSelectionModel().selectFirst();
        numSeatsDropDown.setPrefWidth(175.0);
        GridPane.setConstraints(numSeatsDropDown, 1, 0);
        userVals.getChildren().add(numSeatsDropDown);

        //Name on Credit Card - label
        Label nameOnCardLabel = new Label("Name on Credit Card: ");
        GridPane.setConstraints(nameOnCardLabel, 0, 1);
        userVals.getChildren().add(nameOnCardLabel);
        //Name on Credit Card - input
        TextField nameInput = new TextField();
        nameInput.setPromptText("name on credit card");
        nameInput.setText(loggedInUser.name());
        GridPane.setConstraints(nameInput, 1, 1);
        userVals.getChildren().add(nameInput);

        //Credit Card Number - label
        Label creditNumLabel = new Label("Credit Card Number: ");
        GridPane.setConstraints(creditNumLabel, 0, 2);
        userVals.getChildren().add(creditNumLabel);
        //Credit Card Number - input
        TextField cardNumberInput = new TextField();
        cardNumberInput.setPromptText("credit card number");
        cardNumberInput.setText(loggedInUser.creditNum());
        GridPane.setConstraints(cardNumberInput, 1, 2);
        userVals.getChildren().add(cardNumberInput);

        //Credit Card Expiration Date - Label
        Label expDateLabel = new Label("Credit Card Expiration Date");
        GridPane.setConstraints(expDateLabel, 0, 3);
        userVals.getChildren().add(expDateLabel);
        //Credit Card Expiration Date - input
        DatePicker dp = new DatePicker(loggedInUser.creditExpDate());
        GridPane.setConstraints(dp, 1 , 3);
        userVals.getChildren().add(dp);

        //Credit Card Security Code
        Label securityCodeLabel = new Label("Credit Card Security Code");
        GridPane.setConstraints(securityCodeLabel, 0, 4);
        userVals.getChildren().add(securityCodeLabel);
        //Credit Card Security Code - input
        TextField secCodeInput = new TextField();
        secCodeInput.setPromptText("security code");
        secCodeInput.setText(loggedInUser.secCode());
        GridPane.setConstraints(secCodeInput, 1, 4);
        userVals.getChildren().add(secCodeInput);

        //Zip Code - label
        Label zipCodeLabel = new Label("Zip Code: ");
        GridPane.setConstraints(zipCodeLabel, 0, 5);
        userVals.getChildren().add(zipCodeLabel);
        //Zip Code - input
        TextField zipCodeInput = new TextField();
        zipCodeInput.setPromptText("zip code");
        zipCodeInput.setText(loggedInUser.zipCode());
        GridPane.setConstraints(zipCodeInput, 1, 5);
        userVals.getChildren().add(zipCodeInput);

        //Email Label
        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 6);
        userVals.getChildren().add(emailLabel);
        //Email Input
        TextField emailInput = new TextField();
        emailInput.setPromptText("email");
        emailInput.setText(loggedInUser.email());
        GridPane.setConstraints(emailInput, 1, 6);
        userVals.getChildren().add(emailInput);

        //Add user input values to HBox
        splitScreen.getChildren().add(userVals);

        //Right side of screen - actual seat selection
        GridPane seatSelector = new GridPane();
        seatSelector.setPadding(new Insets(100, 100, 100, 100));
        seatSelector.setHgap(10);
        seatSelector.setVgap(10);

        Rectangle seatRectanges[] = new Rectangle[100];

        //Seat selection rectangles
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                int charLoc = i*10+j;
                seatRectanges[charLoc] = new Rectangle();
                seatRectanges[charLoc].setWidth(20);
                seatRectanges[charLoc].setHeight(20);

                if(thisMovie.seats.charAt(charLoc) == 'e'){
                    seatRectanges[charLoc].setFill(Color.GRAY);
                    seatRectanges[charLoc].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //Check if turning this seat green would go over selected seats #
                            if(numberSeatsSelected < (int)numSeatsDropDown.getValue() && !seatRectanges[charLoc].getFill().equals(Color.GREEN)){
                                seatRectanges[charLoc].setFill(Color.GREEN);
                                numberSeatsSelected++;
                            }else if(seatRectanges[charLoc].getFill().equals(Color.GREEN)){
                                seatRectanges[charLoc].setFill(Color.GRAY);
                                numberSeatsSelected--;
                            }
                            //If its red we do nothing, don't need a check
                        }
                    });
                }else if(thisMovie.seats.charAt(charLoc) == 'o'){ //Occupied
                    seatRectanges[charLoc].setFill(Color.RED);
                }
                GridPane.setConstraints(seatRectanges[charLoc], i, j);
                seatSelector.getChildren().add(seatRectanges[charLoc]);
            }
        }
        //Grid labels
        for(int i = 0; i < 10; i++){
            Label numLabel = new Label("" + (i + 1));
            GridPane.setConstraints(numLabel, 10, i);
            seatSelector.getChildren().add(numLabel);
            Label letterLabel = new Label("" + (char)(i + 65));
            GridPane.setConstraints(letterLabel, i, 10);
            seatSelector.getChildren().add(letterLabel);
        }

        numSeatsDropDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numberSeatsSelected = 0;
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        int charLoc = i * 10 + j;
                        if(thisMovie.seats.charAt(charLoc) == 'e'){
                            seatRectanges[charLoc].setFill(Color.GRAY);
                        }
                        else
                        {
                            seatRectanges[charLoc].setFill(Color.RED);
                        }
                    }
                }
            }
        });

        //Add seat selector to HBox
        splitScreen.getChildren().add(seatSelector);
        mainLayout.getChildren().addAll(splitScreen);

        //Bottom navigation buttons
        HBox bottomButtons = new HBox();
        Button pay = new Button("Complete Purchase");

        Button back = new Button("Previous Screen");
        back.setOnAction(e -> window.setScene(getTheaterScene()));
        pay.setOnAction(e -> paymentClicked(nameInput, cardNumberInput, dp, secCodeInput, zipCodeInput, emailInput, thisMovie, seatRectanges));
        bottomButtons.getChildren().addAll(pay);

        //If user is employee they can pay in cash
        if(loggedInUser instanceof Employee){
            Button cashButton = new Button("Payed in Cash");
            cashButton.setOnAction(e -> paymentClickedCash(seatRectanges, thisMovie));
            bottomButtons.getChildren().add(cashButton);
        }
        bottomButtons.getChildren().add(back);

        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(10.0);
        mainLayout.getChildren().add(bottomButtons);



        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    private void paymentClickedCash(Rectangle seats[], Showing showing){
        for(int i = 0; i < seats.length; i++){
            pm.updateRevenue(true, false, false, false);
            showing.reserveSeat(i);
        }
        window.setScene(paymentSuccessful());
    }

    public void paymentClicked(TextField name, TextField num, DatePicker date, TextField secCode, TextField zip, TextField email, Showing showing, Rectangle seats[]){
        //Make sure all fields are filed
        boolean successful = true;

        Border errorBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        if(name.getText() == null || name.getText().trim().isEmpty()){
            successful = false;
            name.setBorder(errorBorder);
        }
        if(num.getText() == null || num.getText().trim().isEmpty() || !pm.validateCard(num.getText())){
            successful = false;
            num.setBorder(errorBorder);
        }
        if(date.getValue() == null || date.getValue().isBefore(LocalDate.now())){
            successful = false;
            date.setBorder(errorBorder);
        }
        if(secCode.getText() == null || secCode.getText().trim().isEmpty()){
            successful = false;
            secCode.setBorder(errorBorder);
        }
        if(zip.getText() == null || zip.getText().trim().isEmpty()){
            successful = false;
            zip.setBorder(errorBorder);
        }
        if(email.getText() == null || email.getText().trim().isEmpty()){
            successful = false;
            email.setBorder(errorBorder);
        }
        if(successful){
            for(int i = 0; i < seats.length; i++){
                if(seats[i].getFill().equals(Color.GREEN)){
                    showing.reserveSeat(i);
                    pm.updateRevenue(true,false,false,false);       //every time a seat is changed to green, payment manager is also notified
                }
            }
            window.setScene(paymentSuccessful());
        }
    }


    public Scene paymentSuccessful(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        Label successful = new Label("Payment Successful!");
        mainLayout.getChildren().add(successful);
        Button movieTheaterScreen = new Button("Go back to viewing Showtimes");
        movieTheaterScreen.setOnAction(e -> window.setScene(getTheaterScene()));
        mainLayout.getChildren().add(movieTheaterScreen);
        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }

    //used in snack purchase screen
    public void snackPaymentClicked(TextField name, TextField num, DatePicker date, TextField secCode, TextField zip, String pop, String dr, String can){
        //Make sure all fields are filed
        boolean successful = true;

        Border errorBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        if(name.getText() == null || name.getText().trim().isEmpty()){
            successful = false;
            name.setBorder(errorBorder);
        }
        if(num.getText() == null || num.getText().trim().isEmpty() || !pm.validateCard(num.getText())){
            successful = false;
            num.setBorder(errorBorder);
        }
        if(date.getValue() == null || date.getValue().isBefore(LocalDate.now())){
            successful = false;
            date.setBorder(errorBorder);
        }
        if(secCode.getText() == null || secCode.getText().trim().isEmpty()){
            successful = false;
            secCode.setBorder(errorBorder);
        }
        if(zip.getText() == null || zip.getText().trim().isEmpty()){
            successful = false;
            zip.setBorder(errorBorder);
        }
        if(successful){
            int p = Integer.parseInt(pop);
            int d = Integer.parseInt(dr);
            int c = Integer.parseInt(can);

            for (int i = 0; i < p; i++){
                pm.updateRevenue(false, true, false, false);
            }
            for (int i = 0; i < d; i++){
                pm.updateRevenue(false, false, true, false);
            }
            for (int i = 0; i < c; i++){
                pm.updateRevenue(false, false, false, true);
            }

            window.setScene(snackPaymentSuccessful());
        }
    }

    private void snackPaymentClickedCash(String pop, String dr, String can){
        int p = Integer.parseInt(pop);
        int d = Integer.parseInt(dr);
        int c = Integer.parseInt(can);

        for (int i = 0; i < p; i++){
            pm.updateRevenue(false, true, false, false);
        }
        for (int i = 0; i < d; i++){
            pm.updateRevenue(false, false, true, false);
        }
        for (int i = 0; i < c; i++){
            pm.updateRevenue(false, false, false, true);
        }
        window.setScene(paymentSuccessful());
    }


    public Scene snackPaymentSuccessful(){
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        Label successful = new Label("Payment Successful!");
        mainLayout.getChildren().add(successful);
        Button snackScreen = new Button("Go back to snack purchase");
        snackScreen.setOnAction(e -> window.setScene(snackPurchaseScene()));
        mainLayout.getChildren().add(snackScreen);
        Scene scene = new Scene(mainLayout, 1280, 720);
        return scene;
    }


    public Color swapColor(Color current){
        if(current.equals(Color.GRAY)){
            return Color.GREEN;
        }
        else
        {
            return Color.GRAY;
        }
    }
}
