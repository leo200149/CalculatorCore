package edu.hyc.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.hyc.core.arithmetic.ArithmeticCore;
/**
 * Calculator Core
 * 計算機核心
 * 封裝計算邏輯及結果
 * 提供 
 * 1.基本四則運算
 * 2.開根號,倒數,取餘數
 * 3.正負反向,小數點操作,backspace,CE,Clear功能
 * @author leo_Chen
 * @version 1.0
 */
public class CalculatorCore {

	/**
	 * 計算值清單
	 */
	private List<InputValueFunction> values;
	/**
	 * 計算結果 輸出
	 */
	private double outputValue;
	/**
	 * 輸入計算值
	 */
	private double inputValue;
	/**
	 * 是否為小數點狀態
	 */
	private boolean isDot;

	/**
	 * 初始化
	 */
	public CalculatorCore() {
		reset();
	}

	/**
	 * 輸入數字0~9
	 * @param value
	 * @return 輸入計算值inputValue
	 */
	public double inputNumber(int value) {
		if (checkIsTheNewInputNumber()) {
			reset();
		}
		if (isDot) {
			inputValue = this.doDotValue(inputValue, value);
		} else {
			inputValue = inputValue * 10 + value;
		}
		return inputValue;
	}
	
	/**
	 * 使用修改輸入值的功能
	 * 1.DOT小數點
	 * 2.CE清除此次輸入值
	 * 3.C清除並重設計算器
	 * 4.BACKSPACE 刪除最後一個數字
	 * 5.REVERSE 數字正負反向
	 * @param function
	 * @return 輸入計算值inputValue
	 */
	public double useModifyInputValueFunction(MODIFY_INPUT_FUNC function) {
		isDot = false;
		switch (function) {
		case DOT:
			isDot = true;
			break;
		case CE:
			inputValue = 0;
			break;
		case C:
			reset();
			break;
		case BACKSPACE:
			doBackspace();
			break;
		case REVERSE:
			inputValue *= -1;
			break;
		default:
			break;
		}
		return inputValue;
	}
	
	/**
	 * 使用計算結果的功能
	 * 1.四則運算
	 * 2.開根號
	 * 3.倒數
	 * 4.等於
	 * @param function
	 * @return 計算結果 輸出outputValue
	 */
	public double useCountOutputValueFunction(COUNT_OUTPUT_FUNC function) {
		isDot = false;
		switch (function) {
		case SQRT:
		case RECIPROCAL:
		case EQUAL:
			countOutputValueByIsImmediateFunction(function,null);
			break;
		default:
			countOutputValueByNextStepFunction(function,null);
			break;
		}
		return outputValue;
	}
	
	/**
	 * 使用計算結果的功能
	 * for 客制擴充之計算邏輯
	 * @param function
	 * @return 計算結果 輸出outputValue
	 */
	public double useCountOutputValueFunction(ArithmeticCore arithmeticCore) {
		isDot = false;
		if(arithmeticCore.isImmediate()){
			countOutputValueByIsImmediateFunction(arithmeticCore);
		}else{
			countOutputValueByNextStepFunction(arithmeticCore);
		}
		return outputValue;
	}
	
	/**
	 * 計算需要馬上得到結果的功能
	 * 如開根號,倒數,等於
	 * @param function
	 */
	private void countOutputValueByIsImmediateFunction(
			COUNT_OUTPUT_FUNC function,ArithmeticCore arithmeticCore) {
		InputValueFunction inputValueFunction = new InputValueFunction(inputValue, function, true);
		inputValueFunction.setArithmeticCore(arithmeticCore);
		values.add(inputValueFunction);
		outputValue = count();
		inputValue = outputValue;
	}

	/**
	 * 計算需要馬上得到結果的功能
	 * for 客制擴充之計算邏輯
	 * @param function
	 */
	private void countOutputValueByIsImmediateFunction(ArithmeticCore arithmeticCore) {
		countOutputValueByIsImmediateFunction(COUNT_OUTPUT_FUNC.OTHER_ARITHMETIC, arithmeticCore);
	}
	
	/**
	 * 計算上一筆總和,此次輸入值待下次計算
	 * 如四則運算
	 * @param function
	 */
	private void countOutputValueByNextStepFunction(COUNT_OUTPUT_FUNC function,ArithmeticCore arithmeticCore) {
		InputValueFunction inputValueFunction = new InputValueFunction(inputValue, function, false);
		inputValueFunction.setArithmeticCore(arithmeticCore);
		values.add(inputValueFunction);
		outputValue = count();
		inputValue = 0;
	}
	
	/**
	 * 計算上一筆總和,此次輸入值待下次計算
	 * for 客制擴充之計算邏輯
	 * @param function
	 */
	private void countOutputValueByNextStepFunction(ArithmeticCore arithmeticCore) {
		countOutputValueByNextStepFunction(COUNT_OUTPUT_FUNC.OTHER_ARITHMETIC, arithmeticCore);
	}
	
	/**
	 * 重置計算器
	 */
	public void reset() {
		outputValue = 0;
		inputValue = 0;
		isDot = false;
		values = new ArrayList<InputValueFunction>();
	}

