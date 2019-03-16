package logic;

import java.util.ArrayList;

public class Polynom extends ArrayList<Multiplier> {

  public void add(){
    int n, k;

    if (size() == 0) {
      n = 0;
      k = 1;
    } else {
      n = size() % 2 == 0
              ? size() - 1
              : -size();
      k = size() + 1;
    }

    Multiplier multiplier = new Multiplier(n, k);
    add(multiplier);
  }


  public Double value(Double x, int index){
    Double value = 0.0;

    for(int i = 0; i < index; i++){
      value += get(i).value(x);
    }

    return value;
  }

  @Override
  public String toString(){
    StringBuilder result = new StringBuilder();

    for (Multiplier multiplier : this) {
      result.insert(0, multiplier + " * ");
    }

    result.deleteCharAt(result.length() - 2);
    return result.toString();
  }

}
