import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	JLabel lbltemp, lblLeftTime, lblUsedTime;
	int usedTime = 0;
	long diff ,startTime, currentTime, chargedTime, leftTime;
	JButton btnOrder, btnExit;
	Timer time;
	Random random = new Random();
	DateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	public CustomerMain() {
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setTitle("사용자 화면");
		this.addWindowListener(this);
		
		pcNum = random.nextInt(29) + 1;					// 1~30 무작위 숫자
		startTime = System.currentTimeMillis();			// 시작 시간 설정
		chargedTime = 5 * (60*60*1000) - 32400000;		// 충전한 시간 설정 (임시로 5시간 설정)
		time = new Timer();
		TimerTask task = new TimerTasks();				// 따로 타이머 태스크 클래스 정의
		time.scheduleAtFixedRate(task, 1000, 1000);		// 타이머 작동
		
		Color maincolor = new Color(0xF2F2F2);
		Color btnordercolor = new Color(0xA99C90);
		Color btnexitcolor = new Color(0x84878A);
		
		JPanel main = new JPanel(new GridLayout(3, 2, 5, 5));
		main.setBackground(maincolor);
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//사용시간
		JPanel panUsedTime = new JPanel(new BorderLayout());
		
		lbltemp = new JLabel("사용 시간");
		lbltemp.setFont(new Font(FONT, Font.BOLD, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panUsedTime.add(lbltemp, BorderLayout.NORTH);
		
		lblUsedTime = new JLabel();
		lblUsedTime.setFont(new Font(FONT, Font.BOLD, 15));
		lblUsedTime.setHorizontalAlignment(JLabel.CENTER);

		panUsedTime.add(lblUsedTime, BorderLayout.CENTER);
		
		main.add(panUsedTime);
		
		//시작시간
		JPanel panStartTime = new JPanel(new BorderLayout());
		
		lbltemp = new JLabel("시작 시간");
		lbltemp.setFont(new Font(FONT, Font.BOLD, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panStartTime.add(lbltemp, BorderLayout.NORTH);
		
		JLabel lblStartTime = new JLabel();
		lblStartTime.setFont(new Font(FONT, Font.BOLD, 15));
		lblStartTime.setHorizontalAlignment(JLabel.CENTER);
		String stringStartTime = format.format(startTime);
		lblStartTime.setText(stringStartTime);
		panStartTime.add(lblStartTime, BorderLayout.CENTER);
		
		main.add(panStartTime);
		
		//PC번호
		JPanel panPc = new JPanel(new BorderLayout());
		
		lbltemp = new JLabel("PC번호");
		lbltemp.setFont(new Font(FONT, Font.BOLD, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panPc.add(lbltemp, BorderLayout.NORTH);
		
		JLabel lblPc = new JLabel(pcNum + "");
		lblPc.setFont(new Font(FONT, Font.BOLD, 15));
		lblPc.setHorizontalAlignment(JLabel.CENTER);
		panPc.add(lblPc, BorderLayout.CENTER);
		
		main.add(panPc);
		
		//남은시간
		JPanel panLeftTime = new JPanel(new BorderLayout());
		
		lbltemp = new JLabel("남은 시간");
		lbltemp.setFont(new Font(FONT, Font.BOLD, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panLeftTime.add(lbltemp, BorderLayout.NORTH);
		
		lblLeftTime = new JLabel();
		lblLeftTime.setFont(new Font(FONT, Font.BOLD, 15));
		lblLeftTime.setHorizontalAlignment(JLabel.CENTER);
		panLeftTime.add(lblLeftTime, BorderLayout.CENTER);
		
		main.add(panLeftTime);
		
		//먹거리 주문 버튼
		btnOrder = new JButton("먹거리 주문");
		btnOrder.setFont(new Font(FONT, Font.BOLD, 15));
		btnOrder.setBackground(btnordercolor);
		btnOrder.addActionListener(this);
		main.add(btnOrder);
		
		//사용 종료 버튼
		btnExit = new JButton("사용 종료");
		btnExit.setFont(new Font(FONT, Font.BOLD, 15));
		btnExit.setBackground(btnexitcolor);
		btnExit.addActionListener(this);
		main.add(btnExit);
		
		this.add(main);
		
		this.setVisible(true);
	}
	
	private class TimerTasks extends TimerTask {
		@Override
		public void run() {
			// 사용시간 업데이트
			currentTime = System.currentTimeMillis();
			diff = currentTime - startTime;
			String usedTime = format.format(diff - 32400000);
			lblUsedTime.setText(usedTime);
			
			// 남은시간 업데이트
			leftTime = chargedTime - diff;
			String stringLeftTime = format.format(leftTime);
			lblLeftTime.setText(stringLeftTime);
		}
	};
	
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
