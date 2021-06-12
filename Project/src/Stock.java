import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

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
	
	private Vector<String> header = new Vector<>(Arrays.asList("상품코드", "상품이름", "종류", "가격", "재고"));
    private Vector<Vector<String>> contents = new Vector<>();
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    private JTable stock1 = new JTable(tableModel);
    private JScrollPane sc = new JScrollPane(stock1);
    
    private Vector<String> header1 = new Vector<>(Arrays.asList("순서", "상품이름", "개수", "가격"));
    private Vector<Vector<String>> contents1 = new Vector<>();
    private DefaultTableModel tableModel1 = new DefaultTableModel(contents1, header1) {
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    private JTable sumtable = new JTable(tableModel1);
    private JScrollPane sc1 = new JScrollPane(sumtable);
	private int sequence, productID, counts, salePrice;
	private int stock, price;
	private String productName;
	private String category;
	private int sumresult = 0;
    
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
		
		JLabel stocklabel = new JLabel("남은 재고");
		stocklabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stocklabel.setSize(100, 60);
		stocklabel.setLocation(325, 0);
		mainpanel.add(stocklabel);
		
		stocktable = new JPanel();
		stocktable.setBackground(stocktablecolor);
		stocktable.setSize(650, 750);
		stocktable.setLocation(50, 60);
		
		stock1.getTableHeader().setReorderingAllowed(false); // 이동 불가
		stock1.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
		JScrollPane sc = new JScrollPane(stock1);
		sc.setPreferredSize(new Dimension(650,750));
		
		stocktable.add(sc);
		
		
		mainpanel.add(stocktable);
		
		// 현재매출 테이블
		JLabel saleslabel = new JLabel("현재 매출");
		saleslabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		saleslabel.setSize(100, 60);
		saleslabel.setLocation(1100, 0);
		mainpanel.add(saleslabel);
		
		salestable = new JPanel();
		salestable.setBackground(salestablecolor);
		salestable.setSize(650, 750);
		salestable.setLocation(800, 60);
		
		sumtable.getTableHeader().setReorderingAllowed(false); // 이동 불가
		sumtable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
		sc1.setPreferredSize(new Dimension(650,750));
		salestable.add(sc1);
		
		mainpanel.add(salestable);
		
		// 현재 매출 상황
		saleslabel = new JLabel("현재 매출 상황 : ");
		saleslabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		saleslabel.setSize(250, 40);
		saleslabel.setLocation(910, 860);
		mainpanel.add(saleslabel);
				
		// 현재가격
		salessum = new JTextField("0,000");
		salessum.setHorizontalAlignment(JLabel.RIGHT);
		salessum.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		salessum.setForeground(Color.black);
		salessum.setEnabled(false);
		salessum.setSize(250, 40);
		salessum.setLocation(1150, 860);
		mainpanel.add(salessum);
				
		// 원
		wonlabel = new JLabel("원");
		wonlabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		wonlabel.setSize(250, 40);
		wonlabel.setLocation(1410, 860);
		mainpanel.add(wonlabel);
		
		
		String sql = "SELECT * "
				+ "FROM sales s "
				+ "JOIN product p "
				+ "WHERE s.productID = p.productID ";
		String sql1 = "SELECT * FROM product";
		ResultSet rs = db.Query(sql);
		ResultSet rs1 = db.Query(sql1);
		// sumtable 테이블 (현재 매출)
		try {
		    while(rs.next()) {
		        sequence = rs.getInt("sequence");
		        productName = rs.getString("productName");
		        counts = rs.getInt("counts");
		        salePrice = rs.getInt("salePrice");
		        contents1.add(new Vector<String>(Arrays.asList(Integer.toString(sequence), productName
		        											 , Integer.toString(counts), Integer.toString(salePrice))));
		        sumresult  += salePrice;
		        String priceString = NumberFormat.getInstance().format(sumresult);
		        salessum.setText(priceString);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("DB에서 데이터를 받아오지 못함");
		}
		// stock1 테이블 (남은 재고)
		try {
		    while(rs1.next()) {
		        sequence = rs1.getInt("productID");
		        productName = rs1.getString("productName");
		        category = rs1.getString("category");
		        price = rs1.getInt("price");
		        stock = rs1.getInt("stock");
		        contents.add(new Vector<String>(Arrays.asList(Integer.toString(sequence), productName
		        											 , category, Integer.toString(price), Integer.toString(stock))));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("DB에서 데이터를 받아오지 못함");
		}
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Stock("재고 관리", 1500, 1000);
	}
	
}
