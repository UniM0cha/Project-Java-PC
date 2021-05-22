import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainCustomer extends JFrame implements ActionListener{

	private final String FONT = "나눔고딕";
	int num = 0;
	JButton btnOrder, btnExit;
	int usedTime = 0;
	JLabel lblUsedTime;
	Timer time;
	
	public MainCustomer() {
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setTitle("사용자 화면");
		
		Color maincolor = new Color(0xF2F2EF);
		Color btnordercolor = new Color(0xDAF5FD);
		Color btnexitcolor = new Color(0xFFD0BF);
		
		JPanel main = new JPanel(new GridLayout(3, 2, 5, 5));
		main.setBackground(maincolor);
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		lblUsedTime = new JLabel("사용 시간   0초");
		lblUsedTime.setFont(new Font(FONT, Font.BOLD, 15));
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				usedTime++;
				lblUsedTime.setText("사용 시간   " + usedTime + "초");
				lblUsedTime.setFont(new Font(FONT, Font.BOLD, 15));
			}
		};
		
		time = new Timer();
		time.scheduleAtFixedRate(task, 1000, 1000);
		
		JLabel lblStartTime = new JLabel("시작 시간   00:00");
		lblStartTime.setFont(new Font(FONT, Font.BOLD, 15));
		JLabel lblPc = new JLabel("PC번호 : " + num);
		lblPc.setFont(new Font(FONT, Font.BOLD, 15));
		JLabel lblLeftTime = new JLabel("남은 시간   00:00");
		lblLeftTime.setFont(new Font(FONT, Font.BOLD, 15));
		btnOrder = new JButton("상품 주문");
		btnOrder.setBackground(btnordercolor);
		btnOrder.addActionListener(this);
		btnExit = new JButton("사용 종료");
		btnExit.setBackground(btnexitcolor);
		btnExit.addActionListener(this);
		
		main.add(lblUsedTime);
		main.add(lblStartTime);
		main.add(lblPc);
		main.add(lblLeftTime);
		main.add(btnOrder);
		main.add(btnExit);
		this.add(main);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainCustomer();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnOrder) {
			new orderFrame("음식주문", 1200, 900);
		}
		else if (obj == btnExit) {
			System.exit(0);
		}
	}

}
