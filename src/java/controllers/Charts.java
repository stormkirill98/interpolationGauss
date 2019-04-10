package controllers;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import logic.Function;
import logic.PolynomialGauss;

import java.util.*;

@SuppressWarnings({"Duplicates"})
class Charts {
	private Pane pane;

	private LineChart<Number, Number> chart;

	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();

	private XYChart.Series<Number, Number> function = new XYChart.Series<>();
	private XYChart.Series<Number, Number> polynomial = new XYChart.Series<>();
	private XYChart.Series<Number, Number> difference = new XYChart.Series<>();

	private double xBegin = 0.0,
					xEnd = 0.0;

	private double step = 0.001;


	private double maxValue = 0.0;
	private double maxX = 0.0,
					maxY = 0.0;

	private Point2D functionMinMaxY;
	private Point2D polynomialMinMaxY;
	private Point2D differenceMinMaxY;


	Charts(Pane pane) {
		this.pane = pane;

		initChart();
		initAxises();

		initFunction();
		initPolynomial();
		initDifference();

		pane.getChildren().add(chart);

		initLegend();
	}

	private void initChart() {
		chart = new LineChart<>(xAxis, yAxis);
		chart.setPrefSize(pane.getPrefWidth() - 5,
						pane.getPrefHeight() - 5);

		chart.setCreateSymbols(false);
		chart.setAnimated(false);

		chart.getData().add(function);
		chart.getData().add(polynomial);
		chart.getData().add(difference);
	}

	private void initAxises() {
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(-5);
		xAxis.setUpperBound(5);
		xAxis.setTickUnit(0.5);

		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(-5);
		yAxis.setUpperBound(5);
		yAxis.setTickUnit(0.5);
	}

	private void initFunction() {
		function.setName("Function");
	}

	private void initPolynomial() {
		polynomial.setName("Polynomial");
	}

	private void initDifference() {
		difference.setName("Difference");
	}

