package edu.hyc.main;

import java.awt.EventQueue;

import edu.hyc.ui.CalculatorUIFrame;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorUIFrame frame = new CalculatorUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
