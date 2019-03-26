package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Function;
import logic.Interpolation;
import logic.PolynomialGauss;

import javax.swing.event.ChangeEvent;

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

  private Stage stage;

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
      if (!fixInputFunction(a, newValue, oldValue)) {
        return;
      }

      Function.setA(strToDouble(newValue));

      changeSizeTextField(a);
    });

    b.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!fixInputFunction(b, newValue, oldValue)) {
        return;
      }

      Function.setB(strToDouble(newValue));

      changeSizeTextField(b);
    });

    c.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!fixInputFunction(c, newValue, oldValue)) {
        return;
      }

      Function.setC(strToDouble(newValue));

      changeSizeTextField(c);
    });
  }

  //true - did not fix
  private boolean fixInputFunction(TextField textField, String newValue, String oldValue) {
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
        xBegin.setText(xEnd.getText());
        return;
      }

      Interpolation.setXBegin(value);
      charts.setXBegin(value);

      changeSizeTextField(xBegin);
    });

    xEnd.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!isDouble(newValue)) {
        b.setText(oldValue);
        return;
      }

      Double value = strToDouble(newValue);

      if (value < strToDouble(xBegin.getText())) {
        xEnd.setText(xBegin.getText());
        return;
      }

      Interpolation.setXEnd(value);
      charts.setXEnd(value);

      changeSizeTextField(xEnd);
    });
  }

  private void addChangeListenerToInputN() {
    n.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        n.setText("1");
        return;
      }

      if (!isNatural(newValue)) {
        n.setText(oldValue);
        return;
      }

      Interpolation.setN(Integer.valueOf(newValue));

      changeSizeTextField(n);
    });
  }

  private void changeSizeTextField(TextField textField) {
    if (textField == null) {
      return;
    }

    Text text = new Text(textField.getText());
    text.setFont(textField.getFont());
    new Scene(new Group(text));
    text.applyCss();

    double lengthText = text.getLayoutBounds().getWidth() + 20;

    if (lengthText < textField.getMinWidth()) {
      lengthText = textField.getMinWidth();
    }

    if (lengthText > textField.getMaxWidth()) {
      lengthText = textField.getMaxWidth();
    }


    textField.setPrefWidth(lengthText);
  }

  public void buildGraph(ActionEvent event) {
    charts.buildFunction();

    Interpolation interpolation = new Interpolation();
    PolynomialGauss polynomialGauss = interpolation.countInterpolationPolynom();


    charts.buildPolynomial(polynomialGauss);
  }

  public void setStage(Stage stage) {
    this.stage = stage;
    addListenerChangeWindowSize();
  }

  private void addListenerChangeWindowSize(){
    if (stage == null){
      return;
    }

    stage.widthProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() < 600){
        stage.setWidth(oldValue.doubleValue());
        return;
      }

      chartPane.setPrefWidth(newValue.doubleValue() - 400);
      charts.getChart().setPrefWidth(newValue.doubleValue() - 405);
    });

    stage.heightProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() < 260){
        stage.setHeight(oldValue.doubleValue());
        return;
      }

      chartPane.setPrefHeight(newValue.doubleValue() - 10);
      charts.getChart().setPrefHeight(newValue.doubleValue() - 15);
    });
  }
}
