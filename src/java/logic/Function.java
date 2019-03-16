package logic;

public class Function {
  private Double a, b, c;

  String function = "sin(x)";

  public Function(){}

  public static Double value(Double x){
    return Math.sin(x);
  }
}
