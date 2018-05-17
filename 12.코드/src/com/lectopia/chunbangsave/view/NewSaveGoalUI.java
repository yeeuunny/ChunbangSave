package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.QuarterSavingGoalVO;
import com.lectopia.chunbangsave.vo.SavingGoalVO;

public class NewSaveGoalUI extends JDialog{
	private UtilDateModel dateModelStart;
	private UtilDateModel dateModelEnd;
	
	private JTextField oneText;
	private JTextField twoText;
	
	private int parentCode;
	private int childCode;
	private GregorianCalendar cal;
	private SaveGoalUI ui;
	private int pan;
	public NewSaveGoalUI(JDialog frame,int parentCode,int childCode,int pan,SaveGoalUI ui){
		super(frame,true);
		this.ui=ui;
		Container contentPane=getContentPane();
		this.parentCode=parentCode;
		this.childCode=childCode;
		this.pan=pan;
		System.out.println("부모 등록코드 +자녀 등록코드"+parentCode+childCode);
		cal=new GregorianCalendar();
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
		if(pan==1){
			setOne.setText("성공 목표 ");
			setTwo.setText("목표 금액");
			setThree.setText("목표 기간");
		}
		
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
		cancel.addActionListener(new CancelBtnListener(this,oneText,twoText));
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
			int month=cal.get(Calendar.MONTH);
			QuarterNo no=null;
			if(month==0){no=QuarterNo.FIRST_QUARTER;}
			else if(month/3==0){no=QuarterNo.FIRST_QUARTER;}
			else if(month/3==1){no=QuarterNo.SECOND_QUARTER;}
			else if(month/3==2){no=QuarterNo.THIRD_QUARTER;}
			else if(month/3==3){no=QuarterNo.FOURTH_QUARTER;}
			
			String[] date=new String[]{dateModelStart.getYear()+"",(dateModelStart.getMonth()+1)+"",dateModelStart.getDay()+""};
			
			HashMap<String,QuarterSavingGoalVO> manage=QuarterAdminManager.getTotManager()[month/3].getSavingGoalList();
			if(pan!=1){
				
			if(manage.get("C1")==null){
				manage.put("C1",new QuarterSavingGoalVO());
				manage.get("C1").put(no.value(), new SavingGoalVO(date,oneText.getText(),Integer.parseInt(twoText.getText()),0));
			}else{
				manage.get("C1").put(no.value(), new SavingGoalVO(date,oneText.getText(),Integer.parseInt(twoText.getText()),0));
			}
			ui.setData(new SavingGoalVO[]{manage.get("C1").get(no.value()),manage.get("P1").get(no.value())});
			}else{
				
			if(manage.get("P1")==null){
				manage.put("P1",new QuarterSavingGoalVO());
				manage.get("P1").put(no.value(), new SavingGoalVO(date,oneText.getText(),Integer.parseInt(twoText.getText()),0));
			}else{
				manage.get("P1").put(no.value(), new SavingGoalVO(date,oneText.getText(),Integer.parseInt(twoText.getText()),0));
			}
			ui.setData(new SavingGoalVO[]{manage.get("C1").get(no.value()),manage.get("P1").get(no.value())});
			}
			
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
