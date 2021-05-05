import javax.swing.JFrame;

public class Test extends JFrame{

	public Test(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Test("정우 똥꼬 핑크 똥꼬", 300, 300);
	}

}
