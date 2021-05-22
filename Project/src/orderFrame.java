import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class orderFrame extends JFrame implements MouseListener, ActionListener {
	
	private JPanel westP, centerP, southP, southwestP, southeastP, payP, priceP, requestP, orderP;
	private JPanel[] jp = null;
	private JPanel[] jp1 = null;
	private JLabel[] jl = null;
	private JLabel[] jl1 = null;
	private JLabel[] lbl = null;
	private JButton[] jb = null;
	private String[] menulst = {"라면", "밥", "음료수", "스낵"};
	private JList<String> lstmenu, lstprice;
	private JLabel pricelstlbl, paylbl, pricelbl, requestlbl, orderlbl, categorylbl;
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
	private String[] snackStr = {"콜라", "사이다", "몬스터", "맥콜", "지코", "실론티", "솔의눈", "데자와", "쿠우", "토레타"};
	private String[] noodleprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원"};
	private String[] babprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원"};
	private String[] drinkprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원", "5000원", "5000원", "5000원"};
	private String[] snackprice = {"4500원", "3500원", "4000원", "3500원", "3500원", "3000원", "5000원", "5000원", "5000원", "5000원"};
	private int q = 0;
	private DefaultListModel<String> model = new DefaultListModel<>();
	
	public orderFrame(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setResizable(false);
		
		setLayout(new BorderLayout());	
		
		
		//westP 시작
		
		categorylbl = new JLabel("카테고리");
		lstmenu = new JList<String>(menulst);
		lstmenu.addMouseListener(this);
		
		westP = new JPanel();
		westP.setLayout(new BorderLayout());
		westP.setBackground(Color.red);
		
		categorySp = new JScrollPane(lstmenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		
		westP.add(categorylbl, BorderLayout.NORTH);
		westP.add(categorySp, BorderLayout.CENTER);
		
		//westP 끝
		
		//centerP 시작
		
		centerP = new JPanel();
		centerP.setBackground(Color.orange);
		centerP.setLayout(new WrapLayout(FlowLayout.LEFT, 1, 1));
		
		Menu(noodleImgs.length, noodleImgs, noodleStr, noodleprice);
		Menu(babImgs.length, babImgs, babStr, babprice);
		Menu(drinkImgs.length, drinkImgs, drinkStr, drinkprice);
		Menu(snackImgs.length, snackImgs, snackStr, snackprice);
		
		menuSp = new JScrollPane(centerP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		//centerP 끝
		
		//southwestP 시작
		
		pricelstlbl = new JLabel("상품 주문 목록");
		southwestP = new JPanel();
		southwestP.setLayout(new BorderLayout());
		southwestP.add(pricelstlbl, BorderLayout.NORTH);
		lstprice = new JList<>(model);
		southwestP.add(lstprice, BorderLayout.CENTER);
		
		//southwestP 끝
		
		//southeastP 시작
		southeastP = new JPanel();
		
		payP = new JPanel();
		payP.setLayout(new BorderLayout());
		payP.setBackground(Color.red);
		paylbl = new JLabel("결제 방법");
		payP.add(paylbl, BorderLayout.NORTH);
		bg = new ButtonGroup();	
		rbcard = new JRadioButton("카드", true);	
		rbcash = new JRadioButton("현금");
		bg.add(rbcard);						
		bg.add(rbcash);
		payP.add(rbcard, BorderLayout.CENTER);
		payP.add(rbcash, BorderLayout.SOUTH);
		
		priceP = new JPanel();
		priceP.setLayout(new BorderLayout());
		priceP.setBackground(Color.cyan);
		pricelbl = new JLabel("000원 ");
		priceP.add(pricelbl, BorderLayout.CENTER);
		
		requestlbl = new JLabel("주문 요청 사항");
		requestP = new JPanel();
		requestP.setLayout(new BorderLayout());
		requestP.setBackground(Color.black);
		requestP.add(requestlbl, BorderLayout.NORTH);
		requestJt = new JTextArea("", 5, 27);
		requestJt.setLineWrap(true);
		requestSp = new JScrollPane(requestJt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		requestP.add(requestSp, BorderLayout.CENTER);
		
		orderP = new JPanel();
		orderP.setLayout(new BorderLayout());
		orderP.addMouseListener(this);
		orderP.setBackground(Color.blue);
		orderlbl = new JLabel("주문하기");
		orderP.add(orderlbl, BorderLayout.CENTER);
		
		southeastP.setLayout(new GridLayout(2,2));
		southeastP.add(payP);
		southeastP.add(priceP);
		southeastP.add(requestP);
		southeastP.add(orderP);
		
		//southeastP 끝
		
		//폰트 시작
		
		pricelstlbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lstprice.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		categorylbl.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		lstmenu.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		
		pricelbl.setHorizontalAlignment(JLabel.CENTER);
		orderlbl.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트 끝
		
		southP = new JPanel();
		southP.setLayout(new GridLayout(1, 2));
		southP.setBackground(Color.yellow);
		
		southP.add(southwestP, BorderLayout.WEST);
		southP.add(southeastP, BorderLayout.EAST);
		
		add(westP, BorderLayout.WEST);
		add(menuSp, BorderLayout.CENTER);
		add(southP, BorderLayout.SOUTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new orderFrame("음식주문", 1200, 900);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < menulst.length; i++) {
			if(e.getSource() == lstmenu) {
				if(lstmenu.getSelectedIndex() == i) {
					System.out.println(menulst[i]);
					switch(i) {
					case 0:
						q = 0;
					case 1:
						q = 1;
					case 2:
						q = 2;
					case 3:
						q = 3;
					}
				}
			}
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
		switch(q) {		
		case 0:
			for(int i = 0; i < noodleImgs.length; i++) {
				if(e.getSource() == jb[i]) {
					model.addElement(noodleStr[i]);
				}
			}
		case 1:
			for(int i = 0; i < babImgs.length; i++) {
				if(e.getSource() == jb[i]) {
					model.addElement(babStr[i]);
				}
			}
		case 2:
			for(int i = 0; i < drinkImgs.length; i++) {
				if(e.getSource() == jb[i]) {
					model.addElement(drinkStr[i]);
				}
			}
		case 3:
			for(int i = 0; i < snackImgs.length; i++) {
				if(e.getSource() == jb[i]) {
					model.addElement(snackStr[i]);
				}
			}
		}
	}
	public void Menu(int len, ImageIcon[] imgs, String[] str, String[] price) {
		jp = new JPanel[len];
		jp1 = new JPanel[len];
		jl = new JLabel[len];
		jl1 = new JLabel[len];
		lbl = new JLabel[len];
		jb = new JButton[len];
		
		for(int i = 0; i < len; i++) {
			jp[i] = new JPanel();
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
		}
	}
}