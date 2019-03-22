package logic;

import java.util.HashMap;
import java.util.Map;

import static logic.Utility.format;

public class Interpolation {
  private static Double xBegin, xEnd;
  private Double x0;

  //step interpolation  nodes
  private Double h;
  //count interpolation nodes
  private static Integer n;

  private Map<Integer, Double> interpolationNodes = new HashMap<>();

  public Interpolation(){
    if (xBegin == null
            || xEnd == null
            || n == null){
      return;
    }

    x0 = (xEnd + xBegin) / 2;
    h = (xEnd - xBegin) / (2 * n + 2);
    countNodes();
    print();
  }

  public Interpolation(Double xBegin, Double xEnd, int n) {
    Interpolation.xBegin = xBegin;
    Interpolation.xEnd = xEnd;
    x0 = (xEnd + xBegin) / 2;

    Interpolation.n = n;
    h = (xEnd - xBegin) / (2 * n + 2);

    countNodes();
    print();
  }

  public static void setXBegin(Double xBegin) {
    Interpolation.xBegin = xBegin;
  }

  public static void setXEnd(Double xEnd) {
    Interpolation.xEnd = xEnd;
  }

  public static void setN(int n) {
    Interpolation.n = n;
  }

  private void countNodes() {
    putNodeToMap(0);

    int i;
    for (i = 1; i <= n; i++) {
      putNodeToMap(i);
      putNodeToMap(-i);
    }

    putNodeToMap(i);
  }

  private void putNodeToMap(int key) {
    Double node = x0 + h * key;
    interpolationNodes.put(key, node);
  }

  public PolynomialGauss countInterpolationPolynom() {
    if (xBegin == null
            || xEnd == null
            || n == null) {
      return null;
    }

    PolynomialGauss interpolationPolynom = new PolynomialGauss();

    for (int i = 0; i < 2 * n + 2; i++) {
      interpolationPolynom.add();
    }

    System.out.print("Interpolation polynomial: ");
    System.out.println(interpolationPolynom);

    for (double x = -xBegin; x <= xEnd; x += h){
      System.out.printf("P(%.2f) = %.6f    F(%.2f) = %.6f\n", x, interpolationPolynom.value(x), x, Function.value(x));
    }

    return interpolationPolynom;
  }

  private void printInterpolationNodes() {
    System.out.printf("Interpolation nodes: %.2f", interpolationNodes.get(0));

    int i;
    for (i = 1; i <= n; i++) {
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
