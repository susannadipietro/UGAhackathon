package hackathon.covid;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * Application subclass that will display COVID 19 information,
 * and allow users to identify their sympotoms and exposures.
 */

public class CovidApp extends Application {

    public void start(Stage stage) {

        Group grp = new Group();
        Rectangle rec = new Rectangle(200, 200);

        grp.getChildren().addAll(rec);

        Scene scene = new Scene(grp, 640, 480);

        stage.setTitle("COVID-19 symptom and exposure check");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    } //start

} //CovidApp
