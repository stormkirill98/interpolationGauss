package logic;


/*
  Multiplier for polynomial: (x+n)/k
  n - integer
  k - natural number
*/
public class Multiplier {
  int n;
  int k;

  public Multiplier(int n, int k){
    this.n = n;
    this.k = k;
  }

  public Double value(Double x){
    return (x + n) / k;
  }

  @Override
  public String toString(){
    if (n == 0 && k == 1){
      return "x";
    }

    return n > 0 ? "[(x + " + n + ")/" + k + "]"
                 : "[(x - " + -n + ")/" + k + "]";
  }
}
