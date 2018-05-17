package com.lectopia.chunbangsave.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ParentJoinUI extends JDialog 
{
	private JLabel titleLabel;
	private JLabel idLabel;
	private JLabel pwLabel;
	private JLabel pwCheckLabel;
	private JLabel nameLabel;
	private JLabel birthLabel;
	private JLabel emailLabel;
	private JLabel pwQLabel;
	private JLabel pwALabel;
	private JLabel parentIDLabel;
	
	private JTextField idTF;
	private JTextField pwTF;
	private JTextField pwCheckTF;
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField pwQTF;
	private JTextField pwATF;
	private JTextField parentIDTF;
	
	private JComboBox<String> yearCombo;
	private JComboBox<String> monthCombo;
	private JComboBox<String> dateCombo;
	
	private JButton joinBtn;
	private JButton cancelBtn;
	private JButton checkIDBtn;
	private JButton checkParentIDBtn;
	
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	
	public ParentJoinUI(JFrame frame) 
	{
		super(frame, true);
		setTitle("�θ� ȸ������");
		
		titleLabel = new JLabel("õ������ ȸ���Ǳ�", SwingConstants.CENTER);
		titleLabel.setFont(new Font("�������",Font.BOLD,30));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		titlePanel.add(titleLabel);
		
		idLabel = new JLabel("���̵�");
		pwLabel = new JLabel("��й�ȣ");
		pwCheckLabel = new JLabel("��й�ȣ Ȯ��");
		nameLabel = new JLabel("�̸�");
		birthLabel = new JLabel("�������");
		emailLabel = new JLabel("�̸���");
		pwQLabel = new JLabel("��й�ȣ ã�� ����");
		pwALabel = new JLabel("��й�ȣ ã�� �亯");
	//	parentIDLabel = new JLabel("�θ�� ���̵�");
		
		idTF = new JTextField(15);
		pwTF = new JTextField(15);
		pwCheckTF = new JTextField(15);
		nameTF = new JTextField(15);
		emailTF = new JTextField(15);
		pwQTF = new JTextField(15);
		pwATF = new JTextField(15);
	//	parentIDTF = new JTextField(15);
		
		joinBtn = new JButton("ȸ������");
		cancelBtn = new JButton("���");
		checkIDBtn = new JButton("�ߺ�Ȯ��");
	//	checkParentIDBtn = new JButton("Ȯ��");
		
		/********cancel btn ������ �ޱ� *********/
		joinBtn.addActionListener(new ParentJoinBtnListener());
		checkIDBtn.addActionListener(new ParentMemberCheckBtnListener());
		/*
		 * �޺��ڽ� ��¥ ����� 
		 */
		/*
		yearCombo = new JComboBox<String>();
		for(int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1900; i--)
			yearCombo.addItem(Integer.toString(i));
		
		monthCombo = new JComboBox<String>(
				new String[]{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
		
		dateCombo = new JComboBox<String>();
		for(int i = 1; i <= Calendar.getInstance().getActualMaximum(Calendar.DATE); i++)
			dateCombo.addItem(Integer.toString(i));
		*/
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		//JPanel titlePanel = new JPanel();
		JPanel labelPanel = new JPanel(new GridLayout(9, 1, 0, 25));
		JPanel textPanel = new JPanel(new GridLayout(9, 1, 10, 1));
		JPanel centerPanel = new JPanel();
		JPanel btnPanel = new JPanel(new GridLayout(1, 2));
		JPanel birthPanel = new JPanel();
		JPanel idPanel = new JPanel();
		JPanel parentIdPanel = new JPanel();
		
		/*
		birthPanel.add(yearCombo);
		birthPanel.add(monthCombo);
		birthPanel.add(dateCombo);
		*/
		birthPanel.add(datePicker);
		
		centerPanel.add(idLabel);
		centerPanel.add(idPanel);
		
		labelPanel.add(idLabel);
		labelPanel.add(pwLabel);
		labelPanel.add(pwCheckLabel);
		labelPanel.add(nameLabel);
		labelPanel.add(birthLabel);
		labelPanel.add(emailLabel);
		labelPanel.add(pwQLabel);
		labelPanel.add(pwALabel);
		
		idPanel.add(idTF);
		idPanel.add(checkIDBtn);
//		parentIdPanel.add(parentIDTF);
//		parentIdPanel.add(checkParentIDBtn);
		
		textPanel.add(idPanel);
		textPanel.add(pwTF);
		textPanel.add(pwCheckTF);
		textPanel.add(nameTF);
		textPanel.add(birthPanel);
		textPanel.add(emailTF);
		textPanel.add(pwQTF);
		textPanel.add(pwATF);
		/*
		centerPanel.add(idLabel);
		centerPanel.add(idPanel);
		centerPanel.add(pwLabel);
		centerPanel.add(pwTF);
		centerPanel.add(pwCheckLabel);
		centerPanel.add(pwCheckTF);
		centerPanel.add(nameLabel);
		centerPanel.add(nameTF);
		centerPanel.add(birthLabel);
		centerPanel.add(birthPanel);
		centerPanel.add(emailLabel);
		centerPanel.add(emailTF);
		centerPanel.add(pwQLabel);
		centerPanel.add(pwQTF);
		centerPanel.add(pwALabel);
		centerPanel.add(pwATF);
		centerPanel.add(parentIDLabel);
		centerPanel.add(parentIDPanel);
	
		*/
		
		/*****�ڳ� ȸ������â�̸� ***/
		//labelPanel.add(parentIDLabel);
		//textPanel.add(parentIdPanel);
		
		centerPanel.add(labelPanel);
		centerPanel.add(textPanel);
		
		btnPanel.add(joinBtn);
		btnPanel.add(cancelBtn);
		btnPanel.setBorder(new EmptyBorder(0, 40, 10, 40));

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(titlePanel);
		contentPane.add(centerPanel);
		contentPane.add(btnPanel);
		

		setSize(500,550);
		setLocation(350,250);
		//setVisible(true);
	}
	 
	/**
	 * �θ� ���̵� �ߺ� üũ ��ư �̺�Ʈ �ڵ鷯
	 * @author yeeun
	 *
	 */
	private class ParentMemberCheckBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			/**** �Ѱ��� ������ : ���̵�
			 * ������ ������ : ���̵� ���� ����*****/
		}
	}
	/**
	 * �θ� ȸ�� ���� ��ư �̺�Ʈ �ڵ鷯 
	 * @author yeeun
	 *
	 */
	private class ParentJoinBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			/**** �Ѱ��� ������ : �Է��� ȸ������ & ������ȣ 
			 * ������ ������ : ȸ������ ���� ���� *****/
		}
	}
}
