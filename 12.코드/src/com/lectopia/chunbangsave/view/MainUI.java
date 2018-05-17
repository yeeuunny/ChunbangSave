package com.lectopia.chunbangsave.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.SavingGoalVO;


public class MainUI  extends JFrame
{
	private JLabel monthLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	
	private JLabel childLabel;
	private JComboBox<String> selectChildComboBox;
	
	private JButton monthBtn; 
	private JButton yearBtn;
	private JButton toParentBtn;
	private JButton diaryBtn;
	
	private JLabel dayLabel[];
	
	private GregorianCalendar cal;
	private int year;
	private int month;
	private int day;
	
	private JPanel[] moneyPanels;
	//��¥ �г� 
	private JPanel[] datePanels;
	private JLabel[] dateLabels;
	private JPanel calendarPanel;
	
	//UI
	private MonthHistoryUI monthUI;
	private YearHistoryUI yearUI;
	private DayAccountUI dayUI;
	private DiaryUI diaryUI;
	private CommunicationWithParent toParentUI;
	private ChildManage toChildUI;
	private int code;

	private static JProgressBar goalBar;
	private JLabel balanceLabel;
	/**
	 * ���� �ܾ� label
	 */
	private static JLabel realBalanceLabel;
	
	private JLabel[] amountLabels;
	private int quarter; //�б����� 
	public MainUI (MonthCashBookVO monthCashBookVO,SavingGoalVO savingGoal)
	{
		super("õ������");

		//�̹��� ���� �г�
		JPanel imagePanel = new JPanel();
		
		// �� & ��ǥ & �̹��� ���� �г� 
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 20, 20));
		
		//�� & ȭ��ǥ 
		//���� ��¥ ���  
		cal = (GregorianCalendar) Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
/**************/
		//�б���
		quarter = (int)Math.ceil( (month + 1) / 3.0 );
/**************/
		
		addWindowListener(new UserWindowAdapter());
/*
 * UI����
 * */
		code = 2; //�ӽ� ����ڵ�
		int childCode = 2;

		monthUI = new MonthHistoryUI(this,cal,code);
		yearUI = new YearHistoryUI(this,cal,code);
		/*
		 * ����ڵ� ���
		 */
		dayUI = new DayAccountUI(this, cal, code);
		diaryUI = new DiaryUI(this, cal,code);
		toChildUI = new ChildManage(this,cal, childCode);
		toParentUI = new CommunicationWithParent(this, code,cal);
		
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		dayUI.setVisible(false);
		diaryUI.setVisible(false);
		toChildUI.setVisible(false);
		toParentUI.setVisible(false);
//�������
		
		Container contentPane = getContentPane();
		cal.set(Calendar.DATE, 1);
		
		//�� ���̺� 
		monthLabel = new JLabel(Integer.toString(month + 1) + "��");
		monthLabel.setFont(new Font("Courier New", Font.BOLD, 50));
		
		//ȭ��ǥ ���̺� 
		leftArrowLabel = new JLabel("< ");		
		leftArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		rightArrowLabel = new JLabel(" >");
		rightArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		leftArrowLabel.addMouseListener(new PreviousCalendarListener());
		rightArrowLabel.addMouseListener(new NextCalendarListener());
		//�гο� ��, ȭ��ǥ�� ���� 
		JPanel monthArrowPanel = new JPanel();
		monthArrowPanel.add(leftArrowLabel);
		monthArrowPanel.add(monthLabel);
		monthArrowPanel.add(rightArrowLabel);
  
		JPanel monthArrowChildPanel = new JPanel(new BorderLayout());

		selectChildComboBox = new JComboBox<String>(new String[]{"------", "�ڵ���","�ڼ���","�迹��"} ); //�ڳ�� ������ �޾ƿ;��Ѵ� 
		JPanel selectChildPanel = new JPanel();
		childLabel = new JLabel(" ");
		JLabel childLabel2 = new JLabel(" ");

		// ��ǥ �г�, ��ǥ ��
		JPanel goalBalancePanel = new JPanel();
		if(code == 2)
		{
			childLabel.setText("�츮���̴� ");
			selectChildPanel.add(childLabel);
			selectChildPanel.add(selectChildComboBox);
			selectChildComboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					goalBalancePanel.setBorder(new TitledBorder(selectChildComboBox.getSelectedItem().toString()+" �θ��!"));
					
				}
			});
		}
		else
		{
			selectChildPanel.add(childLabel);
			selectChildPanel.add(childLabel2);
		}
		monthArrowChildPanel.add(monthArrowPanel);
		monthArrowChildPanel.add(selectChildPanel, BorderLayout.SOUTH);

		
