package calculator;

public interface ExpressionCalculator {
    /**
     * Checks is string expression is valid
     * and can be processed by method {@link ExpressionCalculator#calculateExpression(String)}
     *
     * @param expression expression to check
     * @return true if expression valid, otherwise false
     * @see ExpressionCalculator#calculateExpression(String)
     */
    boolean isExpressionValid(String expression);

    /**
     * Calculates value of simple math expression
     *
     * @param expression expression to calculate
     * @return expression value
     * @throws IllegalArgumentException if expression can't be processed,
     *                                  use {@link ExpressionCalculator#isExpressionValid(String)} to check
     * @see ExpressionCalculator#isExpressionValid(String)
     */
    double calculateExpression(String expression) throws IllegalArgumentException;
}
