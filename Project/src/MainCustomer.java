import java.awt.Color;
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

	int num = 0;
	JButton btnOrder, btnExit;
	int usedTime = 0;
	JLabel lblUsedTime;
	Timer time;
	
	public MainCustomer() {
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setTitle("사용자 화면");
		
		JPanel main = new JPanel(new GridLayout(3, 2, 5, 5));
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		lblUsedTime = new JLabel("사용 시간   0초");
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				usedTime++;
				lblUsedTime.setText("사용 시간   " + usedTime + "초");
			}
		};
		
		time = new Timer();
		time.scheduleAtFixedRate(task, 1000, 1000);
		
		JLabel lblStartTime = new JLabel("시작 시간   00:00");
		JLabel lblPc = new JLabel("PC번호 : " + num);
		JLabel lblLeftTime = new JLabel("남은 시간   00:00");
		btnOrder = new JButton("상품 주문");
		btnOrder.addActionListener(this);
		btnExit = new JButton("사용 종료");
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
			new SubCustomer();
		}
		else if (obj == btnExit) {
			System.exit(0);
		}
	}

}
