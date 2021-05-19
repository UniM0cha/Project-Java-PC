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

public class CounterMainFrame extends JFrame {

	private JPanel order, customer, customer1;
	private JLabel orderlist, orderlist1, orderlist2, orderlist3, orderlist4, orderlist5;
	private JPanel pcnum1, pcnum2, pcnum3;
	private LineBorder borderThickness4 = new LineBorder(Color.black, 4);
	private LineBorder borderThickness3 = new LineBorder(Color.black, 3);
	private LineBorder borderThickness2 = new LineBorder(Color.black, 2);
	private LineBorder borderThickness1 = new LineBorder(Color.black, 1);
	private String[] pclist1 = { "2번 PC", "4번 PC", "6번 PC", "8번 PC", "10번 PC" };
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

		// 주문목록

		order = new JPanel();
		order.setLayout(new BorderLayout());
		order.setBackground(Color.DARK_GRAY);
		order.setBorder(borderThickness4);
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

		// 손님 이용하는거 보는 틀

		customer = new JPanel();
		customer.setLayout(new BorderLayout());
		customer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		customer.setBackground(Color.gray);

		customer1 = new JPanel();
		customer1.setLayout(new GridLayout(3, 1, 20, 20));
		customer1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		customer1.setBackground(Color.white);

		// 컴퓨터 자리 세팅
		
		pcnum1 = new JPanel();
		pcnum1.setLayout(new GridLayout(2, 5, 5, 5));

		pcnum2 = new JPanel();
		pcnum2.setLayout(new GridLayout(2, 5, 5, 5));

		pcnum3 = new JPanel();
		pcnum3.setLayout(new GridLayout(2, 5, 5, 5));

		bt = new JButton[30];
		lb = new JLabel[30];
		timerset = new JLabel[30];
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton();
			bt[i].setLayout(new BorderLayout());
			bt[i].setBorder(borderThickness2);
			bt[i].setBackground(Color.gray);
			bt[i].setEnabled(false);
			
			lb[i] = new JLabel((i + 1) + "번 PC");
			lb[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lb[i].setHorizontalAlignment(JLabel.CENTER);
			bt[i].add(lb[i], BorderLayout.NORTH);
			
			timerset[i] = new JLabel("오프라인");
			timerset[i].setHorizontalAlignment(JLabel.CENTER);
			timerset[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			bt[i].add(timerset[i], BorderLayout.CENTER);
			
			if (i < 10) {
				pcnum1.add(bt[i]);
			}
			else if (i < 20) {
				pcnum2.add(bt[i]);
			}
			else if (i < 30) {
				pcnum3.add(bt[i]);
			}
		}
		
		// 짝수칸에 온라인 만들기
		for (int pcNum = 1; pcNum < 30; pcNum+=2) {
			Online(pcNum);
		}

		customer1.add(pcnum1, BorderLayout.NORTH);
		customer1.add(pcnum2, BorderLayout.CENTER);
		customer1.add(pcnum3, BorderLayout.SOUTH);
		customer.add(customer1, BorderLayout.CENTER);
		add(customer, BorderLayout.CENTER);

		// 타이머 작동 시작시키기
		timer = new Timer();
		task1 = new TimerTask() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					if (isOn[i]) {
						times[i]++;
						timerset[i].setText(times[i] + "초");
					}
				}
			}
		};

		timer.scheduleAtFixedRate(task1, 0, 1000);

		setVisible(true);
	}

	private void Online(int i) {
		bt[i].setBackground(Color.green);
		bt[i].setEnabled(true);
		bt[i].addActionListener(new MyListener());
		timerset[i].setText("주문 대기 중");
		
	}

	int[] times = new int[30];
	boolean[] isOn = new boolean[30];

	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			for (int i = 0; i < 30; i++) {
				if (obj == bt[i]) {
					bt[i].setBackground(Color.white);
					isOn[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		new CounterMainFrame("카운터 화면", 1200, 900);
	}

}