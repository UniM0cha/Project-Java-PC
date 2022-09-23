import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CounterOrder extends JFrame implements ActionListener {

	private static DB db = new DB();
	private final String FONT = "나눔고딕";
	private JButton btnConfirm, btnClose;
	private Vector<String> header = new Vector<>(Arrays.asList("상품명", "개수"));
	private Vector<Vector<String>> contents = new Vector<>();
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		// 내용 수정 못하게 설정
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	private JTable table = new JTable(tableModel);
	private int pcNum;
	private int sumPrice = 0;
	private String pay, count, productName;
	private String request = "";

	public CounterOrder(int pcNum, Component com) {
		this.setSize(400, 300);
		this.setLocationRelativeTo(com);
		this.setTitle(pcNum + "번 PC");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0x25262B), 5));
		this.setResizable(false);

		Color westcolor = new Color(0x595959);
		Color btncolor = new Color(0xF3F1DF);
		Color centersouthcolor = new Color(0x84878a);
		Color centernorthcolor = new Color(0xFFF7F1);

		this.pcNum = pcNum;
		getDataFromOrders();

		// 왼쪽
		JPanel west = new JPanel();
		west.setBackground(westcolor);
		west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(0x25262B)));

		// PC번호 (00번)
		JLabel lblPcNum = new JLabel(Integer.toString(pcNum));
		lblPcNum.setFont(new Font(FONT, Font.BOLD, 40));
		lblPcNum.setForeground(Color.white);
		west.add(lblPcNum);

		JLabel lblBun = new JLabel("번");
		lblBun.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblBun.setForeground(Color.white);
		west.add(lblBun);

		this.add(west, BorderLayout.WEST);

		// 오른쪽
		JPanel center = new JPanel(new BorderLayout());

		// 주문내역 테이블
		JPanel subCenter = new JPanel(new BorderLayout());
		subCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		subCenter.setBackground(centernorthcolor);
		// 테이블 디자인
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.setRowHeight(25);
		table.setFont(new Font(FONT, Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(0xF3F1DF));
		table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
		table.setBackground(new Color(0xFFFFFF));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
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
		JScrollPane scrollTable = new JScrollPane(table);
		subCenter.add(scrollTable, BorderLayout.CENTER);

		// 요청사항
		JPanel panRequest = new JPanel(new BorderLayout());
		JLabel lblRequest = new JLabel("요청사항");
		panRequest.add(lblRequest, BorderLayout.NORTH);
		JTextArea textAreaRequest = new JTextArea();
		textAreaRequest.setText(request);
		panRequest.add(textAreaRequest, BorderLayout.CENTER);
		subCenter.add(panRequest, BorderLayout.SOUTH);

		center.add(subCenter, BorderLayout.CENTER);

		// 가격, 결제수단, 버튼들
		JPanel subSouth = new JPanel(new BorderLayout());
		subSouth.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x25262B)));

		// 가격, 결제수단
		JPanel subSouthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		subSouthLeft.setBackground(centersouthcolor);
		subSouthLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		String priceString = NumberFormat.getInstance().format(sumPrice);
		JLabel lblPrice = new JLabel(priceString + "원 (" + pay + ")");
		lblPrice.setFont(new Font(FONT, Font.BOLD, 17));
		lblPrice.setForeground(Color.white);
		subSouthLeft.add(lblPrice);

		// 버튼
		JPanel subSouthRight = new JPanel();
		subSouthRight.setBackground(centersouthcolor);

		btnClose = new JButton("닫기");
		btnClose.setFont(new Font(FONT, Font.BOLD, 13));
		btnClose.addActionListener(this);
		btnClose.setBackground(btncolor);
		subSouthRight.add(btnClose);

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
			if (JOptionPane.showConfirmDialog(this, "상품을 제공하셨습니까?", "확인",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				updateStockAtProduct();
				insertDataAtSales();
				deleteDataAtOrders();
				falseIsOrderAtState();
				this.dispose();
			}
		} else if (obj == btnClose) {
			this.dispose();
		}
	}

	// 주문한 만큼 product 테이블의 stock을 줄임
	private void updateStockAtProduct() {
		String sqlSelect = "SELECT pcNum, productID, SUM(counts) counts "
				+ "FROM orders o "
				+ "WHERE pcNum = " + pcNum + " "
				+ "GROUP BY productID";
		ResultSet rs = db.Query(sqlSelect);
		try {
			while (rs.next()) {
				int productID = rs.getInt("productID");
				int sumCounts = rs.getInt("counts");
				String sqlUpdate = "UPDATE product "
						+ "SET stock = stock - " + sumCounts + " "
						+ "WHERE productID = " + productID;
				db.Update(sqlUpdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// orders 테이블로부터 주문내역을 얻어옴
	private void getDataFromOrders() {
		String sql = "SELECT pcNum, productName, counts, payment, salePrice, request "
				+ "FROM orders o "
				+ "JOIN product p "
				+ "WHERE o.productID = p.productID "
				+ "AND pcNum = " + pcNum;
		ResultSet rs = db.Query(sql);
		try {
			while (rs.next()) {
				sumPrice += rs.getInt("salePrice");
				pay = rs.getString("payment");
				String reqTemp = rs.getString("request");
				if (reqTemp != null)
					request += reqTemp + "\n";
				productName = rs.getString("productName");
				count = Integer.toString(rs.getInt("counts"));
				contents.add(new Vector<String>(Arrays.asList(productName, count)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// sales에 매출내역 추가
	private void insertDataAtSales() {
		String sql = "INSERT INTO sales(productID, counts, salePrice) "
				+ "SELECT productID, counts, salePrice "
				+ "FROM orders "
				+ "WHERE pcNum =" + pcNum;
		db.Update(sql);
	}

	// orders의 주문내역을 삭제
	private void deleteDataAtOrders() {
		String sql = "DELETE FROM orders "
				+ "WHERE pcNum = " + pcNum;
		db.Update(sql);
	}

	// state의 isOrder false
	private void falseIsOrderAtState() {
		String sql = "UPDATE state "
				+ "SET isOrder = 0 "
				+ "WHERE pcNum = " + pcNum;
		db.Update(sql);
	}
}

/*
 * 한계점
 * 1. 주문 내역을 보고있는 중 주문이 들어오면 확인 불가 (실시간 연동 필요)
 * 2. 다른 결제수단으로 한번 더 주문시 확인 불가
 */