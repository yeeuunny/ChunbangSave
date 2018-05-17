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
 * �α��� UI
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
		super("õ������");
		
		childJoinUI = new ChildJoinUI(this);
		parentJoinUI = new ParentJoinUI(this);
		childJoinUI.setVisible(false);
		parentJoinUI.setVisible(false);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));
		
		titleLabel = new JLabel("õ������", SwingConstants.CENTER);
		idLabel = new JLabel("���̵�");
		pwLabel = new JLabel("��й�ȣ");
		titleLabel.setFont(new Font("�����ձ۾� ��",Font.ITALIC,40));
		idLabel.setFont(new Font("Apply SD �굹���� N",Font.BOLD,15));
		pwLabel.setFont(new Font("�������",Font.BOLD,15));

		idTF = new JTextField(15);
		pwTF = new JPasswordField(15);
		
		JPanel idPanel = new JPanel();
		JPanel pwPanel = new JPanel();
		idPanel.add(idLabel);
		idPanel.add(idTF);
		pwPanel.add(pwLabel);
		pwPanel.add(pwTF);
		
		loginBtn = new JButton("�α���");
		childJoinBtn = new JButton("����ȸ������");
		parentJoinBtn = new JButton("�θ�ȸ������");
		findBtn = new JButton("���̵�/��й�ȣã��");
		
		loginBtn.addActionListener(new LoginBtnListener());
		childJoinBtn.addActionListener(new ChildSignUpBtnListener());
		parentJoinBtn.addActionListener(new ParentSignUpBtnListener());
		findBtn.addActionListener(new FindInfoBtnListener());
		
		ImageIcon keyIcon = new ImageIcon("login.png");
		Image image = keyIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		keyIcon = new ImageIcon(newimg);  // transform it back
		loginBtn.setIcon(keyIcon);
		
		//ID & PW �Է�, �α��� ��ư �гο� �߰� 
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
		
		//ȸ������, ���̵� ���ã�� ��ư�� �гο� �߰� 
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
		JPanel joinPanel = new JPanel(new GridLayout(2, 1));
		joinPanel.add(childJoinBtn);
		joinPanel.add(parentJoinBtn);
		
		bottomPanel.add(findBtn);
		bottomPanel.add(joinPanel);
		bottomPanel.setBorder(new EmptyBorder(0, 15, 10, 15));

		//�����ӿ� ���
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
	 * �α��� ��ư �̺�Ʈ �ڵ鷯 
	 * @author yeeun
	 *
	 */
	private class LoginBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			/******** r1 ��� ��� �ڵ尪 �Ѱ��ֱ� ******/
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
			/* ����� ���� ���� */
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
				QuarterAdminManager.getTotManager()[quarter - 1] = 
				new QuarterManager(num);
			/**
			 * ������ �ε���!!!!!!!!!!!			
			 */
			//����� ��������
			QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
					QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			//����� �� ����
			ArrayList<MonthCashBookDetailVO> temp = QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", quarterMonth);
			System.out.println(QuarterAdminManager.getTotManager()[quarter - 1].getKey()+"�б� ��ü ����.."+QuarterAdminManager.getTotManager()[quarter - 1].putDetailInfoList("p1", temp));
			//�޸� ����
			totManager.getTotManager()[(month+1)/3].putMemoList("C1", totManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","C1",quarterMonth));			
			totManager.getTotManager()[(month+1)/3].putMemoList("P1", totManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","P1",quarterMonth));
			
			// �޷¿� �ѷ��� ��
			HashMap<String, ArrayList<MonthCashBookVO>> monthCashBookVOMap = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo();
			//���� ��ǥ ����
			HashMap<String, QuarterSavingGoalVO>quarterSavingGoalMap = QuarterAdminManager.getTotManager()[quarter - 1].getSavingGoalList();
		
			QuarterAdminManager.getTotManager()[quarter - 1].putSavingGoalList(
					"P1", num, QuarterManager.getDataLoadManager().loadSavingGoal("f1", "P1"));
			System.out.println("&&&&&&&&&&&&&&&&******************");
			QuarterAdminManager.getTotManager()[quarter - 1].putSavingGoalList(
					"C1", num, QuarterManager.getDataLoadManager().loadSavingGoal("f1", "C1"));
			System.out.println("&&&&&&&&&&&&&&&&******************");

		
			/****** �Ѱ��־�� �� ������ : ���̵� �ش��ϴ� ����ڵ�, �̸�, ��ǥ����, �����ѵ�����, Ī���ϱ�, �θ��ϰ�� �ڳ��̸� ******/
			new MainUI(monthCashBookVOMap.get("p1").get(month+3 - 3*quarter),quarterSavingGoalMap.get("P1").get(quarter));
			dispose();
		}
	}
	/**
	 * ���� ȸ������ ��ư �̺�Ʈ �ڵ鷯 
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
	 * �θ� ȸ������ ��ư �̺�Ʈ �ڵ鷯 
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
	 * ���̵�/��й�ȣ ã�� �̺�Ʈ �ڵ鷯
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
