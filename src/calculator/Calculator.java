package calculator;

/**
 * represents a calculator.
 * it has the methods input and getResult.
 */
public interface Calculator {
  /**
   * method that takes in a character and checks the conditions.
   * checks all the exceptions(which is implemented in other methods).
   * it also calls the calculating method which calculates the result.
   *
   * @param a is the input character.
   * @return new Calculator (created in createTypeCalc called in calc classes).
   */
  public Calculator input(char a);

  /**
   * this gets the result of the computation.
   *
   * @return the result.
   */
  public String getResult();


}