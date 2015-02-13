package edu.hyc.core.arithmetic;

/**
 * 算法核心介面
 * 可實做此介面用以擴充算法功能
 * @author Leo_Chen
 * @version 1.0
 */
public interface ArithmeticCore {
	
	/**
	 * 是否為計算需要馬上得到結果的功能
	 * @return
	 */
	public boolean isImmediate();
	/**
	 * 計算邏輯
	 * 最後輸出outputValue
	 * @param outputValue
	 * @param inputValue
	 * @return outputValue
	 */
	public double arithmeticCore(double outputValue,double inputValue);
	
}
