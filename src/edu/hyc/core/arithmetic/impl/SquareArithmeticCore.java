package edu.hyc.core.arithmetic.impl;

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
