package modell;

import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Színes szöveg kiíratásért felelős osztály
 */
public class TextFlowListCell extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            Text text = new Text(item);

            // Example condition to set different colors, you can customize it
            if (item.contains("gyakoroljon többet vele!")) {
                text.setStyle("-fx-fill: red;");
            }
              else if (item.contains("kiváló")) {
                    text.setStyle("-fx-fill: green;");

            } else {
                text.setStyle("-fx-fill: black;");
            }

            TextFlow textFlow = new TextFlow(text);
            setGraphic(textFlow);
        }
    }
}
