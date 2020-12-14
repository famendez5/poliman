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
<<<<<<< HEAD
=======
        game.getPoliman().setVelocity(20);
        game.getPoliman().setReleaseOnUpdate(true);
>>>>>>> 8a906aa08749664f810499885c9e4efd7d77caed
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