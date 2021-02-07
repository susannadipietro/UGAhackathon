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
    private Image image = new Image("file:resources/fine.png");
    private ImageView showResult = new ImageView(image);

    /** @inheritDoc */
    @Override
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

    /**
     * This method will build and return the welcome menu for the covid checker/
     * @param stage the {@code Stage} that will be used to display the scene.
     * @returns A scene containing a welcome message and start button to begin checking COVID symptoms.
     */
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
            Scene nextScene = emergency(stage);
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

    /**
     * This method will create a Scene which, when displayed, will ask the user to identify emergency COVID symptoms.
     * @param stage the Stage to display the next Scene on.
     * @returns a Scene containing several check boxes to identify urgent COVID symptoms.
     */
    private Scene emergency(Stage stage) {

        Group display = new Group();

        //setting uo the background
        Rectangle background = new Rectangle(350, 250);
        background.setFill(Color.WHITE);

        //setting up instruction text
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

        Button next = nextButton(emergencies, issues.EMER, stage, exposure(stage));

        //adding background for check boxes
        Group rows = checkBackground(5);

        //adding everything to the group
        display.getChildren().addAll(background, rows, instructions, breathing, pain, confusion, wake, blue, next);

        //setting layout within the group
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

        //creating and returning the scene
        Scene emer = new Scene (display, 350, 300);

        return emer;
    } //emergency

    private Scene exposure (Stage stage) {
        System.out.println("exposure made");

        Group display = new Group();

        //intro text
        Text intro = new Text("Thanks! Next, lets check for potential exposures.");
        intro.setFill(Color.MIDNIGHTBLUE);

        //image with guidelines
        Image guide = new Image("file:resources/contact.png");
        ImageView viewGuide = new ImageView(guide);
        viewGuide.setFitWidth(350);
        viewGuide.setPreserveRatio(true);

        //image with exposures
        Image options = new Image("file:resources/options.png");
        ImageView viewOps = new ImageView(options);
        viewOps.setFitWidth(350);
        viewOps.setPreserveRatio(true);

        ArrayList<CheckBox> exposures = new ArrayList<CheckBox>();

        CheckBox asymp = new CheckBox();
        CheckBox symp = new CheckBox();

        exposures.add(symp);
        exposures.add(asymp);

        Button next = nextButton(exposures, issues.EXPO, stage, symptoms(stage));
        Rectangle bg = new Rectangle(350, 300);
        bg.setFill(Color.WHITE);

        display.getChildren().addAll(bg, viewGuide, viewOps, intro, symp, asymp, next);

        intro.setLayoutY(20);
        intro.setLayoutX(43);

        viewOps.setLayoutY(20);
        viewGuide.setLayoutY(180);

        symp.setLayoutY(160);
        symp.setLayoutX(75);

        asymp.setLayoutY(160);
        asymp.setLayoutX(250);


        next.setLayoutY(260);
        next.setLayoutX(135);
        next.setPrefWidth(80);

        Scene expo = new Scene (display, 350, 300);

        return expo;
    } //exposure

    private Scene symptoms(Stage stage) {
        System.out.println("symptoms made");

        Group display = new Group();

        Rectangle background = new Rectangle(350, 250);
        background.setFill(Color.WHITE);

        Text instructions = new Text("One last step!\nPlease mark  any other symptoms you currently have:");
        instructions.setFill(Color.MIDNIGHTBLUE);
        instructions.setTextAlignment(TextAlignment.CENTER);

        //setting up check boxes and list to contain them all
        ArrayList<CheckBox> symptoms = new ArrayList<>();

        CheckBox fever = new CheckBox("Fever or chills");
        symptoms.add(fever);
        CheckBox resp = new CheckBox("Shortness of breath or difficulty breathing");
        symptoms.add(resp);
        CheckBox body = new CheckBox("Fatigue, headache, muscle or body aches");
        symptoms.add(body);
        CheckBox loss = new CheckBox("New loss of taste or smell");
        symptoms.add(loss);
        CheckBox cold = new CheckBox("Sore throat, congestion, cough, or runny nose");
        symptoms.add(cold);
        CheckBox gi = new CheckBox("Nausea, vomiting, or diarrhea");
        symptoms.add(gi);

        Button next = resultButton(symptoms, issues.SYMPT, stage, results(stage));

        //adding background for check boxes
        Group rows = checkBackground(6);

        //adding everything to the group
        display.getChildren().addAll(background, rows, instructions, fever, resp, body, loss, cold, gi, next);

        next.setLayoutY(260);
        next.setLayoutX(135);
        next.setPrefWidth(80);

        rows.setLayoutY(60);
        rows.setLayoutX(35);

        int spacing = 65;

        for (int i = 0; i < symptoms.size(); i++) {
            symptoms.get(i).setLayoutX(40);
            symptoms.get(i).setLayoutY(spacing);
            spacing += 30;
        }

        instructions.setLayoutX(35);
        instructions.setLayoutY(20);

        Scene sympt = new Scene (display, 350, 300);

        return sympt;
    } //symptoms


    private Scene results(Stage stage) {
        System.out.println("results made");

        Group display = new Group();

        //Image image;

        System.out.println("overall: " + overall);

        //ImageView view = new ImageView(image);
        showResult.setFitWidth(350);
        showResult.setPreserveRatio(true);

        display.getChildren().add(showResult);

        Scene result = new Scene(display, 350, 500);
        return result;
    }


    private Group checkBackground(int size) {

        Group bg = new Group();

        int spacing = 0;

        for (int i = 0; i < size; i++) {
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

    private Button resultButton(ArrayList<CheckBox> symptoms, issues category, Stage stage, Scene nextScene) {
        Button next = new Button ("Show Results!");

        EventHandler<ActionEvent> checkResults = (e) -> {
            for (int i = 0; i < symptoms.size(); i++) {
                if (symptoms.get(i).isSelected()) {

                    //if else for what's already selected
                    if (overall == category) {
                        break; //if they match - skip
                    } else if (overall == issues.EMER) {
                        break; //if its an emergency - do not change
                    } else if (overall == issues.EXPO) {
                        overall = issues.BOTH; //if they dont match, and they have been exposed, must be both
                    } else {
                        overall = category; //if none of these apply - set the overall variable to the category
                    }

                }

            }

            Image altImage;

            switch(overall) {
            case EMER:
               System.out.println("emergency");
               altImage = new Image("file:resources/emergency.png");
               showResult.setImage(altImage);
               break;
            case EXPO:
                altImage = new Image("file:resources/exposed.png");
                showResult.setImage(altImage);
                break;
            case SYMPT:
                altImage = new Image("file:resources/symptomatic.png");
                showResult.setImage(altImage);
                break;
            case BOTH:
                System.out.println("both switch");
                altImage = new Image("file:resources/both.png");
                showResult.setImage(altImage);
                break;
             default:
                System.out.println("default");
                altImage = new Image("file:resources/fine.png");
                showResult.setImage(altImage);
                break;
            }//switch


            System.out.println("categor: " + overall);

            stage.setScene(nextScene);
            stage.sizeToScene();
        };

        next.setOnAction(checkResults);

        return next;
    }

    private Button nextButton(ArrayList<CheckBox> symptoms, issues category, Stage stage, Scene nextScene) {
        Button next = new Button ("Next Page");

        EventHandler<ActionEvent> checkResults = (e) -> {
            for (int i = 0; i < symptoms.size(); i++) {
                if (symptoms.get(i).isSelected()) {

                    //if else for what's already selected
                    if (overall == category) {
                        break; //if they match - skip
                    } else if (overall == issues.EMER) {
                        break; //if its an emergency - do not change
                    } else if (overall == issues.EXPO) {
                        overall = issues.BOTH; //if they dont match, and they have been exposed, must be both
                    } else {
                        overall = category; //if none of these apply - set the overall variable to the category
                    }

                }
            }

            System.out.println("categor: " + overall);

            stage.setScene(nextScene);
            stage.sizeToScene();
        };

        next.setOnAction(checkResults);

        return next;
    } //nextButton







} //CovidApp
