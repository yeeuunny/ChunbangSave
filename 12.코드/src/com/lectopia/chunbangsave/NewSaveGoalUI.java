package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class NewSaveGoalUI extends JDialog{
	private UtilDateModel dateModelStart;
	private UtilDateModel dateModelEnd;
	
	private JTextField oneText;
	private JTextField twoText;
	
	private int parentCode;
	private int childCode;
	public NewSaveGoalUI(JDialog frame,int parentCode,int childCode){
		super(frame,true);
		Container contentPane=getContentPane();
		this.parentCode=parentCode;
		this.childCode=childCode;
		System.out.println("부모 등록코드 +자녀 등록코드"+parentCode+childCode);
		
		ImageIcon flag=new ImageIcon("flag.png");
		JLabel flagIcon=new JLabel(flag);
		ImageIcon flag2=new ImageIcon("flag.png");
		JLabel flagIcon2=new JLabel(flag);
		JLabel title=new JLabel("새로운 목표!");
		
		JPanel top=new JPanel(new FlowLayout());
		top.add(flagIcon);
		top.add(title);
		top.add(flagIcon2);
		
		//중단부문 텍스트 입력하는 부분
		JLabel setOne=new JLabel("꼭 성공할 꺼에요!");
		oneText=new JTextField(25);
		
		JLabel setTwo=new JLabel("이만큼 아낄꺼에요!");
		twoText=new JTextField(24);
		JLabel setThree=new JLabel("이만큼 기다릴꺼에요!");
		JPanel setting=new JPanel(new GridLayout(4,1));
		setting.add(setThree);
		
		//데이트 피커 사용부분
		dateModelStart=new UtilDateModel();
		dateModelStart.setSelected(true);
		
		JDatePanelImpl datePanelStart = new JDatePanelImpl(dateModelStart);
		JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart);
		JLabel inp=new JLabel("~");
		
		dateModelEnd=new UtilDateModel();
		dateModelEnd.setSelected(true);
		JDatePanelImpl datePanelEnd=new JDatePanelImpl(dateModelEnd);
		JDatePickerImpl datePickerEnd=new JDatePickerImpl(datePanelEnd);
		//데이터 피커 사용분 끝
		
		
		JPanel datePanel=new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.Y_AXIS));
		datePanel.add(datePickerStart);
		datePanel.add(inp);
		datePanel.add(datePickerEnd);
		
		JPanel one=new JPanel(new FlowLayout());
		one.add(setOne);
		one.add(oneText);
		JPanel two=new JPanel(new FlowLayout());
		two.add(setTwo);
		two.add(twoText);
		JPanel three=new JPanel(new FlowLayout());
		three.add(setting);
		three.add(datePanel);
		
		JPanel newInfo=new JPanel(new GridLayout(4,1));
		newInfo.add(one);
		newInfo.add(two);
		newInfo.add(three);
		
		JButton saveBtn=new JButton("저장");
		JButton cancel=new JButton("취소");
		JPanel but=new JPanel(new FlowLayout());
		saveBtn.addActionListener(new SetGoalBtnListener());
		cancel.addActionListener(new CloseBtnListener());
		but.add(saveBtn);
		but.add(cancel);
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(newInfo);
		contentPane.add(but,BorderLayout.SOUTH);
		
		setSize(500,500);
		setLocation(500,400);
		//setVisible(true);
	}
	private class SetGoalBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("꼭 성공할 꺼에요 넘김 : "+oneText.getText());
			System.out.println("얼마나 아낄지 금액 넘김 : "+twoText.getText());
			System.out.println("자녀등록 코드 +부모 등록코드 보냄 : "+childCode+parentCode);
			setVisible(false);
		}
	}
	private class CloseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
}
