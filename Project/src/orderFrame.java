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
	private String[] noodleStr = {"신라면", "삼양라면", "사리곰탕", "안성탕면", "진라면", "육개장", "짜파게티"};
	private String[] lblprice = {"4500", "3500", "4000", "3500", "3500", "3000", "5000"};
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
		
		jp = new JPanel[noodleImgs.length];
		jp1 = new JPanel[noodleImgs.length];
		jl = new JLabel[noodleImgs.length];
		jl1 = new JLabel[noodleImgs.length];
		lbl = new JLabel[noodleImgs.length];
		jb = new JButton[noodleImgs.length];
		
		for(int i = 0; i < noodleImgs.length; i++) {
			jp[i] = new JPanel();
			jp[i].setLayout(new BorderLayout());
			
			lbl[i] = new JLabel(noodleImgs[i]);
			jp[i].add(lbl[i]);
			
			jl[i] = new JLabel(noodleStr[i]);
			jp[i].add(jl[i], BorderLayout.NORTH);
			
			jp1[i] = new JPanel();
			jp1[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			
			jl1[i] = new JLabel(lblprice[i]);
			jp1[i].add(jl1[i]);
			
			
			jb[i] = new JButton("담기");
			jb[i].addActionListener(this);
			jp1[i].add(jb[i]);
			
			jp[i].add(jp1[i], BorderLayout.SOUTH);
			
			centerP.add(jp[i]);
		}
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
		southP.setLayout(new BorderLayout());
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
		for(int i = 0; i < noodleImgs.length; i++) {
			if(e.getSource() == jb[i]) {
				model.addElement(noodleStr[i]);
			}
		}
		
	}
}