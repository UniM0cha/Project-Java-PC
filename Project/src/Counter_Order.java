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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Counter_Order extends JFrame implements ActionListener {
	
	private static DB db = new DB();
	private final String FONT = "나눔고딕";
	private JButton btnConfirm;
	private Vector<String> header = new Vector<>(Arrays.asList("상품명","개수"));
	private Vector<Vector<String>> contents = new Vector<>();
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	private JTable table = new JTable(tableModel);

	public Counter_Order(int pcNum, Component com) {
		this.setSize(400, 200);
		this.setLocationRelativeTo(com);
		this.setTitle(pcNum + "번 PC");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0x25262B), 5));
		
		Color westcolor = new Color(0x202530);
		Color btncolor = new Color(0xF3F1DF);
		Color centersouthcolor = new Color(0x303745);
		Color centernorthcolor = new Color(0xFFF7F1);
		
		// 임시로 선언
//		modelProductName.addElement("치킨");
//		modelProductCount.addElement(1);
		int price = 4000;
		String pay = "카드";
		
		// 왼쪽
		JPanel west = new JPanel();
		west.setBackground(westcolor);
		west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(0x25262B)));
		
		JLabel lblPcNum = new JLabel(Integer.toString(pcNum));
		lblPcNum.setFont(new Font(FONT, Font.BOLD, 40));
		lblPcNum.setForeground(Color.white);
		west.add(lblPcNum);
		
		JLabel lblBun = new JLabel("번");
		lblBun.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblBun.setForeground(Color.white);
		west.add(lblBun);
		
		this.add(west, BorderLayout.WEST);

		//오른쪽
		JPanel center = new JPanel(new BorderLayout());

		JPanel subCenter = new JPanel(new BorderLayout());
		subCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		subCenter.setBackground(centernorthcolor);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.setRowHeight(25);
		table.setFont(new Font(FONT, Font.BOLD, 18));
		JScrollPane scrollTable = new JScrollPane(table);
		subCenter.add(scrollTable);
		
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
		subSouth.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x25262B)));
		
		JPanel subSouthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		subSouthLeft.setBackground(centersouthcolor);
		subSouthLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		JLabel lblPrice = new JLabel(price + "원 (" + pay + ")");
		lblPrice.setFont(new Font(FONT, Font.BOLD, 20));
		lblPrice.setForeground(Color.white);
		subSouthLeft.add(lblPrice);
		
		JPanel subSouthRight = new JPanel();
		subSouthRight.setBackground(centersouthcolor);

		btnConfirm = new JButton("접수 완료");
		btnConfirm.setFont(new Font(FONT, Font.BOLD, 13));
		btnConfirm.setBackground(btncolor);
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

//현재 한계점 : 주문 내역을 확인하고 있을 때 주문이 들어온다면 확인 불가...?