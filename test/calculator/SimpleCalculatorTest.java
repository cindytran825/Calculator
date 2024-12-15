package calculator;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * class for testing.
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {

  /**
   * this tests the clear C.
   */
  @Test
  public void testAddingInput() {
    Calculator calculator2 = new SimpleCalculator();
    calculator2 = calculator2.input('1').input('2').input('C');
    assertEquals("", calculator2.getResult());

    Calculator calculator = new SimpleCalculator();
    calculator = calculator.input('1').input('2').input('+');
    assertEquals("12+", calculator.getResult());
  }


  @Test
  public void testSubtracting() {
    Calculator calc2 = new SimpleCalculator();
    calc2 = calc2.input('1').input('-').input('3').input('=');
    assertEquals("-2", calc2.getResult());
    calc2.input('+').input('3').input('=');
  }

  /**
   * this tests if it computes after we call +.
   * without the =, 2 operators.
   */
  @Test
  public void testComputeMultiple() {
    Calculator calc = new SimpleCalculator();
    calc = calc.input('1').input('*').input('3').input('=');
    assertEquals("3", calc.getResult());


    Calculator calc2 = new SimpleCalculator();
    calc2 = calc2.input('1').input('+').input('3').input('+');
    assertEquals("4+", calc2.getResult());

  }

  /**
   * this tests the subtracting.
   */
  @Test
  public void testCheck() {
    Calculator calc1 = new SimpleCalculator();
    calc1 = calc1.input('1').input('-').input('3').input('=').input('=');
    assertEquals("-2", calc1.getResult());
    calc1 = calc1.input('+').input('3').input('=');
    assertEquals("1", calc1.getResult());

    Calculator p = new SimpleCalculator();
    p = p.input('1').input('+').input('3').input('=').input('=');
    assertEquals("4", p.getResult());
  }



  @Test(expected = IllegalArgumentException.class)
  public void testInputOverflowException() {
    Calculator calc1 = new SimpleCalculator();
    calc1 = calc1.input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1').input('1').input('1');
  }

  /**
   * this returns the number with the next operator.
   */
  @Test
  public void testSubtraction() {
    Calculator c2 = new SimpleCalculator();
    c2 = c2.input('7').input('-').input('3');
    assertEquals("7-3", c2.getResult());
    c2 = c2.input('-');
    assertEquals("4-", c2.getResult());
    c2 = c2.input('5').input('=');
    assertEquals("-1", c2.getResult());
  }


  /**
   * this checks the illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegative() {

    Calculator r5 = new SimpleCalculator();
    r5.input('+').input('4').input('9').input('*').input('2').input('=');

  }

  /**
   * this checks the illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOperator() {
    //operator first
    Calculator r5 = new SimpleCalculator();
    r5.input('+').input('1').input('+').input('3');

  }


  @Test(expected = IllegalArgumentException.class)
  public void testmultipleOperator() {

    Calculator g4 = new SimpleCalculator();
    g4.input('3').input('+').input('-').input('3').input('=');
  }



  @Test(expected = IllegalArgumentException.class)
  public void testTwoOperators() {
    Calculator r0 = new SimpleCalculator();
    r0.input('3').input('9').input('+').input('=');
  }


  /**
   * this tests the overflow.
   */
  @Test
  public void testOperatorEqual() {
    Calculator calc1 = new SimpleCalculator();
    calc1 = calc1.input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1')
            .input('1').input('+').input('5').input('=');
    assertEquals("1111111116", calc1.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructOperator() {
    //operator first
    Calculator r5 = new SimpleCalculator();
    r5.input('1').input('-').input('+').input('3');

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAExcept() {
    Calculator r4 = new SimpleCalculator();
    r4 = r4.input('+').input('2').input('=');

  }

  /**
   * this returns the type of calculator for testing.
   * @return new SimpleCalculator.
   */
  @Override
  protected AbstractCalculator newCalc() {
    return new SimpleCalculator();
  }
}