package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import logic.Function;
import logic.Interpolation;
import logic.Polynomial;
import logic.PolynomialGauss;

@SuppressWarnings({"Duplicates"})
public class Charts {
  private Pane pane;

  private LineChart<Number, Number> chart;

  private NumberAxis xAxis = new NumberAxis();
  private NumberAxis yAxis = new NumberAxis();

  private XYChart.Series function = new XYChart.Series();
  private XYChart.Series polynomial = new XYChart.Series();


  public Charts(Pane pane){
    this.pane = pane;

    initChart();
    initAxises();
    initFunction();
    initPolynomial();

    pane.getChildren().add(chart);
  }

  private void initChart(){
    chart = new LineChart<>(xAxis, yAxis);
    chart.setPrefSize(pane.getPrefWidth() - 5,
            pane.getPrefHeight() - 5);

    chart.setCreateSymbols(false);

  }

  private void initAxises(){
    xAxis.setTickUnit(0.1);
    yAxis.setTickUnit(0.1);
  }

  private void initFunction(){
    function.setName("Function");
  }

  private void initPolynomial(){
    polynomial.setName("Polynomial");
  }

  public void buildFunction(double xStart, double xEnd){
    chart.getData().clear();

    ObservableList<XYChart.Data<Double, Double>> data
            = FXCollections.observableArrayList();

    for (double x = xStart; x < xEnd; x += 0.1){
      data.add(new XYChart.Data<>(x, Function.value(x)));
    }

    function.setData(data);

    chart.getData().add(function);
  }

  public void buildPolynomial(PolynomialGauss polynomialGauss, double xStart, double xEnd){
    ObservableList<XYChart.Data<Double, Double>> data
            = FXCollections.observableArrayList();


    System.out.println(polynomialGauss.polynomsSize());

    for (double x = xStart; x < 5; x += 0.1){
      data.add(new XYChart.Data<>(x, polynomialGauss.value(x)));
    }

    polynomial.setData(data);
    chart.getData().add(polynomial);
  }
}
