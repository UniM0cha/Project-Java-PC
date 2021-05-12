import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubCustomer extends JFrame implements ActionListener{

	int num = 0;
	JButton btnOrder, btnExit;
	
	public SubCustomer() {
		this.setSize(900, 700);
		this.setLocationRelativeTo(this);
		
		JPanel main = new JPanel(new GridLayout(3, 2, 5, 5));
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel lblUsedTime = new JLabel("사용 시간   00:00");
		JLabel lblStartTime = new JLabel("시작 시간   00:00");
		JLabel lblPc = new JLabel("PC번호 : " + num);
		JLabel lblLeftTime = new JLabel("남은 시간   00:00");
		btnOrder = new JButton("상품 주문");
		btnOrder.addActionListener(this);
		btnExit = new JButton("사용 종료");
		
		main.add(lblUsedTime);
		main.add(lblStartTime);
		main.add(lblPc);
		main.add(lblLeftTime);
		main.add(btnOrder);
		main.add(btnExit);
		this.add(main);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnOrder) {
			new SubCustomer();
		}
	}

}
