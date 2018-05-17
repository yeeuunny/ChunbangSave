package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.CategoryTotalVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;

public class MonthHistoryUI extends JDialog{
	private JLabel prev;
	private JLabel next;
	private JLabel history;
	private ImageIcon prevIcon;
	private ImageIcon nextIcon;
	
	private JTable table; 

	private JScrollPane barScrollPane;
	private JScrollPane tableScrollPane;
	private DefaultTableModel model;
	private JPanel barPanel;
	
	private GregorianCalendar cal;
	private int code;
	private int month;
	/*
	 * �θ�
	 * 0 - �ĺ�
	 * 1 - ����
	 * 2 - ������
	 * 3 - ����
	 * 4 - ī����
	 * 5 - ���� ��Ÿ 
	 * 6 - ����
	 * 7 - ����� 
	 * 8 - ����
	 * 9 - ���ʽ� 
	 * 10 - ���� ��Ÿ 
	 * 11 - ����
	 * 12 - ��� 
	 * 
	 * �ڳ�
	 * 0 - �п�ǰ 
	 * 1 - ����
	 * 2 - ���
	 * 3 - ���� 
	 * 4 - ���� ��Ÿ 
	 * 5 - �뵷 
	 * 6 - ��Ÿ 
	 * 11 - ����
	 * 12 - ��� 
	 */
	private int quarter;
	
	/*
	 * MainUI���� ���޹��� �� ����� ��� ������ 
	 */
	private MonthCashBookDetailVO monthCashBookDetailVO;
	
