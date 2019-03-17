package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.Function;
import logic.Interpolation;
import logic.PolynomialGauss;

import static logic.Utility.*;

public class PrimaryController {
  public Pane chartPane;

  public TextField a;
  public TextField b;
  public TextField c;

  public TextField xBegin;
  public TextField xEnd;

  public TextField n;


  private Charts charts;

  @FXML
  public void initialize() {
    charts = new Charts(chartPane);

    addChangeListenerToInputFunction();
    addChangeListenerToInputRange();
    addChangeListenerToInputN();

    initFields();
  }

  private void initFields() {
    a.setText("1");
    b.setText("1");
    c.setText("1");

    xBegin.setText("-5");
    xEnd.setText("5");

    n.setText("10");
  }

  private void addChangeListenerToInputFunction() {
    a.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!fixInputFunction(a, newValue, oldValue)){
        return;
      }

      Function.setA(strToDouble(newValue));
    });

    b.textProperty().addListener((observable, oldValue, newValue) -> {
      if(fixInputFunction(b, newValue, oldValue)){
        return;
      }

      Function.setB(strToDouble(newValue));
    });

    c.textProperty().addListener((observable, oldValue, newValue) -> {
      if (fixInputFunction(c, newValue, oldValue)){
        return;
      }

      Function.setC(strToDouble(newValue));
    });
  }

  //true - did not fix
  private boolean fixInputFunction(TextField textField, String newValue, String oldValue){
    if (newValue.isEmpty()){
      textField.setText("0");
      return false;
    }

    if (!isDouble(newValue)) {
      textField.setText(oldValue);
      return false;
    }

    return true;
  }

  private void addChangeListenerToInputRange() {
    xBegin.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!isDouble(newValue)) {
        xBegin.setText(oldValue);
        return;
      }

      Double value = strToDouble(newValue);

      if (value > strToDouble(xEnd.getText())) {
        xBegin.setText(oldValue);
        return;
      }

      Interpolation.setXBegin(value);
      charts.setXBegin(value);
    });

    xEnd.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!isDouble(newValue)) {
        b.setText(oldValue);
        return;
      }

      Double value = strToDouble(newValue);

      if (value < strToDouble(xBegin.getText())) {
        xEnd.setText(oldValue);
        return;
      }

      Interpolation.setXEnd(value);
      charts.setXEnd(value);
    });
  }

  private void addChangeListenerToInputN(){
    n.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()){
        n.setText("1");
        return;
      }

      if (!isNatural(newValue)){
        n.setText(oldValue);
        return;
      }

      Interpolation.setN(Integer.valueOf(newValue));
    });
  }

  public void buildGraph(ActionEvent event) {
    charts.buildFunction();

    Interpolation interpolation = new Interpolation();
    PolynomialGauss polynomialGauss = interpolation.countInterpolationPolynom();


    charts.buildPolynomial(polynomialGauss);

  }
}
