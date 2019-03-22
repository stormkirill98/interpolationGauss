import logic.FiniteCentralDifferences;
import org.junit.Before;
import org.junit.Test;

public class FiniteCentralDifferencesTest {
  FiniteCentralDifferences finiteCentralDifferences;

  @Before
  public void setUp() throws Exception {
    finiteCentralDifferences
            = new FiniteCentralDifferences(-2.0,
            2.0,
            0.0,
            1.0,
            2);

  }

  @Test
  public void printBefore(){
    System.out.println(finiteCentralDifferences);
  }

  @Test
  public void printAfter(){
    finiteCentralDifferences.build();
    System.out.println(finiteCentralDifferences);
  }
}