/**
 * ������� �߰� 		
 */

		JPanel goalPanel = new JPanel();
		JLabel goalTitleLabel = new JLabel(savingGoal.getGoalTitle());
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);
		goalPanel.add(goalTitleLabel);
		
		// ���� 0 ~ 100 ���� ����
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// ���̵��� ����
		goalBar.setStringPainted(true);
		int goalPercent =(int)(Double.parseDouble(QuarterManager.getCurrentAmount())*100/savingGoal.getGoalAmount());
		goalBar.setValue(goalPercent);// ���� �ۼ�Ʈ ���̰� 70�ϸ� 70%�� ����
		goalBar.setForeground(Color.orange);// ���α׷����� ����
		// goalBar.setStringPainted(false);//���α׷����ٿ� 70�� ���� �׷����� 70%��� ǥ�õǴµ�
		// �װ� ����� �ڵ�

		// �ܾ� ǥ�� ���̺�
		balanceLabel = new JLabel("���� �뵷 ");
		realBalanceLabel = new JLabel(QuarterManager.getCurrentAmount()); // ////*******�ܾ� �޾Ƽ� ǥ���� �κ�
/***************/
		realBalanceLabel.setText(QuarterManager.getCurrentAmount());

		if (code == 2) // �θ��̸�
			balanceLabel.setText("�ܾ� ");
		JPanel balancePanel = new JPanel();
		balancePanel.add(balanceLabel);
		balancePanel.add(realBalanceLabel);

		goalBalancePanel.add(goalPanel);
		goalBalancePanel.add(balancePanel);
		
		/************���߿� ������ ����**************/
		if(code == 1) //����̸� 
			goalBalancePanel.setBorder(new TitledBorder("�ڵ��� ���!"));

		else //�θ��̸� 
			goalBalancePanel.setBorder(new TitledBorder("---�θ��!"));
/**
 * ������� ���� 		
 */
		topPanel.add(imagePanel);
		//�ڳ��� ��� 
		//if(code == 1)
		//	topPanel.add(monthArrowPanel); 
		 //�θ��� ��� 
		//else
			topPanel.add(monthArrowChildPanel);
		topPanel.add(goalBalancePanel);
/**
 * ������� 
 */
		// �޷��� ���� �޷� �г� 
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6, 7, 5, 5));
		//���� �ݾ� & �����ѵ� & Ī���ϱ� �� �г� 
		//�ִ� �� �� 31 + 6(���ۿ��� �𸣴ϱ� ���� ū ������ ����)
		moneyPanels = new JPanel[37];
		//��¥ �г� 
		datePanels = new JPanel[37];
		dateLabels = new JLabel[37];
		amountLabels = new JLabel[37];
			
		//�� �гε��� �������ְ� 
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel("");
/*****************11/15*********/			
			amountLabels[i] = new JLabel("");
			amountLabels[i].setFont(new Font("�������",Font.PLAIN,8));
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1�� ���� �ִ� �гε��� ������ ������ ���� (���� ����� �����̱� ����)
		//���⼭�� �������� ���� 
		for(j = 0; j < cal.get(Calendar.DAY_OF_WEEK)- 1 ; j++)
		{	
			datePanels[j].add(dateLabels[j]);
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			moneyPanels[j].add(amountLabels[j]);
			amountLabels[j].setHorizontalAlignment(SwingConstants.CENTER);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));
			
			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			

			calendarPanel.add(moneyPanels[j]);
			moneyPanels[j].setVisible(false);
		}
		/*****************11/15*********/
		
		//�� ���� �ϼ��� �ش��ϴ� �г� ����
		for(int i = 0; j < 37; i++, j++)
		{
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			moneyPanels[j].add(amountLabels[j]);
			amountLabels[j].setHorizontalAlignment(SwingConstants.CENTER);


/*****************11/15*********�����ѵ�&Ī���ϱ� �߰�*/
		//	System.out.println("** i = " + monthCashBookVOMap.get("r1").get(0).get(i + 1));
			if(monthCashBookVO.get(i+1) != null)
			{
				if(code == 1){
					/* Ī���ϱ� = 1 , �����ϱ� = 2, �ƹ��͵� �ƴϸ� = 0 */
					if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
						ImageIcon iconLogo = new ImageIcon("thumbs.png");
						Image temp = iconLogo.getImage();
						Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						ImageIcon tem = new ImageIcon(iconLogo2);
						amountLabels[j].setIcon(tem);
					}
					else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
					{	
						ImageIcon iconLogo = new ImageIcon("x.png");
						Image temp = iconLogo.getImage();
						Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						ImageIcon tem = new ImageIcon(iconLogo2);
						amountLabels[j].setIcon(tem);
					}
					else
						amountLabels[j].setDisabledIcon(new ImageIcon("thumbs.png"));
				}
				else
					amountLabels[j].setFont(new Font("�������",Font.BOLD,10));
				amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
			}
				//moneyPanels[j].add(new JLabel(monthCashBookVO.get(i+1).getDayLimit()));
			
