package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DetailAllowanceListUI extends JDialog{
	private JLabel today;
	private JLabel content;
	private JTextArea contentArea;
	private JLabel balance;
	private JLabel balanceLabel;
	private JButton confirmBtn;
	private String[]showList;
	public DetailAllowanceListUI(JDialog dialog,String[]showList,int code){
		super(dialog,true);
		this.showList = showList;
		JLabel needTitleLabel = new JLabel("용돈이 필요했구나!",SwingConstants.CENTER);
		content = new JLabel("이렇게 생각했단다!                                                  ");
		balance = new JLabel("이만큼 주기로 했단다!              ");
		if(code == 1){
			needTitleLabel.setText("용돈이 필요해요!");
			content.setText("이래서 필요해요!                                                  ");
			balance.setText("이만큼 필요해요!              ");
		}
		needTitleLabel.setFont(new Font("맑은고딕",Font.BOLD,30));
		//제목 panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//날짜
		today = new JLabel(showList[0]);
		JLabel dayName = new JLabel("날짜 : ");
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		//이렇게 생각했단다!
		contentArea = new JTextArea(4,27);
		contentArea.setText(showList[3]);
/**/	contentArea.setEditable(false);
		JPanel contentPanel = new JPanel();
		//contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(content);
		contentPanel.add(contentArea);
		//이만큼 주기로했단다!
		balanceLabel = new JLabel(showList[2]);
		JPanel balancePanel = new JPanel();
		balancePanel.add(balance);
		balancePanel.add(balanceLabel);
		//확인 버튼
		confirmBtn = new JButton("확인");
		JPanel  confirmPanel = new JPanel();
		confirmPanel.add(confirmBtn);
		
		JPanel totPanel = new JPanel();
		totPanel.setLayout(new BoxLayout(totPanel, BoxLayout.Y_AXIS));
		totPanel.add(titlePanel1);
		totPanel.add(totdatePanel);
		totPanel.add(contentPanel);
		totPanel.add(balancePanel);
		totPanel.add(confirmPanel);
		
		add(totPanel);
				
		super.setResizable(false);
		super.setLocation(850, 650); // 위치
		super.setPreferredSize(new Dimension(500, 600)); // 크기cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}

 	public void setShowList(String[] showList){
 		this.showList=showList;
		today.setText(showList[0]);
		contentArea.setText(showList[3]);
		balanceLabel.setText(showList[2]);
 	}

}
