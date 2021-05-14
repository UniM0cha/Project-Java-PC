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
		this.setSize(1100, 800);
		this.setLocationRelativeTo(this);
		this.setTitle("상품 주문");

		
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
