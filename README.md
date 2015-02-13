# CalculatorCore
Calculator core by Java, and provide UI example(Jframe).

You can download all source code,or just download CalculatorCore.jar

####Use this calculator core to create calculator app  easily.
Just need to do below steps,you can finish your individual calculator.

  - 1.Build your User interface layout.
  - 2.Create  all number & function button.
  - 3.Use the core provide's method to count and get result.
  - 4.Show result on your User Interface.
 
All the arithmetic logic have been packaged in the calculator core, to achieve complete decoupling, UI needn't to know any about arithmetic logic.

####This core split calculator's button as three function block.
![](https://lh4.googleusercontent.com/-ZhnrNLE6EBM/VN3IDJMa9II/AAAAAAAADV0/-zg20vEn7qw/w1128-h917-no/calculator_setting.jpg)<br>
Let's see it as below:
- 1.Number button:
  - 0-9
- 2.Input function button: 
  - C,CE,Backspace,Dot,Reverse.
- 3.Count function button: 
  - is Immediate: Equal(=),Reciprocal(1/x),Sqrt
  - not Immediate: Four operations(+-*/),Remainder(%)

[is Immediate] mean this button will calculate result immediately,like:Sqrt(x),1/x.<br>
[not Immediate] mean this button will wait second input value then calculate result,like:x+y,x-y.

####Calculator Core main java
```
CalculatorCore core = new CalculatorCore();
double inputValue = core.inputNumber(number);
	   inputValue = core.useModifyInputValueFunction(function);
double outputValue = core.useCountOutputValueFunction(function);

```
####API provide interface for UI implements
```
public interface CalculatorUiClick {
	/**
	 * Number button click
	 */
	public void clickBtnNumber(int number);
	/**
	 * Input function button click
	 */
	public void clickBtnModifyInputFunction(MODIFY_INPUT_FUNC function);
	/**
	 * Count function button click
	 */
	public void clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC function);
	/**
	 * Count function button click(use your arithmeticCore)
	 */
	public void clickBtnCountOutputFunction(ArithmeticCore arithmeticCore);
}
```
Let's see example as below: this is UI  by JFrame.
```
public class CalculatorUIFrame extends JFrame implements CalculatorUiClick{
	
	private JTextField txtShow;
	private CalculatorCore core;

	......

	@Override
	public void clickBtnNumber(int number) {
		double inputValue = core.inputNumber(number);
		txtShow.setText(String.valueOf(inputValue));
	}
	@Override
	public void clickBtnModifyInputFunction(MODIFY_INPUT_FUNC function) {
		double inputValue = core.useModifyInputValueFunction(function);
		txtShow.setText(String.valueOf(inputValue));
	}
	@Override
	public void clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC function) {
		double outputValue = core.useCountOutputValueFunction(function);
		txtShow.setText(String.valueOf(outputValue));
	}
	@Override
	public void clickBtnCountOutputFunction(ArithmeticCore arithmeticCore) {
		double outputValue = core.useCountOutputValueFunction(arithmeticCore);
		txtShow.setText(String.valueOf(outputValue));
	}

}
```

####If the preset function not enough,you can also implements interface ArithmeticCore.
```
package edu.hyc.core.arithmetic;

public interface ArithmeticCore {
	
	public boolean isImmediate();
	public double arithmeticCore(double outputValue,double inputValue);
	
}
```

Let's see example as below: this sample is when button Click, will  count x^2.
```
import edu.hyc.core.arithmetic.ArithmeticCore;

public class SquareArithmeticCore implements ArithmeticCore {

	@Override
	public boolean isImmediate() {
		return true;
	}
	@Override
	public double arithmeticCore(double outputValue, double inputValue) {
		return outputValue*outputValue;
	}

}
```

```
JButton btnTest = new JButton("x^2");
btnTest.setToolTipText("TEST");
btnTest.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		clickBtnCountOutputFunction(new SquareArithmeticCore());
	}
});
```
