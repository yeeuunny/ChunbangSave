package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddMoneyUI extends JDialog{
	private int code;
	private JLabel addTitleLabel;
	private JComboBox useMoneyCategory;
	private JComboBox detailUseMoneyCategory;
	private JTextArea inputContentTf;
	private JTextField inputMoneyTf;
	private JButton addBtn;
	private JButton cancelBtn;
	
	private GregorianCalendar calendar;
	private String[] reviseData;
	private UtilDateModel dateModel;
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.code = code;
		setTitle("����� �߰�");
		//new CancelBtnListener(this,inputMoneyTf,inputContentTf);//��� ��ư ������ ������ ����
		
		/*
		 * code 1�� ��� �θ�
		 */
		if(code==1){
			addTitleLabel = new JLabel("����� �߰�",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","����","����","����","���"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("���� �����ؿ�!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","�뵷���ϱ�","�뵷����","�뵷���ϱ�","�뵷������"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("�߰�");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("�ݱ�");
		
		String[] notSelect = {"�з��� �������ּ���!"};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//ī�װ� ������
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("���з�                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);
		
		JLabel detailKindLabel = new JLabel("�󼼺з�           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		
/*�޷�*/		
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
/*�޷�*/	
		JLabel dateLabel = new JLabel("��¥");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(datePicker);
		
		JLabel inputTextLabel = new JLabel("����");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);

		JLabel inputMoneyLabel = new JLabel("�ݾ�                    ");
		JLabel wonLabel = new JLabel("��");
		JPanel inputMoneyPanel = new JPanel();
		//inputMoneyPanel.setLayout(new GridLayout(1,2));
		inputMoneyPanel.setLayout(new FlowLayout());
		inputMoneyPanel.add(inputMoneyLabel);
		inputMoneyPanel.add(inputMoneyTf);
		inputMoneyPanel.add(wonLabel);
		
		JPanel totContentPanel = new JPanel();
		totContentPanel.setLayout(new BoxLayout(totContentPanel, BoxLayout.Y_AXIS));
		totContentPanel.add(dateChoicePanel);
		totContentPanel.add(kindPanel);
		totContentPanel.add(detailKindPanel);
		totContentPanel.add(inputTextPanel);
		totContentPanel.add(inputMoneyPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(addBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		//setLayout(new BoxLayout(dialog, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(7,1));
		add(titlePanel);
		add(dateChoicePanel);
		add(kindPanel);
		add(detailKindPanel);
		add(inputMoneyPanel);
		add(inputTextPanel);
		add(totBtnPanel);

		cancelBtn.addActionListener(new CancelBtnListener(this,inputMoneyTf,inputContentTf));
		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(750, 550); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	/**
	 * 
	 * @param dialog ���� dialog
	 * @param calendar ��¥ ����
	 * @param code ����ڵ�
	 * @param reviseData ������ ������
	 */
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code,String[]reviseDatas){
		super(dialog,true);
		this.code = code;
		setTitle("����� ����");
		/*
		 * code 1�� ��� �θ�
		 */
		reviseData=reviseDatas;
		if(code==1){
			addTitleLabel = new JLabel("����� ����",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[]useMoneyList = new String[4];
			if(reviseData[1].equals("����")){
				useMoneyList[0] = new String("����");
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("����")){
				useMoneyList[0] = "����";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("����")){
				useMoneyList[0] = "����";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("���")){
				useMoneyList[0] = "���";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "����";
			}
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("���� �����ؿ�!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","�뵷���ϱ�","�뵷����","�뵷���ϱ�","�뵷������"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("�߰�");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("�ݱ�");
		
		String[] notSelect = {reviseData[2]};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//ī�װ� ������
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("���з�                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);

		JLabel detailKindLabel = new JLabel("�󼼺з�           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		
/*�޷�*/		
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
/*�޷�*/	
		JLabel dateLabel = new JLabel("��¥");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		datePicker.setEnabled(true);
		dateChoicePanel.add(datePicker);
		
		JLabel inputTextLabel = new JLabel("����");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);
		//������ ������ ����
		inputContentTf.setText(reviseData[3]);
		inputTextPanel.add(inputContentTf);
		
		JLabel inputMoneyLabel = new JLabel("�ݾ�                    ");
		JLabel wonLabel = new JLabel("��");
		JPanel inputMoneyPanel = new JPanel();
		//inputMoneyPanel.setLayout(new GridLayout(1,2));
		inputMoneyPanel.setLayout(new FlowLayout());
		inputMoneyPanel.add(inputMoneyLabel);	
		//������ ������ ����
		inputMoneyTf.setText(reviseData[0]);
		inputMoneyPanel.add(inputMoneyTf);
		inputMoneyPanel.add(wonLabel);
		
		JPanel totContentPanel = new JPanel();
		totContentPanel.setLayout(new BoxLayout(totContentPanel, BoxLayout.Y_AXIS));
		totContentPanel.add(dateChoicePanel);
		totContentPanel.add(kindPanel);
		totContentPanel.add(detailKindPanel);
		totContentPanel.add(inputTextPanel);
		totContentPanel.add(inputMoneyPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(addBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		//setLayout(new BoxLayout(dialog, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(7,1));
		add(titlePanel);
		add(dateChoicePanel);
		add(kindPanel);
		add(detailKindPanel);
		add(inputTextPanel);
		add(inputMoneyPanel);
		add(totBtnPanel);

		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(800, 600); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	
	public void setData(GregorianCalendar calendar,int code){
		this.calendar=calendar;
		this.code=code;
	}
	public void setData(GregorianCalendar calendar,int code,String[] reviseData){
		this.calendar=calendar;
		this.code=code;
		this.reviseData=reviseData;
	}
	/**
	 * 
	 * @author ����
	 *   �ڳ�,�θ�,ī�װ��� �ٸ��� ���ִ� listener
	 */
	private class CategoryChangeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedSearchList = useMoneyCategory.getSelectedItem().toString();
			if(selectedSearchList.equals("����")){
				detailUseMoneyCategory.removeAllItems();
				if(code==1){
					String[] detailUseMoneyList = {"-------�������ּ���!------","����","�����","����","���ʽ�","��Ÿ"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{
					String[] detailUseMoneyList = {"-------�������ּ���!------","�뵷","��Ÿ"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);					
				}
			}
			else if(selectedSearchList.equals("����")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==1){
					String[] detailUseMoneyList = {"-------�������ּ���!------","�ĺ�","����","������","����","ī����","��Ÿ"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{	
					String[] detailUseMoneyList = {"-------�������ּ���!------","�п�ǰ","����","���","����","��Ÿ"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
			}
		}
	}
	/**
	 * 
	 * @author ����
	 *  ���� �߰�ȭ�鿡�� �Ϸ� ��ư Ŭ��
	 */
	private class EditBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("\n��¥ : ");
			System.out.print(dateModel.getYear()+"-");
			System.out.print(dateModel.getMonth()+1+"-");
			System.out.println(dateModel.getDay());
			System.out.println("���з� : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("�󼼺з� : "+detailUseMoneyCategory.getSelectedItem().toString());
			System.out.println("���� : "+inputContentTf.getText());
			System.out.println("�ݾ� : "+inputMoneyTf.getText());
		}
	}
	/**
	 * 
	 * @author ����
	 *   datePicker ���� Event Listener
	 */
	private class DateSelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int year = dateModel.getYear();
			int month = dateModel.getMonth();
			int day=dateModel.getDay();

			System.out.println("�Ѿ�� ������ code : "+code);
			System.out.println("�Ѿ�� ������ year : "+year);
			System.out.println("�Ѿ�� ������ month : "+month+"1��");
			System.out.println("�Ѿ�� ������ day : "+day);
		}
	}
}
