import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CounterMainFrame extends JFrame{

	private JPanel order, customer, customer1;
	private JLabel orderlist, orderlist1, orderlist2, orderlist3, orderlist4, orderlist5;
	private JPanel pcnum1, pcnum2, pcnum3;
	private LineBorder aa = new LineBorder(Color.black, 4);
	private LineBorder bb = new LineBorder(Color.black, 3);
	private LineBorder cc = new LineBorder(Color.black, 2);
	private LineBorder dd = new LineBorder(Color.black, 1);
	private String [] pclist1 = {"2번 PC", "4번 PC", "6번 PC", "8번 PC", "10번 PC"};
	private JPanel orderNorth;
	private JButton[] bt1, bt2, bt;
	private JLabel[] lb1, lb2, lb;
	private JLabel[] timerset, timerset1, timerset2;
	Timer timer;
	TimerTask task1;

	public CounterMainFrame(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		//  주문목록
		
		order = new JPanel();
		order.setLayout(new BorderLayout());
		order.setBackground(Color.DARK_GRAY);
		order.setBorder(aa);
		add(order, BorderLayout.WEST);
		
		orderNorth = new JPanel();
		orderNorth.setBackground(Color.DARK_GRAY);
		orderNorth.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));
		
		orderlist = new JLabel("주문 목록");
		orderlist.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		orderlist.setForeground(Color.YELLOW);
		
		orderNorth.add(orderlist);
		order.add(orderNorth, BorderLayout.NORTH);
		
		JList<String> pclist = new JList<String>(pclist1);
		pclist.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		pclist.setBackground(Color.DARK_GRAY);
		pclist.setForeground(Color.orange);
		order.add(pclist);
		
		//  손님 이용하는거 보는 틀
		
		customer = new JPanel();
		customer.setLayout(new BorderLayout());
		customer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		customer.setBackground(Color.gray);
		
		
		customer1 = new JPanel();
		customer1.setLayout(new GridLayout(3,1, 20, 20));
		customer1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		customer1.setBackground(Color.white);
		
		
		
		pcnum1 = new JPanel();
		
		pcnum1.setLayout(new GridLayout(2,5, 5, 5));
		
		bt = new JButton[10];
		for(int i = 0; i<bt.length; i++)
		{
			bt[i] = new JButton();
		}
		
		lb = new JLabel[10];
		for(int i = 0; i<lb.length; i++)
		{
			lb[i] = new JLabel((i+1) + "번 PC");
		}
		
		timerset = new JLabel[10];
		for(int i = 0; i<timerset.length; i++)
		{
			timerset[i] = new JLabel((i+1) + "번 주문대기중");
		}
		
		for(int i = 0; i <= 9; i++)
		{
			bt[i].setLayout(new BorderLayout());
			bt[i].setBorder(cc);
			if(i%2 == 0)
			{
				bt[i].setBackground(Color.gray);
				pcnum1.add(bt[i]);
				
				lb[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb[i].setHorizontalAlignment(lb[i].CENTER);
				bt[i].add(lb[i], BorderLayout.NORTH);
				
				timerset[i].setHorizontalAlignment(lb[i].CENTER);
				timerset[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt[i].add(timerset[i], BorderLayout.CENTER);
			}
			else if(i%2 == 1)
			{
				bt[i].setBackground(Color.green);
				bt[i].addActionListener(new MyListener());
				pcnum1.add(bt[i]);
				
				lb[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb[i].setHorizontalAlignment(lb[i].CENTER);
				bt[i].add(lb[i], BorderLayout.NORTH);
				
				timerset[i].setHorizontalAlignment(lb[i].CENTER);
				timerset[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt[i].add(timerset[i], BorderLayout.CENTER);
			}
				
		}
		
		pcnum2 = new JPanel();

		pcnum2.setLayout(new GridLayout(2,5, 5, 5));
		
		bt1 = new JButton[10];
		for(int i = 0; i<bt1.length; i++)
		{
			bt1[i] = new JButton();
		}
		
		lb1 = new JLabel[10];
		for(int i = 0; i<lb1.length; i++)
		{
			lb1[i] = new JLabel((i+11) + "번 PC");
		}
		
		timerset1 = new JLabel[10];
		for(int i = 0; i<timerset1.length; i++)
		{
			timerset1[i] = new JLabel((i+11) + "번 주문대기중");
		}
		
		for(int i = 0; i <= 9; i++)
		{
			bt1[i].setLayout(new BorderLayout());
			bt1[i].setBorder(cc);
			if(i%2 == 0)
			{
				bt1[i].setBackground(Color.gray);
				pcnum2.add(bt1[i]);
				
				lb1[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb1[i].setHorizontalAlignment(lb1[i].CENTER);
				bt1[i].add(lb1[i], BorderLayout.NORTH);
				
				timerset1[i].setHorizontalAlignment(lb1[i].CENTER);
				timerset1[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt1[i].add(timerset1[i], BorderLayout.CENTER);
			}
			else if(i%2 == 1)
			{
				bt1[i].setBackground(Color.green);
				bt1[i].addActionListener(new MyListener());
				pcnum2.add(bt1[i]);
				
				lb1[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb1[i].setHorizontalAlignment(lb1[i].CENTER);
				bt1[i].add(lb1[i], BorderLayout.NORTH);
				
				timerset1[i].setHorizontalAlignment(lb1[i].CENTER);
				timerset1[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt1[i].add(timerset1[i], BorderLayout.CENTER);
			}
				
		}
		
		pcnum3 = new JPanel();
		
		pcnum3.setLayout(new GridLayout(2,5, 5, 5));
		
		bt2 = new JButton[10];
		for(int i = 0; i<bt2.length; i++)
		{
			bt2[i] = new JButton();
		}
		
		lb2 = new JLabel[10];
		for(int i = 0; i<lb2.length; i++)
		{
			lb2[i] = new JLabel((i+21) + "번 PC");
		}
		
		timerset2 = new JLabel[10];
		for(int i = 0; i<timerset2.length; i++)
		{
			timerset2[i] = new JLabel((i+21) + "번 주문대기중");
		}
		
		for(int i = 0; i <= 9; i++)
		{
			bt2[i].setLayout(new BorderLayout());
			bt2[i].setBorder(cc);
			if(i%2 == 0)
			{
				bt2[i].setBackground(Color.gray);
				pcnum3.add(bt2[i]);
				
				lb2[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb2[i].setHorizontalAlignment(lb2[i].CENTER);
				bt2[i].add(lb2[i], BorderLayout.NORTH);
				
				timerset2[i].setHorizontalAlignment(lb2[i].CENTER);
				timerset2[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt2[i].add(timerset2[i], BorderLayout.CENTER);
			}
			else if(i%2 == 1)
			{
				bt2[i].setBackground(Color.green);
				bt2[i].addActionListener(new MyListener());
				pcnum3.add(bt2[i]);
				
				lb2[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				lb2[i].setHorizontalAlignment(lb2[i].CENTER);
				bt2[i].add(lb2[i], BorderLayout.NORTH);
				
				timerset2[i].setHorizontalAlignment(lb2[i].CENTER);
				timerset2[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
				bt2[i].add(timerset2[i], BorderLayout.CENTER);
			}
				
		}
		
		customer1.add(pcnum1, BorderLayout.NORTH);
		customer1.add(pcnum2, BorderLayout.CENTER);
		customer1.add(pcnum3, BorderLayout.SOUTH);
		customer.add(customer1, BorderLayout.CENTER);
		add(customer, BorderLayout.CENTER);

		timer = new Timer();
		task1 = new TimerTask() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					if (isOn[i]) {
						times[i]++;
						timerset[i].setText(times[i] + "초");
					}
				}
				for (int i = 0; i < 10; i++) {
					if (isOn1[i]) {
						times1[i]++;
						timerset1[i].setText(times1[i] + "초");
					}
				}
				for (int i = 0; i < 10; i++) {
					if (isOn2[i]) {
						times2[i]++;
						timerset2[i].setText(times2[i] + "초");
					}
				}
			}
		};
		
		timer.scheduleAtFixedRate(task1, 0, 1000);
		
		setVisible(true);
	}
	
	
	int[] times = new int[30];
	int[] times1 = new int[30];
	int[] times2 = new int[30];
	boolean[] isOn = new boolean[30];
	boolean[] isOn1 = new boolean[30];
	boolean[] isOn2 = new boolean[30];
	
	
	private class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			for(int i = 0; i < 10; i++) {
				if(obj == bt[i]) {
					bt[i].setBackground(Color.white);
					isOn[i] = true;
				}
			}
			for(int i = 0; i < 10; i++) {
				if(obj == bt1[i]) {
					bt1[i].setBackground(Color.white);
					isOn1[i] = true;
				}
			}
			for(int i = 0; i < 10; i++) {
				if(obj == bt2[i]) {
					bt2[i].setBackground(Color.white);
					isOn2[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		new CounterMainFrame("카운터 화면", 1200, 900);
	}

}