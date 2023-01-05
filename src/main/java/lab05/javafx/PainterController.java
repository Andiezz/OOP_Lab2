package lab05.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private ToggleGroup tool;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if (tool.getSelectedToggle() != null) {
          ToggleButton selectedButton = (ToggleButton) tool.getSelectedToggle();
          String buttonText = selectedButton.getText();
          if(buttonText.equals("Pen")){
              Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
              drawingAreaPane.getChildren().add(newCircle);
          }
          else if(buttonText.equals("Eraser")){
              Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.WHITE);
                drawingAreaPane.getChildren().add(newCircle);
          }
        }
    }

}

