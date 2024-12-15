package calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * SmartCalculatorTest that extends the AbstractCalcTest.
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {


  @Test
  public void testOneOperand() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('2').input('+').input('=');
    assertEquals("24", c2.getResult());

    Calculator c3 = new SmartCalculator();
    c3 = c3.input('1').input('2').input('-').input('=');
    assertEquals("0", c3.getResult());

    Calculator c4 = new SmartCalculator();
    c4 = c4.input('1').input('2').input('+').input('=').input('=');
    assertEquals("36", c4.getResult());
  }

  @Test
  public void testTwoOperand() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('-').input('+');
    assertEquals("1+", c2.getResult());

    c2 = c2.input('-').input('=');
    assertEquals("0", c2.getResult());

  }

  @Test
  public void testBeginPlus() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('+').input('2').input('+').input('2');
    assertEquals("2+2", c2.getResult());

    c2 = c2.input('=');
    assertEquals("4", c2.getResult());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBeginOperator() {
    Calculator c2 = new SmartCalculator();
    c2.input('-').input('2').input('+').input('2');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBeginOperating() {
    Calculator c2 = new SmartCalculator();
    c2.input('*').input('2').input('+').input('3');
  }

  @Test
  public void testMemoryEquals() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('+').input('=').input('=');
    assertEquals("3", c2.getResult());

    Calculator calc = new SmartCalculator();
    calc = calc.input('1').input('+').input('2').input('=');
    assertEquals("3", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());
  }

  @Test
  public void testAddingMore() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('4').input('+').input('-');
    assertEquals("4-", c2.getResult());
    c2 = c2.input('=');
    assertEquals("0", c2.getResult());
    c2 = c2.input('=');
    assertEquals("-4", c2.getResult());
    c2 = c2.input('+').input('2');
    assertEquals("-4+2", c2.getResult());
  }

  @Test
  public void testMultiplyEqual() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('4').input('*');
    assertEquals("4*", c2.getResult());
    c2 = c2.input('=');
    assertEquals("16", c2.getResult());
    c2 = c2.input('=');
    assertEquals("64", c2.getResult());
    c2 = c2.input('+').input('2');
    assertEquals("64+2", c2.getResult());
  }

  @Test
  public void testMemory() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('+').input('=').input('2');
    assertEquals("2", c2.getResult());

    Calculator calc = new SmartCalculator();
    calc = calc.input('1').input('*').input('2').input('=').input('=');
    assertEquals("4", calc.getResult());

    calc = calc.input('-');
    assertEquals("4-", calc.getResult());
  }

  @Test
  public void testTwoOp() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('1').input('+').input('-').input('2');
    assertEquals("11-2", c2.getResult());
    c2 = c2.input('=');
    assertEquals("9", c2.getResult());

  }

  @Test
  public void testOp() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('3').input('2').input('+').input('=');
    assertEquals("64", c2.getResult());
    c2 = c2.input('+').input('2').input('4').input('=');
    assertEquals("88", c2.getResult());
  }

  @Test
  public void testExtra() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('3').input('2').input('+').input('2').input('4').input('=');
    assertEquals("56", c2.getResult());
    c2 = c2.input('=').input('=');
    assertEquals("104", c2.getResult());
  }

  @Test
  public void testCase() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('5').input('+').input('+').input('5');
    assertEquals("5+5", c2.getResult());
  }

  @Test
  public void testase() {
    Calculator c2 = new SmartCalculator();
    c2 = c2.input('1').input('+').input('1').input('=');
    assertEquals("2", c2.getResult());
    c2 = c2.input('+').input('2').input('4');
    assertEquals("2+24", c2.getResult());
  }

  @Override
  protected AbstractCalculator newCalc() {
    return new SmartCalculator();
  }
}