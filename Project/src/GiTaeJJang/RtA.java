package GiTaeJJang;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RtA extends JFrame implements ActionListener {
	
	private ImageIcon[] imgs = {new ImageIcon("images/RTA.jpg")};
	private JLabel lblImg, lblcnt, lblprice;
	private JButton jb1, jb2;
	private int index;
	private String cnt, price;
	private JPanel jp, jp1, jp2;
	
	public RtA(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout(10, 20));	
		index = 0;
		cnt = "0";
		price = "100";
		
		jp = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jb1 = new JButton("-");
		jb1.addActionListener(this);
		jb2 = new JButton("+");
		jb2.addActionListener(this);
		
		lblImg = new JLabel(imgs[index]);
		lblcnt = new JLabel(cnt + "");
		lblprice = new JLabel(price + "원        ");
		
		jp.add(jb1);
		jp.add(lblcnt);
		jp.add(jb2);
		
		jp2.setLayout(new BorderLayout());
		
		jp2.add(jp, BorderLayout.WEST);
		jp2.add(lblprice, BorderLayout.EAST);
		
		add(lblImg);
		add(jp2, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) {
		new RtA("Test", 300, 248);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
