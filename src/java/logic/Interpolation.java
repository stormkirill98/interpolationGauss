package logic;

public class Interpolation {
  private static Double xBegin, xEnd;
  private Double x0;

  //step interpolation  nodes
  private Double h;
  //count interpolation nodes
  private static Integer n;

  public Interpolation(){
    if (xBegin == null
            || xEnd == null
            || n == null){
      return;
    }

    x0 = (xEnd + xBegin) / 2;
    h = (xEnd - xBegin) / (2 * n);
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

  public PolynomialGauss countInterpolationPolynom() {
    if (xBegin == null
            || xEnd == null
            || n == null) {
      return null;
    }

    PolynomialGauss interpolationPolynom = new PolynomialGauss(xBegin, h, n);
    interpolationPolynom.countPolynomial();

    System.out.print("Interpolation polynomial: ");
    System.out.println(interpolationPolynom);

    for (double x = -xBegin; x <= xEnd; x += h){
      System.out.printf("P(%.2f) = %.6f    F(%.2f) = %.6f\n", x, interpolationPolynom.value(x), x, Function.value(x));
    }

    return interpolationPolynom;
  }

  private void print() {
    System.out.printf("%.2f < x < %.2f\n", xBegin, xEnd);
    System.out.printf("x0 = %.2f\n", x0);
    System.out.printf("n = %d\n", n);
    System.out.printf("h = %.2f\n", h);
  }
}
