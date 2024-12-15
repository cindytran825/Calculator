package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SmartCalculator extends AbstractCalculator.
 * it should also do things that can be done the SimpleCalc.
 */
public class SmartCalculator extends AbstractCalculator {
  private String save;

  /**
   * super constructor that has the result and check computed fields.
   */
  public SmartCalculator() {
    super();
  }

  /**
   * private constructor that takes in string result and boolean checkComputed.
   *
   * @param result        returns the result as a string.
   * @param checkComputed boolean that checks if the result has already been computed.
   */
  private SmartCalculator(String result, boolean checkComputed) {
    super(result, checkComputed);
  }

  /**
   * this private constructor is used to save the last number.
   * it is used when there is more than one '=' and saves the operand in case '='.
   * is called without another operand inputted.
   */
  private SmartCalculator(String result, boolean checkComputed, String save) {
    this.result = result;
    this.checkComputed = checkComputed;
    this.save = save;
  }

  /**
   * this is called in the input method when the current character is '='.
   * this checks the cases for Smart calculator that isn't in Simple.
   *
   * @param s updated string.
   * @return a new SmartCalculator.
   */
  @Override
  protected Calculator checkEquals(String s) {
    List<Character> validOperators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '='));
    String r = this.result;
    String save = this.save;
    s = r;
    // if it has already been computed and only has the operand in the string.
    //ex. 32=
    //this takes the saved number and calls multiEqual method to calculate
    if (checkComputed && !hasOperator(s)) {
      multiEqual(s, save);
      r = multiEqual(s, save);
      return new SmartCalculator(r, checkComputed, save);
    }
    //if the last index has an operator.
    if (!checkComputed && validOperators.contains(s.charAt(s.length() - 1))) {
      // 3+=   ->6
      //save is the first number and operator
      save = s;
      oneOperand(s); //method that puts operand1 = operand2 and gets the operator
      String result = oneOperand(s);
      return new SmartCalculator(result, true, save);
    } else {
      s = r;
      //save is the last number and operator
      // 32+24 -> save +24
      save = s.substring(1, s.length());
      calculating(s);
      String newResult = calculating(s);
      return new SmartCalculator(newResult, true, save);
    }
  }

  /**
   * this checks if the first is '+'.
   * takes out the '+'
   *
   * @param s updated string.
   * @return the updated result. (without '+')
   */
  @Override
  protected String beginPlus(String s) {
    String r = this.result;
    r = s.substring(1);
    return r;
  }

  /**
   * called in input method.
   * if there are two operators next to each other.
   * it takes out the operator and replaces with the new one.
   *
   * @param s updated string.
   * @return the updated result.
   */
  @Override
  protected String doubleOperator(String s) {
    String r = this.result;
    String[] operate = s.split("(?<=\\D)|(?=\\D)");
    String[] num = s.split("[\\D]");
    s = "";
    r = s.concat(num[0]).concat(operate[operate.length - 1]);
    return r;
  }

  /**
   * called in input to return the type of calculator.
   *
   * @param result        updated string inputs.
   * @param checkComputed checks if the input has already been computed.
   * @return new SimpleCalculator.
   */
  @Override
  protected AbstractCalculator createTypeCalc(String result, boolean checkComputed) {
    return new SmartCalculator(result, checkComputed);
  }

  /**
   * this returns the empty types of Calculator.
   *
   * @return new SmartCalculator.
   */
  @Override
  protected AbstractCalculator mtCalc() {
    return new SmartCalculator();
  }

  /**
   * this is called when there is an operand and '='.
   * the last operand is saved in the checkEquals method and.
   * is called here to split and assign as operator and operand.
   *
   * @param s    is the updated string.
   * @param save is a string that saves the last operand and operator.
   * @return the new result.
   */
  private String multiEqual(String s, String save) {
    String r = this.result;
    int outcome = 0;
    //takes the saved and splits
    String[] splitSaved = save.split("[\\D]");
    String[] splitOperator = save.split("[\\d]");
    int operand1 = Integer.parseInt(s);
    String[] returnOperand = new String[]{};
    String[] returnOperator = new String[]{};
    //loops to get the second operand from the saved string.
    for (int i = 0; i < splitSaved.length; i++) {
      if (!splitSaved[i].isEmpty()) {
        returnOperand = new String[]{splitSaved[i]};
      }
    }
    //gets the operator in the saved string.
    for (int i = 0; i < splitOperator.length; i++) {
      if (!splitOperator[i].isEmpty()) {
        returnOperator = new String[]{splitOperator[i]};
      }
    }
    int operand2 = Integer.parseInt(returnOperand[0]);
    char operator = returnOperator[0].charAt(0);
    //calls to do the calculating.
    outcome = doCalc(operand1, operand2, operator);
    s = "";
    s = s.concat(String.valueOf(outcome));
    r = s;
    return r;

  }

  /**
   * called in the checkEquals method.
   * gets the operand and operator to compute.
   * for the case 3+=
   *
   * @param s is the string that was updated.
   * @return new string as the result.
   */
  private String oneOperand(String s) {
    int outcome = 0;
    String r = this.result;
    String[] num = s.split("[\\D]");
    //assigns the first and second operand as the same number.
    int operand1 = Integer.parseInt(num[0]);
    int operand2 = Integer.parseInt(num[0]);
    String[] emptyCheck = s.split("\\d");
    char operator = emptyCheck[emptyCheck.length - 1].charAt(
            emptyCheck[emptyCheck.length - 1].length() - 1);
    outcome = doCalc(operand1, operand2, operator);
    s = "";
    s = s.concat(String.valueOf(outcome));
    r = s;
    return r;
  }

}