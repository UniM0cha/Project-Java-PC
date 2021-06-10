import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Stock extends JFrame{
	
	private static DB db = new DB();
	private JPanel mainpanel, stocktable, salestable;
	private JTextField salessum;
	private JLabel saleslabel;
	private JLabel wonlabel;
	private String header[] = { "학생이름", "국어", "영어", "수학"};
	private String contents[][] = {
			{"박영수", "90", "87", "98"},
			{"김영희", "90", "87", "98"},
			{"김철수", "90", "87", "98"}
	};
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };

	public Stock(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);
		
		Color mainpanelcolor = new Color(0x7f7f7f);
		Color stocktablecolor = new Color(0xD0CECE);
		Color salestablecolor = new Color(0xD0CECE);
		Color salessumcolor = new Color(0xA99C90);
		
		// 메인패널
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		mainpanel.setBackground(mainpanelcolor);
		mainpanel.setSize(1500, 1000);
		mainpanel.setLocation(0, 0);
		add(mainpanel);
		
		// 재고관리 테이블
		stocktable = new JPanel();
		stocktable.setBackground(stocktablecolor);
		stocktable.setSize(650, 750);
		stocktable.setLocation(50, 50);
		
		JTable stock = new JTable(tableModel);
		stock.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stock.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
		JScrollPane sc = new JScrollPane(stock);
		sc.setPreferredSize(new Dimension(650,750));
		
		stocktable.add(sc);
		
		
		mainpanel.add(stocktable);
		
		// 현재매출 테이블
		salestable = new JPanel();
		salestable.setBackground(salestablecolor);
		salestable.setSize(650, 750);
		salestable.setLocation(800, 50);
		
		String header1[] = { "학생이름", "국어", "영어", "수학"};
		String contents1[][] = {
				{"박영수", "90", "87", "98"},
				{"김영희", "90", "87", "98"},
				{"김철수", "90", "87", "98"}
		};
		JTable stock1 = new JTable(contents1, header1);
		JScrollPane sc1 = new JScrollPane(stock1);
		sc1.setPreferredSize(new Dimension(650,750));
		salestable.add(sc1);
		
		mainpanel.add(salestable);
		
		// 현재 매출 상황
		saleslabel = new JLabel("현재 매출 상황 : ");
		saleslabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		saleslabel.setSize(250, 40);
		saleslabel.setLocation(910, 850);
		mainpanel.add(saleslabel);
				
		// 현재가격
		salessum = new JTextField("0,000");
		salessum.setHorizontalAlignment(JLabel.RIGHT);
		salessum.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		salessum.setEnabled(false);
		salessum.setSize(250, 40);
		salessum.setLocation(1150, 850);
		mainpanel.add(salessum);
				
		// 원
		wonlabel = new JLabel("원");
		wonlabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		wonlabel.setSize(250, 40);
		wonlabel.setLocation(1410, 850);
		mainpanel.add(wonlabel);
		
		
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Stock("재고 관리", 1500, 1000);
	}
	
}
