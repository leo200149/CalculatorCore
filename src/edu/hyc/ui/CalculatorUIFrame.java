package edu.hyc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.hyc.core.CalculatorCore;
import edu.hyc.core.CalculatorCore.COUNT_OUTPUT_FUNC;
import edu.hyc.core.CalculatorCore.MODIFY_INPUT_FUNC;
import edu.hyc.core.arithmetic.ArithmeticCore;
import edu.hyc.core.arithmetic.impl.SquareArithmeticCore;

public class CalculatorUIFrame extends JFrame implements CalculatorUiClick{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtShow;
	private CalculatorCore core;

	/**
	 * Create the new frame.
	 */

	public CalculatorUIFrame() {
		core = new CalculatorCore();
		initContentPane();
		initTxtShow();
		initAllButton();
	}

	private void initContentPane() {
		setTitle("Leo_Chen's test Calculator");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void initTxtShow() {
		txtShow = new JTextField();
		txtShow.setEditable(false);
		txtShow.setHorizontalAlignment(SwingConstants.RIGHT);
		txtShow.setText("0.0");
		txtShow.setBounds(10, 34, 355, 26);
		contentPane.add(txtShow);
		txtShow.setColumns(10);
	}

	private void initAllButton() {
		JButton btnBS = new JButton("\u2190");
		btnBS.setToolTipText("BackSpace");
		btnBS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnModifyInputFunction(MODIFY_INPUT_FUNC.BACKSPACE);
			}
		});
		btnBS.setBounds(10, 70, 108, 23);
		contentPane.add(btnBS);

		JButton btnCE = new JButton("CE");
		btnCE.setToolTipText("\u6E05\u9664\u6B64\u6578");
		btnCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnModifyInputFunction(MODIFY_INPUT_FUNC.CE);
			}
		});
		btnCE.setBounds(128, 70, 108, 23);
		contentPane.add(btnCE);

		JButton btnC = new JButton("C");
		btnC.setToolTipText("\u5168\u90E8\u6E05\u9664");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnModifyInputFunction(MODIFY_INPUT_FUNC.C);
			}
		});
		btnC.setBounds(246, 70, 108, 23);
		contentPane.add(btnC);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(0);
			}
		});
		btn0.setBounds(10, 229, 60, 32);
		contentPane.add(btn0);

		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(1);
			}
		});
		btn1.setBounds(10, 187, 60, 32);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(2);
			}
		});
		btn2.setBounds(80, 187, 60, 32);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(3);
			}
		});
		btn3.setBounds(150, 187, 60, 32);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(4);
			}
		});
		btn4.setBounds(10, 145, 60, 32);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(5);
			}
		});
		btn5.setBounds(80, 145, 60, 32);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(6);
			}
		});
		btn6.setBounds(150, 145, 60, 32);
		contentPane.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(7);
			}
		});
		btn7.setBounds(10, 103, 60, 32);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(8);
			}
		});
		btn8.setBounds(80, 103, 60, 32);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnNumber(9);
			}
		});
		btn9.setBounds(150, 103, 60, 32);
		contentPane.add(btn9);

		JButton btnPD = new JButton("+/-");
		btnPD.setToolTipText("\u6B63\u8CA0\u865F");
		btnPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnModifyInputFunction(MODIFY_INPUT_FUNC.REVERSE);
			}
		});
		btnPD.setBounds(80, 229, 60, 32);
		contentPane.add(btnPD);

		JButton btnDot = new JButton(".");
		btnDot.setToolTipText("\u5C0F\u6578\u9EDE");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnModifyInputFunction(MODIFY_INPUT_FUNC.DOT);
			}
		});
		btnDot.setBounds(150, 229, 60, 32);
		contentPane.add(btnDot);

		JButton btnAdd = new JButton("+");
		btnAdd.setToolTipText("\u76F8\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.PLUS);
			}
		});
		btnAdd.setBounds(220, 103, 60, 32);
		contentPane.add(btnAdd);

		JButton btnSub = new JButton("-");
		btnSub.setToolTipText("\u76F8\u6E1B");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.REDUCE);
			}
		});
		btnSub.setBounds(220, 145, 60, 32);
		contentPane.add(btnSub);

		JButton btnMul = new JButton("*");
		btnMul.setToolTipText("\u76F8\u4E58");
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.MULITPLY);
			}
		});
		btnMul.setBounds(220, 187, 60, 32);
		contentPane.add(btnMul);

		JButton btnDiv = new JButton("/");
		btnDiv.setToolTipText("\u76F8\u9664");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.DIVIDE);
			}
		});
		btnDiv.setBounds(220, 229, 60, 32);
		contentPane.add(btnDiv);

		JButton btnSqrt = new JButton("\u221A");
		btnSqrt.setToolTipText("\u958B\u6839\u865F");
		btnSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.SQRT);
			}
		});
		btnSqrt.setBounds(290, 103, 60, 32);
		contentPane.add(btnSqrt);

		JButton btnRem = new JButton("%");
		btnRem.setToolTipText("\u53D6\u9918\u6578");
		btnRem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.REMAINDER);
			}
		});
		btnRem.setBounds(290, 145, 60, 32);
		contentPane.add(btnRem);

		JButton btnRec = new JButton("1/x");
		btnRec.setToolTipText("\u5012\u6578");
		btnRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.RECIPROCAL);
			}
		});
		btnRec.setBounds(290, 187, 60, 32);
		contentPane.add(btnRec);

		JButton btnEqu = new JButton("=");
		btnEqu.setToolTipText("\u7B49\u65BC");
		btnEqu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(COUNT_OUTPUT_FUNC.EQUAL);
			}
		});
		btnEqu.setBounds(290, 229, 60, 32);
		contentPane.add(btnEqu);
		
		JButton btnTest = new JButton("x^2");
		btnTest.setToolTipText("TEST");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBtnCountOutputFunction(new SquareArithmeticCore());
			}
		});
		btnTest.setBounds(290, 259, 60, 32);
		contentPane.add(btnTest);
	}
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
