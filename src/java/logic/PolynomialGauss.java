package logic;

import java.util.ArrayList;
import java.util.List;

public class PolynomialGauss {
  //function value for every added
  private List<Double> values = new ArrayList<>();
  private Polynom polynoms = new Polynom();

  private Double value0;
  private Double value1;

  private int index = 0;

  public PolynomialGauss(){
    value0 = Function.value(0.0);
    value1 = Function.value(0.5);

    polynoms.add(null);
  }

  public void add(){
    addValue();
    addPolynomial();
    index++;
  }

  //TODO: не хватате последнего коэффициента
  private void addValue(){
    if (index == 0){
      values.add(value0);
      return;
    }
    if (index == 1){
      values.add(value1);
      return;
    }

    Double value = values.get(index - 2);
    value = index % 2 == 0
            ? value * value0
            : value * value1;

    values.add(value);
  }

  private void addPolynomial(){
    polynoms.add();
  }

  @Override
  public String toString(){
    StringBuilder result = new StringBuilder();

    result.append(values.get(0)).append(" + ");
    for (int i = 1; i < index; i++){
      result.append(values.get(i)).append("*")
              .append(polynoms.get(i)).append(" + ");
    }

    return result.toString();
  }
}
