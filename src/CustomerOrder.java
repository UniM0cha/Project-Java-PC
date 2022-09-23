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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CustomerOrder extends JFrame implements MouseListener, ActionListener {

	// 상품을 표시하기 위한 임시 변수
	private JPanel[] jp, jp1;
	private JLabel[] jl, jl1, lbl;
	private JButton[] jb = null;
	private int[] counts;

	// 레이아웃 설정을 위한 JPanel
	private JPanel westP, centerP, southP, southwestP, southeastP, payP, priceP, requestP, orderP, payPcenter;

	// 구성 요소들
	private JButton orderbtn; // 주문하기 버튼
	private String[] menulst = { "라면", "밥", "음료수", "스낵" }; // 카테고리 리스트
	private JList<String> lstmenu;
	private JLabel pricelstlbl, paylbl, pricelbl, requestlbl, categorylbl;
	private ButtonGroup bg;
	private JRadioButton rbcard, rbcash;
	private JTextArea requestJt;
	private JScrollPane categorySp, requestSp, menuSp;

	// 이미지
	private ImageIcon[] noodleImgs = {
			new ImageIcon("images/신라면.jpg"),
			new ImageIcon("images/삼양라면.png"),
			new ImageIcon("images/사리곰탕.jpg"),
			new ImageIcon("images/안성탕면.jpg"),
			new ImageIcon("images/진라면.jpg"),
			new ImageIcon("images/육개장.jpg"),
			new ImageIcon("images/짜파게티.jpg"),
			new ImageIcon("images/비빔면.jpg") };
	private ImageIcon[] babImgs = {
			new ImageIcon("images/치킨카레볶음밥.jpg"),
			new ImageIcon("images/잡채볶음밥.jpg"),
			new ImageIcon("images/옛날제육덮밥.jpg"),
			new ImageIcon("images/소불고기덮밥.jpg"),
			new ImageIcon("images/새우볶음밥.jpg"),
			new ImageIcon("images/상하이치킨덮밥.jpg"),
			new ImageIcon("images/햄김치참치덮밥.jpg"),
			new ImageIcon("images/햄에그필라프볶음밥.jpg"),
			new ImageIcon("images/갈릭소시지필라프.jpg"),
			new ImageIcon("images/커리소시지필라프.jpg") };
	private ImageIcon[] drinkImgs = {
			new ImageIcon("images/콜라.jpg"),
			new ImageIcon("images/사이다.jpg"),
			new ImageIcon("images/몬스터.jpg"),
			new ImageIcon("images/맥콜.jpg"),
			new ImageIcon("images/지코.jpg"),
			new ImageIcon("images/실론티.jpg"),
			new ImageIcon("images/솔의눈.jpg"),
			new ImageIcon("images/데자와.jpg"),
			new ImageIcon("images/쿠우.jpg"),
			new ImageIcon("images/토레타.jpg"),
			new ImageIcon("images/참이슬.jpg"),
			new ImageIcon("images/카스.jpg") };
	private ImageIcon[] snackImgs = {
			new ImageIcon("images/고구마깡.jpg"),
			new ImageIcon("images/신짱.jpg"),
			new ImageIcon("images/오잉.jpg"),
			new ImageIcon("images/포스틱.jpg"),
			new ImageIcon("images/쫄병.jpg"),
			new ImageIcon("images/베이컨칩.jpg"),
			new ImageIcon("images/무뚝뚝.jpg"),
			new ImageIcon("images/오징어집.jpg"),
			new ImageIcon("images/초코비.jpg"),
			new ImageIcon("images/자갈치.jpg"),
			new ImageIcon("images/고래밥.jpg"),
			new ImageIcon("images/콘칩.jpg") };

	private int len = 0, pricetemp = 0, temp = 0, sumprice = 0, cnt = 0;

	private String productName, count, pay, productname, category, payment;

	// 주문내용을 표시하기 위한 테이블
	private static DB db = new DB();
	private Vector<String> header = new Vector<>(Arrays.asList("상품명", "개수", "가격"));
	private Vector<Vector<String>> contents = new Vector<>();
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	private JTable table = new JTable(tableModel);
	private JScrollPane scrollpane = new JScrollPane(table);
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
	private Vector<String> id = new Vector<>();
	private Vector<String> ID = new Vector<>();
	private LineBorder borderThickness1 = new LineBorder(new Color(0x767171), 4);
	private int pcNum, Price, productid;

	public CustomerOrder(int pcNum) {
		setTitle("음식주문");
		setSize(1200, 900);
		setLocationRelativeTo(this);
		setResizable(false); // 크기 조절 불가
		setLayout(new BorderLayout());

		this.pcNum = pcNum;

		// 데이터베이스에서 값 가져오고 벡터에 추가
		String sql = "SELECT * FROM product";
		ResultSet rs = db.Query(sql);
		try {
			while (rs.next()) {
				productid = rs.getInt("productID");
				productname = rs.getString("productName");
				category = rs.getString("category");
				Price = rs.getInt("price");
				if (category.equals("라면")) {
					noodleID.add(Integer.toString(productid));
					noodlestr.add(productname);
					noodlePrice.add(Integer.toString(Price));
				} else if (category.equals("밥")) {
					babID.add(Integer.toString(productid));
					babstr.add(productname);
					babPrice.add(Integer.toString(Price));
				} else if (category.equals("음료수")) {
					drinkID.add(Integer.toString(productid));
					drinkstr.add(productname);
					drinkPrice.add(Integer.toString(Price));
				} else if (category.equals("스낵")) {
					snackID.add(Integer.toString(productid));
					snackstr.add(productname);
					snackPrice.add(Integer.toString(Price));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB에서 데이터를 받아오지 못함");
		}

		Color westPcolor = new Color(0xb4b4b4);
		Color categorySPcolor = new Color(0xEEEEEE);
		Color centerPcolor = new Color(0xEEEEEE);
		Color payPcolor = new Color(0xb4b4b4);
		Color pricePcolor = new Color(0xb4b4b4);
		Color orderPcolor = new Color(0xb4b4b4);
		Color orderbtncolor = new Color(0xF3F1DF);
		Color requestPcolor = new Color(0xb4b4b4);

		// westP 시작
		categorylbl = new JLabel("카테고리");
		categorylbl.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0x767171)));
		lstmenu = new JList<String>(menulst);

		lstmenu.setBackground(categorySPcolor);
		lstmenu.addMouseListener(this);

		westP = new JPanel();
		westP.setLayout(new BorderLayout());
		westP.setBackground(westPcolor);

		categorySp = new JScrollPane(lstmenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		westP.add(categorylbl, BorderLayout.NORTH);
		westP.add(categorySp, BorderLayout.CENTER);
		// westP 끝

		// centerP 시작
		centerP = new JPanel();
		centerP.setBackground(centerPcolor);
		centerP.setLayout(new WrapLayout(FlowLayout.LEFT, 1, 1));

		menuSp = new JScrollPane(centerP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// centerP 끝

		// southwestP 시작
		pricelstlbl = new JLabel("상품 주문 목록", JLabel.CENTER);

		southwestP = new JPanel();
		southwestP.setBackground(pricePcolor);
		southwestP.setLayout(new BorderLayout());
		southwestP.add(pricelstlbl, BorderLayout.NORTH);

		scrollpane.setPreferredSize(new Dimension(200, 200));
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
		table.getTableHeader().setBackground(new Color(0xF3F1DF));
		table.getTableHeader().setFont(new Font("나눔 고딕", Font.BOLD, 12));
		table.setFont(new Font("나눔 고딕", Font.BOLD, 20));
		table.setBackground(new Color(0xFFFFFF));
		table.setRowHeight(22);
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer1 = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule1 = table.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule1.getColumnCount(); i++) {
			tcmSchedule1.getColumn(i).setCellRenderer(tScheduleCellRenderer1);
		}
		southwestP.add(scrollpane, BorderLayout.CENTER);
		// southwestP 끝

		// southeastP 시작
		southeastP = new JPanel();
		payP = new JPanel();
		payP.setBorder(BorderFactory.createMatteBorder(0, 4, 4, 0, new Color(0x767171)));
		payP.setLayout(new BorderLayout());
		payP.setBackground(payPcolor);
		paylbl = new JLabel("결제 방법", JLabel.CENTER);
		payP.add(paylbl, BorderLayout.NORTH);
		bg = new ButtonGroup();
		JLabel chickshow = new JLabel("                                                                               ");
		rbcard = new JRadioButton("카드", true);
		rbcash = new JRadioButton("현금");
		rbcard.addActionListener(this);
		rbcash.addActionListener(this);
		payPcenter = new JPanel();
		payment = "카드";
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

		pricelbl = new JLabel("0원");
		priceP.add(pricelbl, BorderLayout.CENTER);

		requestlbl = new JLabel("주문 요청 사항", JLabel.CENTER);
		requestP = new JPanel();
		requestP.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0x767171)));
		requestP.setLayout(new BorderLayout());
		requestP.setBackground(requestPcolor);
		requestP.add(requestlbl, BorderLayout.NORTH);
		requestJt = new JTextArea("", 5, 27);
		requestJt.setBackground(categorySPcolor);
		requestJt.setLineWrap(true);
		requestSp = new JScrollPane(requestJt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

		southeastP.setLayout(new GridLayout(2, 2));
		southeastP.add(payP);
		southeastP.add(priceP);
		southeastP.add(requestP);
		southeastP.add(orderP);
		// southeastP 끝

		// 폰트 시작
		pricelstlbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		paylbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		requestlbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		orderbtn.setFont(new Font("나눔고딕", Font.BOLD, 20));
		pricelbl.setFont(new Font("나눔고딕", Font.BOLD, 20));
		categorylbl.setFont(new Font("나눔고딕", Font.BOLD, 50));
		lstmenu.setFont(new Font("나눔고딕", Font.BOLD, 50));

		pricelbl.setHorizontalAlignment(JLabel.CENTER);
		// 폰트 끝

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

	public void Menu(int len, ImageIcon[] imgs, Vector<String> str, Vector<String> price, Vector<String> id) {
		centerP.removeAll();
		this.len = len;
		this.price = price;
		this.id = id;
		jp = new JPanel[len];
		jp1 = new JPanel[len];
		jl = new JLabel[len];
		jl1 = new JLabel[len];
		lbl = new JLabel[len];
		jb = new JButton[len];
		counts = new int[len];

		// 주문카테고리에 따라 메뉴패널 추가
		for (int i = 0; i < len; i++) {

			jp[i] = new JPanel();
			jp[i].setBorder(borderThickness1);
			jp[i].setLayout(new BorderLayout());

			lbl[i] = new JLabel(imgs[i], JLabel.CENTER);
			jp[i].add(lbl[i]);

			jl[i] = new JLabel(str.get(i), JLabel.CENTER);
			jl[i].setFont(new Font("", Font.BOLD, 20));
			jp[i].add(jl[i], BorderLayout.NORTH);

			jp1[i] = new JPanel();
			jp1[i].setLayout(new BorderLayout());

			jl1[i] = new JLabel(price.get(i) + "원", JLabel.CENTER);
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

	// 주문카테고리에 따라 맞는 값들 불러오기
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (lstmenu.getSelectedIndex()) {
			case 0:
				Menu(noodleImgs.length, noodleImgs, noodlestr, noodlePrice, noodleID);
				break;
			case 1:
				Menu(babImgs.length, babImgs, babstr, babPrice, babID);
				break;
			case 2:
				Menu(drinkImgs.length, drinkImgs, drinkstr, drinkPrice, drinkID);
				break;
			case 3:
				Menu(snackImgs.length, snackImgs, snackstr, snackPrice, snackID);
				break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// 라디오 버튼에 따라 결제방법 변경
		if (obj == rbcard) {
			payment = "카드";
		} else if (obj == rbcash) {
			payment = "현금";
		}

		// 담기버튼 눌렀을 때 테이블에 추가
		for (int i = 0; i < len; i++) {
			if (obj == jb[i]) {
				cnt++;
				sumprice += Integer.parseInt(jl1[i].getText().substring(0, (jl1[i].getText().length() - 1)));
				productName = jl[i].getText();
				temp = counts[i] + 1;
				count = Integer.toString(temp);
				counts[i] = temp;
				pricetemp = temp * Integer.parseInt(price.get(i));
				pay = Integer.toString(pricetemp);

				if (temp == 1) {
					contents.add(new Vector<String>(Arrays.asList(productName, count, pay)));
					ID.add(id.get(i));
				} else {

					for (int j = 0; j < table.getRowCount(); j++) {
						if (jl[i].getText() == table.getValueAt(j, 0)) {
							table.setValueAt(count, j, 1);
							table.setValueAt(pay, j, 2);
						}

					}
				}
				tableModel.fireTableDataChanged();

				pricelbl.setText(NumberFormat.getInstance().format(sumprice) + "원");

			}
		}

		// sql문으로 데이터 베이스에 값 추가
		if (obj == orderbtn) {
			if (cnt > 0) {
				updateisOrderAtState(1);
				int j = 0;
				String sql2 = "INSERT INTO orders(pcNum, productID, counts, payment, salePrice, request) VALUE ("
						+ pcNum + ", " + ID.get(j) + ", " + table.getValueAt(j, 1) + ", '" + payment + "', "
						+ table.getValueAt(j, 2) + ", '" + requestJt.getText() + "')";
				db.Update(sql2);
				for (int i = 1; i < table.getRowCount(); i++) {
					String sql3 = "INSERT INTO orders(pcNum, productID, counts, payment, salePrice) VALUE ("
							+ pcNum + ", " + ID.get(i) + ", " + table.getValueAt(i, 1) + ", '" + payment + "', "
							+ table.getValueAt(i, 2) + ")";
					db.Update(sql3);
				}
				dispose();
			}
		}
	}

	// 주문
	private void updateisOrderAtState(int isOrder) {
		String sql1 = "UPDATE state " + "SET isOrder = " + isOrder + " " + "WHERE pcNum = " + pcNum;
		db.Update(sql1);
	}
}