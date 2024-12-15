package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this represents a simple single calculator.
 * this has the public method input and getresult.
 * this class also implements the interface calculator.
 * and extends AbstractCalculator.
 */
public class SimpleCalculator extends AbstractCalculator {
  /**
   * this constructor is from the AbstractConstructor which takes in nothing.
   * which as the result and checkComputed fields.
   */
  public SimpleCalculator() {
    super();
  }

  /**
   * private constructor for SimpleCalculator.
   * it takes in the result and boolean checkComputed.
   *
   * @param result        this returns the result after the inputs are added in.
   * @param checkComputed this returns true if the inputs have already been computed.
   */
  private SimpleCalculator(String result, boolean checkComputed) {
    super(result, checkComputed);
  }

  /**
   * this is called in the abstract method.
   * because I wasn't able to return the specific type of calculator.
   * I called this method to return a new Calculator.
   *
   * @param result        is the result either after it has been computed or appended.
   * @param checkComputed this returns true if the inputs have already been computed.
   * @return a new SimpleCalculator. This also ensures that it doesn't mutate.
   */
  @Override
  protected AbstractCalculator createTypeCalc(String result, boolean checkComputed) {
    return new SimpleCalculator(result, checkComputed);
  }

  /**
   * just like the createTypeCalc method, I use this to return the specific.
   * type of calculator.
   *
   * @return a new empty SimpleCalculator.
   */
  @Override
  protected AbstractCalculator mtCalc() {
    return new SimpleCalculator();
  }

  /**
   * called in the input method and if the current character is '='.
   * this calls the calculating method if it hasn't been computed and.
   * if it has, it just returns the result.
   *
   * @param s is the string that has been updated.
   * @return a new SimpleCalculator with the new result.
   */
  protected Calculator checkEquals(String s) {
    List<Character> operatorsList = new ArrayList<Character>(Arrays.asList('+', '-', '*'));
    String r = this.result;
    //already computed
    if (checkComputed) {
      return this;
    } else {
      s = r;
      if (operatorsList.contains(s.charAt(s.length() - 1))) {
        throw new IllegalArgumentException("invalid input");
      }
      //call calculate method
      this.calculating(s);
      String newResult = calculating(s);

      return new SimpleCalculator(newResult, true);
    }
  }

  /**
   * called in the input method.
   * if the string begins with a '+'.
   * it throws an exception.
   *
   * @param s is the string that has been updated.
   * @return IllegalArgumentException.
   */
  @Override
  protected String beginPlus(String s) {
    throw new IllegalArgumentException("can't input operator first");
  }

  /**
   * called in input method.
   * if there are two operators next to each other, it returns.
   * exception.
   *
   * @param s is the string that has been updated.
   * @return IllegalArgumentException.
   */
  @Override
  protected String doubleOperator(String s) {
    throw new IllegalArgumentException("invalid input");
  }

}