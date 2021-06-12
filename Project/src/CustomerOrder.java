import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class CustomerOrder extends JFrame implements MouseListener, ActionListener {
	
	private JPanel westP, centerP, southP, southwestP, southeastP, payP, priceP, requestP, orderP, payPcenter;
	private JPanel[] jp = null;
	private JPanel[] jp1 = null;
	private JLabel[] jl = null;
	private JLabel[] jl1 = null;
	private JLabel[] lbl = null;
	private int[] counts;
	
	private JButton[] jb = null;
	private JButton orderbtn;
	private String[] menulst = {"라면", "밥", "음료수", "스낵"};
	private JList<String> lstmenu, lstprice;
	private JLabel pricelstlbl, paylbl, pricelbl, requestlbl, categorylbl;
	private ButtonGroup bg;
	private JRadioButton rbcard, rbcash;
	private JTextArea requestJt;
	private JScrollPane categorySp, requestSp, menuSp;
	private ImageIcon[] noodleImgs = {new ImageIcon("images/신라면.jpg"), new ImageIcon("images/삼양라면.png"), new ImageIcon("images/사리곰탕.jpg"), new ImageIcon("images/사리곰탕.jpg"), new ImageIcon("images/안성탕면.jpg"), new ImageIcon("images/진라면.jpg"), new ImageIcon("images/육개장.jpg"), new ImageIcon("images/짜파게티.jpg")};
	private ImageIcon[] babImgs = {new ImageIcon("images/간장소고기덮밥.jpg"), new ImageIcon("images/김치삼겹볶음덮밥.jpg"), new ImageIcon("images/명란아보카도덥팝.jpg"), new ImageIcon("images/스테이크덮밥.jpg"), new ImageIcon("images/스팸김치덮밥.jpg"), new ImageIcon("images/양파덮밥.jpg"), new ImageIcon("images/연어마요덮밥.jpg"), new ImageIcon("images/장어덮밥.jpg"), new ImageIcon("images/제육덮밥.jpg"), new ImageIcon("images/제육덮밥.jpg")};
	private ImageIcon[] drinkImgs = {new ImageIcon("images/콜라.jpg"), new ImageIcon("images/사이다.jpg"), new ImageIcon("images/몬스터.jpg"), new ImageIcon("images/맥콜.jpg"), new ImageIcon("images/지코.jpg"), new ImageIcon("images/실론티.jpg"), new ImageIcon("images/솔의눈.jpg"), new ImageIcon("images/데자와.jpg"), new ImageIcon("images/쿠우.jpg"), new ImageIcon("images/토레타.jpg"), new ImageIcon("images/참이슬.jpg"), new ImageIcon("images/카스.jpg")};
	private ImageIcon[] snackImgs = {new ImageIcon("images/고구마깡.jpg"), new ImageIcon("images/신짱.jpg"), new ImageIcon("images/오잉.jpg"), new ImageIcon("images/포스틱.jpg"), new ImageIcon("images/쫄병.jpg"), new ImageIcon("images/베이컨칩.jpg"), new ImageIcon("images/무뚝뚝.jpg"), new ImageIcon("images/오징어집.jpg"), new ImageIcon("images/초코비.jpg"), new ImageIcon("images/자갈치.jpg"), new ImageIcon("images/자갈치.jpg"), new ImageIcon("images/자갈치.jpg")};
	
	/*private String[] noodleStr = {"신라면", "삼양라면", "사리곰탕", "안성탕면", "진라면", "육개장", "짜파게티"};
	private String[] babStr = {"간장소고기덮밥", "김치삼겹볶음덮밥", "명란아보카도덥팝", "스테이크덮밥", "스팸김치덮밥", "양파덮밥", "연어마요덮밥", "장어덮밥", "제육덮밥"};
	private String[] drinkStr = {"콜라", "사이다", "몬스터", "맥콜", "지코", "실론티", "솔의눈", "데자와", "쿠우", "토레타", "참이슬", "카스"};
	private String[] snackStr = {"고구마깡", "신짱", "오잉", "포스틱", "쫄병", "베이컨칩", "무뚝뚝", "오징어집", "초코비", "자갈치"};
	
	private String[] noodleprice = {"4500", "3500", "4000", "3500", "3500", "3000", "5000"};
	private String[] babprice = {"4500", "3500", "4000", "3500", "3500", "3000", "5000", "5000", "5000"};
	private String[] drinkprice = {"4500", "3500", "4000", "3500", "3500", "3000", "5000", "5000", "5000", "5000", "5000", "5000"};
	private String[] snackprice = {"4500", "3500", "4000", "3500", "3500", "3000", "5000", "5000", "5000", "5000"};*/
	private DefaultListModel<String> model = new DefaultListModel<>();
	private int len = 0, pricetemp = 0, temp = 0, sumprice = 0;
	
	private String productName, count, pay;
	private Vector<String> header = new Vector<>(Arrays.asList("상품명", "개수", "가격"));
    private Vector<Vector<String>> contents = new Vector<>();
    private Vector<String> price = new Vector<>();
    
    private Vector<String> noodleID = new Vector<>();
	private Vector<String> babID = new Vector<>();
	private Vector<String> drinkID = new Vector<>();
	private Vector<String> snackID = new Vector<>();

	private Vector<String> noodlestr = new Vector<>();
	private Vector<String> babstr = new Vector<>();
	private Vector<String> drinkstr = new Vector<>();
	private Vector<String> snackstr = new Vector<>();
	
	private Vector<String> noodlePrice = new Vector<>();
	private Vector<String> babPrice = new Vector<>();
	private Vector<String> drinkPrice = new Vector<>();
	private Vector<String> snackPrice = new Vector<>();
	
    private DefaultTableModel tableModel = new DefaultTableModel(contents, header);
    private JTable table = new JTable(tableModel);
	private JScrollPane scrollpane = new JScrollPane(table);
    
	private LineBorder borderThickness1 = new LineBorder(new Color(0x767171), 4);
	private LineBorder borderThickness2 = new LineBorder(new Color(0x767171), 4);
	private static DB db = new DB();
	

	public CustomerOrder() {
		setTitle("음식주문");
		setSize(1200, 900);
		setLocationRelativeTo(this);
		setResizable(false);
		
		setLayout(new BorderLayout());	
		
		
		
		
		
		String sql = "SELECT * FROM product";
		ResultSet rs = db.Query(sql);
		try {
		    while(rs.next()) {
		    	int productID = rs.getInt("productID");
		    	String productname = rs.getString("productName");
		        String category = rs.getString("category");
		        int price = rs.getInt("price");
		        if(category.equals("라면")) {
		        	noodleID.add(Integer.toString(productID));
		        	noodlestr.add(productname);
		        	noodlePrice.add(Integer.toString(price));
		        }else if(category.equals("밥")) {
		        	babID.add(Integer.toString(productID));
		        	babstr.add(productname);
		        	babPrice.add(Integer.toString(price));
		        }else if(category.equals("음료수")) {
		        	drinkID.add(Integer.toString(productID));
		        	drinkstr.add(productname);
		        	drinkPrice.add(Integer.toString(price));
		        }else if(category.equals("스낵")) {
		        	snackID.add(Integer.toString(productID));
		        	snackstr.add(productname);
		        	snackPrice.add(Integer.toString(price));
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("DB에서 데이터를 받아오지 못함");
		}
		
		
		
		Color westPcolor = new Color(0xA99C90);
		Color categorySPcolor = new Color(0xEEEEEE);
		Color centerPcolor = new Color(0xEEEEEE);
		Color southwestPcolor = new Color(0xA99C90);
		Color payPcolor = new Color(0xA99C90);
		Color pricePcolor = new Color(0xA99C90);
		Color orderPcolor = new Color(0xA99C90);
		Color orderbtncolor = new Color(0xF3F1DF);
		Color requestPcolor = new Color(0xA99C90);
		
		//westP 시작
		
		categorylbl = new JLabel("카테고리");
		categorylbl.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0x767171)));
		lstmenu = new JList<String>(menulst);
		lstmenu.setBackground(categorySPcolor);
		lstmenu.addMouseListener(this);
		
		westP = new JPanel();
		westP.setLayout(new BorderLayout());
		westP.setBackground(westPcolor);
		//westP.setBorder(borderThickness2);
		
		categorySp = new JScrollPane(lstmenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		
		westP.add(categorylbl, BorderLayout.NORTH);
		westP.add(categorySp, BorderLayout.CENTER);
		
		//westP 끝
		
		//centerP 시작
		
		centerP = new JPanel();
		centerP.setBackground(centerPcolor);
		centerP.setLayout(new WrapLayout(FlowLayout.LEFT, 1, 1));
		
		menuSp = new JScrollPane(centerP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		//centerP 끝
		
		//southwestP 시작
		
		pricelstlbl = new JLabel("상품 주문 목록");
		
		southwestP = new JPanel();
		southwestP.setBackground(pricePcolor);
		southwestP.setLayout(new BorderLayout());
		southwestP.add(pricelstlbl, BorderLayout.NORTH);
		
		/*String header[] = {"상품명", "주문 수량", "가격"};
		String contents[][] = {
				
		};
		JTable table = new JTable(contents, header);
		JScrollPane scroll = new JScrollPane(table);*/
		scrollpane.setPreferredSize(new Dimension(200,200));
		southwestP.add(scrollpane, BorderLayout.CENTER);
		/*lstprice = new JList<>(model);
		lstprice.setBackground(categorySPcolor);
		southwestP.add(lstprice, BorderLayout.CENTER);*/
		
		//southwestP 끝
		
		//southeastP 시작
		southeastP = new JPanel();
		
		payP = new JPanel();
		payP.setBorder(BorderFactory.createMatteBorder(0, 4, 4, 0, new Color(0x767171)));
		payP.setLayout(new BorderLayout());
		payP.setBackground(payPcolor);
		paylbl = new JLabel("결제 방법");
		payP.add(paylbl, BorderLayout.NORTH);
		bg = new ButtonGroup();
		JLabel chickshow = new JLabel("                                                                               ");
		rbcard = new JRadioButton("카드", true);	
		rbcash = new JRadioButton("현금");
		payPcenter = new JPanel();
		bg.add(rbcard);						
		bg.add(rbcash);
		payPcenter.add(chickshow);
		payPcenter.add(rbcard);
		payPcenter.add(rbcash);
		payP.add(payPcenter, BorderLayout.CENTER);
		
		priceP = new JPanel();
		priceP.setBorder(BorderFactory.createMatteBorder(0, 4, 4, 0, new Color(0x767171)));
		priceP.setLayout(new BorderLayout());
		priceP.setBackground(pricePcolor);
		
		pricelbl = new JLabel("0,000원 ");
		priceP.add(pricelbl, BorderLayout.CENTER);
		
		requestlbl = new JLabel("주문 요청 사항");
		requestP = new JPanel();
		requestP.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0x767171)));
		requestP.setLayout(new BorderLayout());
		requestP.setBackground(requestPcolor);
		requestP.add(requestlbl, BorderLayout.NORTH);
		requestJt = new JTextArea("", 5, 27);
		requestJt.setBackground(categorySPcolor);
		requestJt.setLineWrap(true);
		requestSp = new JScrollPane(requestJt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		requestP.add(requestSp, BorderLayout.CENTER);
		
		orderP = new JPanel();
		orderP.setLayout(new BorderLayout());
		orderP.addMouseListener(this);
		orderP.setBackground(orderPcolor);
		orderbtn = new JButton("주문하기");
		orderbtn.addActionListener(this);
		orderbtn.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0x767171)));
		orderbtn.setBackground(orderbtncolor);
		orderP.add(orderbtn, BorderLayout.CENTER);
		
		southeastP.setLayout(new GridLayout(2,2));
		southeastP.add(payP);
		southeastP.add(priceP);
		southeastP.add(requestP);
		southeastP.add(orderP);
		
		//southeastP 끝
		
		//폰트 시작
		
		pricelstlbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		paylbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		requestlbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		//lstprice.setFont(new Font("나눔고딕", Font.BOLD, 10));
		orderbtn.setFont(new Font("나눔고딕", Font.BOLD, 20));
		pricelbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		categorylbl.setFont(new Font("나눔고딕", Font.BOLD, 50));
		lstmenu.setFont(new Font("나눔고딕", Font.BOLD, 50));
		
		pricelbl.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트 끝
		
		southP = new JPanel();
		southP.setLayout(new GridLayout(1, 2));
		southP.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, new Color(0x767171)));
		
		southP.add(southwestP, BorderLayout.WEST);
		southP.add(southeastP, BorderLayout.EAST);
		
		add(westP, BorderLayout.WEST);
		add(menuSp, BorderLayout.CENTER);
		add(southP, BorderLayout.SOUTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new CustomerOrder();

	}

	public void Menu(int len, ImageIcon[] imgs, Vector<String> str, Vector<String> price) {
		centerP.removeAll();
		this.len = len;
		this.price = price;
		jp = new JPanel[len];
		jp1 = new JPanel[len];
		jl = new JLabel[len];
		jl1 = new JLabel[len];
		lbl = new JLabel[len];
		jb = new JButton[len];
		counts = new int[len];
		
		
		for(int i = 0; i < len; i++) {
			
			jp[i] = new JPanel();
			jp[i].setBorder(borderThickness1);
			jp[i].setLayout(new BorderLayout());
			
			lbl[i] = new JLabel(imgs[i]);
			jp[i].add(lbl[i]);
			
			jl[i] = new JLabel(str.get(i));
			jp[i].add(jl[i], BorderLayout.NORTH);
			
			jp1[i] = new JPanel();
			jp1[i].setLayout(new BorderLayout());
			
			jl1[i] = new JLabel(price.get(i));
			jp1[i].add(jl1[i]);
			
			jb[i] = new JButton("담기");
			counts[i] = 0;
			jb[i].addActionListener(this);
			jp1[i].add(jb[i], BorderLayout.EAST);
			jp[i].add(jp1[i], BorderLayout.SOUTH);
			
			centerP.add(jp[i]);
			centerP.revalidate();
			centerP.repaint();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(lstmenu.getSelectedIndex()) {
		case 0:
			Menu(noodleImgs.length, noodleImgs, noodlestr, noodlePrice);
			break;
		case 1:
			Menu(babImgs.length, babImgs, babstr, babPrice);
			break;
		case 2:
			Menu(drinkImgs.length, drinkImgs, drinkstr, drinkPrice);
			break;
		case 3:
			Menu(snackImgs.length, snackImgs, snackstr, snackPrice);
			break;
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		for(int i = 0; i < len; i++) {
			if(obj == jb[i]) {
				sumprice += Integer.parseInt(jl1[i].getText());
				productName = jl[i].getText();
				temp = counts[i] + 1;
				count = Integer.toString(temp);
				counts[i] = temp;
				pricetemp = temp * Integer.parseInt(price.get(i));
				pay = Integer.toString(pricetemp);
				if(temp == 1) {
					contents.add(new Vector<String>(Arrays.asList(productName, count, pay)));
				}else {
					
					for(int j = 0; j < table.getRowCount(); j++) {
						if(jl[i].getText() == table.getValueAt(j, 0)) {
							table.setValueAt(count, j, 1);
							table.setValueAt(pay, j, 2);
						}
						
					}
				}
				tableModel.fireTableDataChanged();

				
				pricelbl.setText(NumberFormat.getInstance().format(sumprice) + "원");
			
			}
		}
		if (obj == orderbtn) {
			String sql = "Update orders SET state = false WHERE pcNum = 1";
			db.Update(sql);
			
			dispose();
		}
	}
	
	
	
}