	private void initLegend() {
		Legend legend = getLegendChart();
		if (legend == null) {
			System.out.println("legend = null");
			return;
		}

		ObservableList<Legend.LegendItem> legendItems = legend.getItems();
		List<Node> lines = getLines();


		Node functionSymbol = legendItems.get(0).getSymbol();
		Node functionLine = lines.get(0);

		functionSymbol.setId("activeFunctionSymbol");
		functionLine.setId("visibleFunctionLine");

		functionSymbol.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			String symbolId = functionSymbol.getId();
			String lineId;

			if (symbolId.equals("activeFunctionSymbol")) {
				symbolId = "nonActiveFunctionSymbol";
				lineId = "nonVisibleLine";
			} else {
				symbolId = "activeFunctionSymbol";
				lineId = "visibleFunctionLine";
			}

			functionSymbol.setId(symbolId);
			functionLine.setId(lineId);
		});


		Node polynomialSymbol = legendItems.get(1).getSymbol();
		Node polynomialLine = lines.get(1);

		polynomialSymbol.setId("activePolynomialSymbol");
		polynomialLine.setId("visiblePolynomialLine");

		polynomialSymbol.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			String symbolId = polynomialSymbol.getId();
			String lineId;

			if (symbolId.equals("activePolynomialSymbol")) {
				symbolId = "nonActivePolynomialSymbol";
				lineId = "nonVisibleLine";
			} else {
				symbolId = "activePolynomialSymbol";
				lineId = "visiblePolynomialLine";
			}

			polynomialSymbol.setId(symbolId);
			polynomialLine.setId(lineId);
		});

		Node differenceSymbol = legendItems.get(2).getSymbol();
		Node differenceLine = lines.get(2);

		differenceSymbol.setId("activeDifferenceSymbol");
		differenceLine.setId("visibleDifferenceLine");

		differenceSymbol.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			String symbolId = differenceSymbol.getId();
			String lineId;

			if (symbolId.equals("activeDifferenceSymbol")) {
				symbolId = "nonActiveDifferenceSymbol";
				lineId = "nonVisibleLine";
			} else {
				symbolId = "activeDifferenceSymbol";
				lineId = "visibleDifferenceLine";
			}

			differenceSymbol.setId(symbolId);
			differenceLine.setId(lineId);
		});
	}

	private Legend getLegendChart() {
		Set<Node> items = chart.lookupAll("Label.chart-legend-item");
		if (!items.isEmpty()) {
			Optional<Node> nodeOptional = items.stream().findFirst();
			try {
				Node node = nodeOptional.get();
				Parent legendParent = node.getParent();
				return (Legend) legendParent;
			} catch (ClassCastException ex) {
				return null;
			}
		} else {
			return null;
		}
	}

	private List<Node> getLines(){
		List<Node> lines = new ArrayList<>();

		for (XYChart.Series series : chart.getData()){
			lines.add(series.getNode());
		}

		return lines;
	}

	void setXBegin(double xStart) {
		this.xBegin = xStart;
	}

	void setXEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	void buildFunction() {
		ObservableList<XYChart.Data<Number, Number>> data
						= FXCollections.observableArrayList();

		for (double x = xBegin; x < xEnd; x += step) {
			data.add(new XYChart.Data<>(x, Function.value(x)));
		}

		function.getData().clear();
		function.setData(data);

		double minY = getMinY(data);
		double maxY = getMaxY(data);
		functionMinMaxY = new Point2D(minY, maxY);
	}

	void buildPolynomial(PolynomialGauss polynomialGauss) {
		if (polynomialGauss == null) {
			return;
		}

		ObservableList<XYChart.Data<Number, Number>> data
						= FXCollections.observableArrayList();

		for (double x = xBegin; x < xEnd; x += step) {
			data.add(new XYChart.Data<>(x, polynomialGauss.value(x)));
		}

		polynomial.getData().clear();
		polynomial.setData(data);

		double minY = getMinY(data);
		double maxY = getMaxY(data);
		polynomialMinMaxY = new Point2D(minY, maxY);

		buildDifference();
	}

	private void buildDifference() {
		maxValue = 0.0;

		ObservableList<XYChart.Data<Number, Number>> data
						= FXCollections.observableArrayList();

		ObservableList<XYChart.Data<Number, Number>> dataFunction
						= function.getData();
		ObservableList<XYChart.Data<Number, Number>> dataPolynomial
						= polynomial.getData();

		for (int i = 0; i < dataFunction.size(); i++) {
			Number x = dataFunction.get(i).getXValue();
			Number y = dataFunction.get(i).getYValue().doubleValue()
							- dataPolynomial.get(i).getYValue().doubleValue();
			data.add(new XYChart.Data<>(x, y));

			if (Math.abs(y.doubleValue()) > maxValue) {
				maxValue = Math.abs(y.doubleValue());
				maxX = x.doubleValue();
				maxY = y.doubleValue();
			}
		}

		difference.getData().clear();
		difference.setData(data);

		double minY = getMinY(data);
		double maxY = getMaxY(data);
		differenceMinMaxY = new Point2D(minY, maxY);
	}

	LineChart<Number, Number> getChart() {
		return chart;
	}

	private double getMaxY(ObservableList<XYChart.Data<Number, Number>> data) {
		Optional<XYChart.Data<Number, Number>> max = data.stream().max((o1, o2) -> {
			if (o1.getYValue().doubleValue() < o2.getYValue().doubleValue())
				return -1;
			if (o1.getYValue().doubleValue() > o2.getYValue().doubleValue())
				return 1;
			return 0;
		});

		return max.get().getYValue().doubleValue();
	}

	private double getMinY(ObservableList<XYChart.Data<Number, Number>> data) {
		Optional<XYChart.Data<Number, Number>> min = data.stream().min((o1, o2) -> {
			if (o1.getYValue().doubleValue() < o2.getYValue().doubleValue())
				return -1;
			if (o1.getYValue().doubleValue() > o2.getYValue().doubleValue())
				return 1;
			return 0;
		});

		return min.get().getYValue().doubleValue();
	}

	private void tuneAxises(ObservableList<XYChart.Data<Number, Number>> data) {
		xAxis.setLowerBound(xBegin);
		xAxis.setUpperBound(xEnd);

		double tickUnitX = (xEnd - xBegin) / 20;
		tickUnitX = Math.round(tickUnitX * 10.0) / 10.0;
		xAxis.setTickUnit(tickUnitX);

		Point2D bestMinMax = getBestMinMax();
		double minY = bestMinMax.getX();
		minY = minY < 0 ? minY + minY * 0.1
						: minY - minY * 0.1;
		double maxY = bestMinMax.getY();
		maxY = maxY < 0 ? maxY - maxY * 0.1
						: maxY + maxY * 0.1;
		yAxis.setLowerBound(minY);
		yAxis.setUpperBound(maxY);

		double tickUnitY = (maxY - minY) / 20;
		tickUnitY = Math.round(tickUnitY * 10.0) / 10.0;
		yAxis.setTickUnit(tickUnitY);
	}

	private Point2D getBestMinMax(){
		Point2D bestMinMax = null;

		double min = 1, max = 2;

		double relationFunction = (functionMinMaxY.getY() - functionMinMaxY.getX())
															/ (xEnd - xBegin);
		double relationPolynomial = (polynomialMinMaxY.getY() - polynomialMinMaxY.getX())
															/ (xEnd - xBegin);
		double relationDifference = (differenceMinMaxY.getY() - differenceMinMaxY.getX())
															/ (xEnd - xBegin);

		if (relationDifference < max && relationDifference > min){
			bestMinMax = differenceMinMaxY;
		}
		if (relationPolynomial < max && relationPolynomial > min){
			bestMinMax = polynomialMinMaxY;
		}
		if (relationFunction < max && relationFunction > min){
			bestMinMax = functionMinMaxY;
		}

		return bestMinMax;
	}


	double getMaxValue() {
		return maxValue;
	}

	double getMaxX() {
		return maxX;
	}

	double getMaxY() {
		return maxY;
	}
}
