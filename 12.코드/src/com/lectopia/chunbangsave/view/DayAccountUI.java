package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.QuarterManager;

public class DayAccountUI extends JDialog{
	private JLabel[][] list;
	private LinkedList<CashBookDetailVO> bookList;
	private MainUI mainUI;
	
	private JPanel panel;
	
	private JLabel remainCost;
	private GregorianCalendar today;
	private int code;
	private JLabel dayLabel;
	private JLabel allowanceTitle;
	private JLabel dayLimit;
	private JLabel useTotal;
	private JButton setDayLimitBtn;
	private JButton addAccountBtn;
	private JButton homeBtn;
	private AddMoneyUI addMoneyUI;
	private SetDayLimitUI setDayLimitUI;
	private DetailAccountUI detailAccountUI;
	public void panelUpdate(LinkedList<CashBookDetailVO> bookList){
		/*
		 * �����ѵ� �̼��� �� 
		 */
		panel.setBackground(Color.white);
		if(dayLimit.getText().equals("")){
			if(code==2)
				dayLimit.setText("�����ѵ��� ���� �����ϼ���!");
			else
				dayLimit.setText("������ ��ŭ �����?");
			useTotal.setText("");
			remainCost.setText("");
			setDayLimitBtn.setEnabled(true);
			addAccountBtn.setEnabled(false);
			panel.removeAll();
			getContentPane().validate();
			return;
		}			
		/*
		 * �ش� ���� ����� ��� ���� ��
		 */
		if(bookList==null){
			System.out.println("DetailAccountUI-panelUpdate : �����ѵ��� �����ϴµ� ���ϰ���� �����̾��׿�?");
			useTotal.setText("");
			remainCost.setText("");
			panel.removeAll();
			getContentPane().validate();	
			addAccountBtn.setEnabled(true);	
			return;
		}
		
		addAccountBtn.setEnabled(true);
		panel.removeAll();
		//panel.validate();
		/*
		 * �󼼺��� ������
		 */
		int total = 0;
		this.bookList = bookList;
		DetailViewMouseBtnListener tempListener = new DetailViewMouseBtnListener();
		for(int i=0;i<bookList.size();++i){
			JPanel tempPanel=new JPanel(new GridLayout(1,4));
			tempPanel.setPreferredSize(new Dimension(10,30));
			tempPanel.setBackground(Color.white);
			JLabel registerLabel = new JLabel(bookList.get(i).getRegisterCategory().value());
			registerLabel.setFont(new Font("�������",Font.BOLD,22));
			if(bookList.get(i).getRegisterCategory()==RegisterCategory.IMPORT)	
				registerLabel.setForeground(Color.blue);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.EXPORT)
				 registerLabel.setForeground(Color.red);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.SAVE)
				 registerLabel.setForeground(Color.green);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.DONATION)
				 registerLabel.setForeground(Color.yellow);
			tempPanel.add(registerLabel);
			JLabel detailCategory = new JLabel(bookList.get(i).getDetailCategory());
			detailCategory.setFont(new Font("�������",Font.BOLD,13));
			tempPanel.add(detailCategory);
			JLabel indexLabel = new JLabel(Integer.toString(i));
			indexLabel.setForeground(Color.white);
			indexLabel.addMouseListener(tempListener);
			tempPanel.add(indexLabel);
			System.out.println("DayAccountUI-�ݾ� : "+bookList.get(i).getAmount());
			//�����հ� ������Ʈ
			if(!bookList.get(i).getRegisterCategory().value().equals("+"))
				total += Integer.parseInt(bookList.get(i).getAmount());
			else
				total -= Integer.parseInt(bookList.get(i).getAmount());
			JLabel amountLabel = new JLabel(bookList.get(i).getAmount());
			amountLabel.setFont(new Font("�������",Font.BOLD,13));
			tempPanel.add(amountLabel);
			panel.add(tempPanel);
			panel.setBackground(Color.white);			
		}
		this.useTotal.setText(Integer.toString(total));
		this.remainCost.setText(Integer.toString(Integer.parseInt(dayLimit.getText())-total));
		getContentPane().validate();
	}
	public void setBookList(LinkedList<CashBookDetailVO> bookList){
		this.bookList = bookList;
	}
	public void getTodayInfo(GregorianCalendar today,JLabel dayLimit,MainUI mainUI){
		this.today = today;	
		this.mainUI = mainUI;
		dayLabel.setText(this.today.get(Calendar.YEAR)+"�� "+(this.today.get(Calendar.MONTH)+1)+"�� "+this.today.get(Calendar.DATE)+"��");
		/*
		 * ������ �Ͽ��� �޾ƿ� �����ѵ� ����
		 */
		this.dayLimit.setText(dayLimit.getText());
		/*
		 * �����ѵ� �������� ������
		 */
		if(this.dayLimit.getText().equals(""))
			return;/*
		 * ������ �Ͽ��� �޾ƿ� �����ѵ� ����
		 */
		// ���� �Ѿ�
		int total=0;
		if(this.bookList!=null){
			for(int i = 0 ; i<this.bookList.size();++i)
				total += Integer.parseInt(bookList.get(i).getAmount());
			this.useTotal.setText(Integer.toString(total));
		}
		else{
			if(code==1)
				useTotal.setText("��� ����߳���?");
			if(code==2)
				useTotal.setText("����θ� �߰��ϼ���!");
		}
		setDayLimitBtn.setEnabled(false);
		this.remainCost.setText(Integer.toString(Integer.parseInt(dayLimit.getText())-total));
	}
	public DayAccountUI(JFrame frame,GregorianCalendar today, int code){
		super(frame,true);
		setTitle("���ϰ��");
		this.today = today;
		this.code = code;
		
		addMoneyUI=new AddMoneyUI(DayAccountUI.this,today,code);
		addMoneyUI.setVisible(false);
		
		setDayLimitUI=new SetDayLimitUI(DayAccountUI.this,today,code);
		setDayLimitUI.setVisible(false);
		
		detailAccountUI=new DetailAccountUI(DayAccountUI.this,code, today);
		
		Container contentpane = getContentPane();
		System.out.println(this.today.get(Calendar.YEAR));
		System.out.println(this.today.get(Calendar.MONTH)+1);
		System.out.println(this.today.get(Calendar.DATE));
		dayLabel = new JLabel(this.today.get(Calendar.YEAR)+"�� "
								+(this.today.get(Calendar.MONTH)+1)+"�� "
									+this.today.get(Calendar.DATE)+"��",SwingConstants.CENTER);
		
		dayLabel.setFont(new Font("�޸��߰���ü",Font.BOLD,30));
		/*
		 * �θ� �ڳ� ����
		 */
		if(code == 1){
			allowanceTitle = new JLabel("������ �뵷",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			setDayLimitBtn = new JButton("������ �̸�ŭ ���Կ�!");
			addAccountBtn = new JButton("���� �����ؿ�!");
		}
		else if(code == 2){
			allowanceTitle = new JLabel("������ ����",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			setDayLimitBtn = new JButton("��Ȱ�� ����");
			addAccountBtn = new JButton("����� ���� �߰�");
		}
		/*
		 * �����ѵ� �����;��ϴºκ�
		 */
		dayLimit = new JLabel("",SwingConstants.CENTER);
		useTotal = new JLabel("0��",SwingConstants.CENTER);
		dayLimit.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		dayLimit.setForeground(Color.BLUE);
		useTotal.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		useTotal.setForeground(Color.RED);
		
		/*
		 * ������ �̸�ŭ ! nested class
		 */
		setDayLimitBtn.addActionListener(new TodayLimitBtnListener());
		addAccountBtn.addActionListener(new MoneyEditBtnListener());
		homeBtn = new JButton("�ݱ�");
		homeBtn.addActionListener(new HomeBtnListener());
		
		JPanel temp = new JPanel();
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));	
		temp2.setBackground(Color.white);
		JPanel temp4 = new JPanel();
		temp4.add(new JLabel("========================",SwingConstants.CENTER));
		temp4.setBackground(Color.white);
	
		
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(dayLabel);
		titlePanel1.add(allowanceTitle);
		titlePanel1.setBackground(Color.white);
		
		JPanel titlePanel3 = new JPanel();
		titlePanel3.setLayout(new GridLayout(4,1));
		titlePanel3.add(dayLimit);
		titlePanel3.add(useTotal);
		titlePanel3.add(temp4);
		/*
		 * �����ѵ� - ���ݾ�
		 */
		remainCost = new JLabel("0��",SwingConstants.CENTER);
		remainCost.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		titlePanel3.add(remainCost);
		titlePanel3.setBackground(Color.white);
		
		//add(titlePanel1,BorderLayout.NORTH);
		//add(titlePanel2,BorderLayout.CENTER);
		//add(titlePanel3,BorderLayout.SOUTH);
		
		/*
		 * ����� ��� 
		 */
		int listCnt = 5; // ��� ��
		int category = 1; // �ӽ� ī�װ� ��ȣ
		JPanel listPanel[][] = new JPanel[listCnt][];
		for(int i = 0; i < listCnt; ++i){
			listPanel[i] = new JPanel[3];
			for(int j = 0 ; j < 3;++j){
				listPanel[i][j] = new JPanel();
				listPanel[i][j].setBackground(Color.white);
			}
		}
		/*
		 * �󼼺��⸦ ���� ���콺 ��ư ������
		 */
		DetailViewMouseBtnListener tempListener = new DetailViewMouseBtnListener();
		list = new JLabel[listCnt][];
		for(int i = 0; i <listCnt;++i){
			//JLabel[][]list = new JLabel[listCnt][];
			
			if(category == 1){
				list[i] = new JLabel[4];
				list[i][0]=new JLabel();
				list[i][1]=new JLabel();
				list[i][2]=new JLabel();
				list[i][3]=new JLabel();
				//��Ģ����
				//list[i][0] = new JLabel("��");
				//list[i][0].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				//list[i][0].setForeground(Color.green);
				//ī�װ�
				//list[i][1] = new JLabel("�뵷");
				//list[i][1].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				/*
				 * ���� ������ ���콺 Ŭ���̺�Ʈ�� �߻��Ѵ�! ���⿡�ٰ� index��ȣ�� �ְų� unique�� �����͸� ����
				 *  - index��ȣ�� �༭ listener���� �� index�� ������ ȸ�� ����� ������ �����;��ҵ�
				 */
				//list[i][2] = new JLabel(Integer.toString(i));
				list[i][2].addMouseListener(tempListener);
				//list[i][2].setForeground(Color.white);
				//����ݾ�
				//list[i][3] = new JLabel("10000");
				JPanel tempPanel = new JPanel();
				tempPanel.setLayout(new GridLayout(1, 4));
				tempPanel.setBackground(Color.white);
				
				tempPanel.add(list[i][0]);
				tempPanel.add(list[i][1]);
				tempPanel.add(list[i][2]);
				tempPanel.add(list[i][3]);
				listPanel[i][1].add(tempPanel);
			}
		}	
		/*
		 * ȭ�� �ϼ�
		 */
		/*
		JPanel panel = new JPanel();
		JScrollPane scrollpane = new JScrollPane();
		panel.setLayout(new GridLayout(listCnt,3));
		for(int i = 0 ; i < listCnt;++i)
			for(int j = 0 ; j < 3; ++j)
				panel.add(listPanel[i][j]);
		*/
		panel = new JPanel();
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//���� ��ũ�� �Ⱦ�����.
		scrollpane.setBorder(new LineBorder(Color.WHITE));
		//////////
		panel.setLayout(new GridLayout(0,1));
		for(int i = 0 ; i < listCnt;++i)
				panel.add(listPanel[i][1]);
		scrollpane.setViewportView(panel);
		JPanel cashBookListPanel = new JPanel();
		cashBookListPanel.setLayout(new GridLayout(1,3));
		cashBookListPanel.add(listPanel[0][0]);
		cashBookListPanel.add(scrollpane);
		cashBookListPanel.add(listPanel[0][2]);
		
		setLayout(new GridLayout(4, 1));
		add(titlePanel1);
		add(titlePanel3);
		add(cashBookListPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(setDayLimitBtn,BorderLayout.CENTER);
		btnPanel.add(addAccountBtn,BorderLayout.CENTER);
		btnPanel.add(homeBtn);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		add(totBtnPanel);
		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(700, 500); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		//super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author ����
	 *  ����� ���� �߰� ��ư listener
	 */
	private class MoneyEditBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			addMoneyUI.setData(today, code,panel,DayAccountUI.this);
			addMoneyUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author ����
	 *  �����ѵ� ���� ��ư listener
	 */
	private class TodayLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setDayLimitUI.setData(today, code,dayLimit,addAccountBtn,setDayLimitBtn);
			setDayLimitUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author ����
	 *  �󼼺��⸦ ���� listener
	 */
	private class DetailViewMouseBtnListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			detailAccountUI.setData(DayAccountUI.this,today,  
					bookList.get(Integer.parseInt(tempLabel.getText())),Integer.parseInt(tempLabel.getText()), code,panel);
			detailAccountUI.setVisible(true);
		}
	}
	private class HomeBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			GregorianCalendar calendar2 = new GregorianCalendar();
			calendar2.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH),1);
			int dayLimitNum = calendar2.get(Calendar.DAY_OF_WEEK)+today.get(Calendar.DATE)-2;
			if(!dayLimit.getText().toString().contains("�����ѵ�")){
				if(!dayLimit.getText().toString().equals("������ ��ŭ �����?")){				
					mainUI.getAmountLabels()[dayLimitNum].setText(dayLimit.getText());
					mainUI.getAmountLabels()[dayLimitNum].setFont(new Font("�������",Font.BOLD,10));
				}
				else
					dayLimit.setText("");
					
			}
			useTotal.setText("");
			remainCost.setText("");
			MainUI.getRealBalanceLabel().setText(QuarterManager.getCurrentAmount());
		}
	}
	public JLabel[][] getList() {
		return list;
	}
	public void setList(JLabel[][] list) {
		this.list=list;		
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JLabel getDayLimit() {
		return dayLimit;
	}
	public void setDayLimit(JLabel dayLimit) {
		this.dayLimit = dayLimit;
	}
	
}
