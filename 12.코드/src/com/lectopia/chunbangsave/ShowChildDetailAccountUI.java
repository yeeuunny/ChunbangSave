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
		System.out.println("사용자가 클릭한 가계부일지 index번호 : " + index);
		String[] reviseData=new String[]{"100","수입","1000","잘해라"};
		
		addMoneyUI=new AddMoneyUI(ShowChildDetailAccountUI.this,calendar,code,reviseData);
		addMoneyUI.setVisible(false);
		
		JPanel panel = new JPanel();
		TitledBorder title = new TitledBorder("영수증");
		title.setTitleFont(new Font("휴먼중간팸체", Font.BOLD, 30));
		panel.setBorder(title);

		today = new JLabel(calendar.get(Calendar.YEAR) + "년 "
				+ (calendar.get(Calendar.MONTH) + 1) + "월 "
				+ calendar.get(Calendar.DATE) + "일");
		today.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel dayName = new JLabel("날짜 : ");
		dayName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		 * 현재 잔액을 index를 통해 불러와야하는부분
		 */
		balance = new JLabel(Integer.toString(index *1000));
		balance.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel balanceName = new JLabel("금액 : ");
		balanceName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		 * 현재 분류를 index를 통해 불러와야하는부분
		 */
		category = new JLabel("+ 수입");
		category.setForeground(Color.BLUE);
		category.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel categoryName = new JLabel("분류 : ");
		categoryName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		 * 현재 카테고리를 index를 통해 불러와야하는부분
		 */
		detailCategory = new JLabel("간식");
		detailCategory.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel detailCategoryName = new JLabel("카테고리 : ");
		detailCategoryName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		 * 현재 내역을 index를 통해 불러와야하는부분
		 */
		useContent = new JLabel("떡볶이");
		useContent.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel useContentName = new JLabel("내역 : ");
		useContentName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		 * 수정, 삭제 버튼 리스너
		 */
		/*reviseBtn = new JButton("수정");
		reviseBtn.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		reviseBtn.addActionListener(new HistoryViewModifyBtnListener());
		deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
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
		
		JButton cancel=new JButton("닫기");
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
		super.setLocation(750, 550); // 위치
		super.setPreferredSize(new Dimension(500, 600)); // 크기cx000000000000000000
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
