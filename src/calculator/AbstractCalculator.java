package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this is the abstract class that is extended in the SimpleCalculator class.
 * and the SmartCalculator class. it implements Calculator.
 */
public abstract class AbstractCalculator implements Calculator {
  protected String result;
  protected boolean checkComputed;

  /**
   * constructor for AbstractCalculator.
   * has the fields result and checkComputed.
   */
  public AbstractCalculator() {
    this.result = "";
    this.checkComputed = false;
  }

  /**
   * constructor that takes in the result and checkComputed.
   *
   * @param result        is the updated result.
   * @param checkComputed returns true if the input(or result) has been computed.
   */
  protected AbstractCalculator(String result, boolean checkComputed) {
    this.result = result;
    this.checkComputed = checkComputed;
  }

  /**
   * abstract method that is called in the input method.
   * and if the current character is '='.
   * @param s updated string.
   * @return a new Calculator.
   */
  protected abstract Calculator checkEquals(String s);

  /**
   * abstract method that checks if the string begins with a +.
   * @param s updated string.
   * @return the result string.
   */
  protected abstract String beginPlus(String s);

  /**
   * if there are 2 consecutive operators.
   * it calls this to replace the operator with the current one.
   * in the SmartCalculator class.
   * throws exception in the Simple.
   * @param s updated string.
   * @return the new string.
   */
  protected abstract String doubleOperator(String s);

