import database.GradeDatabaseDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Az UI kezelő osztrály
 */
public class StudentGradeApplication extends Application {
    @Getter
    private static GradeDatabaseDao dao;

    @Override
    public void start(Stage primaryStage) {
        try {
            dao = database.JdbiSingleton.setupDatabase();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentGradeUI.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tanuló értékelő program");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
