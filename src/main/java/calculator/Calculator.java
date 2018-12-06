package calculator;

public class Calculator implements ExpressionCalculator {

    private int findDuzchka(String expression, int i) {
        int tmpPosition = i;
        int num = 0;

        while (tmpPosition < expression.length()) {
            if (expression.charAt(tmpPosition) == ')') {
                if (expression.charAt(tmpPosition) == ')' && num == 0) {
                    break;
                }
                num--;
            }
            if (expression.charAt(tmpPosition) == '(') {
                num++;
            }
            tmpPosition++;
        }
        return tmpPosition;
    }

    public boolean  isExpressionValid(String expression) {
        if (expression.length() <=0){
            return false;
        }
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (!((expression.charAt(i) >= '(' && expression.charAt(i) <= '+') ||
                    (expression.charAt(i) >= '-' && expression.charAt(i) <= '9'))) {
                return false;
            }
            if (expression.charAt(i) == '(') {
                if (expression.charAt(i+1) == ')' || expression.charAt(i+1) == '.'){
                    return false;
                }
                num++;
            }

            if (expression.charAt(i) == ')') {
                if (num > 0) {
                    num--;
                } else {
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
                            if (!Character.isDigit(expression.charAt(i+1))){
                                return false;
                            }
                        }
                        if (i == expression.length() - 1) {
                            i--;
                            break;
                        }
                        if (expression.charAt(i+1) == '(') {
                            return false;
                        }
                    } else {
                        if (expression.charAt(i) == '.') {
                            return false;
                        }
                        i--;
                        break;
                    }
                }
            }

            if (expression.charAt(i) == '-' || expression.charAt(i) == '+' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                if (expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == '+'
                        || expression.charAt(i + 1) == '*' || expression.charAt(i + 1) == '/'
                        || expression.charAt(i+1) == '.') {
                    return false;
                }
            }
        }
        return num == 0;
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

        if (expression.charAt(0) == '+') {
            position++;
        }

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
            while (position < expression.length()) {
                if (expression.charAt(position) == '(') {
                    position++;
                    int tmpPosition = findDuzchka(expression, position);

                    result = calculateExpression(expression.substring(position, tmpPosition));
                    position = tmpPosition;
                    if (position == (expression.length() - 1)) {
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
                position++;

                if (expression.charAt(position) == '(') {
                    if (operator == '*' || operator == '/') {
                        position++;
                        int tmpPosition = findDuzchka(expression, position);
                        rightSide = calculateExpression(expression.substring(position, tmpPosition));
                        position = tmpPosition;
                        position++;
                        if (operator == '*') {
                            result = leftSide * rightSide;
                        } else {
                            result = leftSide / rightSide;
                        }

                    } else {
                        position++;
                        int tmpPosition = findDuzchka(expression, position);
                        if (tmpPosition == (expression.length() - 1)) {
                            rightSide = calculateExpression(expression.substring(position, tmpPosition));
                            if (operator == '+') {
                                return leftSide + rightSide;
                            }
                            if (operator == '-') {
                                return leftSide - rightSide;
                            }
                        }
                        if (expression.charAt(tmpPosition + 1) == '*' || expression.charAt(tmpPosition + 1) == '/') {
                            tmpPosition++;
                            int num = 0;
                            while (true) {
                                tmpPosition++;
                                if (expression.charAt(tmpPosition) == '-' || expression.charAt(tmpPosition) == '+') {
                                    if (num == 0) {
                                        break;
                                    }
                                }
                                if (expression.charAt(tmpPosition) == '(') {
                                    num++;
                                }
                                if (expression.charAt(tmpPosition) == ')') {
                                    if (num > 0) {
                                        num--;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        rightSide = calculateExpression(expression.substring(position, tmpPosition));
                        if (operator == '+') {
                            result = leftSide + rightSide;
                        } else {
                            result = leftSide - rightSide;
                        }

                        position = tmpPosition;
                    }
                } else {
                    if (operator == '*' || operator == '/') {
                        int tmpPosition = position;
                        while (position < expression.length()) {
                            if (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.') {
                                position++;
                            } else {
                                break;
                            }
                        }
                        if (position == expression.length()) {
                            rightSide = Double.parseDouble(expression.substring(tmpPosition));
                        } else {
                            rightSide = Double.parseDouble(expression.substring(tmpPosition, position));
                        }
                        if (operator == '*') {
                            result = leftSide * rightSide;
                        }
                        if (operator == '/') {
                            result = leftSide / rightSide;
                        }
                    } else {
                        int tmpPosition = position;
                        while (tmpPosition < expression.length()) {
                            if (Character.isDigit(expression.charAt(tmpPosition)) || expression.charAt(tmpPosition) == '.') {
                                tmpPosition++;
                            } else {
                                break;
                            }
                        }
                        if (tmpPosition == expression.length()) {
                            rightSide = Double.parseDouble(expression.substring(position));
                            if (operator == '+') {
                                return leftSide + rightSide;
                            }
                            if (operator == '-') {
                                return leftSide - rightSide;
                            }
                        }
                        if (expression.charAt(tmpPosition) == '*' || expression.charAt(tmpPosition) == '/') {
                            int num = 0;
                            while (true) {
                                if (tmpPosition == expression.length() - 1) {
                                    break;
                                } else {
                                    tmpPosition++;
                                    if (expression.charAt(tmpPosition) == '-' || expression.charAt(tmpPosition) == '+') {
                                        if (num == 0) {
                                            break;
                                        }
                                    }
                                    if (expression.charAt(tmpPosition) == '(') {
                                        num++;
                                    }
                                    if (expression.charAt(tmpPosition) == ')') {
                                        if (num > 0) {
                                            num--;
                                        }
                                    }

                                }
                            }
                        }
                        if (tmpPosition == expression.length() - 1) {
                            rightSide = calculateExpression(expression.substring(position));
                        } else {
                            if (operator == '+') {
                                rightSide = calculateExpression(expression.substring(position, tmpPosition));
                            } else {
                                rightSide = calculateExpression(expression.substring(position - 1, tmpPosition));
                            }
                        }
                        result = leftSide + rightSide;
                        position = tmpPosition;
                    }
                }
            }
        }
        return result;
    }
}
