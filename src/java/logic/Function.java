package logic;

public class Function {
  private static Double a;
  private static Double b;
  private static Double c;

  String function = "sin(x)";

  public Function(Double a, Double b, Double c){
    Function.a = a;
    Function.b = b;
    Function.c = c;
  }

  public static Double value(Double x){
    return a * Math.sin(Math.tan(b * x)) * Math.sin(c * x);
  }
}
