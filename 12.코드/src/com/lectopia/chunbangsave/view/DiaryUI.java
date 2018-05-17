package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class DiaryUI extends JDialog{
	private JTextArea diaryText;
	private JTextField diaryTitleText;
	private GregorianCalendar cal;
	private UtilDateModel dateModel;
	private int code;
	public DiaryUI(JFrame frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("일기장");
		this.cal=cal;
		this.code = code;
		System.out.println("년도 월 날짜정보  등록코드"+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE));
		System.out.println("등록코드 : "+code);
		
		Container contentPane=getContentPane();
		ImageIcon smileOne=new ImageIcon("smile.png");
		ImageIcon smiletwo=new ImageIcon("smile.png");
		JLabel smileIcon =new JLabel(smileOne);
		JLabel smileTwoIcon =new JLabel(smiletwo);
		
		JLabel title=new JLabel("오늘 하루는 어땟나요?");
		JPanel top=new JPanel(new FlowLayout());
		top.add(smileIcon);
		top.add(title);
		top.add(smileTwoIcon);
		
		//중간 부분의 작성 코드
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);
		
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
		JComboBox day=new JComboBox(new String[]{"맑음","흐림","비","눈"});
		JPanel submid=new JPanel(new FlowLayout());
		submid.add(datePicker);
		submid.add(day);
		
		JLabel diaryTitle=new JLabel("제목");
		diaryTitleText=new JTextField(20);
		JPanel diaryTitleInfo=new JPanel(new FlowLayout());
		diaryTitleInfo.add(diaryTitle);
		diaryTitleInfo.add(diaryTitleText);
		
		
		JLabel diaryContents=new JLabel("내용");
		JPanel content=new JPanel(new GridLayout(10,1));
		content.add(diaryContents);
		diaryText=new JTextArea(10,20);
		JPanel diaryContentsInfo=new JPanel(new FlowLayout());
		diaryContentsInfo.add(content);
		diaryContentsInfo.add(diaryText);
		
		//미드 판넬에 위에 만든것들 추가
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(submid);
		mid.add(diaryTitleInfo);
		mid.add(diaryContentsInfo);
		
		JButton save=new JButton("저장");
		JButton cancel=new JButton("취소");
		JPanel but=new JPanel(new FlowLayout());
		but.add(save);
		but.add(cancel);
		
		save.addActionListener(new SaveDiaryBtnListener());
		cancel.addActionListener(new CancelBtnListener(this,diaryTitleText,diaryText));
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		setSize(500,400);
		setLocation(400,300);
	//	setVisible(true);
	}
	private class DateSelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int year = dateModel.getYear();
			int month = dateModel.getMonth();
			int day=dateModel.getDay();

			System.out.println("넘어가는 데이터 code : "+code);
			System.out.println("넘어가는 데이터 year : "+year);
			System.out.println("넘어가는 데이터 month : "+month);
			System.out.println("넘어가는 데이터 day : "+day);
		}
	}
	private class SaveDiaryBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String a=diaryText.getText();
			System.out.println("날짜정보 년: "+dateModel.getYear());
			System.out.println("날짜정보 월: "+dateModel.getMonth());
			System.out.println("날짜정보 일: "+dateModel.getDay());
			System.out.println("등록코드");
			diaryTitleText.setText("");
			diaryText.setText("");
			setVisible(false);
		}
	}

	
}
