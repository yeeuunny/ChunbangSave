package com.lectopia.chunbangsave.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.QuarterSavingGoalVO;
/**
 * 로그인 UI
 * @author yeeun
 *
 */
public class LoginUI extends JFrame
{
	private JLabel titleLabel;
	private JLabel idLabel;
	private JLabel pwLabel;
	
	private JTextField idTF;
	private JPasswordField pwTF;
	
	private JButton loginBtn;
	private JButton childJoinBtn;
	private JButton parentJoinBtn;
	private JButton findBtn;
	//UI
	private ChildJoinUI childJoinUI;
	private ParentJoinUI parentJoinUI;
	public LoginUI()
	{
		super("천방저축");
		
		childJoinUI = new ChildJoinUI(this);
		parentJoinUI = new ParentJoinUI(this);
		childJoinUI.setVisible(false);
		parentJoinUI.setVisible(false);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));
		
		titleLabel = new JLabel("천방저축", SwingConstants.CENTER);
		idLabel = new JLabel("아이디");
		pwLabel = new JLabel("비밀번호");
		titleLabel.setFont(new Font("나눔손글씨 붓",Font.ITALIC,40));
		idLabel.setFont(new Font("Apply SD 산돌광수 N",Font.BOLD,15));
		pwLabel.setFont(new Font("맑은고딕",Font.BOLD,15));

		idTF = new JTextField(15);
		pwTF = new JPasswordField(15);
		
		JPanel idPanel = new JPanel();
		JPanel pwPanel = new JPanel();
		idPanel.add(idLabel);
		idPanel.add(idTF);
		pwPanel.add(pwLabel);
		pwPanel.add(pwTF);
		
		loginBtn = new JButton("로그인");
		childJoinBtn = new JButton("아이회원가입");
		parentJoinBtn = new JButton("부모회원가입");
		findBtn = new JButton("아이디/비밀번호찾기");
		
		loginBtn.addActionListener(new LoginBtnListener());
		childJoinBtn.addActionListener(new ChildSignUpBtnListener());
		parentJoinBtn.addActionListener(new ParentSignUpBtnListener());
		findBtn.addActionListener(new FindInfoBtnListener());
		
		ImageIcon keyIcon = new ImageIcon("login.png");
		Image image = keyIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		keyIcon = new ImageIcon(newimg);  // transform it back
		loginBtn.setIcon(keyIcon);
		
		//ID & PW 입력, 로그인 버튼 패널에 추가 
		JPanel topPanel = new JPanel();
		JPanel idpwLabelPanel = new JPanel(new GridLayout(2, 1));
		JPanel idpwTFPanel = new JPanel(new GridLayout(2, 1));
		JPanel btnPanel = new JPanel();
		
		idpwLabelPanel.add(idPanel);
		idpwLabelPanel.add(pwPanel);
		idpwTFPanel.add(idTF);
		idpwTFPanel.add(pwTF);
		btnPanel.add(loginBtn);
		
		topPanel.add(idpwLabelPanel);
		topPanel.add(idpwTFPanel);
		topPanel.add(btnPanel);
		
		//회원가입, 아이디 비번찾기 버튼들 패널에 추가 
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
		JPanel joinPanel = new JPanel(new GridLayout(2, 1));
		joinPanel.add(childJoinBtn);
		joinPanel.add(parentJoinBtn);
		
		bottomPanel.add(findBtn);
		bottomPanel.add(joinPanel);
		bottomPanel.setBorder(new EmptyBorder(0, 15, 10, 15));

		//프레임에 담기
		contentPane.add(titleLabel);
		contentPane.add(topPanel);
		contentPane.add(bottomPanel);
		
		setPreferredSize(new Dimension(400, 300));
		setResizable(false);
		setLocation(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	/**
	 * 로그인 버튼 이벤트 핸들러 
	 * @author yeeun
	 *
	 */
	private class LoginBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			/******** r1 대신 등록 코드값 넘겨주기 ******/
			QuarterAdminManager totManager = new QuarterAdminManager();
			int month = GregorianCalendar.getInstance().get(Calendar.MONTH);
			int quarter = (int)Math.ceil((month + 1) / 3.0);
			int[] quarterMonth = new int[3];
			QuarterNo num = null;
			
			System.out.println("quarter ******* : " + quarter);
			switch(quarter)
			{
			case 1: 
				quarterMonth[0] = 1; quarterMonth[1] = 2; quarterMonth[2] = 3;
				num = QuarterNo.FIRST_QUARTER;
				break;
			case 2: 
				quarterMonth[0] = 4; quarterMonth[1] = 5; quarterMonth[2] = 6;
				num = QuarterNo.SECOND_QUARTER;
				break;
			case 3: 
				quarterMonth[0] = 7; quarterMonth[1] = 8; quarterMonth[2] = 9;
				num = QuarterNo.THIRD_QUARTER;
				break;
			case 4: 
				quarterMonth[0] = 10; quarterMonth[1] = 11; quarterMonth[2] = 12;
				num = QuarterNo.FOURTH_QUARTER;
				break;	
			}
			/* 가계부 일일 정보 */
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
				QuarterAdminManager.getTotManager()[quarter - 1] = 
				new QuarterManager(num);
			/**
			 * 데이터 로딩중!!!!!!!!!!!			
			 */
			//가계부 일일정보
			QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
					QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			//가계부 상세 정보
			ArrayList<MonthCashBookDetailVO> temp = QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", quarterMonth);
			System.out.println(QuarterAdminManager.getTotManager()[quarter - 1].getKey()+"분기 객체 생성.."+QuarterAdminManager.getTotManager()[quarter - 1].putDetailInfoList("p1", temp));
			//메모 정보
			totManager.getTotManager()[(month+1)/3].putMemoList("C1", totManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","C1",quarterMonth));			
			totManager.getTotManager()[(month+1)/3].putMemoList("P1", totManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","P1",quarterMonth));
			
			// 달력에 뿌려줄 것
			HashMap<String, ArrayList<MonthCashBookVO>> monthCashBookVOMap = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo();
			//저축 목표 정보
			HashMap<String, QuarterSavingGoalVO>quarterSavingGoalMap = QuarterAdminManager.getTotManager()[quarter - 1].getSavingGoalList();
		
			QuarterAdminManager.getTotManager()[quarter - 1].putSavingGoalList(
					"P1", num, QuarterManager.getDataLoadManager().loadSavingGoal("f1", "P1"));
			System.out.println("&&&&&&&&&&&&&&&&******************");
			QuarterAdminManager.getTotManager()[quarter - 1].putSavingGoalList(
					"C1", num, QuarterManager.getDataLoadManager().loadSavingGoal("f1", "C1"));
			System.out.println("&&&&&&&&&&&&&&&&******************");

		
			/****** 넘겨주어야 할 데이터 : 아이디에 해당하는 등록코드, 이름, 목표정보, 일일한도정보, 칭찬하기, 부모일경우 자녀이름 ******/
			new MainUI(monthCashBookVOMap.get("p1").get(month+3 - 3*quarter),quarterSavingGoalMap.get("P1").get(quarter));
			dispose();
		}
	}
	/**
	 * 아이 회원가입 버튼 이벤트 핸들러 
	 * @author yeeun
	 *
	 */
	private class ChildSignUpBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			childJoinUI.setVisible(true);
		}
	}
	/**
	 * 부모 회원가입 버튼 이벤트 핸들러 
	 * @author yeeun
	 *
	 */
	private class ParentSignUpBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			parentJoinUI.setVisible(true);
		}
	}
	/**
	 * 아이디/비밀번호 찾기 이벤트 핸들러
	 * @author yeeun
	 *
	 */
	private class FindInfoBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}	
	}
}
