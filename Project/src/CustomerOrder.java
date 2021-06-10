import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class CustomerOrder extends JFrame implements MouseListener, ActionListener {
	
	private JPanel westP, centerP, southP, southwestP, southeastP, payP, priceP, requestP, orderP, payPcenter;
	private JPanel[] jp = null;
	private JPanel[] jp1 = null;
	private JLabel[] jl = null;
	private JLabel[] jl1 = null;
	private JLabel[] lbl = null;
	private JButton[] jb = null;
	private JButton orderbtn;
	private String[] menulst = {"라면", "밥", "음료수", "스낵"};
	private JList<String> lstmenu, lstprice;
	private JLabel pricelstlbl, paylbl, pricelbl, requestlbl, categorylbl;
	private ButtonGroup bg;
	private JRadioButton rbcard, rbcash;
	private JTextArea requestJt;
	private JScrollPane categorySp, requestSp, menuSp;
	private ImageIcon[] noodleImgs = {new ImageIcon("images/신라면.jpg"), new ImageIcon("images/삼양라면.png"), new ImageIcon("images/사리곰탕.jpg"), new ImageIcon("images/안성탕면.jpg"), new ImageIcon("images/진라면.jpg"), new ImageIcon("images/육개장.jpg"), new ImageIcon("images/짜파게티.jpg")};
	private ImageIcon[] babImgs = {new ImageIcon("images/간장소고기덮밥.jpg"), new ImageIcon("images/김치삼겹볶음덮밥.jpg"), new ImageIcon("images/명란아보카도덥팝.jpg"), new ImageIcon("images/스테이크덮밥.jpg"), new ImageIcon("images/스팸김치덮밥.jpg"), new ImageIcon("images/양파덮밥.jpg"), new ImageIcon("images/연어마요덮밥.jpg"), new ImageIcon("images/장어덮밥.jpg"), new ImageIcon("images/제육덮밥.jpg")};
	private ImageIcon[] drinkImgs = {new ImageIcon("images/콜라.jpg"), new ImageIcon("images/사이다.jpg"), new ImageIcon("images/몬스터.jpg"), new ImageIcon("images/맥콜.jpg"), new ImageIcon("images/지코.jpg"), new ImageIcon("images/실론티.jpg"), new ImageIcon("images/솔의눈.jpg"), new ImageIcon("images/데자와.jpg"), new ImageIcon("images/쿠우.jpg"), new ImageIcon("images/토레타.jpg"), new ImageIcon("images/참이슬.jpg"), new ImageIcon("images/카스.jpg")};
	private ImageIcon[] snackImgs = {new ImageIcon("images/고구마깡.jpg"), new ImageIcon("images/신짱.jpg"), new ImageIcon("images/오잉.jpg"), new ImageIcon("images/포스틱.jpg"), new ImageIcon("images/쫄병.jpg"), new ImageIcon("images/베이컨칩.jpg"), new ImageIcon("images/무뚝뚝.jpg"), new ImageIcon("images/오징어집.jpg"), new ImageIcon("images/초코비.jpg"), new ImageIcon("images/자갈치.jpg")};
	
	private String[] noodleStr = {"신라면", "삼양라면", "사리곰탕", "안성탕면", "진라면", "육개장", "짜파게티"};
	private String[] babStr = {"간장소고기덮밥", "김치삼겹볶음덮밥", "명란아보카도덥팝", "스테이크덮밥", "스팸김치덮밥", "양파덮밥", "연어마요덮밥", "장어덮밥", "제육덮밥"};
	private String[] drinkStr = {"콜라", "사이다", "몬스터", "맥콜", "지코", "실론티", "솔의눈", "데자와", "쿠우", "토레타", "참이슬", "카스"};
	private String[] snackStr = {"고구마깡", "신짱", "오잉", "포스틱", "쫄병", "베이컨칩", "무뚝뚝", "오징어집", "초코비", "자갈치"};
	
	private String[] noodleprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원"};
	private String[] babprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원"};
	private String[] drinkprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원", "5000원", "5000원", "5000원"};
	private String[] snackprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원", "5000원"};
	private DefaultListModel<String> model = new DefaultListModel<>();
	private int len = 0;
	
	private LineBorder borderThickness1 = new LineBorder(new Color(0x767171), 4);
	private LineBorder borderThickness2 = new LineBorder(new Color(0x767171), 4);

	public CustomerOrder(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setResizable(false);
		
		setLayout(new BorderLayout());	
		
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
		lstprice = new JList<>(model);
		lstprice.setBackground(categorySPcolor);
		southwestP.add(lstprice, BorderLayout.CENTER);
		
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
		lstprice.setFont(new Font("나눔고딕", Font.BOLD, 10));
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
		new CustomerOrder("음식주문", 1197, 900);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(lstmenu.getSelectedIndex()) {
		case 0:
			Menu(noodleImgs.length, noodleImgs, noodleStr, noodleprice);
			break;
		case 1:
			Menu(babImgs.length, babImgs, babStr, babprice);
			break;
		case 2:
			Menu(drinkImgs.length, drinkImgs, drinkStr, drinkprice);
			break;
		case 3:
			Menu(snackImgs.length, snackImgs, snackStr, snackprice);
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
		for(int i = 0; i < len; i++) {
			if(obj == jb[i]) {
				model.addElement(jl[i].getText());
			}
		}
		if (obj == orderbtn) {
			dispose();
		}
	}
	public void Menu(int len, ImageIcon[] imgs, String[] str, String[] price) {
		centerP.removeAll();
		this.len = len;
		jp = new JPanel[len];
		jp1 = new JPanel[len];
		jl = new JLabel[len];
		jl1 = new JLabel[len];
		lbl = new JLabel[len];
		jb = new JButton[len];
		
		
		for(int i = 0; i < len; i++) {
			jp[i] = new JPanel();
			jp[i].setBorder(borderThickness1);
			jp[i].setLayout(new BorderLayout());
			
			lbl[i] = new JLabel(imgs[i]);
			jp[i].add(lbl[i]);
			
			jl[i] = new JLabel(str[i]);
			jp[i].add(jl[i], BorderLayout.NORTH);
			
			jp1[i] = new JPanel();
			jp1[i].setLayout(new BorderLayout());
			
			jl1[i] = new JLabel(price[i]);
			jp1[i].add(jl1[i]);
			
			jb[i] = new JButton("담기");
			jb[i].addActionListener(this);
			jp1[i].add(jb[i], BorderLayout.EAST);
			jp[i].add(jp1[i], BorderLayout.SOUTH);
			
			centerP.add(jp[i]);
			centerP.revalidate();
			centerP.repaint();
		}
	}
}