/***************************/
			dateLabels[j].setText(Integer.toString(i + 1));
			datePanels[j].add(dateLabels[j]);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			calendarPanel.add(moneyPanels[j]);
		}		
		
		//�� ���� �� ���� ��� �гε��� �� ���̰� 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE) + 1; i--)
			moneyPanels[i].setVisible(false);
		
		//datePanels[cal.get(Calendar.DAY_OF_WEEK) + day - 1].setBackground(Color.ORANGE);

		//���� �г� 
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(2, 7, 10, 10));
		dayLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i] = new JLabel();
			dayPanel.add(new JPanel());
		}
		
		dayLabel[0].setText("��");
		dayLabel[1].setText("��");
		dayLabel[2].setText("ȭ");
		dayLabel[3].setText("��");
		dayLabel[4].setText("��");
		dayLabel[5].setText("��");
		dayLabel[6].setText("��");
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i].setFont(new Font("Courier New", Font.BOLD, 15));
			dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dayPanel.add(dayLabel[i]);
		}
		totalPanel.add(new JPanel());
		totalPanel.add(topPanel);
		totalPanel.add(dayPanel);
		
		//��ư�� �� �̹��� ������ ���� �� ������ ���� 
		ImageIcon diaryIcon = new ImageIcon("Pencil.png");
		Image image = diaryIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		diaryIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon yearIcon = new ImageIcon("chart1.png");
		image = yearIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		yearIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon monthIcon = new ImageIcon("chart2.png");
		image = monthIcon.getImage(); // transform it 
		image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		monthIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon toParentIcon = new ImageIcon("heart.png");
		image = toParentIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		toParentIcon = new ImageIcon(newimg);  // transform it back
		//��ư�� ���� 
