package PcBangOrder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements ActionListener{

	// 현재 프레임 사이즈 측정을 위한 버튼임
	JButton sizeTestBtn;
	
	public MainFrame() {
		//정윤		
		this.setSize(1000, 750);
		this.setTitle("주문내역");
		this.setLocationRelativeTo(this);
		
		// 배경색깔은 어떻게 할까 싶어. 피씨방이니까 다크한 분위기는 어떨까 싶은데
		this.getContentPane().setBackground(new Color(0x2F2F2F));
		
		// 버튼 붙히려고 임시로 설정했어
		this.setLayout(new FlowLayout());
		
		sizeTestBtn = new JButton("사이즈 출력");
		sizeTestBtn.addActionListener(this);
		this.add(sizeTestBtn);
		
		//준기
		
		
		//정우
		
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == sizeTestBtn) {
			System.out.println(this.getSize().width + ", " + this.getSize().height);
		}
		
	}

}
