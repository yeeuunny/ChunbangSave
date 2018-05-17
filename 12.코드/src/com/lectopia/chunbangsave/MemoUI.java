package com.lectopia.chunbangsave.view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class MemoUI extends JDialog{
	
	private JLabel emptyLabel1;
	private JLabel emptyLabel2;

	private JLabel childTitleLabel1;
	private JLabel childTitleLabel2;
	private JLabel parentTitleLabel1;
	private JLabel parentTitleLabel2;
	private JLabel memoTitleLabel1;
	private JLabel memoTitleLabel2;
	private JLabel memoContentsLabel1;
	private JLabel memoContentsLabel2;
	
	private JTextField memoTitleText1;
	private JTextField memoTitleText2;
	private JTextArea memoContentsText1;	
	private JTextArea memoContentsText2;
	
	private JButton saveBtn;
	private JButton cancelBtn;
	private UtilDateModel dateModel1;
	private UtilDateModel dateModel2;
	private int code;
	private class SaveMemoBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class CloseBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}
	private class DateSelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(code==1){
				int year = dateModel1.getYear();
				int month = dateModel1.getMonth();
				int day=dateModel1.getDay();
	
				System.out.println("�Ѿ�� ������ code : "+code);
				System.out.println("�Ѿ�� ������ year : "+year);
				System.out.println("�Ѿ�� ������ month : "+month+"1");
				System.out.println("�Ѿ�� ������ day : "+day);
			}
			else{
				int year = dateModel2.getYear();
				int month = dateModel2.getMonth();
				int day= dateModel2.getDay();
	
				System.out.println("�Ѿ�� ������ code : "+code);
				System.out.println("�Ѿ�� ������ year : "+year);
				System.out.println("�Ѿ�� ������ month : "+month+"1");
				System.out.println("�Ѿ�� ������ day : "+day);		
			}
		}
	}
	public MemoUI(JDialog frame,int code){
		super(frame,true);
		setTitle("�ϰ���� ���� �־��!");
		this.code = code;
		Container contentPane=getContentPane();
		
		//Ÿ��Ʋ ���̺� ���� �� ��Ʈ ���� 
		emptyLabel1 = new JLabel(" ");
		childTitleLabel1=new JLabel("����! �ƺ�!", SwingConstants.CENTER);
		childTitleLabel2=new JLabel("�ϰ���� ���� �־��!", SwingConstants.CENTER);
		emptyLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		childTitleLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		childTitleLabel2.setFont(new Font("Courier New", Font.BOLD, 30));
		
		emptyLabel2 = new JLabel(" ");
		parentTitleLabel1=new JLabel("����, �ƺ���", SwingConstants.CENTER);
		parentTitleLabel2=new JLabel("�ϰ���� ���� �־��!", SwingConstants.CENTER);
		emptyLabel2.setFont(new Font("Courier New", Font.BOLD, 30));
		parentTitleLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		parentTitleLabel2.setFont(new Font("Courier New", Font.BOLD, 30));

		JPanel titlePanel1=new JPanel();
		titlePanel1.setLayout(new BorderLayout());
		titlePanel1.add(emptyLabel1, BorderLayout.NORTH);
		titlePanel1.add(childTitleLabel1);
		titlePanel1.add(childTitleLabel2, BorderLayout.SOUTH);
	
		JPanel titlePanel2=new JPanel();
		titlePanel2.setLayout(new BorderLayout());
		titlePanel2.add(emptyLabel2, BorderLayout.NORTH);
		titlePanel2.add(parentTitleLabel1);
		titlePanel2.add(parentTitleLabel2, BorderLayout.SOUTH);
	
		//��¥ ���� ����Ʈ ��Ŀ 
		dateModel1 = new UtilDateModel();
		dateModel1.setSelected(true);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.addActionListener(new DateSelectListener());
		
		dateModel2 = new UtilDateModel();
		dateModel2.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.addActionListener(new DateSelectListener());
		
		//���� ���̺�, �ؽ�Ʈ�ʵ�, �г� 
		memoTitleLabel1 = new JLabel("����");
		memoTitleText1 = new JTextField(20);
		JPanel memoTitlePanel1 = new JPanel();
		memoTitlePanel1.add(memoTitleLabel1);
		memoTitlePanel1.add(memoTitleText1);
		
		memoTitleLabel2 = new JLabel("����");
		memoTitleText2 = new JTextField(20);
		JPanel memoTitlePanel2 = new JPanel();
		memoTitlePanel2.add(memoTitleLabel2);
		memoTitlePanel2.add(memoTitleText2);

		//���� ���̺�, �ؽ�Ʈ�ʵ�, �г�
		memoContentsLabel1 = new JLabel("����");
		memoContentsText1 = new JTextArea(10,20);
		//JPanel memoContentsPanel=new JPanel(new GridLayout(10,10));
		JPanel memoContentsPanel1 = new JPanel();
		memoContentsPanel1.add(memoContentsLabel1);
		//memoContentsPanel2.add(memoContentsPanel);
		memoContentsPanel1.add(memoContentsText1);
		
		memoContentsLabel2 = new JLabel("����");
		memoContentsText2 = new JTextArea(10,20);
		//JPanel memoContentsPanel=new JPanel(new GridLayout(10,10));
		JPanel memoContentsPanel2 = new JPanel();
		memoContentsPanel2.add(memoContentsLabel2);
		//memoContentsPanel2.add(memoContentsPanel);
		memoContentsPanel2.add(memoContentsText2);
		
		//��¥, ����, ���� �гο� ��ġ�� 
		JPanel contentsPanel1 = new JPanel();
		contentsPanel1.setLayout(new BoxLayout(contentsPanel1, BoxLayout.Y_AXIS));
		contentsPanel1.add(datePicker1);
		contentsPanel1.add(memoTitlePanel1);
		contentsPanel1.add(memoContentsPanel1);
		
		JPanel contentsPanel2 = new JPanel();
		contentsPanel2.setLayout(new BoxLayout(contentsPanel2, BoxLayout.Y_AXIS));
		contentsPanel2.add(datePicker2);
		contentsPanel2.add(memoTitlePanel2);
		contentsPanel2.add(memoContentsPanel2);
		
		//��ư�� ��ư �г� ����
		saveBtn=new JButton("����");
		cancelBtn=new JButton("���");
		JPanel btnPanel = new JPanel();
		btnPanel.add(saveBtn);
		btnPanel.add(cancelBtn);
		
		saveBtn.addActionListener(new SaveMemoBtnListener());
		cancelBtn.addActionListener(new CloseBtnListener());
		
		JPanel toParentPanel = new JPanel();
		JPanel fromParentPanel = new JPanel();
		
		fromParentPanel.add(titlePanel1,BorderLayout.NORTH);
		fromParentPanel.add(contentsPanel1);
		
		toParentPanel.add(titlePanel2,BorderLayout.NORTH);
		toParentPanel.add(contentsPanel2);
		toParentPanel.add(btnPanel,BorderLayout.SOUTH);
		
		contentPane.setLayout(new GridLayout(1, 2));
		contentPane.add(fromParentPanel);
		contentPane.add(toParentPanel);
		setSize(800,450);
		setLocation(400,300);
		//setVisible(true);
	}
}
