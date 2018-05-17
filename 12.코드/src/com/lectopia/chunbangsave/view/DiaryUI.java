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
		setTitle("�ϱ���");
		this.cal=cal;
		this.code = code;
		System.out.println("�⵵ �� ��¥����  ����ڵ�"+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE));
		System.out.println("����ڵ� : "+code);
		
		Container contentPane=getContentPane();
		ImageIcon smileOne=new ImageIcon("smile.png");
		ImageIcon smiletwo=new ImageIcon("smile.png");
		JLabel smileIcon =new JLabel(smileOne);
		JLabel smileTwoIcon =new JLabel(smiletwo);
		
		JLabel title=new JLabel("���� �Ϸ�� �����?");
		JPanel top=new JPanel(new FlowLayout());
		top.add(smileIcon);
		top.add(title);
		top.add(smileTwoIcon);
		
		//�߰� �κ��� �ۼ� �ڵ�
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);
		
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
		JComboBox day=new JComboBox(new String[]{"����","�帲","��","��"});
		JPanel submid=new JPanel(new FlowLayout());
		submid.add(datePicker);
		submid.add(day);
		
		JLabel diaryTitle=new JLabel("����");
		diaryTitleText=new JTextField(20);
		JPanel diaryTitleInfo=new JPanel(new FlowLayout());
		diaryTitleInfo.add(diaryTitle);
		diaryTitleInfo.add(diaryTitleText);
		
		
		JLabel diaryContents=new JLabel("����");
		JPanel content=new JPanel(new GridLayout(10,1));
		content.add(diaryContents);
		diaryText=new JTextArea(10,20);
		JPanel diaryContentsInfo=new JPanel(new FlowLayout());
		diaryContentsInfo.add(content);
		diaryContentsInfo.add(diaryText);
		
		//�̵� �ǳڿ� ���� ����͵� �߰�
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(submid);
		mid.add(diaryTitleInfo);
		mid.add(diaryContentsInfo);
		
		JButton save=new JButton("����");
		JButton cancel=new JButton("���");
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

			System.out.println("�Ѿ�� ������ code : "+code);
			System.out.println("�Ѿ�� ������ year : "+year);
			System.out.println("�Ѿ�� ������ month : "+month);
			System.out.println("�Ѿ�� ������ day : "+day);
		}
	}
	private class SaveDiaryBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String a=diaryText.getText();
			System.out.println("��¥���� ��: "+dateModel.getYear());
			System.out.println("��¥���� ��: "+dateModel.getMonth());
			System.out.println("��¥���� ��: "+dateModel.getDay());
			System.out.println("����ڵ�");
			diaryTitleText.setText("");
			diaryText.setText("");
			setVisible(false);
		}
	}

	
}
