package calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * abstract class for testing.
 * SimpleCalcTest and SmartCalcTest extends.
 */
public abstract class AbstractCalculatorTest {

  protected abstract AbstractCalculator newCalc();

  @Test
  public void testInput() {
    Calculator calc = newCalc();
    calc = calc.input('1');
    assertEquals("1", calc.getResult());

  }

  @Test
  public void testOneInput() {
    Calculator c2 = newCalc();
    c2 = c2.input('2').input('2');
    assertEquals("22", c2.getResult());

    Calculator c3 = newCalc();
    c3 = c3.input('2').input('2').input('+');
    assertEquals("22+", c3.getResult());
  }


  @Test
  public void testTwoInputs() {
    Calculator calc = newCalc();
    calc = calc.input('1').input('+').input('2').input('=');
    assertEquals("3", calc.getResult());

    Calculator c2 = newCalc();
    c2 = c2.input('1').input('2').input('C');
    assertEquals("", c2.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBeginOperator() {
    Calculator calculator3 = newCalc();
    calculator3.input('*');

  }

  /**
   * this tests adding the operands after calling =.
   */

  @Test
  public void testComputeAdding() {
    Calculator calc = newCalc();
    calc = calc.input('1').input('+').input('3').input('=');
    assertEquals("4", calc.getResult());
    Calculator calc2 = newCalc();
    calc2 = calc2.input('1').input('-').input('3').input('=');
    assertEquals("-2", calc2.getResult());


    Calculator c = newCalc();
    c = c.input('1').input('-').input('3').input('=').input('1');
    assertEquals("1", c.getResult());
  }

  /**
   * this adds 3 digit numbers.
   */
  @Test
  public void testComputeAddingLarge() {
    Calculator calc = newCalc();
    calc = calc.input('1').input('6').input('8').input('+').input('9').input('3').input('=');
    assertEquals("261", calc.getResult());

    Calculator calc2 = newCalc();
    calc2 = calc2.input('1').input('+').input('3').input('+').input('8').input('=');
    assertEquals("12", calc2.getResult());

    Calculator calc4 = newCalc();
    calc4 = calc4.input('1').input('+').input('3').input('-').input('1').input('=');
    assertEquals("3", calc4.getResult());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputOverfowException() {
    Calculator calc1 = newCalc();
    calc1 = calc1.input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1').input('1').input('1');

  }

  /**
   * outcome = 0 when overflow a+b.
   */
  @Test
  public void testComputeAddingMultiple() {
    Calculator calc2 = newCalc();
    //11 1's times 2, return 0
    calc2 = calc2.input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1')
            .input('1').input('1').input('*').input('2').input('=');
    assertEquals("0", calc2.getResult());
  }

  @Test
  public void testReturnAfterEquals() {
    Calculator calc2 = newCalc();
    calc2 = calc2.input('1').input('+').input('4');
    assertEquals("1+4", calc2.getResult());
    calc2 = calc2.input('=');
    assertEquals("5", calc2.getResult());
    calc2 = calc2.input('+');
    assertEquals("5+", calc2.getResult());
  }

  @Test
  public void testInvalidInputOverflowException() {
    Calculator calc1 = newCalc();
    try {
      calc1 = calc1.input('1').input('1').input('1').input('1')
              .input('1').input('1').input('1').input('1').input('1').input('1');
    } catch (IllegalArgumentException e) {
      assertEquals("operand overflow", e.getMessage());
      assertEquals("1111111111", calc1.getResult());
    }

  }

  /**
   * this checks the illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal() {
    //can't have a negative, operator first
    Calculator r2 = newCalc();
    r2.input('-').input('5').input('6');

  }

  /**
   * this checks the illegal arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testArgue() {
    //can't have a negative, operator first
    //not between 0-9
    Calculator r4 = newCalc();
    r4.input('j').input('-').input('p');

  }

}