/**
 * 	
 */
		diaryBtn = new JButton(" �ϱ���", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		diaryBtn.addActionListener(new DiaryBtnListener());
		yearBtn = new JButton(" ����������", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" ��������", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		toParentBtn = new JButton(" ����! �ƺ�!", toParentIcon);
		toParentBtn.setBackground(new Color(255, 135, 0));
		toParentBtn.addActionListener(new ToParentBtnListener());
		
		//�θ��� ��� 
		if(code == 2)
			toParentBtn.setText("�ڳ� ����");
		
		//��ư�� ���� ��ư �г� ���� 
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(monthBtn);
		buttonsPanel.add(yearBtn);
		buttonsPanel.add(toParentBtn);
		buttonsPanel.add(diaryBtn);
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/*	dayPanel.setBackground(Color.yellow);
		topPanel.setBackground(Color.yellow);
		totalPanel.setBackground(Color.yellow);
		calendarPanel.setBackground(Color.yellow);
		buttonsPanel.setBackground(Color.yellow);*/
		
		contentPane.add(totalPanel, BorderLayout.NORTH);
		//contentPane.add(dayPanel);
		contentPane.add(calendarPanel);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(700, 700));
		setResizable(false);
		setLocation(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	private class PreviousCalendarListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(month != 0)
			{
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);
				
			//�г� ��� �Ⱥ��̰� ���� �س��� 
			for(int j = 0; j < 37; j++)
				moneyPanels[j].setVisible(false);
			//���� �޷� �޷��� ������� 
			cal.set(year, --month, 1);
			//���̺� ���� ���� ���� 
			monthLabel.setText(Integer.toString(month + 1) + "��");
/******************* ���� ���� �����ѵ� & Ī���ϱ� �ε� **********/
			
			//�бⰡ ������ Ȯ���Ͽ� �޶������� �б⸦ �ٲ� 
			if(quarter != (int)Math.ceil((month + 1) / 3.0))
				quarter = (int)Math.ceil((month + 1) / 3.0);
			int[] quarterMonth = new int[3];
			QuarterNo num = null;
			
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
			//�ش� �б��� ���� �Ŵ��� �ִ��� Ȯ���Ͽ� ������ ����, �ε� 
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
			{
				System.out.println("test");
				QuarterAdminManager.getTotManager()[quarter - 1] = 
						new QuarterManager(num);
						QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
								QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			}
			//�ش� ���� ���� ���� ������ 
			MonthCashBookVO monthCashBookVO = null;
			System.out.println("%%%" + QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo());
			if(QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1") != null)
			{
				monthCashBookVO = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1").get(month+3 - 3*quarter);
			}
			
			//�� ���� ������ ������ ���س��� (�迭�� ������� -1)
			int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			//�� ���� �� �� ��ŭ ���� ���� 
			for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
			{
				amountLabels[j].setText("");

				//�� ���� ������ ���ۺ��� �� �� ��ŭ ���̰� �ϰ� ���̺� ���� 
				dateLabels[j].setText(Integer.toString(i + 1));
				//�ش� ���� ����� ���������� ������ ��, �����ѵ� �ݾ׵� �ٲ���
				if(monthCashBookVO != null)
				{
					if(monthCashBookVO.get(i+1) != null)
					{
						if(code == 1){
							/* Ī���ϱ� = 1 , �����ϱ� = 2, �ƹ��͵� �ƴϸ� = 0 */
							if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
								System.out.println("MainUI-Ī���ϱ�_608line");
								ImageIcon iconLogo = new ImageIcon("thumbs.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
							{	
								System.out.println("MainUI-�����ϱ�_617line");
								ImageIcon iconLogo = new ImageIcon("x.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else
								amountLabels[j].setDisabledIcon(new ImageIcon("thumbs.png"));
						}
						amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
					}
				}
			
				moneyPanels[j].setVisible(true);
			}
			}

			//1�� �������� �������ϰ� ���̺� ��Ȱ��ȭ 
			else
				leftArrowLabel.setEnabled(false);
			
		}
		
	}
	//������ ȭ��ǥ �����޷� ���� 
	private class NextCalendarListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(month != 11)
			{
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);
				
			for(int j = 0; j < 37; j++)
				moneyPanels[j].setVisible(false);
			
			cal.set(year, ++month, 1);
			monthLabel.setText(Integer.toString(month + 1) + "��");
/******************* ���� ���� �����ѵ� & Ī���ϱ� �ε� **********/
			
			//�бⰡ ������ Ȯ���Ͽ� �޶������� �б⸦ �ٲ� 
			if(quarter != (int)Math.ceil((month + 1) / 3.0))
				quarter = (int)Math.ceil((month + 1) / 3.0);
			int[] quarterMonth = new int[3];
			QuarterNo num = null;
			
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
			//�ش� �б��� ���� �Ŵ��� �ִ��� Ȯ���Ͽ� ������ ����, �ε� 
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
			{
				System.out.println("test");
				QuarterAdminManager.getTotManager()[quarter - 1] = 
						new QuarterManager(num);
						QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
								QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			}
			//�ش� ���� ���� ���� ������ 
			MonthCashBookVO monthCashBookVO = null;
			System.out.println("%%%" + QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo());
			if(QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1") != null)
			{
				monthCashBookVO = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1").get(month+3 - 3*quarter);
			}
			
			
			int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			//�޷¿� �ٽ� ���� 
			for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
			{
				amountLabels[j].setText("");
				
				dateLabels[j].setText(Integer.toString(i + 1));
				if(monthCashBookVO != null)
				{
					if(monthCashBookVO.get(i+1) != null)
					{
						if(i==15){
							amountLabels[j].setIcon(null);
							revalidate();
						}
						if(code == 1){
							System.out.println(monthCashBookVO.get(i + 1).getRegisterDate()[2]+" "+monthCashBookVO.get(i + 1).getCompliment());
							/* Ī���ϱ� = 1 , �����ϱ� = 2, �ƹ��͵� �ƴϸ� = 0 */
							if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
								System.out.println("MainUI-Ī���ϱ�_608line");
								ImageIcon iconLogo = new ImageIcon("thumbs.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
							{	
								System.out.println("MainUI-�����ϱ�_617line");
								ImageIcon iconLogo = new ImageIcon("x.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
						}
		/////////////////////////////////////////////////////////////////
						amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
					}
					else{
						amountLabels[j].setIcon(null);
						revalidate();
					}
				}
				moneyPanels[j].setVisible(true);
			}
			}
			//12�� ���ķ� �������ϰ� ���̺� ��Ȱ��ȭ 
			else
				rightArrowLabel.setEnabled(false);
		}
	}
		//��¥ Ŭ�� �� ���� ��� ���� 
		private class DayAccountLabelListener extends MouseAdapter
		{
			/**
			 * 4�б� manager
			 */
			private QuarterAdminManager totManager;
			public void mouseClicked(MouseEvent e)
			{
				totManager = new QuarterAdminManager();
				quarter = (int) Math.ceil( month / 3.0 );
				System.out.println("�б� ���� : "+quarter);
				QuarterManager addManager = QuarterAdminManager.getTotManager()[quarter-1];
				
				/*
				 * ���� ��¥( �б� )�� QuarterManager�� ���� �� �����Ѵ�.
				 */
				int []tMonth = {1,1,1};
				if(addManager==null){
					if(1 == quarter)
						addManager = new QuarterManager(QuarterNo.FIRST_QUARTER);
					else if(2 == quarter)
						addManager = new QuarterManager(QuarterNo.SECOND_QUARTER);
					else if(3 == quarter)
						addManager = new QuarterManager(QuarterNo.THIRD_QUARTER);
					else if(4 == quarter)
						addManager = new QuarterManager(QuarterNo.FOURTH_QUARTER);
					/*
					 * �� �б� Manager ����� ���� 
					 */
					QuarterAdminManager.getTotManager()[quarter-1] = addManager;
					if(QuarterAdminManager.getTotManager()[quarter-1]!=null)
						System.out.println("MainUI-listener : �б� ������ �� �����ϴ�.");

					if(QuarterNo.FIRST_QUARTER == addManager.getKey()){
						tMonth[0] = 1; tMonth[1] = 2; tMonth[2] = 3;
					}
					else if(QuarterNo.SECOND_QUARTER == addManager.getKey()){
						tMonth[0] = 4; tMonth[1] = 5; tMonth[2] = 6;
					}
					else if(QuarterNo.THIRD_QUARTER == addManager.getKey()){
						tMonth[0] = 7; tMonth[1] = 8; tMonth[2] = 9;
					}
					else if(QuarterNo.FOURTH_QUARTER == addManager.getKey()){
						tMonth[0] = 10; tMonth[1] = 11; tMonth[2] = 12;
					}
					ArrayList<MonthCashBookDetailVO> temp = QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", tMonth);
					System.out.println(addManager.getKey()+"�б� ��ü ����.."+addManager.putDetailInfoList("p1", temp));
				}
				/*
				 * ���� �� ���� ��������
				 */
				int clickMonth = cal.get(Calendar.MONTH)+3 - 3*quarter;
				int clickDay = Integer.parseInt(((JLabel)e.getSource()).getText());
				System.out.println("clickMonth = "+clickMonth+" clickDay = "+clickDay);
				LinkedList<CashBookDetailVO> clickDayList = 
						addManager.getDetailInfoList().get("p1").get(clickMonth).get(clickDay);
				if(clickDayList!=null){
					JLabel[][] cashBookList = new JLabel[clickDayList.size()][]; 
					CashBookDetailVO insertInfo = null;
					for(int i = 0 ; i < clickDayList.size(); ++i){
						insertInfo = clickDayList.get(i);
						///////////////////
						System.out.println("MainUI-Listener:"+insertInfo);
						cashBookList[i] = new JLabel[4];
						cashBookList[i][0] = new JLabel(insertInfo.getRegisterCategory().value());
						 cashBookList[i][0].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
						 if(insertInfo.getRegisterCategory()==RegisterCategory.IMPORT)	
							 cashBookList[i][0].setForeground(Color.blue);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.EXPORT)
							 cashBookList[i][0].setForeground(Color.orange);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.SAVE)
							 cashBookList[i][0].setForeground(Color.green);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.DONATION)
							 cashBookList[i][0].setForeground(Color.yellow);
						 
						cashBookList[i][0].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
	
						cashBookList[i][1] = new JLabel(insertInfo.getDetailCategory());
						cashBookList[i][2] = new JLabel(Integer.toString(i));
						cashBookList[i][2].setForeground(Color.white);
						cashBookList[i][3] = new JLabel(insertInfo.getAmount());
					}
				}
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(year, month, Integer.parseInt(((JLabel)e.getSource()).getText()));
				GregorianCalendar calendar2 = new GregorianCalendar();
				calendar2.set(year, month,1);
				int dayLimitNum = calendar2.get(Calendar.DAY_OF_WEEK)+Integer.parseInt(((JLabel)e.getSource()).getText())-2;
				System.out.println(calendar2.get(Calendar.DAY_OF_WEEK)+" "+Integer.parseInt(((JLabel)e.getSource()).getText()));
				System.out.println("MainUI- listener ������ ���糯¥ index��ȣ : "+ dayLimitNum);
				/*
				 * ���� ��¥�� ���� ��¥�� �����ѵ� ���
				 */
				dayUI.getTodayInfo(calendar,amountLabels[dayLimitNum],MainUI.this);
				/*
				 * �� ������ ���� ��� ������Ʈ 
				 */
				dayUI.panelUpdate(clickDayList);
					//amountLabels[dayLimitNum].setText(dayUI.getDayLimit().getText());
				clickDayList = null;
				dayUI.setVisible(true);
			}
		}
		//�� �� ��� 
		private class MonthAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<MonthCashBookDetailVO> temp = QuarterAdminManager.getTotManager()[quarter-1].getDetailInfoList().get("p1");
				monthUI.setData(temp.get(month+3 - 3*quarter), month);
				monthUI.setTable();
				monthUI.setBar();
				monthUI.setVisible(true);
			}
		}
		//���� �� ��� 
		private class YearAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				yearUI.setVisible(true);
			}
		}//����! �ƺ�! 
		private class ToParentBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				//�θ��ڳ౸���ؾ�
				if(code != 1){
					toChildUI.setVisible(true);
				}
				else
					toParentUI.setVisible(true);
			}
		}//�ϱ���
		private class DiaryBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				diaryUI.setVisible(true);
			}
		}
		public JLabel[] getAmountLabels() {
			return amountLabels;
		}
		public void setAmountLabels(JLabel[] amountLabels) {
			this.amountLabels = amountLabels;
		}
		/**
		 * 
		 * @return ���� �ܾ� ��
		 */
		public static JLabel getRealBalanceLabel() {
			return realBalanceLabel;
		}
		/**
		 * 
		 * @param realBalanceLabel ���ο� ���� �ܾ� ��
		 */
		public static void setRealBalanceLabel(JLabel realBalanceLabel) {
			MainUI.realBalanceLabel = realBalanceLabel;
		}
		/**
		 * 
		 * @return ���� ���� ��ǥ����  progressBar
		 */
		public static JProgressBar getGoalBar() {
			return goalBar;
		}
		/**
		 * 
		 * @param goalBar ���ο� ���� ��ǥ ���� progressBar
		 */
		public static void setGoalBar(JProgressBar goalBar) {
			MainUI.goalBar = goalBar;
		}
		/**
		 * 
		 * @author ����
		 *	���� ȭ�� ���� ��ư Event Action Listener
		 */
		private class UserWindowAdapter extends WindowAdapter{
			/**
			 * model�� �����͸� �ְ� �ޱ� ���� controller 
			 */
			private final QuarterAdminManager totManager;
			/**
			 * default������
			 */
			public UserWindowAdapter() {
				this.totManager = new QuarterAdminManager();
			}
			@Override
	        public void windowClosing(WindowEvent e) {//������ ����(GUI�� �׻� ����)
	        //â �ݴ°� Ŭ���ϴ��� ����͸�
				totManager.getTotManager()[quarter-1].getDataSaveManager().setCashBookUniqueList(totManager.getTotManager()[quarter-1].getDataLoadManager().getCashBookUniqueList());
				System.out.println("closing : "+totManager.getTotManager()[quarter-1].getDayInfo().get("p1").get(month+3-3*quarter).get(15));
				System.out.println("���� �б� : "+quarter+" ���� �� : "+month+1+" ���� ���� : "+(month+3-3*quarter));
				if(JOptionPane.showConfirmDialog(null, "���泻���� �����ұ��?")==0){
					totManager.getTotManager()[quarter-1].getDataSaveManager().saveOneMonthCBUnique("F0001", "p1", month+1,
							totManager.getTotManager()[quarter-1].getDayInfo().get("p1"));
				}					
	         }
		}
}
