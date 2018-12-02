package calculator;

public class Calculator implements ExpressionCalculator {

    public boolean isExpressionValid(String expression) {
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (!((expression.charAt(i) >= '(' && expression.charAt(i) <= '+') ||
                    (expression.charAt(i) >= '-' && expression.charAt(i) <= '9'))) {
                return false;
            }
            if (expression.charAt(i) == '(') {
                num++;
            }

            if (expression.charAt(i) == ')') {
                if (num > 0) {
                    num--;
                } else {
                    return false;
                }
            }

            if (i == expression.length() - 1) {
                if (num != 0) {
                    return false;
                }
            }

            if (Character.isDigit(expression.charAt(i))) {
                int numPoint = 0;
                while (true) {
                    if (i == expression.length() - 1) {
                        break;
                    } else {
                        i++;
                    }
                    if (Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '.' && numPoint == 0)) {
                        if (expression.charAt(i) == '.') {
                            numPoint++;
                        }
                        if (i == expression.length() - 1) {
                            i--;
                            break;
                        }
                    } else {
                        if (numPoint > 1) {
                            return false;
                        }
                        break;
                    }
                }
            }

            if (expression.charAt(i) == '-' || expression.charAt(i) == '+' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                if (expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == '+'
                        || expression.charAt(i + 1) == '*' || expression.charAt(i + 1) == '/') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public double calculateExpression(String expression) throws IllegalArgumentException {
        if (!isExpressionValid(expression)) {
            throw new IllegalArgumentException(expression);
        }
        double leftSide;
        double rightSide;
        Double result = null;
        int position = 0;

        if (expression.charAt(position) == '-' && expression.charAt(position + 1) == '(') {
            return -calculateExpression(expression.substring(1));
        } else {
            if (expression.charAt(position) == '-') {
                position++;
                while (position < expression.length()) {
                    if (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.') {
                        position++;
                    } else {
                        break;
                    }
                }
                result = Double.parseDouble(expression.substring(0, position));
            }

            if (expression.charAt(0) == '+') {
                position++;
            }


            while (position < expression.length()) {

                if (expression.charAt(position) == '(') {
                    position++;
                    int tmpPosition = position;
                    int num = 1; //правильная ли скобка
                    while (true) {
                        if (expression.charAt(tmpPosition) == ')' && num == 1) {
                            break;
                        }
                        if (expression.charAt(tmpPosition) == ')') {
                            num--;
                        }
                        if (expression.charAt(tmpPosition) == '(') {
                            num++;
                        }
                        tmpPosition++;
                    }
                    if (result != null) {
                        result += calculateExpression(expression.substring(position, tmpPosition));
                    } else {
                        result = calculateExpression(expression.substring(position, tmpPosition));
                    }
                    position = tmpPosition;
                    if (position == expression.length() - 1) {
                        return result;
                    }
                    position++;
                }

                if (result != null) {
                    leftSide = result;
                } else {
                    while (position < expression.length()) {
                        if (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.') {
                            position++;
                        } else {
                            break;
                        }
                    }
                    leftSide = Double.parseDouble(expression.substring(0, position));
                    if (position == expression.length()) {
                        return Double.parseDouble(expression);
                    }
                }
                char operator = expression.charAt(position);

                if (expression.charAt(position + 1) == '(') {
                    position++;
                    rightSide = calculateExpression(expression.substring(position));
                    if (operator == '*') {
                        return leftSide * rightSide;
                    }
                    if (operator == '/') {
                        return leftSide / rightSide;
                    }
                    if (operator == '+') {
                        return leftSide + rightSide;
                    } else {
                        return leftSide - rightSide;
                    }
                } else {
                    if (operator == '*' || operator == '/') {
                        position++;
                        int tmpPosition = position;

                        while (position < expression.length()) {
                            if (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.') {
                                position++;
                            } else {
                                break;
                            }
                        }
                        rightSide = Double.parseDouble(expression.substring(tmpPosition, position));
                        if (operator == '*') {
                            result = leftSide * rightSide;
                        }
                        if (operator == '/') {
                            result = leftSide / rightSide;
                        }


                    } else {
                        rightSide = calculateExpression(expression.substring(position + 1));
                        if (operator == '+') {
                            return leftSide + rightSide;
                        } else return leftSide - rightSide;
                    }
                }
            }
        }
        return result;
    }
}