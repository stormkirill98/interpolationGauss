import controllers.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Interpolation;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = loadFXML("primary");

        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        scene.getStylesheets().add("style.css");
        stage.show();

        PrimaryController primaryController = fxmlLoader.getController();
        primaryController.setStage(stage);
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource("/views/" + fxml + ".fxml"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
