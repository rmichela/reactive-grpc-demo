package demo.backpressure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

public class BackpressureController {

    @FXML
    public LineChart lineChart;
    @FXML
    public Label producedLabel;
    @FXML
    public Label consumedLabel;

    @FXML
    public void startBackpressure(ActionEvent actionEvent) {

    }
}