  /**
   * method that takes in a character and checks the conditions.
   * checks all the exceptions(which is implemented in other methods).
   * it also calls the calculating method which calculates the result.
   * @param a is the input character.
   * @return new Calculator (created in createTypeCalc called in calc classes).
   */
  public Calculator input(char a) {
    String s = "";
    List<Character> validOperators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '='));
    String r = this.result;
    boolean checkComputed = this.checkComputed;
    //checks the clear and returns exception when input isn't valid
    if (a == 'C') {
      r = "";
      checkComputed = false;
      return mtCalc();
    }
    if (r.equals("") && validOperators.contains(a) && !checkComputed && a != '+') {
      throw new IllegalArgumentException("invalid input");
    }
    //check if its already been computed
    //and if the first is a number, then starts back empty
    if (checkComputed && !validOperators.contains(a)) {
      r = "";
      checkComputed = false;
    }
    //method to check if the input is valid
    checkCatch(a);
    if (a == '=') {
      //abstract method
      checkEquals(s);
      Calculator newR = checkEquals(s);
      return newR;
    }
    else {
      //appends the input into the string
      s = r.concat(String.valueOf(a));
      r = this.result.concat(s);
      r = s;
      result = r;
      checkFirstOperator(s);
      //return new object
      if (s.charAt(0) == '+') {
        beginPlus(s);
        r = beginPlus(s);
      }
      else if (hasOperator(String.valueOf(a))
              && hasOperator(String.valueOf(s.charAt(s.length() - 2)))) {
        r = doubleOperator(s);
        return createTypeCalc(r, checkComputed);
      }
      //checks if the number overflows
      if (!this.hasOperator(s)) {
        catchOverflow(s);
        String newCatchOverflow = catchOverflow(r);
        return createTypeCalc(newCatchOverflow, false);

      } else if (operateAmount(s) >= 2) {
        this.calculating(s);
        String newResult = calculating(s);
        return createTypeCalc(newResult, checkComputed);
      }
      checkComputed = false;
    }

    return createTypeCalc(r, checkComputed);
  }


  /**
   * this creates a new calculator in the type of calculator(class).
   *
   * @param result        updated string inputs.
   * @param checkComputed checks if the input has already been computed.
   * @return new Calculator.
   */
  protected abstract AbstractCalculator createTypeCalc(String result, boolean checkComputed);

  /**
   * this checks if the input is valid.
   *
   * @param a inputed character.
   */
  private void checkCatch(char a) {
    String s = "";
    List<Character> validInput = new ArrayList<Character>(Arrays.asList('0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '='));
    String r = this.result;
    if (!validInput.contains(a)) {
      throw new IllegalArgumentException("isn't a valid input");
    }

  }

  /**
   * this checks if the first is an operator and throws exception.
   *
   * @param s string.
   * @return a new Calculator.
   */
  private Calculator checkFirstOperator(String s) {
    List<Character> validOperators = new ArrayList<Character>(Arrays.asList('-', '*', '='));
    String r = this.result;
    //&& result.charAt(0) != '-'
    if (validOperators.contains(r.charAt(0)) && result.charAt(0) != '-') {
      throw new IllegalArgumentException("can't input operator first");
    }

    r = "";
    return createTypeCalc(r, checkComputed);
  }

  /**
   * counts the amount of operators in the string.
   *
   * @param s updated string.
   * @return the amount of operators in the string.
   */
  //this checks if the string has an operator
  protected boolean hasOperator(String s) {
    List<Character> operatorsList = new ArrayList<Character>(Arrays.asList('+', '-', '*'));
    for (int i = 0; i < s.length(); i++) {
      char operator = s.charAt(i);
      if (operatorsList.contains(operator)) {
        return true;
      }
    }
    return false;
  }

  //counts the amount of operators in the string.
  private int operateAmount(String s) {
    List<Character> operatorsList = new ArrayList<Character>(Arrays.asList('+', '-', '*'));
    int amount = 0;
    for (int i = 1; s.length() > i; i++) {
      char operator = s.charAt(i);
      if (operatorsList.contains(operator)) {
        amount += 1;
      }
    }
    return amount;
  }

  //this catches the overflow (checks if a number is over 32 bits)
  protected String catchOverflow(String s) {
    String r = this.result;

    if (Long.parseLong(this.result) > Integer.MAX_VALUE) {
      r = s.substring(0, s.length() - 1);
      throw new IllegalArgumentException("operand overflow");
    }
    return r;
  }

  protected abstract AbstractCalculator mtCalc();

  protected boolean isNegative(String s) {
    return (s.charAt(0) == '-');
  }

  /**
   * this splits the string to get the first and second operand.
   * splits to get the operator.
   * calls the doCalc() method to actually get the outcome.
   * and then goes back to return the outcome as a string.
   *
   * @param s updated string.
   * @return the result as a string.
   */
  protected String calculating(String s) {
    int outcome = 0;
    String[] num = s.split("[\\D]");
    String[] getOperand1 = new String[]{};
    String[] getOperand2 = new String[]{};
    boolean firstOp = false;
    for (int i = 0; i < num.length; i++) {
      if (!num[i].isEmpty()) {
        if (!firstOp) {
          getOperand1 = new String[]{num[i]};
          firstOp = true;
        }
        else {
          getOperand2 = new String[]{num[i]};
        }
      }
    }
    int operand1 = Integer.parseInt(getOperand1[0]);
    int operand2 = Integer.parseInt(getOperand2[0]);

    String[] getOperator = new String[]{};
    String[] secondOperator = new String[]{};
    String[] emptyCheck = s.split("\\d");
    boolean taken = false;
    for (int i = 0; i < emptyCheck.length; i++) {
      if (!emptyCheck[i].isEmpty()) {
        if (!taken) {
          getOperator = new String[]{emptyCheck[i]};
          taken = true;
        } else {
          secondOperator = new String[]{emptyCheck[i]};
        }
      }
    }
    char operator = getOperator[0].charAt(0);
    if (isNegative(s)) {
      operator = secondOperator[0].charAt(0);
      operand1 = Integer.parseInt(getOperator[0].concat(getOperand1[0]));
    }

    String r = this.result;
    outcome = doCalc(operand1, operand2, operator);
    if (this.operateAmount(s) > 1) {
      s = "";
      s = s.concat(String.valueOf(outcome)).concat(secondOperator[0]);
      r = s;
    } else {
      s = "";
      s = s.concat(String.valueOf(outcome));
      r = s;
    }
    return r;
  }

  /**
   * this is called in the calculating method.
   * does the calculating and also returns 0 if the.
   * number computed is above the max value.
   *
   * @param operand1 first operand.
   * @param operand2 second operand.
   * @param operator operator.
   * @return the number after computed.
   */
  protected int doCalc(int operand1, int operand2, char operator) {
    int outcome = 0;
    switch (operator) {
      case '+':
        if (operand1 > Integer.MAX_VALUE - operand2) {
          //checks if the computed number is above the max value
        } else {
          outcome = operand1 + operand2;
        }
        break;
      case '-':
        if (operand1 < Integer.MIN_VALUE + operand2) {
          //checks if the difference is less then min value
        } else {
          outcome = operand1 - operand2;
        }
        break;
      case '*':
        if (operand1 > Integer.MAX_VALUE / operand2 || operand1 < Integer.MIN_VALUE / operand2) {
        //checks if the computed is above or below value
        } else {
          outcome = operand1 * operand2;
        }
        break;
      default:
        break;
    }
    return outcome;
  }


  /**
   * this get the result after computing.
   *
   * @return a string that is the result after calculating.
   */
  public String getResult() {
    return this.result;
  }

}