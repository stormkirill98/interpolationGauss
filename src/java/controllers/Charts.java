package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import logic.Function;
import logic.PolynomialGauss;

@SuppressWarnings({"Duplicates"})
public class Charts {
  private Pane pane;

  private LineChart<Number, Number> chart;

  private NumberAxis xAxis = new NumberAxis();
  private NumberAxis yAxis = new NumberAxis();

  private XYChart.Series<Number, Number> function = new XYChart.Series<>();
  private XYChart.Series<Number, Number> polynomial = new XYChart.Series<>();

  private double xBegin = 0.0,
          xEnd = 0.0;


  public Charts(Pane pane) {
    this.pane = pane;

    initChart();
    initAxises();
    initFunction();
    initPolynomial();

    pane.getChildren().add(chart);
  }

  private void initChart() {
    chart = new LineChart<>(xAxis, yAxis);
    chart.setPrefSize(pane.getPrefWidth() - 5,
            pane.getPrefHeight() - 5);

    chart.setCreateSymbols(false);
    chart.setAnimated(false);

    chart.getData().add(function);
    chart.getData().add(polynomial);
  }

  private void initAxises() {
    xAxis.setTickUnit(0.1);

    yAxis.setTickUnit(0.1);
  }

  private void initFunction() {
    function.setName("Function");
  }

  private void initPolynomial() {
    polynomial.setName("Polynomial");
  }

  public void setXBegin(double xStart) {
    this.xBegin = xStart;
  }

  public void setXEnd(double xEnd) {
    this.xEnd = xEnd;
  }

  public void buildFunction() {
    ObservableList<XYChart.Data<Number, Number>> data
            = FXCollections.observableArrayList();

    data.clear();

    for (double x = xBegin; x < xEnd; x += 0.1) {
      data.add(new XYChart.Data<>(x, Function.value(x)));
    }

    function.getData().clear();
    function.setData(data);
  }

  public void buildPolynomial(PolynomialGauss polynomialGauss) {
    if (polynomialGauss == null) {
      return;
    }

    ObservableList<XYChart.Data<Number, Number>> data
            = FXCollections.observableArrayList();

    data.clear();

    for (double x = xBegin; x < 5; x += 0.1) {
      data.add(new XYChart.Data<>(x, polynomialGauss.value(x)));
    }

    polynomial.getData().clear();
    polynomial.setData(data);
  }

  public LineChart<Number, Number> getChart() {
    return chart;
  }
}
