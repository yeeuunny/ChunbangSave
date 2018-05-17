package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YearHistoryUI extends JDialog{
	private JLabel prev;
	private JLabel next;
	private ImageIcon prevIcon;
	private ImageIcon nextIcon;
	
	private JLabel history;
	private JButton stop;
	
	private GregorianCalendar cal;
	private int code;
	public YearHistoryUI(JFrame frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("�⺰���");
		this.code=code;
		this.cal=cal;
		System.out.println("������ : "+cal.get(Calendar.YEAR)+"����� ����ڵ�"+code);
		
		Container contentPane=getContentPane();
		
		//��� ��ư ����
		history=new JLabel(cal.get(Calendar.YEAR)+"�� ���");
		prevIcon = new ImageIcon("prevImage.png");
		nextIcon=new ImageIcon("nextImage.png");
		prev=new JLabel(prevIcon);
		next=new JLabel(nextIcon);
		prev.addMouseListener(new PreviousYearListener());
		next.addMouseListener(new NextYearListener());
		
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(prev);
		top.add(history);
		top.add(next);
		//���� ���ȭ �صѰ�
		JPanel draw=new JPanel(){
			public void paint(Graphics g){
				setSize(400,270);
				g.clearRect(0,0,getWidth(),getHeight());
				g.drawLine(50, 250, 350, 250);
				for(int cnt=1;cnt<4;++cnt){
					g.drawString("�п�ǰ", 0, cnt*80);
					
				}
				g.drawLine(50,20,50,250);//x,y���� x2,y2 ���׸�
				g.fillRect(60,68,40,20);//�г��� x,y��ǥ ,���γ���, ���γ���
				g.drawString("40%", 100,78);
				g.setColor(Color.RED);
				g.fillRect(60,136,80,20);
				g.drawString("80%", 140,146);
				g.setColor(Color.BLUE);
				g.fillRect(60,204,30,20);
				g.drawString("30%", 90,214);
			}
		};
		JLabel money=new JLabel("�ݾ� : 100000");
		money.setAlignmentX(CENTER_ALIGNMENT);
		JLabel mid=new JLabel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(draw);
		mid.add(money);
		
		stop=new JButton("�׸�����");
		stop.addActionListener(new CancelBtnListener(this));
		
		JPanel bot=new JPanel();
		bot.setLayout(new FlowLayout());
		bot.add(stop);
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(bot,BorderLayout.SOUTH);
		
		setSize(500,430);
		setLocation(400,300);
	}
	private class PreviousYearListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);

				history.setText(cal.get(Calendar.YEAR)+"�⵵ ���");
				System.out.println("������ ����ڵ�"+cal.get(Calendar.YEAR)+"  "+code);
			}
		}
	}
	private class NextYearListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);

				history.setText(cal.get(Calendar.YEAR)+"�⵵ ���");
				System.out.println("������ ����ڵ�"+cal.get(Calendar.YEAR)+"  "+code);
			}
		}
	}
	
}
