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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Counter_Order extends JFrame implements ActionListener {
	
	private static DB db = new DB();
	private final String FONT = "나눔고딕";
	private JButton btnConfirm;
//	private DefaultListModel<String> modelProductName = new DefaultListModel<>();
//	private DefaultListModel<Integer> modelProductCount = new DefaultListModel<>();
//	private JList<String> listProductName = new JList<>(modelProductName);
//	private JList<Integer> listProductCount = new JList<>(modelProductCount);
	private String header[] = {"상품명", "개수"};
	private String contents[][] = {
			{"치킨","1"}
	};
	private JTable table = new JTable(contents, header);

	public Counter_Order(int pcNum, Component com) {
		this.setSize(400, 200);
		this.setLocationRelativeTo(com);
		this.setTitle(pcNum + "번 PC");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		// 임시로 선언
//		modelProductName.addElement("치킨");
//		modelProductCount.addElement(1);
		int price = 4000;
		String pay = "카드";
		
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

		JPanel subCenter = new JPanel(new BorderLayout());
		subCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		subCenter.setBackground(Color.white);
		subCenter.add(table);
		
//		JPanel panProductName = new JPanel(new BorderLayout());
//		panProductName.setBackground(Color.black);
//		panProductName.add(listProductName);
//		subCenter.add(panProductName, BorderLayout.CENTER);
//		
//		JPanel panProductCount = new JPanel(new BorderLayout());
//		panProductCount.add(listProductCount);
//		subCenter.add(panProductCount, BorderLayout.EAST);
		
//		for(String menu : menus) {
//			JLabel lblMenu = new JLabel(menu);
//			lblMenu.setFont(new Font(FONT, Font.BOLD, 15));
//			subCenter.add(lblMenu);
//		}
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnConfirm) {
			this.dispose();
		}
	}
}