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
import java.util.ArrayList;

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

        Rectangle background = new Rectangle(350, 250);
        background.setFill(Color.WHITE);

        Text instructions = new Text("Please indicate any symptoms you are experiencing:");
        instructions.setFill(Color.MIDNIGHTBLUE);

        //setting up check boxes and list to contain them all
        ArrayList<CheckBox> emergencies = new ArrayList<>();

        CheckBox breathing = new CheckBox("Difficulty Breathing");
        emergencies.add(breathing);
        CheckBox pain = new CheckBox("Persistent Pain or Pressure in your Chest");
        emergencies.add(pain);
        CheckBox confusion = new CheckBox("New Confusion");
        emergencies.add(confusion);
        CheckBox wake = new CheckBox("Inability to Wake/Stay Awake");
        emergencies.add(wake);
        CheckBox blue = new CheckBox("Bluish Lips or Face");
        emergencies.add(blue);

        Button next = nextButton(emergencies);

        //adding bacjground for check boxes
        Group rows = checkBackground();

        //adding everything to the group
        display.getChildren().addAll(background, rows, instructions, breathing, pain, confusion, wake, blue, next);

        next.setLayoutY(260);
        next.setLayoutX(135);
        next.setPrefWidth(80);

        rows.setLayoutY(70);
        rows.setLayoutX(35);

        int spacing = 75;

        for (int i = 0; i < emergencies.size(); i++) {
            emergencies.get(i).setLayoutX(40);
            emergencies.get(i).setLayoutY(spacing);
            spacing += 30;
        }

        instructions.setLayoutX(25);
        instructions.setLayoutY(40);

        Scene emer = new Scene (display, 350, 300);

        return emer;
    } //emergency

    private Group checkBackground() {

        Group bg = new Group();

        int spacing = 0;

        for (int i = 0; i < 5; i++) {
            Rectangle behind = new Rectangle(280, 30);
            if (i % 2 == 0) {
                behind.setFill(Color.LIGHTSTEELBLUE);
            } else {
                behind.setFill(Color.LIGHTGREY);
            }

            bg.getChildren().add(behind);
            behind.setLayoutY(spacing);
            spacing += 30;

        }

        return bg;
    } //checkBackground

    private Button nextButton(ArrayList<CheckBox> symptoms) {
        Button next = new Button ("Next Page");

        EventHandler<ActionEvent> checkResults = (e) -> {
            for (int i = 0; i < symptoms.size(); i++) {
                if (symptoms.get(i).isSelected()) {
                    overall = issues.EMER;
                }
            }

            if (overall == issues.EMER) {
                System.out.println("yikes srry");

            }

        };

        next.setOnAction(checkResults);

        return next;
        //swap scene
    } //nextButton







} //CovidApp
