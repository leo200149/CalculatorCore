package edu.hyc.ui;

import edu.hyc.core.CalculatorCore.COUNT_OUTPUT_FUNC;
import edu.hyc.core.CalculatorCore.MODIFY_INPUT_FUNC;
import edu.hyc.core.arithmetic.ArithmeticCore;

public interface CalculatorUiClick {
	/**
	 * 按0~9按鈕時需要做的事
	 * example:
	 * double inputValue = core.inputNumber(number);\n
	 * txtShow.setText(String.valueOf(inputValue));
	 * @param number
	 */
	public void clickBtnNumber(int number);
	/**
	 * 按修改輸入值功能按鈕時需要做的事(C,CE,Backspace,dot)
	 * example:
	 * double inputValue = core.useModifyInputValueFunction(function);\n
	 * txtShow.setText(String.valueOf(inputValue));
	 * @param function
	 */
	public void clickBtnModifyInputFunction(MODIFY_INPUT_FUNC function);
	/**
	 * 按計算功能按鈕時需要做的事(四則運算,sqrt...)
	 *  example:
	 *	double outputValue = core.useCountOutputValueFunction(function);
	 *	txtShow.setText(String.valueOf(outputValue));
	 * @param function
	 */
	public void clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC function);
	/**
	 * 按計算功能按鈕時需要做的事(自訂擴充算法)
	 *  example:
	 *	double outputValue = core.useCountOutputValueFunction(arithmeticCore);
	 *	txtShow.setText(String.valueOf(outputValue));
	 * @param arithmeticCore
	 */
	public void clickBtnCountOutputFunction(ArithmeticCore arithmeticCore);
}
