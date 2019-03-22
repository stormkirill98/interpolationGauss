package logic;

import java.util.*;

import static logic.Utility.format;

public class FiniteCentralDifferences {
  private TreeMap<Double, List<Double>> table;

  private Double xBegin = 0.0;
  private Double xEnd = 0.0;
  private Double x0 = 0.0;

  private Double h = 0.0;
  private int n = 0;

  private final double keyStep = 0.5;

  public FiniteCentralDifferences(Double xBegin,
                                  Double xEnd,
                                  Double x0,
                                  Double h,
                                  int n) {
    table = new TreeMap<>();

    this.xBegin = xBegin;
    this.xEnd = xEnd;
    this.x0 = x0;

    this.h = h;
    this.n = n;

    initTable();
  }

  private void initTable() {
    double key = -n;
    Double x = xBegin;
    for (int i = 0; i <= 4 * n + 2; i++) {
      List<Double> row = new ArrayList<>();
      row.add(Function.value(x));
      table.put(key, row);

      x += h / 2;
      key += keyStep;
    }
  }

  public Double get(Double key, int i) {
    return table.get(key).get(i);
  }

  public void add(Double key, Double value) {
    table.get(key).add(value);
  }

  public void build() {
    for (int i = 1; i < 2 * n + 2; i++) {
      Double key = table.firstKey();
      key += i * keyStep;

      Double endKey = table.lastKey();
      endKey -= i * keyStep;

      for (; key <= endKey; key += keyStep) {
        Double newValue = get(key + keyStep, i - 1)
                - get(key - keyStep, i - 1);

        add(key, newValue);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    result.append(String.format("%6s", "k"))
            .append(String.format("%6s", "Fk"));
    for (int i = 1; i < 2 * n + 2; i++){
      result.append(String.format("   d%dFk", i));
    }
    result.append("\n");

    for (Double key : table.keySet()) {
      result.append(String.format("%6.3f", key))
              .append(" ");

      List<Double> row = table.get(key);
      for (Double value : row) {
        result.append(String.format("%6.3f", value))
                .append(" ");
      }

      result.append("\n");
    }

    return result.toString();
  }
}
