package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NeedAllowanceUI extends JDialog{
	private int code;
	private JLabel needTitleLabel;
	private JComboBox useMoneyCategory;
	private JTextArea inputContentArea;
	private JTextField inputMoneyTf;
	private JButton lookBtn;
	private JButton sendBtn;
	private JButton cancelBtn;
	private JLabel today;
	private GregorianCalendar calendar;
	private AllowanceListUI allowanceUI;
	public NeedAllowanceUI(JDialog dialog,int code){
		super(dialog,true);
		this.calendar = new GregorianCalendar();
		setTitle("�뵷�� �ʿ��ؿ�!");
		this.calendar = calendar;
		this.code = code;
		
		allowanceUI = new AllowanceListUI(this,calendar,this.code);
		allowanceUI.setVisible(false);
		
		needTitleLabel = new JLabel("����!�ƺ�!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("�������",Font.BOLD,30));
		JLabel needTitleLabel2 = new JLabel("�뵷�� �ʿ��ؿ�!",SwingConstants.CENTER);
		needTitleLabel2.setFont(new Font("�������",Font.BOLD,30));
		String[] useMoneyList = {"�������ּ���!","�п�ǰ","����","���","����","��Ÿ"};
		useMoneyCategory = new JComboBox(useMoneyList);
		lookBtn = new JButton("�亯����");
		lookBtn.addActionListener(new ShowResponseBtnListener());
		sendBtn = new JButton("������");
		sendBtn.addActionListener(new AskAllowanceBtnListener());
		cancelBtn = new JButton("���");
		//���� panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		titlePanel1.add(needTitleLabel2);
		//��¥ panel
		today = new JLabel(calendar.get(Calendar.YEAR) + "�� "
				+ (calendar.get(Calendar.MONTH) + 1) + "�� "
				+ calendar.get(Calendar.DATE) + "��");
		JLabel dayName = new JLabel("��¥ : ");
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		//ī�װ�
		String[] detailUseMoneyList = {"�������ּ���!","�п�ǰ","����","���","����","��Ÿ"};
		useMoneyCategory = new JComboBox(detailUseMoneyList);
		JLabel kindLabel = new JLabel("���⿡ ���Կ�!                               ");
		JPanel kindPanel = new JPanel();
		kindPanel.setLayout(new FlowLayout());
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);
		//�ݾ�
		JLabel inputMoneyLabel = new JLabel("�̸�ŭ �ʿ��ؿ�!               ");
		JPanel inputMoneyPanel = new JPanel();
		kindPanel.setLayout(new FlowLayout());
		
		inputMoneyPanel.add(inputMoneyLabel);
		inputMoneyTf = new JTextField(15);
		inputMoneyPanel.add(inputMoneyTf);
		//����
		JLabel inputTextLabel = new JLabel("�̷��� ���Կ�!");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.Y_AXIS));
		//inputTextPanel.setLayout(new GridLayout(2,1));
		//inputTextPanel.setLayout(new BoxLayout(inputTextPanel,BoxLayout.Y_AXIS));
		//inputTextPanel.setLayout(new BorderLayout());
		
		inputTextPanel.add(inputTextLabel,BorderLayout.NORTH);
		inputContentArea = new JTextArea(5, 20);
		inputTextPanel.add(inputContentArea);
		
		//��ư
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(lookBtn,BorderLayout.CENTER);
		btnPanel.add(sendBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel2 = new JPanel();
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel2);
		totBtnPanel.add(btnPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(titlePanel1);
		panel.add(totdatePanel);
		panel.add(kindPanel);
		panel.add(inputMoneyPanel);
		panel.add(inputTextPanel);
		panel.add(btnPanel);
		
		add(panel);
		
		super.setResizable(false);
		super.setLocation(750, 550); // ��ġ
		super.setPreferredSize(new Dimension(500, 600)); // ũ��cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author ����
	 *   �뵷 ��û �亯 ���� ��ư Event Listener
	 */
	private class ShowResponseBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			allowanceUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author ����
	 *    ������ ��ư Event Listener
	 */
	private class AskAllowanceBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("��û��¥ : "+ calendar.get(Calendar.YEAR) + "�� "
					+ (calendar.get(Calendar.MONTH) + 1) + "�� "
					+ calendar.get(Calendar.DATE) + "��");
			System.out.println("��û ī�װ� : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("��û �ݾ� : "+ inputMoneyTf.getText());
			System.out.println("��û ���� : "+ inputContentArea.getText());
		}
	}
}
