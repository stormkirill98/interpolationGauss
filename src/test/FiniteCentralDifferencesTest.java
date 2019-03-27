import logic.FiniteCentralDifferences;
import org.junit.Before;
import org.junit.Test;

public class FiniteCentralDifferencesTest {
  FiniteCentralDifferences finiteCentralDifferences;

  @Before
  public void setUp() {
    finiteCentralDifferences
            = new FiniteCentralDifferences(-2.0,
            1.0,
            2);

  }


  @Test
  public void print() {
    finiteCentralDifferences.build();
    System.out.println(finiteCentralDifferences);
  }
}