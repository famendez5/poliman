import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PolimanApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setTitle("PoliMan");
        stage.setScene(scene);

        PolimanGame game = new PolimanGame();
        game.getPoliman().setVelocity(20);
        game.getPoliman().setReleaseOnUpdate(true);
        game.enableDebug(true);

        scene.setOnKeyPressed(event -> game.onKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> game.onKeyReleased(event.getCode()));

        game.setup();
        root.getChildren().add(game.getCanvas());

        game.start();

        stage.setResizable(false);
        stage.show();
    }

}