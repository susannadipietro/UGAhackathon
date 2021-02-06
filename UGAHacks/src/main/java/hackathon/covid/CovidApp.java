package hackathon.covid;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

/**
 * Application subclass that will display COVID 19 information,
 * and allow users to identify their sympotoms and exposures.
 */

public class CovidApp extends Application {

    private enum issues { EMER, EXPO, SYMPT, BOTH, NONE };

    private issues overall = issues.NONE;

    public void start(Stage stage) {

        Group grp = new Group();
        Rectangle rec = new Rectangle(200, 200);

        grp.getChildren().addAll(rec);

        Scene scene = makeMenu(stage);

        stage.setTitle("COVID-19 symptom and exposure check");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    } //start

    private Scene makeMenu(Stage stage) {

        Group display = new Group();

        //text set up
        Text welcome = new Text("Welcome to the COVID-19 symptom and exposure checker!");

        Text explain = new Text("We will go through three pages of questions\n" +
        "  to help you decide what your next steps should be.");

        welcome.setFill(Color.MIDNIGHTBLUE);
        explain.setFill(Color.MIDNIGHTBLUE);
        explain.setTextAlignment(TextAlignment.CENTER);

        //image set up
        Image noThanksCovid = new Image("file:resources/nocovid.png");
        ImageView imgView = new ImageView(noThanksCovid);
        imgView.setFitWidth(200);
        imgView.setPreserveRatio(true);
        imgView.setLayoutX(100);
        imgView.setLayoutY(100);


        //button set up
        Button start = new Button("Start");
        start.setPrefWidth(80);

        EventHandler<ActionEvent> nextScreen = (e) -> {
            Scene nextScene = emergency();
            stage.setScene(nextScene);
            stage.sizeToScene();
        }; //handler

        start.setOnAction(nextScreen);

        //background set up
        Rectangle background = new Rectangle(400, 300);
        background.setFill(Color.WHITE);

        display.getChildren().addAll(background, welcome, explain, imgView, start);

        //setting layout
        welcome.setLayoutX(35);
        welcome.setLayoutY(40);

        explain.setLayoutX(68);
        explain.setLayoutY(70);

        start.setLayoutX(160);
        start.setLayoutY(300);

        //creating the scene
        Scene intro = new Scene (display, 400, 350);

        return intro;
    } //makeMenu

    private Scene emergency() {

        Group display = new Group();

        Rectangle background = new Rectangle(400, 500);
        background.setFill(Color.WHITE);

        CheckBox breathing = new CheckBox("Are you having difficulty breathing?");

        display.getChildren().addAll(background, breathing);

        Scene emer = new Scene (display, 400, 500);

        return emer;
    } //emergency





} //CovidApp
