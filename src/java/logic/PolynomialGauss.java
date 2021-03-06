package logic;

import static logic.Utility.format;
import static logic.Utility.isZero;

public class PolynomialGauss {

  private FiniteCentralDifferences finiteCentralDifferences;
  private Polynomial polynomials = new Polynomial();

  private int n = 0;


  public PolynomialGauss(Double xBegin, Double h, int n) {
    this.n = n;

    finiteCentralDifferences = new FiniteCentralDifferences(xBegin, h, n);
    finiteCentralDifferences.build();

    System.out.println(finiteCentralDifferences);
  }

  public void countPolynomial() {
    for (int i = 0; i < 2 * n + 1; i++) {
      polynomials.add();
    }
  }

  @SuppressWarnings("Duplicates")
  public Double value(Double x) {
    Double result = 0.0;

    result += finiteCentralDifferences.get(0.0, 0);

    for (int i = 0; i < 2 * n + 1; i++) {
      Double key = i % 2 == 0 ? 0.5 : 0.0;

      Double value = finiteCentralDifferences.get(key, i + 1);
      if (isZero(value)) {
        continue;
      }

      result += value * polynomials.value(x, i);
    }

    return result;
  }

  @SuppressWarnings("Duplicates")
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    Double value0 = finiteCentralDifferences.get(0.0, 0);
    if (!isZero(value0)) {
      result.append(format(value0)).append(" + ");
    }

    for (int i = 0; i < 2 * n + 1; i++) {
      Double key = i % 2 == 0 ? 0.5 : 0.0;

      Double value = finiteCentralDifferences.get(key, i + 1);
      if (isZero(value)) {
        continue;
      }

      result.append(format(value)).append("*")
              .append(polynomials.toString(i)).append("\n+ ");
    }

    if (result.length() > 2) {
      result.delete(result.length() - 2, result.length());
    }
    return result.toString();
  }
}
