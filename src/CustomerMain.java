import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerMain extends JFrame implements ActionListener, WindowListener {

	private static DB db = new DB();

	// 폰트
	private final String FONT = "나눔고딕";
	private final int FONTSIZE = 23;

	// 구성요소
	private JLabel lbltemp, lblLeftTime, lblUsedTime;
	private JButton btnOrder, btnExit;

	// 시간 관련
	private long diff, startTime, currentTime, chargedTime, leftTime;
	private Timer time;
	private DateFormat format = new SimpleDateFormat("HH:mm");

	// 랜덤
	private Random random = new Random();
	private int pcNum = random.nextInt(29) + 1; // 1~30 무작위 숫자

	public static void main(String[] args) {
		new CustomerMain();
	}

	public CustomerMain() {
		this.setSize(350, 300);
		this.setLocationRelativeTo(this);
		this.setTitle(pcNum + "번 PC");
		this.addWindowListener(this);
		this.setResizable(false);

		startTime = System.currentTimeMillis(); // 시작 시간 설정
		chargedTime = 5 * (60 * 60 * 1000) - 32400000; // 충전한 시간 설정 (임시로 5시간 설정)
		time = new Timer();
		TimerTask task = new TimerTasks(); // 따로 타이머 태스크 클래스 정의
		time.scheduleAtFixedRate(task, 1000, 1000); // 타이머 작동

		// 색깔
		Color maincolor = Color.DARK_GRAY;
		Color btnordercolor = new Color(0xA99C90);
		Color btnexitcolor = new Color(0x84878A);
		Color panColor = new Color(0x646466);

		JPanel main = new JPanel(new BorderLayout(0, 25));
		main.setBackground(maincolor);
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// 위쪽 부분 (정보 표시)
		JPanel panUp = new JPanel(new GridLayout(2, 2, 8, 8));
		panUp.setBackground(maincolor);

		// 사용시간
		JPanel panUsedTime = new JPanel(new GridLayout(2, 1));
		panUsedTime.setBackground(panColor);

		lbltemp = new JLabel("사용 시간");
		lbltemp.setForeground(Color.WHITE);
		lbltemp.setFont(new Font(FONT, Font.PLAIN, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panUsedTime.add(lbltemp, BorderLayout.NORTH);

		lblUsedTime = new JLabel();
		lblUsedTime.setForeground(Color.WHITE);
		lblUsedTime.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		lblUsedTime.setHorizontalAlignment(JLabel.CENTER);

		panUsedTime.add(lblUsedTime, BorderLayout.CENTER);

		panUp.add(panUsedTime);

		// 시작시간
		JPanel panStartTime = new JPanel(new GridLayout(2, 1));
		panStartTime.setBackground(panColor);

		lbltemp = new JLabel("시작 시간");
		lbltemp.setForeground(Color.WHITE);
		lbltemp.setFont(new Font(FONT, Font.PLAIN, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panStartTime.add(lbltemp, BorderLayout.NORTH);

		JLabel lblStartTime = new JLabel();
		lblStartTime.setForeground(Color.WHITE);
		lblStartTime.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		lblStartTime.setHorizontalAlignment(JLabel.CENTER);
		String stringStartTime = format.format(startTime);
		lblStartTime.setText(stringStartTime);
		panStartTime.add(lblStartTime, BorderLayout.CENTER);

		panUp.add(panStartTime);

		// PC번호
		JPanel panPc = new JPanel(new GridLayout(2, 1));
		panPc.setBackground(panColor);

		lbltemp = new JLabel("PC번호");
		lbltemp.setForeground(Color.WHITE);
		lbltemp.setFont(new Font(FONT, Font.PLAIN, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panPc.add(lbltemp, BorderLayout.NORTH);

		JLabel lblPc = new JLabel(pcNum + "");
		lblPc.setForeground(Color.WHITE);
		lblPc.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		lblPc.setHorizontalAlignment(JLabel.CENTER);
		panPc.add(lblPc, BorderLayout.CENTER);
		panUp.add(panPc);

		main.add(panUp, BorderLayout.CENTER);

		// 남은시간
		JPanel panLeftTime = new JPanel(new GridLayout(2, 1));
		panLeftTime.setBackground(panColor);

		lbltemp = new JLabel("남은 시간");
		lbltemp.setForeground(Color.WHITE);
		lbltemp.setFont(new Font(FONT, Font.PLAIN, 15));
		lbltemp.setHorizontalAlignment(JLabel.CENTER);
		panLeftTime.add(lbltemp, BorderLayout.NORTH);

		lblLeftTime = new JLabel();
		lblLeftTime.setForeground(Color.yellow);
		lblLeftTime.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		lblLeftTime.setHorizontalAlignment(JLabel.CENTER);
		panLeftTime.add(lblLeftTime, BorderLayout.CENTER);

		panUp.add(panLeftTime);

		// 아래쪽 부분 (버튼 부분)
		JPanel panDown = new JPanel(new GridLayout(1, 2, 8, 8));
		panDown.setBackground(maincolor);

		// 먹거리 주문 버튼
		btnOrder = new JButton("먹거리 주문");
		btnOrder.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		btnOrder.setBackground(btnordercolor);
		btnOrder.setForeground(Color.white);
		btnOrder.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
		btnOrder.addActionListener(this);
		panDown.add(btnOrder);
		// 사용 종료 버튼
		btnExit = new JButton("사용 종료");
		btnExit.setFont(new Font(FONT, Font.BOLD, FONTSIZE));
		btnExit.setBackground(btnexitcolor);
		btnExit.setForeground(Color.white);
		btnExit.addActionListener(this);
		panDown.add(btnExit);

		main.add(panDown, BorderLayout.SOUTH);

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

	// state 테이블의 statement 업데이트
	private void updateStatementAtState(int state) {
		String sql = "UPDATE state "
				+ "SET statement = " + state + " "
				+ "WHERE pcNum = " + pcNum;
		db.Update(sql);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnOrder) {
			new CustomerOrder(pcNum);
		} else if (obj == btnExit) {
			updateStatementAtState(0); // 사용 종료 버튼을 누를 때 오프라인으로 변경
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		updateStatementAtState(1); // 창이 켜질 때 온라인으로 변경
	}

	@Override
	public void windowClosing(WindowEvent e) {
		updateStatementAtState(0); // x버튼으로 끌 때 오프라인으로 변경
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
