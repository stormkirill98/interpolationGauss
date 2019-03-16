package logic;

import java.util.ArrayList;
import java.util.List;

import static logic.Utility.*;

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
  }

  public void add(){
    addValue();
    addPolynomial();
    index++;
  }

  //TODO: не хватате последнего коэффициента
  private void addValue(){
    if (index == 0){
      values.add(value1);
      return;
    }
    if (index == 1){
      values.add(value0 * value0);
      return;
    }

    Double value = values.get(index - 2);
    value = index % 2 == 0
            ? value * value1
            : value * value0;

    values.add(value);
  }

  private void addPolynomial(){
    polynoms.add();
  }

  @Override
  public String toString(){
    StringBuilder result = new StringBuilder();

    if (zero(value0)){
      result.append(format(value0)).append(" + ");
    }
    for (int i = 0; i < index - 1; i++){
      Double value = values.get(i);
      if (zero(value)){
        continue;
      }

      result.append(format(value)).append("*")
              .append(polynoms.toString(i)).append(" + ");
    }

    result.deleteCharAt(result.length() - 2);
    return result.toString();
  }


}
