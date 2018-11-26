package calculator;

public class Calculator implements ExpressionCalculator {

    private String str;

    public boolean isExpressionValid(String expression) {
        int num = 0;
        str = expression;
        int leng = str.length();
        for (int i=0; i<leng; i++){
            if (str.charAt(i) == '-' || str.charAt(i) == '+' || str.charAt(i) == '*' || str.charAt(i) == '/') {
                if (str.charAt(i+1) == '-' || str.charAt(i+1) == '+' || str.charAt(i+1) == '*' || str.charAt(i+1) == '/') return false;
            }
            if (str.charAt(i) == ')') num--;
            if (str.charAt(i) == '(') num++;
        }
        return num == 0;
    }

    public double calculateExpression(String expression) throws IllegalArgumentException{
        if (!isExpressionValid(expression))
            throw new IllegalArgumentException();
        str = expression;
        double result=0;
        if (str.startsWith("(") && str.endsWith(")")) {
            return calculateExpression(str.substring(1, str.length() - 1));
        } else {
            int position = 0;
            if (str.charAt(position) == '-' && str.charAt(position + 1) == '(') {
                return calculateExpression(str.substring(1,str.length()-1));
            } else {
                if (str.charAt(position) == '-'){
                    position++;
                    while (position < str.length() - 1) {
                        if (Character.isDigit(str.charAt(position)) || str.charAt(position) == '.'){
                            position++;
                        } else break;
                    }
                    result = Double.parseDouble(str.substring(0, position));
                }

                while (position < str.length()) {
                    double leftSide;
                    if (result != 0) {
                        leftSide = result;
                    } else {
                        while (position < str.length()) {
                            if (Character.isDigit(str.charAt(position)) || str.charAt(position) == '.') {
                                position++;
                            } else break;
                        }
                        leftSide = Double.parseDouble(str.substring(0, position));
                    }
                    char operator = str.charAt(position);
                    double rightSide;
                    if (operator == '*' || operator == '/') {
                        position++;
                        int tmpPosition = position;

                        while (position < str.length()) {
                            if (Character.isDigit(str.charAt(position)) || str.charAt(position) == '.') {
                                position++;
                            } else break;
                        }
                        rightSide = Double.parseDouble(str.substring(tmpPosition, position));
                        if (operator == '*') result = leftSide * rightSide;
                        if (operator == '/') result = leftSide / rightSide;


                    } else {
                        rightSide = calculateExpression(str.substring(position + 1));
                        if (operator == '+') {
                            result = leftSide + rightSide;
                        } else  result = leftSide - rightSide;
                        return result;
                    }

                }
            }
        }
        return result;
    }
}
