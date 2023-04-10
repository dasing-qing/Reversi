package code;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author dasing
 * 
 */
public class jiemian extends JFrame implements Config {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		jiemian WZQ = new jiemian();
		WZQ.showUI();
	}

	static  int[][] chesses = new int[Config.SHU][Config.HENG];


	public void showUI() {

		this.setTitle("黑白棋");
		this.setSize(1000, 720);
		this.setResizable(false);
		MyPanel jp = new MyPanel();
		jp.setPreferredSize(new Dimension(713, 714));
		JButton cxks = new JButton("按钮");
		this.add(jp);
		this.add(cxks);
		FlowLayout f1 = new FlowLayout();
		this.setLayout(f1);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		this.setLocation(300, 0);
		Graphics g =jp.getGraphics();
		chesses[Config.HENG / 2 - 1][Config.SHU / 2 - 1] = -1;
		chesses[Config.HENG / 2][Config.SHU / 2 - 1] = 1;
		chesses[Config.HENG / 2 - 1][Config.SHU / 2] = 1;
		chesses[Config.HENG / 2][Config.SHU / 2] = -1;
		ChessListener CL = new ChessListener(g,  jp);
		ButtonListener BL = new ButtonListener(jp);
		jp.addMouseListener(CL);
		cxks.addActionListener(BL);

	}

//
//	public void paint(Graphics g) {
//		super.paint(g);
//		drawQipan(g);
//		drawQizi(g, chesses);
//
//	}
//绘制棋子
	public void drawQizi(Graphics g) {
		
		Image imgW = new ImageIcon("D:\\桌面\\javaTask\\src\\code\\黑棋.png")
				.getImage();
		Image imgB = new ImageIcon("D:\\桌面\\javaTask\\src\\code\\白棋.png")
				.getImage();
		for (int i = 0; i < Config.SHU; i++) {
			for (int j = 0; j < Config.HENG; j++) {
				int x = Config.X0 + Config.SIZE * i;
				int y = Config.Y0 + Config.SIZE * j;
				if (chesses[i][j] == 1) {
					g.drawImage(imgB, x - Config.CHESS_SIZE / 2, y
							- Config.CHESS_SIZE / 2, Config.CHESS_SIZE,
							Config.CHESS_SIZE, null);
					// g.setColor(Color.black);
					// g.fillOval(x-Config.CHESS_SIZE/2, y-Config.CHESS_SIZE/2,
					// Config.CHESS_SIZE, Config.CHESS_SIZE);
				} else if (chesses[i][j] == -1) {
					g.drawImage(imgW, x - Config.CHESS_SIZE / 2, y
							- Config.CHESS_SIZE / 2, Config.CHESS_SIZE,
							Config.CHESS_SIZE, null);
					// g.setColor(Color.white);
					// g.fillOval(x-Config.CHESS_SIZE/2, y-Config.CHESS_SIZE/2,
					// Config.CHESS_SIZE, Config.CHESS_SIZE);
				}

			}
		}

	}
//绘制棋盘
	public void drawQipan(Graphics g) {
		Image img = new ImageIcon("D:\\桌面\\javaTask\\src\\code\\黑白棋棋盘.jpg")
				.getImage();
		g.drawImage(img, 0, 0, 713, 714, null);
	}
		// for(int i=0;i<Config.HENG;i++){
		// g.drawLine(Config.X0, Config.SIZE*(i+1), Config.X0*Config.HENG,
		// Config.SIZE*(i+1));
		// }
		// for(int i=0;i<Config.SHU;i++){
		// g.drawLine(Config.SIZE*(i+1), Config.X0, Config.SIZE*(i+1),
		// Config.SIZE*Config.SHU);
		// }
	class MyPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g) {
			super.paint(g);
			drawQipan(g);
			drawQizi(g);
	
		}


	}

}
