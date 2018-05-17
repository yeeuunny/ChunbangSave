package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ShowChildDetailAccountUI extends JDialog{
	private JLabel today;
	private JLabel balance;
	private JLabel category;
	private JLabel detailCategory;
	private JLabel useContent;
	//private JButton reviseBtn;
	//private JButton deleteBtn;
	private GregorianCalendar calendar;
	private int code;
	private int index;
	
	private AddMoneyUI addMoneyUI;
	public ShowChildDetailAccountUI(JDialog dialog, int code, GregorianCalendar calendar) {
		super(dialog, true);
		this.calendar = calendar;
		this.code = code;
		this.index = index;
		System.out.println("����ڰ� Ŭ���� ��������� index��ȣ : " + index);
		String[] reviseData=new String[]{"100","����","1000","���ض�"};
		
		addMoneyUI=new AddMoneyUI(ShowChildDetailAccountUI.this,calendar,code,reviseData);
		addMoneyUI.setVisible(false);
		
		JPanel panel = new JPanel();
		TitledBorder title = new TitledBorder("������");
		title.setTitleFont(new Font("�޸��߰���ü", Font.BOLD, 30));
		panel.setBorder(title);

		today = new JLabel(calendar.get(Calendar.YEAR) + "�� "
				+ (calendar.get(Calendar.MONTH) + 1) + "�� "
				+ calendar.get(Calendar.DATE) + "��");
		today.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel dayName = new JLabel("��¥ : ");
		dayName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		
		/*
		 * ���� �ܾ��� index�� ���� �ҷ��;��ϴºκ�
		 */
		balance = new JLabel(Integer.toString(index *1000));
		balance.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel balanceName = new JLabel("�ݾ� : ");
		balanceName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JPanel balancePanel = new JPanel();
		balancePanel.setLayout(new FlowLayout());
		balancePanel.add(balanceName, BorderLayout.CENTER);
		balancePanel.add(balance, BorderLayout.CENTER);
		JPanel totBalancePanel = new JPanel();
		totBalancePanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel2 = new JPanel();
		totBalancePanel.add(emptyPanel2);
		totBalancePanel.add(balancePanel);
		/*
		 * ���� �з��� index�� ���� �ҷ��;��ϴºκ�
		 */
		category = new JLabel("+ ����");
		category.setForeground(Color.BLUE);
		category.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel categoryName = new JLabel("�з� : ");
		categoryName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new FlowLayout());
		categoryPanel.add(categoryName, BorderLayout.CENTER);
		categoryPanel.add(category, BorderLayout.CENTER);
		JPanel totCategoryPanel = new JPanel();
		totCategoryPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel3 = new JPanel();
		totCategoryPanel.add(emptyPanel3);
		totCategoryPanel.add(categoryPanel);
		/*
		 * ���� ī�װ��� index�� ���� �ҷ��;��ϴºκ�
		 */
		detailCategory = new JLabel("����");
		detailCategory.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel detailCategoryName = new JLabel("ī�װ� : ");
		detailCategoryName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JPanel detailCategoryPanel = new JPanel();
		detailCategoryPanel.setLayout(new FlowLayout());
		detailCategoryPanel.add(detailCategoryName, BorderLayout.CENTER);
		detailCategoryPanel.add(detailCategory, BorderLayout.CENTER);
		JPanel totDetailCategoryPanel = new JPanel();
		totDetailCategoryPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel4 = new JPanel();
		totDetailCategoryPanel.add(emptyPanel4);
		totDetailCategoryPanel.add(detailCategoryPanel);
		/*
		 * ���� ������ index�� ���� �ҷ��;��ϴºκ�
		 */
		useContent = new JLabel("������");
		useContent.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel useContentName = new JLabel("���� : ");
		useContentName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JPanel useContentPanel = new JPanel();
		useContentPanel.setLayout(new FlowLayout());
		useContentPanel.add(useContentName, BorderLayout.CENTER);
		useContentPanel.add(useContent, BorderLayout.CENTER);
		JPanel totUseContentPanel = new JPanel();
		totUseContentPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel5 = new JPanel();
		totUseContentPanel.add(emptyPanel5);
		totUseContentPanel.add(useContentPanel);
		/*
		 * ����, ���� ��ư ������
		 */
		/*reviseBtn = new JButton("����");
		reviseBtn.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		reviseBtn.addActionListener(new HistoryViewModifyBtnListener());
		deleteBtn = new JButton("����");
		deleteBtn.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		deleteBtn.addActionListener(new HistroyViewDelBtnListener());
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(reviseBtn, BorderLayout.CENTER);
		btnPanel.add(deleteBtn, BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		totBtnPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel6 = new JPanel();
		totBtnPanel.add(emptyPanel6);
		totBtnPanel.add(btnPanel);*/
		
		JButton cancel=new JButton("�ݱ�");
		JPanel cancelPanel=new JPanel(new FlowLayout());
		cancelPanel.add(cancel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
		panel.add(totdatePanel);
		panel.add(totBalancePanel);
		panel.add(totCategoryPanel);
		panel.add(totDetailCategoryPanel);
		panel.add(totUseContentPanel);
		panel.add(cancelPanel);
		add(panel);

		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(750, 550); // ��ġ
		super.setPreferredSize(new Dimension(500, 600)); // ũ��cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}
	public void setData(GregorianCalendar calendar,int index,int code){
		this.calendar=calendar;
		this.index=index;
		this.code=code;
	}
}
