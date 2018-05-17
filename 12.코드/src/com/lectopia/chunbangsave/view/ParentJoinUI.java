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
		setTitle("부모 회원가입");
		
		titleLabel = new JLabel("천방저축 회원되기", SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은고딕",Font.BOLD,30));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		titlePanel.add(titleLabel);
		
		idLabel = new JLabel("아이디");
		pwLabel = new JLabel("비밀번호");
		pwCheckLabel = new JLabel("비밀번호 확인");
		nameLabel = new JLabel("이름");
		birthLabel = new JLabel("생년월일");
		emailLabel = new JLabel("이메일");
		pwQLabel = new JLabel("비밀번호 찾기 질문");
		pwALabel = new JLabel("비밀번호 찾기 답변");
	//	parentIDLabel = new JLabel("부모님 아이디");
		
		idTF = new JTextField(15);
		pwTF = new JTextField(15);
		pwCheckTF = new JTextField(15);
		nameTF = new JTextField(15);
		emailTF = new JTextField(15);
		pwQTF = new JTextField(15);
		pwATF = new JTextField(15);
	//	parentIDTF = new JTextField(15);
		
		joinBtn = new JButton("회원가입");
		cancelBtn = new JButton("취소");
		checkIDBtn = new JButton("중복확인");
	//	checkParentIDBtn = new JButton("확인");
		
		/********cancel btn 리스너 달기 *********/
		joinBtn.addActionListener(new ParentJoinBtnListener());
		checkIDBtn.addActionListener(new ParentMemberCheckBtnListener());
		/*
		 * 콤보박스 날짜 만들기 
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
		
		/*****자녀 회원가입창이면 ***/
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
	 * 부모 아이디 중복 체크 버튼 이벤트 핸들러
	 * @author yeeun
	 *
	 */
	private class ParentMemberCheckBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			/**** 넘겨줄 데이터 : 아이디
			 * 가져올 데이터 : 아이디 존재 여부*****/
		}
	}
	/**
	 * 부모 회원 가입 버튼 이벤트 핸들러 
	 * @author yeeun
	 *
	 */
	private class ParentJoinBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			/**** 넘겨줄 데이터 : 입력한 회원정보 & 가족번호 
			 * 가져올 데이터 : 회원가입 성공 여부 *****/
		}
	}
}
