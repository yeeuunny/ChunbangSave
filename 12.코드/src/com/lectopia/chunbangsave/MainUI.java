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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;


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

	private JProgressBar goalBar;
	private JLabel balanceLabel;
	private JLabel realBalanceLabel;
	public MainUI ()
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
		
		//cal.set(Calendar.DATE, 1);
/*
 * UI����
 * */
		code = 2; //�ӽ� ����ڵ�
		int childCode = 1;

		monthUI = new MonthHistoryUI(this,cal,1);
		yearUI = new YearHistoryUI(this,cal,1);
		/*
		 * ����ڵ� ���
		 */
		dayUI = new DayAccountUI(this, cal, 1);
		diaryUI = new DiaryUI(this, cal,1);
		toChildUI = new ChildManage(this,cal, childCode);
		toParentUI = new CommunicationWithParent(this, code);
		
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		dayUI.setVisible(false);
		diaryUI.setVisible(false);
		toChildUI.setVisible(false);
		toParentUI.setVisible(false);
//�������
		
		Container contentPane = getContentPane();
		
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
/**
 * �߰��κ� 
 */   
		childLabel = new JLabel("�츮 ���̴�");
		selectChildComboBox = new JComboBox<String>(new String[]{"------"} ); //�ڳ�� ������ �޾ƿ;��Ѵ� 
		JPanel selectChildPanel = new JPanel();
		selectChildPanel.add(childLabel);
		selectChildPanel.add(selectChildComboBox);
	
		JPanel monthArrowChildPanel = new JPanel(new BorderLayout());
		monthArrowChildPanel.add(monthArrowPanel);
		monthArrowChildPanel.add(selectChildPanel, BorderLayout.SOUTH);
/**
 * ������� �߰� 		
 */
		
		// ��ǥ �г�, ��ǥ ��
		JPanel goalBalancePanel = new JPanel();

		JPanel goalPanel = new JPanel();
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);

		// ���� 0 ~ 100 ���� ����
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// ���̵��� ����
		goalBar.setStringPainted(true);
		goalBar.setValue(70);// ���� �ۼ�Ʈ ���̰� 70�ϸ� 70%�� ����
		goalBar.setForeground(Color.orange);// ���α׷����� ����
		// goalBar.setStringPainted(false);//���α׷����ٿ� 70�� ���� �׷����� 70%��� ǥ�õǴµ�
		// �װ� ����� �ڵ�

		// �ܾ� ǥ�� ���̺�
		balanceLabel = new JLabel("���� �뵷 ");
		realBalanceLabel = new JLabel("1000��"); // ////*******�ܾ� �޾Ƽ� ǥ���� �κ�

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
			goalBalancePanel.setBorder(new TitledBorder("�ڵ��� �θ��!"));
/**
 * ������� ���� 		
 */
		topPanel.add(imagePanel);
		//�θ��� ��� 
		if(code == 1)  
			topPanel.add(monthArrowPanel); 
		 //�ڳ��� ��� 
		else
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
			
		//�� �гε��� �������ְ� 
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel();
			
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1�� ���� �ִ� �гε��� ������ ������ ���� (���� ����� �����̱� ����)
		//���⼭�� �������� ���� 
		for(j = 0; j < cal.get(Calendar.DAY_OF_WEEK)- 1 ; j++)
		{	
			dateLabels[j].setText("");
			datePanels[j].add(dateLabels[j]);
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
			calendarPanel.add(moneyPanels[j]);
			moneyPanels[j].setVisible(false);
		}
		//�� ���� �ϼ��� �ش��ϴ� �г� ���� 
		for(int i = 0; j < 37; i++, j++)
		{
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			dateLabels[j].setText(Integer.toString(i + 1));
			datePanels[j].add(dateLabels[j]);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			calendarPanel.add(moneyPanels[j]);
		}		
		
		//�� ���� �� ���� ��� �гε��� �� ���̰� 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE); i--)
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
	/**
	 * ���콺�����ʸ� ���콺 ����ͷ� �� �ٲ� 
	 */
		//���� ȭ��ǥ ���� �޷� ���� 
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
				
				//�� ���� ������ ������ ���س��� (�迭�� ������� -1)
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
				//�� ���� �� �� ��ŭ ���� ���� 
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					//�� ���� ������ ���ۺ��� �� �� ��ŭ ���̰� �ϰ� ���̺� ���� 
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
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
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
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
			public void mouseClicked(MouseEvent e)
			{
				System.out.println(((JLabel)e.getSource()).getText());
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(year, month, Integer.parseInt(((JLabel)e.getSource()).getText()));
				dayUI.getCalendar(calendar);
				dayUI.setVisible(true);
			}
		}
		//�� �� ��� 
		private class MonthAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
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
		
}
