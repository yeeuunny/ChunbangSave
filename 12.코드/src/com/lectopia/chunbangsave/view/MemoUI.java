package com.lectopia.chunbangsave.view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.lectopia.chunbangsave.vo.MemoVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;

public class MemoUI extends JDialog{
	
	private JLabel emptyLabel1;
	private JLabel emptyLabel2;

	private JLabel childTitleLabel1;
	private JLabel childTitleLabel2;
	private JLabel parentTitleLabel1;
	private JLabel parentTitleLabel2;
	private JLabel memoTitleLabel1;
	private JLabel memoTitleLabel2;
	private JLabel memoContentsLabel1;
	private JLabel memoContentsLabel2;
	
	private JTextField memoTitleText1;
	private JTextField memoTitleText2;
	private JTextArea memoContentsText1;	
	private JTextArea memoContentsText2;
	
	private JButton saveBtn;
	private JButton cancelBtn;
	private UtilDateModel dateModel1;
	private UtilDateModel dateModel2;
	private int code;
	private String cod;
	private class SaveMemoBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class CloseBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}
	private class DateSelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(code==1){
				int year = dateModel1.getYear();
				int month = dateModel1.getMonth();
				int day=dateModel1.getDay();
	
				
				int quarter = (int)Math.ceil((month + 1) / 3.0);
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
//////////////////////메모 없으 시 생성 구문
				if(QuarterAdminManager.getTotManager()[month/3]==null){
					QuarterAdminManager.getTotManager()[month/3]=new QuarterManager(num);
					QuarterAdminManager.getTotManager()[(month+1)/3].putMemoList("P1", QuarterAdminManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","P1",quarterMonth));
				//	QuarterAdminManager.getTotManager()[(month+1)/3].putMemoList("P1",QuarterAdminManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","P1",quarterMonth));
					
					MemoVO vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(day);
					if(vo!=null){
						memoTitleText1.setText(vo.getMemoTitle());
						memoContentsText1.setText(vo.getMemoContent());
					}else{
						memoTitleText1.setText("");
						memoContentsText1.setText("");
					}
					
				}
				else{
					MemoVO vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(day);
					
					if(vo!=null){
						memoTitleText1.setText(vo.getMemoTitle());
						memoContentsText1.setText(vo.getMemoContent());
					}else{
						memoTitleText1.setText("");
						memoContentsText1.setText("");
					}
					
				}
			}
			else{
				int year = dateModel2.getYear();
				int month = dateModel2.getMonth();
				int day= dateModel2.getDay();
	
				System.out.println("넘어가는 데이터 code : "+code);
				System.out.println("넘어가는 데이터 year : "+year);
				System.out.println("넘어가는 데이터 month : "+month+"1");
				System.out.println("넘어가는 데이터 day : "+day);		
			}
		}
	}private class DateSelectListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(true){
				int year = dateModel1.getYear();
				int month = dateModel1.getMonth();
				int day=dateModel1.getDay();
				
				int quarter = (int)Math.ceil((month + 1) / 3.0);
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
//////////////////////메모 없으 시 생성 구문
				if(QuarterAdminManager.getTotManager()[month/3]==null){
					QuarterAdminManager.getTotManager()[month/3]=new QuarterManager(num);
			
					QuarterAdminManager.getTotManager()[(month+1)/3].putMemoList("C1",QuarterAdminManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","C1",quarterMonth));
					
					
					MemoVO vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(day);
					
					if(vo2!=null){
						memoTitleText2.setText(vo2.getMemoTitle());
						memoContentsText2.setText(vo2.getMemoContent());
					}
					else{
						memoTitleText2.setText("");
						memoContentsText2.setText("");
					}
				}
				else{
					
					MemoVO vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(day);
					if(vo2==null){
						memoTitleText2.setText("");
						memoContentsText2.setText("");
					}						
					if(vo2!=null|| !vo2.getMemoTitle().equals("")){
						memoTitleText2.setText(vo2.getMemoTitle());
						memoContentsText2.setText(vo2.getMemoContent());
					}
				}
			}
			else{
				int year = dateModel2.getYear();
				int month = dateModel2.getMonth();
				int day= dateModel2.getDay();
	
				System.out.println("넘어가는 데이터 code : "+code);
				System.out.println("넘어가는 데이터 year : "+year);
				System.out.println("넘어가는 데이터 month : "+month+"1");
				System.out.println("넘어가는 데이터 day : "+day);		
			}
		}
	}
	public MemoUI(JDialog frame,int code){
		super(frame,true);
		setTitle("하고싶은 말이 있어요!");
		this.code = code;
		Container contentPane=getContentPane();
		
		//타이틀 레이블 생성 및 폰트 설정 
		emptyLabel1 = new JLabel(" ");
		childTitleLabel1=new JLabel("엄마, 아빠가", SwingConstants.CENTER);
		childTitleLabel2=new JLabel("하고싶은 말이 있어요!", SwingConstants.CENTER);
		emptyLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		childTitleLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		childTitleLabel2.setFont(new Font("Courier New", Font.BOLD, 30));
		
		emptyLabel2 = new JLabel(" ");
		parentTitleLabel1=new JLabel("엄마! 아빠!", SwingConstants.CENTER);
		parentTitleLabel2=new JLabel("하고싶은 말이 있어요!", SwingConstants.CENTER);
		emptyLabel2.setFont(new Font("Courier New", Font.BOLD, 30));
		parentTitleLabel1.setFont(new Font("Courier New", Font.BOLD, 30));
		parentTitleLabel2.setFont(new Font("Courier New", Font.BOLD, 30));
		
		if(code == 2)
		{
			childTitleLabel1.setText("우리 아이가");
			parentTitleLabel1.setText("우리 아이에게");
		}
		
		JPanel titlePanel1=new JPanel();
		titlePanel1.setLayout(new BorderLayout());
		titlePanel1.add(emptyLabel1, BorderLayout.NORTH);
		titlePanel1.add(childTitleLabel1);
		titlePanel1.add(childTitleLabel2, BorderLayout.SOUTH);
	
		JPanel titlePanel2=new JPanel();
		titlePanel2.setLayout(new BorderLayout());
		titlePanel2.add(emptyLabel2, BorderLayout.NORTH);
		titlePanel2.add(parentTitleLabel1);
		titlePanel2.add(parentTitleLabel2, BorderLayout.SOUTH);
	
		//날짜 선택 데이트 피커 
		dateModel1 = new UtilDateModel();
		dateModel1.setSelected(true);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.addActionListener(new DateSelectListener());
		
		dateModel2 = new UtilDateModel();
		dateModel2.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.addActionListener(new DateSelectListener2());
		
		//제목 레이블, 텍스트필드, 패널 
		memoTitleLabel1 = new JLabel("제목");
		memoTitleText1 = new JTextField(20);
		JPanel memoTitlePanel1 = new JPanel();
		memoTitlePanel1.add(memoTitleLabel1);
		memoTitlePanel1.add(memoTitleText1);
		
		memoTitleLabel2 = new JLabel("제목");
		memoTitleText2 = new JTextField(20);
		JPanel memoTitlePanel2 = new JPanel();
		memoTitlePanel2.add(memoTitleLabel2);
		memoTitlePanel2.add(memoTitleText2);

		//내용 레이블, 텍스트필드, 패널
		memoContentsLabel1 = new JLabel("내용");
		memoContentsText1 = new JTextArea(10,20);
		//JPanel memoContentsPanel=new JPanel(new GridLayout(10,10));
		JPanel memoContentsPanel1 = new JPanel();
		memoContentsPanel1.add(memoContentsLabel1);
		//memoContentsPanel2.add(memoContentsPanel);
		memoContentsPanel1.add(memoContentsText1);
		
		memoContentsLabel2 = new JLabel("내용");
		memoContentsText2 = new JTextArea(10,20);
		//JPanel memoContentsPanel=new JPanel(new GridLayout(10,10));
		JPanel memoContentsPanel2 = new JPanel();
		memoContentsPanel2.add(memoContentsLabel2);
		//memoContentsPanel2.add(memoContentsPanel);
		memoContentsPanel2.add(memoContentsText2);
		
		//날짜, 제목, 내용 패널에 합치기 
		JPanel contentsPanel1 = new JPanel();
		contentsPanel1.setLayout(new BoxLayout(contentsPanel1, BoxLayout.Y_AXIS));
		contentsPanel1.add(datePicker1);
		contentsPanel1.add(memoTitlePanel1);
		contentsPanel1.add(memoContentsPanel1);
		
		JPanel contentsPanel2 = new JPanel();
		contentsPanel2.setLayout(new BoxLayout(contentsPanel2, BoxLayout.Y_AXIS));
		contentsPanel2.add(datePicker2);
		contentsPanel2.add(memoTitlePanel2);
		contentsPanel2.add(memoContentsPanel2);
		
		//버튼과 버튼 패널 생성
		saveBtn=new JButton("저장");
		cancelBtn=new JButton("취소");
		JPanel btnPanel = new JPanel();
		btnPanel.add(saveBtn);
		btnPanel.add(cancelBtn);
		
		saveBtn.addActionListener(new SaveMemoBtnListener());
		cancelBtn.addActionListener(new CancelBtnListener(this,memoTitleText1,memoTitleText2,memoContentsText1,memoContentsText2));
		JPanel toParentPanel = new JPanel();
		JPanel fromParentPanel = new JPanel();
		
		fromParentPanel.add(titlePanel1,BorderLayout.NORTH);
		fromParentPanel.add(contentsPanel1);
		
		toParentPanel.add(titlePanel2,BorderLayout.NORTH);
		toParentPanel.add(contentsPanel2);
		toParentPanel.add(btnPanel,BorderLayout.SOUTH);
		
		contentPane.setLayout(new GridLayout(1, 2));
		contentPane.add(fromParentPanel);
		contentPane.add(toParentPanel);
		setSize(800,450);
		setLocation(400,300);
		//setVisible(true);
	}
	public void setData(String titleChild,String titleParent,String contentChild,String contentParent){
		this.memoContentsText1.setText(contentChild);
		this.memoTitleText1.setText(titleChild);
		this.memoTitleText2.setText(titleParent);
		this.memoContentsText2.setText(contentParent);
	}
}
