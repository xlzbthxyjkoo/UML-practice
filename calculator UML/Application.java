package calculator;

import java.awt.event.*;
import javax.swing.*;

public class Application {
	private JFrame frame;
	private final JTextField numberField = new JTextField();
	private final JButton button_1 = new JButton();
	private final JButton button_2 = new JButton();
	private final JButton button_3 = new JButton();
	private final JButton button_4 = new JButton();
	private final JButton button_5 = new JButton();
	private final JButton button_6 = new JButton();
	private final JButton button_7 = new JButton();
	private final JButton button_8 = new JButton();
	private final JButton button_9 = new JButton();
	private final JButton button_0 = new JButton();
	private final JButton button_point = new JButton();
	private final JButton button_sign = new JButton();
	private final JButton button_clear = new JButton();
    

    public static void main(final String[] args) {
        try {
            final Application window = new Application();
            window.frame.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Application() {

        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setBounds(100, 100, 255, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(numberField);
        numberField.setText("");
        numberField.setHorizontalAlignment(4);
        numberField.setBounds(38, 10, 165, 22);
        frame.getContentPane().add(button_1);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(1);
            }
        });
        button_1.setText("1");
        button_1.setBounds(38, 71, 51, 28);
        frame.getContentPane().add(button_2);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(2);
            }
        });
        button_2.setText("2");
        button_2.setBounds(95, 71, 51, 28);
        frame.getContentPane().add(button_3);
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(3);
            }
        });
        button_3.setText("3");
        button_3.setBounds(152, 71, 51, 28);
        frame.getContentPane().add(button_4);
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(4);
            }
        });
        button_4.setText("4");
        button_4.setBounds(38, 105, 51, 28);
        frame.getContentPane().add(button_5);
        button_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(5);
            }
        });
        button_5.setText("5");
        button_5.setBounds(95, 105, 51, 28);
        frame.getContentPane().add(button_6);
        button_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(6);
            }
        });
        button_6.setText("6");
        button_6.setBounds(152, 105, 51, 28);
        frame.getContentPane().add(button_7);
        button_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(7);
            }
        });
        button_7.setText("7");
        button_7.setBounds(38, 139, 51, 28);
        frame.getContentPane().add(button_8);
        button_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(8);
            }
        });
        button_8.setText("8");
        button_8.setBounds(95, 139, 51, 28);
        frame.getContentPane().add(button_9);
        button_9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(9);
            }
        });
        button_9.setText("9");
        button_9.setBounds(152, 139, 51, 28);
        frame.getContentPane().add(button_0);
        button_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digit(0);
            }
        });
        button_0.setText("0");
        button_0.setBounds(38, 173, 51, 28);
        frame.getContentPane().add(button_point);
        button_point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                point();
            }
        });
        button_point.setText(".");
        button_point.setBounds(95, 173, 51, 28);
        frame.getContentPane().add(button_sign);
        button_sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sign();
            }
        });
        button_sign.setText("+/-");
        button_sign.setBounds(152, 173, 51, 28);
        frame.getContentPane().add(button_clear);
        button_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        button_clear.setText("Clear");
        button_clear.setBounds(38, 38, 70, 28);
		//
		// Start from the initial state
		//
        changeState(STATE.Initial);
    }
    
	//
	// Definition of states
	//
    
    enum STATE {
        Initial, Waiting, NumberEntered, PointEntered, PointAndNoNumber, 
        PointAndZero, NumberEnteredAfterPoint;}
    STATE state;
	//
	// Data fields
	//
    String sign, value;
    
	//
	// Trigger event method
	//
    void digit(int d) {
        if (state != STATE.Initial) {
            if (state == STATE.Waiting) {
                if (d != 0) {
                    value = Integer.toString(d);
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEntered);
                }
            }
            else if (state == STATE.NumberEntered) {
                value = value + Integer.toString(d);
                display(String.valueOf(sign) + value);
                changeState(STATE.NumberEntered);
            }
            else if (state == STATE.PointEntered) {
                value = value + "." + Integer.toString(d);
                display(String.valueOf(sign) + value);
                changeState(STATE.NumberEnteredAfterPoint);
            }
            else if (state == STATE.PointAndNoNumber) {
                if (d == 0) {
                    value = value + ".0";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.PointAndZero);
                }
                else if (d != 0) {
                    value = value + "." + Integer.toString(d);
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEnteredAfterPoint);
                }
            }
            else if (state == STATE.PointAndZero) {
                if (d == 0) {
                    value = value + "0";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.PointAndZero);
                }
                else if (d != 0) {
                    value = value + Integer.toString(d);
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEnteredAfterPoint);
                }
            }
            else if (state == STATE.NumberEnteredAfterPoint) {
                value = value + Integer.toString(d);
                display(String.valueOf(sign) + value);
                changeState(STATE.NumberEnteredAfterPoint);
            }
        }
    }
    
	//
	// Trigger event method
	//   
    void point() {
        if (state != STATE.Initial) {
            if (state == STATE.Waiting) {
                changeState(STATE.PointAndNoNumber);
            }
            else if (state == STATE.NumberEntered) {
                changeState(STATE.PointEntered);
            }
        }
    }
	//
	// Trigger event method
	//   
    void sign() {
        if (state != STATE.Initial && state != STATE.Waiting) {
            if (state == STATE.NumberEntered) {
                if (sign.equals("-")) {
                    sign = "";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEntered);
                }
                else if (sign.equals("")) {
                    sign = "-";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEntered);
                }
            }
            else if (state == STATE.PointEntered) {
                if (sign.equals("-")) {
                    sign = "";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.PointEntered);
                }
                else if (sign.equals("")) {
                    sign = "-";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.PointEntered);
                }
            }
            else if (state != STATE.PointAndNoNumber && state != STATE.PointAndZero && state == STATE.NumberEnteredAfterPoint) {
                if (sign.equals("-")) {
                    sign = "";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEnteredAfterPoint);
                }
                else if (sign.equals("")) {
                    sign = "-";
                    display(String.valueOf(sign) + value);
                    changeState(STATE.NumberEnteredAfterPoint);
                }
            }
        }
    }
	//
	// Trigger event method
	//   
    void clear() {
        if (state != STATE.Initial && state != STATE.Waiting) {
            sign = "";
            value = "0";
            display(String.valueOf(sign) + value);
            changeState(STATE.Waiting);
        }
    }
	//
	// State transition method
	//
    void changeState(STATE destState) {
        state = destState;
        if (state == STATE.Initial) {
            sign = "";
            value = "0";
            display(String.valueOf(sign) + value);
            changeState(STATE.Waiting);
        }

    }
	//
	// Effect method
	//
    void display(String str) {
        numberField.setText(str);
    }
    
}
