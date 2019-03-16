package logic;

import java.util.ArrayList;
import java.util.List;

import static logic.Utility.*;

public class PolynomialGauss {

  //function value for every added
  private List<Double> functionValues = new ArrayList<>();
  private Polynomial polynoms = new Polynomial();

  private Double value0;
  private Double value1;

  private int index = 0;

  public PolynomialGauss(){
    value0 = Function.value(0.0);
    value1 = Function.value(0.5);
  }

  public void add(){
    addFunctionValue();
    addPolynomial();
    index++;
  }

  //TODO: не хватате последнего коэффициента
  private void addFunctionValue(){
    if (index == 0){
      functionValues.add(value1);
      return;
    }
    if (index == 1){
      functionValues.add(value0 * value0);
      return;
    }

    Double value = functionValues.get(index - 2);
    value = index % 2 == 0
            ? value * value1 * value1
            : value * value0 * value0;

    functionValues.add(value);
  }

  private void addPolynomial(){
    polynoms.add();
  }

  public Double value(Double x){
    Double result = 0.0;

    result += value0;

    for (int i = 0; i < index - 1; i++){
      Double value = functionValues.get(i);
      if (zero(value)){
        continue;
      }
      result += value * polynoms.value(x, i);
    }

    return result;
  }

  @Override
  public String toString(){
    StringBuilder result = new StringBuilder();

    if (!zero(value0)){
      result.append(format(value0)).append(" + ");
    }
    for (int i = 0; i < index; i++){
      Double value = functionValues.get(i);
      if (zero(value)){
        continue;
      }

      result.append(format(value)).append("*")
              .append(polynoms.toString(i)).append("\n+ ");
    }

    result.delete(result.length() - 2, result.length());
    return result.toString();
  }

  public int polynomsSize(){
    return polynoms.size();
  }
}
