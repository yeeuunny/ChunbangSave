package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lectopia.chunbangsave.vo.MemoVO;
import com.lectopia.chunbangsave.vo.MonthPocketMoneyVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.SavingGoalVO;

public class CommunicationWithParent extends JDialog{
	private int code;
	private NeedAllowanceUI needUI;
	private MemoUI memoUI;
	private SaveGoalUI saveUI;
	private GregorianCalendar cal;
	public CommunicationWithParent(JFrame frame,int code,GregorianCalendar cal){
		super(frame,true);
		this.code = code;
		this.cal = cal;
		Container contentPane=getContentPane();
		//창 미리생성
		needUI = new NeedAllowanceUI(this, this.code);
		memoUI = new MemoUI(this,this.code);
		saveUI = new SaveGoalUI(this,1,this.code,2);
		needUI.setVisible(false);
		memoUI.setVisible(false);
		saveUI.setVisible(false);

		JLabel needTitleLabel = new JLabel("엄마!아빠!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("맑은고딕",Font.BOLD,30));
		//제목 panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//가장 상단 부분의 패널 배치
		JButton home=new JButton("홈");
		JPanel homes=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homes.add(home);
		//ImageIcon child=new ImageIcon("child.png");
		//JLabel childIcon=new JLabel(child);

		JPanel topen=new JPanel();
		
		topen.setLayout(new BoxLayout(topen,BoxLayout.Y_AXIS));
		topen.add(homes);
		//homes.set;
		
		home.addActionListener(new CancelBtnListener());
		
		//중간 부분의 버튼 배치
		JButton childCalendar=new JButton("    용돈이 필요해요!   ");
		JPanel calendar=new JPanel(new FlowLayout(FlowLayout.CENTER,0,1));
		calendar.add(childCalendar);
		childCalendar.setSize(40,50);
		JButton childMoneyIn=new JButton(" 하고싶은 말이 있어요!  ");
		JPanel money=new JPanel(new FlowLayout(FlowLayout.CENTER));
		childMoneyIn.addActionListener(new CommunicationBtnListener());
		money.add(childMoneyIn);
		JButton togetherSave=new JButton("        함께 저축해요         ");
		JPanel save=new JPanel(new FlowLayout(FlowLayout.CENTER));
		save.add(togetherSave);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(titlePanel1);
		mid.add(calendar);
		mid.add(money);
		mid.add(save);
		
		childCalendar.addActionListener(new NeedAllowanceBtnListener());
		togetherSave.addActionListener(new SavingBtnListener());
	//	childMoneyIn.addActionListener(new GiveMoneyBtnListener());
		contentPane.add(topen,BorderLayout.NORTH);
		contentPane.add(mid);
		
		setSize(500,400);
		setLocation(400,300);
	//	setVisible(true);
	}
	private class NeedAllowanceBtnListener implements ActionListener{//----------------------------------------------------

		public void actionPerformed(ActionEvent arg0) {
			int month=cal.get(Calendar.MONTH);
			//if(QuarterAdminManager.getTotManager()[month/3].get)
			QuarterManager manager=QuarterAdminManager.getTotManager()[month/3];
			if(manager.getPocketMoneyList().get("C1")==null){
				MonthPocketMoneyVO vo=manager.getDataLoadManager().loadOneMonthPocketMoney("f1", "P1", month);
				//System.out.println("NeedAllowanceBtnListener: "+vo);
				manager.getPocketMoneyList().put("C1", vo);
			}
			if(manager.getPocketMoneyList().get("P1")==null){
				MonthPocketMoneyVO vo=manager.getDataLoadManager().loadOneMonthPocketMoney("f1", "C1", month);
				manager.getPocketMoneyList().put("P1", vo);
			}
			if(manager.getDailyPocketMoney("C1", cal.get(Calendar.DATE))==null){
			}
			//System.out.println(QuarterAdminManager.getTotManager()[month/3].getPocketMoneyList());
			
			needUI.setVisible(true);
		}
	}
	private class CommunicationBtnListener implements ActionListener{//----------------------------------------------------
		public void actionPerformed(ActionEvent arg0){
			int month=cal.get(Calendar.MONTH);
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
			//System.out.println(month);
			MemoVO vo;
			MemoVO vo2;
			if(QuarterAdminManager.getTotManager()[month/3]==null){
				QuarterAdminManager.getTotManager()[month/3]=new QuarterManager(num);
	///////////메모 없을 시 생성 구문!!!!!!!!!!!!!
				QuarterAdminManager.getTotManager()[(month+1)/3].putMemoList("C1", QuarterAdminManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","C1",quarterMonth));
				QuarterAdminManager.getTotManager()[(month+1)/3].putMemoList("P1",QuarterAdminManager.getTotManager()[(month+1)/3].getDataLoadManager().loadThreeMonthMemo("f1","P1",quarterMonth));
				
				//System.out.println(QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1"));
				vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(cal.get(Calendar.DATE));
				vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(cal.get(Calendar.DATE));
				String titleChild=vo.getMemoTitle();//등록코드 받는 부분 수정할것
				String contentChild=vo.getMemoContent();
				String titleParent=vo2.getMemoTitle();
				String contentParent=vo2.getMemoContent();
				memoUI.setData(titleChild,titleParent, contentChild,contentParent);
			}else{
				System.out.println(QuarterAdminManager.getTotManager()[month/3].getMemoList());
				vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(cal.get(Calendar.DATE)+14);
				vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(cal.get(Calendar.DATE)+14);
				
				//System.out.println(cal.get(Calendar.MONTH));
				//System.out.println("---"+QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(cal.get(Calendar.DATE)));
				String titleChild=vo.getMemoTitle();//등록코드 받는 부분 수정할것
				String contentChild=vo.getMemoContent();
				String titleParent=vo2.getMemoTitle();
				String contentParent=vo2.getMemoContent();
				memoUI.setData(titleChild,titleParent, contentChild,contentParent);
			}
			memoUI.setVisible(true);
		}
	}
	private class SavingBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			int month=cal.get(Calendar.MONTH);
			QuarterManager manage=QuarterAdminManager.getTotManager()[month/3];
			QuarterNo quarter=null;
			if(month==0){quarter=QuarterNo.FIRST_QUARTER;}
			else if(month/3==0){quarter=QuarterNo.FIRST_QUARTER;}
			else if(month/3==1){quarter=QuarterNo.SECOND_QUARTER;}
			else if(month/3==2){quarter=QuarterNo.THIRD_QUARTER;}
			else if(month/3==3){quarter=QuarterNo.FOURTH_QUARTER;}
			
			if(QuarterAdminManager.getTotManager()[month/3].getSavingGoalList().get("C1")==null){//해당 분기 파일 존재하는가 검사
				SavingGoalVO child= manage.getDataLoadManager().loadSavingGoal("f1","C1");
				System.out.println("asdfasdfasdfasdf12132312123132213asdf"+child);
				int quar = Integer.parseInt(child.getStartDate()[1])-1;
				System.out.println(child);
				QuarterNo no=null;
				if(quar==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==1){no=QuarterNo.SECOND_QUARTER;}
				else if(quar/3==2){no=QuarterNo.THIRD_QUARTER;}
				else if(quar/3==3){no=QuarterNo.FOURTH_QUARTER;}
				
				manage.putSavingGoalList("C1",no, child);
			}else{
				SavingGoalVO child= manage.getDataLoadManager().loadSavingGoal("f1","C1");
				System.out.println("asdfasdfasdfas1321312321312321dfasdf"+child);
				int quar = Integer.parseInt(child.getStartDate()[1])-1;
				System.out.println(child);
				QuarterNo no=null;
				if(quar==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==1){no=QuarterNo.SECOND_QUARTER;}
				else if(quar/3==2){no=QuarterNo.THIRD_QUARTER;}
				else if(quar/3==3){no=QuarterNo.FOURTH_QUARTER;}
				
				manage.putSavingGoalList("C1",no, child);
			}
			if(QuarterAdminManager.getTotManager()[month/3].getSavingGoalList().get("P1")==null){
				SavingGoalVO parent=manage.getDataLoadManager().loadSavingGoal("f1","P1");
				int quar = Integer.parseInt(parent.getStartDate()[1])-1;
				
				QuarterNo no=null;
				if(quar==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==1){no=QuarterNo.SECOND_QUARTER;}
				else if(quar/3==2){no=QuarterNo.THIRD_QUARTER;}
				else if(quar/3==3){no=QuarterNo.FOURTH_QUARTER;}
				
				manage.putSavingGoalList("P1",no ,parent);
			}else{
				SavingGoalVO parent=manage.getDataLoadManager().loadSavingGoal("f1","P1");
				int quar = Integer.parseInt(parent.getStartDate()[1])-1;
				
				QuarterNo no=null;
				if(quar==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==0){no=QuarterNo.FIRST_QUARTER;}
				else if(quar/3==1){no=QuarterNo.SECOND_QUARTER;}
				else if(quar/3==2){no=QuarterNo.THIRD_QUARTER;}
				else if(quar/3==3){no=QuarterNo.FOURTH_QUARTER;}
				
				manage.putSavingGoalList("P1",no ,parent);
			}
			
			System.out.println(quarter.value());
			System.out.println("****: " + manage.getSavingGoalList());
			System.out.println("asdf"+manage.getSavingGoalList().get("P1"));
			System.out.println("asdf"+manage.getSavingGoalList().get("C1"));
			System.out.println("asd"+manage.getSavingGoalList().get("C1").get(quarter.value()));
			System.out.println("asd"+manage.getSavingGoalList().get("P1").get(quarter.value()));
			
			saveUI.setData(new SavingGoalVO[]{manage.getSavingGoalList().get("C1").get(quarter.value()),manage.getSavingGoalList().get("P1").get(quarter.value())});
			saveUI.setVisible(true);
		}
	}
	private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}
}
