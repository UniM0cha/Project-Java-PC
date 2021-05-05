import javax.swing.JFrame;

public class MainProject extends JFrame {

	// 뭘 만들어야 할지는 모르겠지만 일단 프레임 부터 만듦
	public MainProject() {
		this.setSize(500, 500);
		this.setTitle("뭘 만들어야 할까");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainProject();

	}

}
