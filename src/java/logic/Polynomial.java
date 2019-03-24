package logic;

import java.util.ArrayList;

public class Polynomial extends ArrayList<Multiplier> {

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
              : -(size() + 1) / 2;
      k = size() + 1;
    }

    add(new Multiplier(n, k));
  }


  public Double value(Double x, int indexUp) {
    Double value = 1.0;

    for (int i = 0; i <= indexUp; i++) {
      value *= get(i).value(x);
    }

    return value;
  }

  public String toString(int indexUp) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i <= indexUp; i++) {
      result.append(get(i)).append(" * ");
    }


    result.delete(result.length() - 3, result.length());
    return result.toString();
  }

}
