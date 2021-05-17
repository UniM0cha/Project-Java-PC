import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Counter_Order extends JFrame implements ActionListener {
	
	final String FONT = "맑은 고딕";
	JButton btnConfirm;

	public Counter_Order(int pcNum, Component com, String[] menus, int price, String pay) {
		this.setSize(400, 200);
		this.setLocationRelativeTo(com);
		this.setTitle(pcNum + "번 PC");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		// 왼쪽
		JPanel west = new JPanel();
		west.setBackground(Color.orange);
		west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.black));
		
		JLabel lblPcNum = new JLabel(Integer.toString(pcNum));
		lblPcNum.setFont(new Font(FONT, Font.BOLD, 40));
		west.add(lblPcNum);
		
		JLabel lblBun = new JLabel("번");
		lblBun.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		west.add(lblBun);
		
		this.add(west, BorderLayout.WEST);

		//오른쪽
		JPanel center = new JPanel(new BorderLayout());

		JPanel subCenter = new JPanel(new GridLayout(6,2));
		subCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		subCenter.setBackground(Color.white);

		// 정우가 만약 메뉴들을 스트링 배열로 준다면
		for(String menu : menus) {
			JLabel lblMenu = new JLabel(menu);
			lblMenu.setFont(new Font(FONT, Font.BOLD, 15));
			subCenter.add(lblMenu);
		}
		center.add(subCenter, BorderLayout.CENTER);
		
		JPanel subSouth = new JPanel(new BorderLayout());
		
		JPanel subSouthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		subSouthLeft.setBackground(Color.DARK_GRAY);
		subSouthLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		JLabel lblPrice = new JLabel(price + "원 (" + pay + ")");
		lblPrice.setFont(new Font(FONT, Font.PLAIN, 20));
		lblPrice.setForeground(Color.white);
		subSouthLeft.add(lblPrice);
		
		JPanel subSouthRight = new JPanel();
		subSouthRight.setBackground(Color.DARK_GRAY);

		btnConfirm = new JButton("접수 완료");
		btnConfirm.setFont(new Font(FONT, Font.BOLD, 13));
		btnConfirm.addActionListener(this);
		subSouthRight.add(btnConfirm);

		subSouth.add(subSouthLeft, BorderLayout.CENTER);
		subSouth.add(subSouthRight, BorderLayout.EAST);
		center.add(subSouth, BorderLayout.SOUTH);
		
		this.add(center);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Temp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnConfirm) {
			this.dispose();
		}
	}
}

class Temp extends JFrame implements MouseListener{
	
	JPanel p;
	String[] menus = {"후라이드 치킨", "소떡소떡", "너구리 라면", "참이슬"};
	
	public Temp() {
		this.setSize(300,300);
		this.setLocationRelativeTo(this);
		this.setTitle("TEST");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p = new JPanel();
		p.addMouseListener(this);
		this.add(p);
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == p) {
			if(e.getClickCount() == 2) {	//더블클릭
				new Counter_Order(14, p, menus, 4000, "카드");
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}