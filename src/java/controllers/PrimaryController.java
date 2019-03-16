package controllers;

import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PrimaryController {

    public LineChart chart;

    public HBox function;
    public TextField a = new TextField("1");
    public TextField b = new TextField("1");
    public TextField c = new TextField("1");

    public TextField xBegin = new TextField("-2");
    public TextField xEnd = new TextField("3");


    public TextField n;
}
