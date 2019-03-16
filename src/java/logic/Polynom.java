package logic;

import java.util.ArrayList;

public class Polynom extends ArrayList<Multiplier> {

  public void add() {
    int n = 0, k = 1;

    if (size() == 0) {
      n = 0;
      k = 1;
    }

    if (size() == 1) {
      n = -1;
      k = 2;
    }

    if (size() > 1) {
      n = size() % 2 == 0
              ? size() / 2
              : -(size() - 1);
      k = size() + 1;
    }

    add(new Multiplier(n, k));
  }


  public Double value(Double x, int index) {
    Double value = 0.0;

    for (int i = 0; i < index; i++) {
      value += get(i).value(x);
    }

    return value;
  }

  public String toString(int indexTo) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i <= indexTo; i++) {
      result.append(get(i)).append(" * ");
    }

    result.deleteCharAt(result.length() - 2);
    return result.toString();
  }

}
