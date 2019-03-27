package logic;

import static logic.Utility.format;

public class Function {
  private static Double a = 0.0;
  private static Double b = 0.0;
  private static Double c = 0.0;

  public Function(Double a, Double b, Double c) {
    Function.a = a;
    Function.b = b;
    Function.c = c;
  }

  public static void setA(Double a) {
    Function.a = a;
  }

  public static void setB(Double b) {
    Function.b = b;
  }

  public static void setC(Double c) {
    Function.c = c;
  }

  public static Double value(Double x) {
    //return a * Math.sin(Math.tan(b * x)) * Math.sin(c * x);
    return Math.tan(x);
  }

  public static String string() {
    return format(a) + " * sin(tg" + format(b) + "*x) * sin(" + format(c) + "*x)";
  }
}