	public MonthHistoryUI(JFrame frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("������� ����");
		this.cal=cal;
		this.code=code;
		
		month = cal.get(Calendar.MONTH);
		System.out.println("�⵵ ������ ������ : "+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+"����ڵ� �ʿ� "+code);
		quarter = (int)Math.ceil((month+ 1) / 3.0);
		
		Container contentPane=getContentPane();
		// ���� ��� �κ��� ������ ȭ��ǥ ��ư ����
		history=new JLabel(month + 1 +"�� ���");
		history.setFont(new Font("�������",Font.BOLD,30));
		prevIcon = new ImageIcon("leftArrow.png");
		nextIcon=new ImageIcon("rightArrow.png");
		Image image = prevIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		prevIcon = new ImageIcon(newimg);  // transform it back
		image = nextIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		nextIcon = new ImageIcon(newimg);  // transform it back
		
		prev=new JLabel(prevIcon);
		next=new JLabel(nextIcon);
		
		
		//���� ��� �κ��� ������ ȭ��ǥ ��ư ��ġ
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(prev);
		top.add(history);
		top.add(next);
		
		prev.addMouseListener(new PreviousMonthListener());
		next.addMouseListener(new NextMonthListener());
		//���̺�  ����
		String[]columNames={"��¥","ī�װ�", "��ī�װ�", "�ݾ�","�޼�����"};
		model=new DefaultTableModel(columNames,0)
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		table = new JTable(model);
		tableScrollPane = new JScrollPane(table);
		
		//�ܾ� ���̺� ������ ���̺�� ���� �۾�
		//JLabel money=new JLabel("�ܾ� : 1000��");
		JPanel moneySet=new JPanel(new FlowLayout(FlowLayout.CENTER));
		//moneySet.add(��);
		JPanel subMid=new JPanel();
		subMid.setLayout(new BoxLayout(subMid,BoxLayout.Y_AXIS));
		JPanel subsubMid=new JPanel();
		
		//subMid.add(subsubMid);
		subMid.setBorder(new TitledBorder(""));
		//subMid.setLayout(new BoxLayout(contentPane,BoxLayout.X_AXIS));
		subMid.add(tableScrollPane);
		subMid.add(moneySet);
		
		//�׸� ���� ����
		JButton stop=new JButton("�׸�����");
		JPanel stopBtn=new JPanel();
		stopBtn.setLayout(new FlowLayout());
		stopBtn.add(stop);
		stop.addActionListener(new CancelBtnListener(this));
		
		JPanel mid=new JPanel();
		mid.setLayout(new GridLayout(2,1));
		
		barPanel = new JPanel();
		barScrollPane = new JScrollPane(barPanel);
	    barScrollPane.setBorder(null);
	    setBar();

	    mid.add(barScrollPane);
		mid.add(subMid);
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(stopBtn,BorderLayout.SOUTH);
		
		setSize(500,700);
		setLocation(400,300);
	//	setVisible(true);
	}
	public void setTable()
	{
		//model.setRowCount(0);
		model.setNumRows(0);
		getContentPane().validate();
		//String[] data = null;
		if(monthCashBookDetailVO == null)
			return;
		for(int i = 0; i < cal.getActualMaximum(month + 1); i++)
		{
			if(monthCashBookDetailVO.get(i) != null)
			{
			for(int j = 0; j < monthCashBookDetailVO.get(i).size(); j++)
			{
				CashBookDetailVO oneDetailvo = monthCashBookDetailVO.get(i).get(j);
				/*
				 * ���̺� �ִ� �۾� 
				 */
				String[] data = new String[]{
						oneDetailvo.getRegisterDate()[0] + "-"
						+ oneDetailvo.getRegisterDate()[1] + "-"
						+ oneDetailvo.getRegisterDate()[2] ,
						oneDetailvo.getRegisterCategory().value(), oneDetailvo.getDetailCategory(),
						oneDetailvo.getAmount() };
				model.addRow(data);
			
			}
			}
		}
	}
	public void setBar() 
	{
		barPanel.removeAll();
		// ���� 0 ~ 100 ���� ����
		barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.Y_AXIS));
		if(monthCashBookDetailVO == null)
			return;
		System.out.println("**quarter " + quarter);
		CategoryTotalVO[] totCategory =
				QuarterAdminManager.getTotManager()[quarter - 1].calculateCategoryTotal("p1", month);
		if(totCategory == null)
			return;
		
		/* �Ѿ�� ��� ī�װ� �� ��ŭ �г�, ���α׷�����, ���̺� �迭 ���� */
		JPanel[] panel = new JPanel[totCategory.length];
		JProgressBar[] bar = new JProgressBar[totCategory.length];
		JLabel[] label = new JLabel[totCategory.length];
		
		/* ī�װ�, �ݾ�, �ۼ�Ʈ ���� */
		for(int i = 0; i < totCategory.length; i++)
		{
			label[i] = new JLabel();
			bar[i] = new JProgressBar();
			panel[i] = new JPanel();
			
			label[i].setText(totCategory[i].getDetailCategory() +"\t\t\t\t\t\t"); //////* ī�װ��� ����
			
			bar[i].setMinimum(0);
			bar[i].setMaximum(100);
			bar[i].setStringPainted(true);
		//	bar[i].setForeground(Color.orange);// ���α׷����� ����

			int percent = Integer.parseInt(totCategory[i].getPercent());
			bar[i].setValue(percent); /////* �ۼ�Ʈ�� ���� 
			bar[i].setString(percent +"% " + totCategory[i].getAmount() + "��"); ////* �ۼ�Ʈ�� �Ѿ����� ���� 
			
			panel[i].add(label[i]);
		//	label[i].setHorizontalAlignment(SwingConstants.CENTER);
			panel[i].add(bar[i]);
			
			barPanel.add(panel[i]);
		}
		getContentPane().validate();
	}
	
	public void setData(MonthCashBookDetailVO monthCashBookDetailVO, int month)
	{
		this.monthCashBookDetailVO = monthCashBookDetailVO;
		this.month = month;
		
	}

	private class PreviousMonthListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				if (month == 1)
					return;
				cal.set(2016, --month, 1);

				history.setText(month + 1 + "�� ���");
				System.out.println("����⵵, ������ , ����ڵ�" + cal.get(Calendar.YEAR)
						+ month + code);
				
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
					ArrayList<MonthCashBookDetailVO> temp 
						= QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", quarterMonth);
					monthCashBookDetailVO = temp.get(month + 3 - 3*quarter);
				}
				setTable();
				setBar();
			}
		}
	}
	private class NextMonthListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 1) {
				if (month == 12)
					return;
				cal.set(2016, ++month, 1);

				history.setText(month + 1 + "�� ���");
				System.out.println("����⵵, ������ , ����ڵ�" + cal.get(Calendar.YEAR)
						+ month+1 + code);
				
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
					ArrayList<MonthCashBookDetailVO> temp 
						= QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", quarterMonth);
					monthCashBookDetailVO = temp.get(month + 3 - 3*quarter);
					System.out.println("@@@@" + monthCashBookDetailVO);
				}
				setTable();
				setBar();
			}
		}
	}
	private class CloseBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			//setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
	}
}