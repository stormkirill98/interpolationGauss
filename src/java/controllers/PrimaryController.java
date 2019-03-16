package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import logic.Function;
import logic.Interpolation;
import logic.PolynomialGauss;

public class PrimaryController {
    public Pane chartPane;

    public TextField a = new TextField("1");
    public TextField b = new TextField("1");
    public TextField c = new TextField("1");

    public TextField xBegin = new TextField("-5");
    public TextField xEnd = new TextField("5");

    public TextField n = new TextField("10");


    private Charts charts;

    @FXML
    public void initialize(){
        charts = new Charts(chartPane);
    }

    public void buildGraph(ActionEvent event) {
        charts.buildFunction(Double.valueOf(xBegin.getText()),
                             Double.valueOf(xEnd.getText()));


        Interpolation interpolation = new Interpolation(
                Double.valueOf(xBegin.getText()),
                Double.valueOf(xEnd.getText()),
                Integer.valueOf(n.getText()));
        PolynomialGauss polynomialGauss = interpolation.countInterpolationPolynom();


        charts.buildPolynomial(polynomialGauss,
                               Double.valueOf(xBegin.getText()),
                               Double.valueOf(xEnd.getText()));

    }

    public void createFunction(ActionEvent event) {
        new Function(Double.valueOf(a.getText()),
                    Double.valueOf(b.getText()),
                    Double.valueOf(c.getText()));
    }
}
