package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lectopia.chunbangsave.vo.MemoVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.MonthPocketMoneyVO;
import com.lectopia.chunbangsave.vo.PocketMoneyVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.QuarterSavingGoalVO;
import com.lectopia.chunbangsave.vo.SavingGoalVO;

public class ChildManage extends JDialog{
	private MoneyInputUI moneyInput;
	private SaveGoalUI saving;
	private MemoUI memo;
	
	private ChildMainUI childUI;
	private GregorianCalendar calendar;
	private int code;
	public ChildManage(JFrame frame,GregorianCalendar calendar,int code){
		super(frame,true);
		this.calendar=calendar;
		this.code=code;
		System.out.println("year month day 등록코드"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DATE));
		
		/*
		 * 자식 정보 넘기기
		 */
		// 달력에 뿌려줄 것
		int quarter = (int)Math.ceil((calendar.get(Calendar.MONTH) + 1) / 3.0);
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
		HashMap<String, ArrayList<MonthCashBookVO>> monthCashBookVOMap = 
				QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo();
		//저축 목표 정보
		HashMap<String, QuarterSavingGoalVO>quarterSavingGoalMap = QuarterAdminManager.getTotManager()[quarter - 1].getSavingGoalList();
		//QuarterAdminManager.getTotManager()[quarter - 1].putSavingGoalList(
		//		"C1", num, QuarterAdminManager.getTotManager()[quarter - 1].getDataLoadManager().loadSavingGoal("f1", "P1"));
		
		childUI = new ChildMainUI(monthCashBookVOMap.get("p1").get((calendar.get(Calendar.MONTH)+3 - 3*quarter)),quarterSavingGoalMap.get("P1").get(quarter));
		childUI.setVisible(false);
		
		this.calendar=calendar;
		moneyInput=new MoneyInputUI(this,calendar,1);
		moneyInput.setVisible(false);
		saving=new SaveGoalUI(this,1,1,1);//자녀 , 부모 등록코드를 받는 전달인자 두개 추가.
		saving.setVisible(false);
		memo = new MemoUI(this,this.code);
		memo.setVisible(false);
		
		Container contentPane=getContentPane();
		//가장 상단 부분의 패널 배치
		JButton home=new JButton("홈");
		JPanel homes=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homes.add(home);
		ImageIcon child=new ImageIcon("child.png");
		JLabel childIcon=new JLabel(child);
		JLabel childLabel=new JLabel("자녀관리");
		JPanel top=new JPanel(new FlowLayout());
		top.add(childIcon);
		top.add(childLabel);
		JPanel topen=new JPanel();
		
		topen.setLayout(new BoxLayout(topen,BoxLayout.Y_AXIS));
		topen.add(homes);
		//homes.set;
		topen.add(top);
		
	//
		//home.addActionListener(new CancelBtnListener());
		home.addActionListener(new CancelBtnListener(this));
		//중간 부분의 버튼 배치
		JButton childCalendar=new JButton("자녀 달력 조회");
		JPanel calendarPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,0,1));
		calendarPanel.add(childCalendar);
		childCalendar.setSize(40,50);
		JButton childMoneyIn=new JButton("     용돈 주기     ");
		JPanel money=new JPanel(new FlowLayout(FlowLayout.CENTER));
		money.add(childMoneyIn);
		JButton childToTalk=new JButton(" 자녀와의 대화 ");
		JPanel talk=new JPanel(new FlowLayout(FlowLayout.CENTER));
		childToTalk.addActionListener(new CommunicationBtnListener());
		talk.add(childToTalk);
		JButton togetherSave=new JButton(" 함께 저축해요 ");
		JPanel save=new JPanel(new FlowLayout(FlowLayout.CENTER));
		save.add(togetherSave);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(calendarPanel);
		mid.add(money);
		mid.add(talk);
		mid.add(save);
		
		childCalendar.addActionListener(new CheckChildMainBtnListener());
		togetherSave.addActionListener(new SavingBtnListener());
		childMoneyIn.addActionListener(new GiveMoneyBtnListener());
		contentPane.add(topen,BorderLayout.NORTH);
		contentPane.add(mid);
		
		setSize(500,400);
		setLocation(350,250);
