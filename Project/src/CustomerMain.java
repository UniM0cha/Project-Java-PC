import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerMain extends JFrame implements ActionListener, WindowListener{

	private static DB db = new DB();
	private final String FONT = "나눔고딕";
	int pcNum;
	int usedTime = 0;
	JButton btnOrder, btnExit;
	JLabel lblUsedTime;
	Timer time;
	Random random = new Random();
	
	public CustomerMain() {
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setTitle("사용자 화면");
		this.addWindowListener(this);
		
		pcNum = random.nextInt(29) + 1;	// 1~30 무작위 숫자
		
		Color maincolor = new Color(0xF2F2F2);
		Color btnordercolor = new Color(0xA99C90);
		Color btnexitcolor = new Color(0x84878A);
		
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
		JLabel lblPc = new JLabel("PC번호 : " + pcNum);
		lblPc.setFont(new Font(FONT, Font.BOLD, 15));
		JLabel lblLeftTime = new JLabel("남은 시간   00:00");
		lblLeftTime.setFont(new Font(FONT, Font.BOLD, 15));
		btnOrder = new JButton("먹거리 주문");
		btnOrder.setFont(new Font(FONT, Font.BOLD, 18));
		btnOrder.setBackground(btnordercolor);
		btnOrder.addActionListener(this);
		btnExit = new JButton("사용 종료");
		btnExit.setFont(new Font(FONT, Font.BOLD, 18));
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
	
	private void updateStatementAtState(int state) {
		String sql = "UPDATE state "
				+ "SET statement = " + state + " "
				+ "WHERE pcNum = " + pcNum;
		db.Update(sql);
	}

	public static void main(String[] args) {
		new CustomerMain();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnOrder) {
			new CustomerOrder();
		}
		else if (obj == btnExit) {
			updateStatementAtState(0);  //사용 종료 버튼을 누를 때 오프라인으로 변경
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		updateStatementAtState(1);	//창이 켜질 때 온라인으로 변경
	}
	@Override
	public void windowClosing(WindowEvent e) {
		updateStatementAtState(0);  //x버튼으로 끌 때 오프라인으로 변경
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}

}