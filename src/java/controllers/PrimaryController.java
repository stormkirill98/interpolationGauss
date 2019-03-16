package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PrimaryController {
    public Pane chartPane;

    public HBox function;
    public TextField a = new TextField("1");
    public TextField b = new TextField("1");
    public TextField c = new TextField("1");

    public TextField xBegin = new TextField("-2");
    public TextField xEnd = new TextField("3");

    public TextField n;


    private Charts charts;

    @FXML
    public void initialize(){
        charts = new Charts(chartPane);
    }

    public void buildGraph(ActionEvent event) {
        charts.buildFunction();
        //charts.buildPolynomial();

    }
}
