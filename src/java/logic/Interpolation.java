package logic;

import java.util.HashMap;
import java.util.Map;

import static logic.Utility.format;

public class Interpolation {
  private PolynomialGauss interpolationPolynom;

  private Double xBegin, xEnd;
  private Double x0;

  //step interpolation  nodes
  private Double h;
  //count interpolation nodes
  private int n;

  private Map<Integer, Double> interpolationNodes = new HashMap<>();

  public Interpolation(Double xBegin, Double xEnd, int n) {
    this.xBegin = xBegin;
    this.xEnd = xEnd;
    x0 = (xEnd + xBegin) / 2;

    this.n = n;
    h = (xEnd - xBegin) / (2 * n + 2);

    countNodes();
    print();
  }

  private void countNodes() {
    putNodeToMap(0);

    int i;
    for (i = 1; i < 2 * n; i++) {
      putNodeToMap(i);
      putNodeToMap(-i);
    }

    putNodeToMap(i);
  }

  private void putNodeToMap(int key) {
    Double node = x0 + h * key;
    interpolationNodes.put(key, node);
  }

  public void countInterpolationPolynom() {
    interpolationPolynom = new PolynomialGauss();

    for (int i = 0; i < 2 * n + 2; i++) {
      interpolationPolynom.add();
    }

    System.out.print("Interpolation polynomial: ");
    System.out.println(interpolationPolynom);

    System.out.println("P(2) = " + format(interpolationPolynom.value(2.0)));
  }

  private void printInterpolationNodes() {
    System.out.printf("Interpolation nodes: %.2f", interpolationNodes.get(0));

    int i;
    for (i = 1; i < 2 * n; i++) {
      System.out.printf(" %.2f", interpolationNodes.get(i));
      System.out.printf(" %.2f", interpolationNodes.get(-i));
    }

    System.out.printf(" %.2f\n", interpolationNodes.get(i));
  }

  private void print() {
    System.out.printf("%.2f < x < %.2f\n", xBegin, xEnd);
    System.out.printf("x0 = %.2f\n", x0);
    System.out.printf("n = %d\n", n);
    System.out.printf("h = %.2f\n", h);

    printInterpolationNodes();
  }
}
