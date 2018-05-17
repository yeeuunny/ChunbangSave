package com.lectopia.chunbangsave.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CancelBtnListener implements ActionListener{
	private JDialog dialog;
	private JTextField textOne;
	private JTextField textTwo;

	private JTextArea areaOne;
	private JTextArea areaTwo;
	
	public CancelBtnListener(JDialog dialog) {
		super();
		this.dialog = dialog;
	}
	public CancelBtnListener(JDialog dialog, JTextField textOne) {
		super();
		this.dialog = dialog;
		this.textOne = textOne;
	}
	public CancelBtnListener(JDialog dialog, JTextField textOne,
			JTextArea areaOne) {
		super();
		this.dialog = dialog;
		this.textOne = textOne;
		this.areaOne = areaOne;
	}
	public CancelBtnListener(JDialog dialog, JTextField textOne,
			JTextField textTwo) {
		super();
		this.dialog = dialog;
		this.textOne = textOne;
		this.textTwo = textTwo;
	}
	public CancelBtnListener(JDialog dialog, JTextField textOne,
			JTextField textTwo, JTextArea areaOne) {
		super();
		this.dialog = dialog;
		this.textOne = textOne;
		this.textTwo = textTwo;
		this.areaOne = areaOne;
	}
	public CancelBtnListener(JDialog dialog, JTextField textOne,
			JTextField textTwo, JTextArea areaOne, JTextArea areaTwo) {
		super();
		this.dialog = dialog;
		this.textOne = textOne;
		this.textTwo = textTwo;
		this.areaOne = areaOne;
		this.areaTwo = areaTwo;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(textOne!=null){
			textOne.setText("");
		}
		if(textTwo!=null){
			textOne.setText("");
		}
		if(areaOne!=null){
			textOne.setText("");
		}
		if(areaTwo!=null){
			textOne.setText("");
		}
		dialog.setVisible(false);
	}

}