	/**
	 * 開始計算
	 * @return 計算結果
	 */
	private double count() {
		double finalValue = 0;

		Iterator<InputValueFunction> valuesIt = values.iterator();
		InputValueFunction firstValue = valuesIt.next();
		finalValue = firstValue.getValue();
		InputValueFunction beforeValue = firstValue;
		if (firstValue.isImmediate()) {
			for (InputValueFunction valueFunc : values) {
				finalValue = countMethodCore(finalValue, beforeValue, valueFunc);
				beforeValue = valueFunc;
			}
		} else {
			while (valuesIt.hasNext()) {
				InputValueFunction valueFunc = valuesIt.next();
				finalValue = countMethodCore(finalValue, beforeValue, valueFunc);
				beforeValue = valueFunc;
			}
		}
		return finalValue;
	}

	
	/**
	 * 計算器核心
	 * @param finalValue
	 * @param beforeValue
	 * @param nowValue
	 * @return 計算結果
	 */
	private double countMethodCore(double finalValue,
			InputValueFunction beforeValue, InputValueFunction nowValue) {
		if (beforeValue != null && !beforeValue.isImmediate()) {
			finalValue = arithmeticCore(finalValue, nowValue.getValue(),
					beforeValue);
		}
		if (nowValue.isImmediate()) {
			finalValue = arithmeticCore(finalValue, nowValue.getValue(),
					nowValue);
		}
		return finalValue;
	}

	/**
	 * 算法邏輯
	 * @param finalValue
	 * @param newValue
	 * @param inputValueFunction
	 * @return
	 */
	private double arithmeticCore(double finalValue, double newValue,
			InputValueFunction inputValueFunction) {
		COUNT_OUTPUT_FUNC function = inputValueFunction.getFunction();
		switch (function) {
		case PLUS:
			finalValue += newValue;
			break;
		case REDUCE:
			finalValue -= newValue;
			break;
		case MULITPLY:
			finalValue *= newValue;
			break;
		case DIVIDE:
			if (newValue != 0)
				finalValue /= newValue;
			break;
		case REMAINDER:
			if (newValue != 0)
				finalValue %= newValue;
			break;
		case SQRT:
			if (finalValue >= 0)
				finalValue = Math.sqrt(finalValue);
			break;
		case RECIPROCAL:
			if (finalValue != 0)
				finalValue = 1 / finalValue;
			break;
		case EQUAL:
			break;
		case OTHER_ARITHMETIC:
			if(inputValueFunction.getArithmeticCore()!=null){
				ArithmeticCore arithmeticCore = inputValueFunction.getArithmeticCore();
				finalValue = arithmeticCore.arithmeticCore(finalValue, newValue);
			}
			break;
		default:
			break;
		}
		return finalValue;
	}
	
	
	/**
	 * 處理小數點輸入數值
	 * @param inputValue
	 * @param value
	 * @return
	 */
	private double doDotValue(double inputValue, int value) {
		String newValueStr = String.valueOf(inputValue);
		if (inputValue % 1 != 0) {
			newValueStr += String.valueOf(value);
			inputValue = Double.parseDouble(newValueStr);
		} else {
			inputValue += (double) value / 10;
		}
		return inputValue;
	}

	/**
	 * 處理刪除鍵數值
	 */
	private void doBackspace() {
		String newValueStr = String.valueOf(inputValue);
		if (newValueStr.indexOf("E") > -1) {
			inputValue = inputValue / 10;
		} else if (inputValue % 1 == 0) {
			inputValue = inputValue / 10;
			newValueStr = String.valueOf(inputValue);
			newValueStr = newValueStr.substring(0, newValueStr.length() - 1);
			inputValue = Double.parseDouble(newValueStr);
		} else {
			newValueStr = newValueStr.substring(0, newValueStr.length() - 1);
			inputValue = Double.parseDouble(newValueStr);
		}
	}
	
	/**
	 * 檢核是否為新的一筆輸入值 
	 * @return
	 */
	private boolean checkIsTheNewInputNumber() {
		return values.size() > 0 && values.get(values.size() - 1).isImmediate();
	}

	/**
	 * 取得計算輸出結果
	 * @return
	 */
	public double getOutputValue() {
		return outputValue;
	}
	
	/**
	 * 取得輸入之計算值
	 * @return
	 */
	public double getInputValue() {
		return inputValue;
	}
	
	/**
	 * 留給想以鍵盤輸入的方式設值使用
	 * @param inputValue
	 */
	public void setInputValue(double inputValue) {
		this.inputValue = inputValue;
	}
	
	/**
	 * 計算值物件
	 * 包含計算的方法
	 */
	private class InputValueFunction {
		/**
		 * 輸入值
		 */
		private double value;
		/**
		 * 計算方法
		 */
		private COUNT_OUTPUT_FUNC function;
		/**
		 * 是否馬上計算
		 */
		private boolean isImmediate;
		
		private ArithmeticCore arithmeticCore;

		public InputValueFunction(double value, COUNT_OUTPUT_FUNC function,
				boolean isImmediate) {
			super();
			this.value = value;
			this.function = function;
			this.isImmediate = isImmediate;
		}

		public double getValue() {
			return value;
		}

		public COUNT_OUTPUT_FUNC getFunction() {
			return function;
		}

		public boolean isImmediate() {
			return isImmediate;
		}

		public ArithmeticCore getArithmeticCore() {
			return arithmeticCore;
		}

		public void setArithmeticCore(ArithmeticCore arithmeticCore) {
			this.arithmeticCore = arithmeticCore;
		}
	}

	/**
	 * 提供的計算功能
	 */
	public enum COUNT_OUTPUT_FUNC {
		PLUS("+"), REDUCE("-"), MULITPLY("*"), DIVIDE("/"), REMAINDER("%"), EQUAL(
				"="), RECIPROCAL("1/x*"), SQRT("sqrt"),OTHER_ARITHMETIC("other_arithmetic");
		private String value;

		COUNT_OUTPUT_FUNC(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}
	
	/**
	 * 提供的修改輸入值功能
	 */
	public enum MODIFY_INPUT_FUNC {
		DOT("."), REVERSE("-1*"), BACKSPACE("Backspace"), CE("CE"), C("C"),OTHER_ARITHMETIC("other_arithmetic");
		private String value;

		MODIFY_INPUT_FUNC(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}

}