//		setVisible(true);
	}
	private class CheckChildMainBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			System.out.println("asdfasdf");
			childUI.setVisible(true);
		}
	}
	private class GiveMoneyBtnListener implements ActionListener{//-----------------------------------
		public void actionPerformed(ActionEvent arg0){
			//자녀 용돈주기 버튼이 눌렸을 때 자녀코드에 저장되어있는 용돈주기하는 string들을 set메소드를 만들던가해서 넘겨주어 용돈주기창에서 setText해야한다!!
			int month=calendar.get(Calendar.MONTH);
			QuarterManager manager=QuarterAdminManager.getTotManager()[month/3];
			MonthPocketMoneyVO child=manager.getPocketMoneyList().get("C1");
			MonthPocketMoneyVO parent=manager.getPocketMoneyList().get("P1");
			
			if(child==null){//아이 vo가 null일때 읽어옴
				
				MonthPocketMoneyVO vo=manager.getDataLoadManager().loadOneMonthPocketMoney("f1", "C1", month+1);
				manager.getPocketMoneyList().put("C1", vo);
				System.out.println("asdf");
			}
			System.out.println(manager.getPocketMoneyList().get("C1"));
			if(parent==null){//부모vo가 null일때 읽어옴
				MonthPocketMoneyVO vo=manager.getDataLoadManager().loadOneMonthPocketMoney("f1","P1",month+1);
				manager.getPocketMoneyList().put("P1", vo);
				System.out.println(parent);
				//System.out.println("asdf2");
			}

			//System.out.println(mon);
			PocketMoneyVO day=manager.getDailyPocketMoney("P1",calendar.get(Calendar.DATE)+15);//자녀의 하루치를 읽어옴 
			//System.out.println(day);
			boolean a=false;
			if(day!=null){
			if(day.getIsReceived().equals("0"))a=true;//자녀의 포켓머니 상태가 1이라면 이미 돈을 지급했음 .
//			moneyInput.setData(day.getCategory(),day.getAmount(),day.getContent(),a);//다음 ui에서 set해줌
			}else
//				moneyInput.setData("","","",a);
			System.out.println("날짜 정보,자녀코드 :"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DATE));
			moneyInput.setVisible(true);
		}
	}
	private class CommunicationBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			int month=calendar.get(Calendar.MONTH);
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
				vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(calendar.get(Calendar.DATE));
				vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(calendar.get(Calendar.DATE));
				String titleChild=vo.getMemoTitle();//등록코드 받는 부분 수정할것
				String contentChild=vo.getMemoContent();
				String titleParent=vo2.getMemoTitle();
				String contentParent=vo2.getMemoContent();
				memo.setData(titleChild,titleParent, contentChild,contentParent);
			}else{
				System.out.println(QuarterAdminManager.getTotManager()[month/3].getMemoList());
				vo=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(calendar.get(Calendar.DATE)+14);
				vo2=QuarterAdminManager.getTotManager()[month/3].getMemoList().get("P1").get(month%3).get(calendar.get(Calendar.DATE)+14);
				//System.out.println(cal.get(Calendar.MONTH));
				//System.out.println("---"+QuarterAdminManager.getTotManager()[month/3].getMemoList().get("C1").get(month%3).get(cal.get(Calendar.DATE)));
				String titleChild=vo.getMemoTitle();//등록코드 받는 부분 수정할것
				String contentChild=vo.getMemoContent();
				String titleParent=vo2.getMemoTitle();
				String contentParent=vo2.getMemoContent();
				memo.setData(titleChild,titleParent, contentChild,contentParent);
			}
			System.out.println("날짜 정보,자녀코드 :"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DATE));
			memo.setVisible(true);
		}
	}
	private class SavingBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("자녀 등록코드");
			int month=calendar.get(Calendar.MONTH);
			QuarterManager manage=QuarterAdminManager.getTotManager()[month/3];
			QuarterNo quarter=null;
			if(month==0){quarter=QuarterNo.FIRST_QUARTER;}
			else if(month/3==0){quarter=QuarterNo.FIRST_QUARTER;}
			else if(month/3==1){quarter=QuarterNo.SECOND_QUARTER;}
			else if(month/3==2){quarter=QuarterNo.THIRD_QUARTER;}
			else if(month/3==3){quarter=QuarterNo.FOURTH_QUARTER;}
			
			if(QuarterAdminManager.getTotManager()[month/3].getSavingGoalList().get("C1")==null){//해당 분기 파일 존재하는가 검사
				SavingGoalVO child= manage.getDataLoadManager().loadSavingGoal("f1","C1");
				
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
			}
			System.out.println(quarter.value());
			saving.setData(new SavingGoalVO[]{manage.getSavingGoalList().get("C1").get(quarter.value()) ,null});
			saving.setVisible(true);
		}
	}
	/*private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}*/
}
