package calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorTest {

    private ExpressionCalculator calculator;

    private static final String expression0 = "2+2";
    private static final String expression1 = "(2*3)/(-2)";
    private static final String expression2 = "-3*(2+2)";
    private static final String expression3 = "+(2+2)";
    private static final String expression4 = "(2.3+2)";
    private static final String expression5 = "-(2+2)";
    private static final String expression6 = "(2+(+2-7))";
    private static final String expression7 = "2.1*3";
    private static final String expression8 = "-3";
    private static final String expression9 = "2-3+(5-1)";
    private static final String expression10 = "(5-3*(5-4)-1)";

    private static final Double answer0 = 4d;
    private static final Double answer1 = -3d;
    private static final Double answer2 = -12d;
    private static final Double answer3 = 4d;
    private static final Double answer4 = 4.3d;
    private static final Double answer5 = -4d;
    private static final Double answer6 = -3d;
    private static final Double answer7 = 6.3d;
    private static final Double answer8 = -3d;
    private static final Double answer9 = 3d;
    private static final Double answer10 = 1d;

    private static final String expression_0 = "2+2";
    private static final String expression_1 = "(2*3)/(-2)";
    private static final String expression_2 = "-3*(2+2)";
    private static final String expression_3 = "2*+2";
    private static final String expression_4 = "(2+2";
    private static final String expression_5 = "2.5.3+2";
    private static final String expression_6 = "2.+2";
    private static final String expression_7 = "2 + 2";
    private static final String expression_8 = "";
    private static final String expression_9 = "(2*3)/-2";
    private static final String expression_10 = "(5-3*(5-4)-1)";
    private static final String expression_11 = "2+abc";

    private static final boolean answer_0 = true;
    private static final boolean answer_1 = true;
    private static final boolean answer_2 = true;
    private static final boolean answer_3 = false;
    private static final boolean answer_4 = false;
    private static final boolean answer_5 = false;
    private static final boolean answer_6 = false;
    private static final boolean answer_7 = false;
    private static final boolean answer_8 = false;
    private static final boolean answer_9 = false;
    private static final boolean answer_10 = true;
    private static final boolean answer_11 = false;


    @Before
    public void setUp()
    {
        calculator = CalculatorFactory.getCalculator();
    }

    @Test
    public void testCalculateExpression_0() {
        double actual = calculator.calculateExpression(expression0);
        Assert.assertEquals(answer0, actual, 0.001d);
    }

    @Test
    public void testCalculateExpression_1() {
        double actual = calculator.calculateExpression(expression1);
        Assert.assertEquals(answer1, actual, 0.001d);
    }

    @Test
    public void testCalculateExpression_2() {
        double actual = calculator.calculateExpression(expression2);
        Assert.assertEquals(answer2, actual, 0.001d);
    }

    @Test
    public void testCalculateExpression_3() {
        double actual = calculator.calculateExpression(expression3);
        Assert.assertEquals(answer3, actual, 0.001d);
    }

    @Test
    public void testCalculateExpression_4() {
        double actual = calculator.calculateExpression(expression4);
        Assert.assertEquals(answer4, actual, 0.001d);
    }
    @Test
    public void testCalculateExpression_5() {
        double actual = calculator.calculateExpression(expression5);
        Assert.assertEquals(answer5, actual, 0.001d);
    }

    @Test
    public void testCalculateExpression_6() {
        double actual = calculator.calculateExpression(expression6);
        Assert.assertEquals(answer6, actual, 0.001d);
    }
    @Test
    public void testCalculateExpression_7() {
        double actual = calculator.calculateExpression(expression7);
        Assert.assertEquals(answer7, actual, 0.001d);
    }
    @Test
    public void testCalculateExpression_8() {
        double actual = calculator.calculateExpression(expression8);
        Assert.assertEquals(answer8, actual, 0.001d);
    }
    @Test
    public void testCalculateExpression_9() {
        double actual = calculator.calculateExpression(expression9);
        Assert.assertEquals(answer9, actual, 0.001d);
    }
    @Test
    public void testCalculateExpression_10() {
        double actual = calculator.calculateExpression(expression10);
        Assert.assertEquals(answer10, actual, 0.001d);
    }




    @Test
    public void testIsValidExpression_0() {
        boolean actual = calculator.isExpressionValid(expression_0);
        Assert.assertEquals(answer_0, actual);
    }

    @Test
    public void testIsValidExpression_1()
    {
        boolean actual = calculator.isExpressionValid(expression_1);
        Assert.assertEquals(answer_1, actual);
    }

    @Test
    public void testIsValidExpression_2()
    {
        boolean actual = calculator.isExpressionValid(expression_2);
        Assert.assertEquals(answer_2, actual);
    }

    @Test
    public void testIsValidExpression_3()
    {
        boolean actual = calculator.isExpressionValid(expression_3);
        Assert.assertEquals(answer_3, actual);
    }
    @Test
    public void testIsValidExpression_4()
    {
        boolean actual = calculator.isExpressionValid(expression_4);
        Assert.assertEquals(answer_4, actual);
    }
    @Test
    public void testIsValidExpression_5()
    {
        boolean actual = calculator.isExpressionValid(expression_5);
        Assert.assertEquals(answer_5, actual);
    }
    @Test
    public void testIsValidExpression_6()
    {
        boolean actual = calculator.isExpressionValid(expression_6);
        Assert.assertEquals(answer_6, actual);
    }
    @Test
    public void testIsValidExpression_7()
    {
        boolean actual = calculator.isExpressionValid(expression_7);
        Assert.assertEquals(answer_7, actual);
    }
    @Test
    public void testIsValidExpression_8()
    {
        boolean actual = calculator.isExpressionValid(expression_8);
        Assert.assertEquals(answer_8, actual);
    }
    @Test
    public void testIsValidExpression_9()
    {
        boolean actual = calculator.isExpressionValid(expression_9);
        Assert.assertEquals(answer_9, actual);
    }
    @Test
    public void testIsValidExpression_10()
    {
        boolean actual = calculator.isExpressionValid(expression_10);
        Assert.assertEquals(answer_10, actual);
    }
    @Test
    public void testIsValidExpression_11()
    {
        boolean actual = calculator.isExpressionValid(expression_11);
        Assert.assertEquals(answer_11, actual);
    }
}
