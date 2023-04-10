package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.jiemian.MyPanel;

/**
 * @author dasing
 * 
 */
public class ChessListener extends MouseAdapter {
	private Graphics g;
	static int count = 0;
	static int xiafa;
	static int[] jilu = { 60, 2, 2 };
	static int Ch = 0;
	static int te = 0;
	private MyPanel jf;


	public ChessListener(Graphics g, MyPanel jf) {

		this.g = g;
		this.jf = jf;
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent e) {
		String s = "";
		Ch = 0;//
		int a;

		int x1 = e.getX();
		int y1 = e.getY();
		// System.out.println(x1 + y1);

		for (int j = 0; j < Config.HENG; j++) {
			for (int i = 0; i < Config.SHU; i++) {

				int x = Config.X0 + Config.SIZE * i;
				int y = Config.Y0 + Config.SIZE * j;

				if (x1 > x - Config.SIZE / 3 && x1 < x + Config.SIZE / 3
						&& y1 > y - Config.SIZE / 3 && y1 < y + Config.SIZE / 3) {
					if (jiemian.chesses[i][j] == 0) {

						if (count == 0) {
							jiemian.chesses[i][j] = 1;
							g.setColor(Color.black);
							count = 1;
						} else {
							jiemian.chesses[i][j] = -1;
							g.setColor(Color.white);
							count = 0;
						}
						if (jilu[1] == 0 || jilu[2] == 0) {
							for (int n = 0; n < Config.HENG; n++) {
								for (int m = 0; m < Config.HENG; m++) {
									if (jiemian.chesses[m][n] == 0) {
										jiemian.chesses[m][n] = 2;
									}
								}
							}
						} else

						if (hengYou(i, j) == null && hengZuo(i, j) == null
								&& hengShang(i, j) == null
								&& hengXia(i, j) == null
								&& xieyouS(i, j) == null
								&& xieyouX(i, j) == null
								&& xiezuoS(i, j) == null
								&& xiezuoX(i, j) == null) {
							jiemian.chesses[i][j] = 0;
							if (count == 0) {
								count = 1;
							} else {
								count = 0;
							}
							javax.swing.JOptionPane.showMessageDialog(null,
									"网格");

						} else {
							hengYou1(i, j);
							hengZuo1(i, j);
							hengShang1(i, j);
							hengXia1(i, j);
							xieyou1(i, j);
							xieyou2(i, j);
							xiezuo1(i, j);
							xiezuo2(i, j);
							g.fillOval(x - Config.CHESS_SIZE / 2, y
									- Config.CHESS_SIZE / 2, Config.CHESS_SIZE,
									Config.CHESS_SIZE);
							g.setColor(Color.BLACK);
							jf.update(g);
							// System.out.println("count="+count);

							if (count == 0)
								a = 1;
							else
								a = -1;
							if (count == 0) {
								s = "1";
							} else {
								s = "0";
							}
							xiafa = Check(a);
							chessView();
							if (Ch != 0) {
								System.out.println(s + "下法" + xiafa + "请重新查看");
							}
							if (xiafa == 0 && jilu[0] != 0) {

								javax.swing.JOptionPane.showMessageDialog(null,
										s + "放置");
								te++;
								if (count == 0)
									count = 1;
								else
									count = 0;
								if (count == 0)
									a = 1;
								else
									a = -1;
								if (count == 0) {
									s = "1";
								} else {
									s = "0";
								}
								xiafa = Check(a);
								System.out.println(s + "下法" + xiafa + "请重新查看");
								if (xiafa == 0) {
									te++;
								}else te=0;
							} else
								te = 0;
							WinJudge();
						}

					}
				}
			}
		}

	}


	/**
	 * 绘制棋盘
	 */
	public void paintChess(int r1, int c1, int r2, int c2) {
		if (c1 == c2) {
			for (int k = Math.min(r1, r2) + 1; k < Math.max(r1, r2); k++) {
				jiemian.chesses[k][c1] = jiemian.chesses[r1][c1];
				Ch++;
			}
		}
		if (r1 == r2) {
			for (int k = Math.min(c1, c2) + 1; k < Math.max(c1, c2); k++) {
				jiemian.chesses[r1][k] = jiemian.chesses[r1][c1];
				Ch++;
			}
		}

	}
	public void paintChess1(int r1, int c1, int r2, int c2) {
		for (int k = Math.min(r1, r2) + 1, v = Math.max(c1, c2) - 1; k < Math
				.max(r1, r2); k++, v--) {
			jiemian.chesses[k][v] = jiemian.chesses[r1][c1];
			Ch++;
		}
	}
	public void paintChess2(int r1, int c1, int r2, int c2) {
		for (int k = Math.min(r1, r2) + 1, v = Math.min(c1, c2) + 1; k < Math
				.max(r1, r2); k++, v++) {
			jiemian.chesses[k][v] = jiemian.chesses[r1][c1];
			Ch++;
		}
	}
	public void paintChess3(int r1, int c1, int r2, int c2) {
		for (int k = Math.max(r1, r2) - 1, v = Math.max(c1, c2) - 1; k > Math
				.min(r1, r2); k--, v--) {
			jiemian.chesses[k][v] = jiemian.chesses[r1][c1];
			Ch++;
		}
	}
	public void paintChess4(int r1, int c1, int r2, int c2) {
		for (int k = Math.min(r1, r2) + 1, v = Math.max(c1, c2) - 1; k <= Math
				.max(r1, r2); k++, v--) {
			jiemian.chesses[k][v] = jiemian.chesses[r1][c1];
			Ch++;
		}
	}
	public int[] hengYou(int x, int y) {
		int r = -2;
		int i;
		for (i = x + 1; i < Config.SHU; i++) {
			if (jiemian.chesses[i][y] != 1 && jiemian.chesses[i][y] != -1) {
				break;
			}
			if (jiemian.chesses[i][y] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x + 1][y] != jiemian.chesses[i][y]) {

			return new int[] { r, y };
		} else {
			return null;
		}
	}
	public int[] hengZuo(int x, int y) {
		int r = -2;
		int i;
		for (i = x - 1; i >= 0; i--) {
			if (jiemian.chesses[i][y] != 1 && jiemian.chesses[i][y] != -1) {
				break;
			}
			if (jiemian.chesses[i][y] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x - 1][y] != jiemian.chesses[i][y]) {

			return new int[] { r, y };
		} else {
			return null;
		}
	}
	public int[] hengShang(int x, int y) {
		int r = -2;
		int i;
		for (i = y - 1; i >= 0; i--) {
			if (jiemian.chesses[x][i] == 0) {
				break;
			}
			if (jiemian.chesses[x][i] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x][y - 1] != jiemian.chesses[x][i]) {

			return new int[] { x, r };
		} else {
			return null;
		}
	}

	public int[] hengShang1(int x, int y) {
		int r = -2;
		int i;
		for (i = y - 1; i >= 0; i--) {
			if (jiemian.chesses[x][i] == 0) {
				break;
			}
			if (jiemian.chesses[x][i] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x][y - 1] != jiemian.chesses[x][i]) {
			paintChess(x, y, x, r);
			return new int[] { x, r };
		} else {
			return null;
		}
	}
	public int[] hengXia(int x, int y) {
		int r = -2;
		int i;
		for (i = y + 1; i < Config.HENG; i++) {
			if (jiemian.chesses[x][i] == 0) {
				break;
			}
			if (jiemian.chesses[x][i] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x][y + 1] != jiemian.chesses[x][i]) {
			return new int[] { x, r };
		} else {
			return null;
		}
	}
	public int[] hengXia1(int x, int y) {
		int r = -2;
		int i;
		for (i = y + 1; i < Config.HENG; i++) {
			if (jiemian.chesses[x][i] == 0) {
				break;
			}
			if (jiemian.chesses[x][i] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x][y + 1] != jiemian.chesses[x][i]) {
			paintChess(x, y, x, r);
			return new int[] { x, r };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xieyouS(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x + 1, j = y - 1; i < Config.HENG && j >= 0; i++, j--) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x + 1][y - 1] != jiemian.chesses[i][j]) {
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xieyouX(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x + 1, j = y + 1; i < Config.HENG && j < Config.SHU; i++, j++) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x + 1][y + 1] != jiemian.chesses[i][j]) {
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xiezuoS(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x - 1][y - 1] != jiemian.chesses[i][j]) {

			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xiezuoX(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x - 1, j = y + 1; i >= 0 && j < Config.SHU; i--, j++) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x - 1][y + 1] != jiemian.chesses[i][j]) {
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	public int[] hengYou1(int x, int y) {
		int r = -2;
		int i;
		for (i = x + 1; i < Config.SHU; i++) {
			if (jiemian.chesses[i][y] != 1 && jiemian.chesses[i][y] != -1) {
				break;
			}
			if (jiemian.chesses[i][y] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x + 1][y] != jiemian.chesses[i][y]) {
			paintChess(x, y, r, y);
			return new int[] { r, y };
		} else {
			return null;
		}
	}

	public int[] hengZuo1(int x, int y) {
		int r = -2;
		int i;
		for (i = x - 1; i >= 0; i--) {
			if (jiemian.chesses[i][y] != 1 && jiemian.chesses[i][y] != -1) {
				break;
			}
			if (jiemian.chesses[i][y] == jiemian.chesses[x][y]) {
				r = i;
				break;
			}
		}

		if (r != -2 && jiemian.chesses[x - 1][y] != jiemian.chesses[i][y]) {
			paintChess(x, y, r, y);
			return new int[] { r, y };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xieyou1(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x + 1, j = y - 1; i < Config.HENG && j >= 0; i++, j--) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x + 1][y - 1] != jiemian.chesses[i][j]) {
			paintChess1(x, y, i, j);
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xieyou2(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x + 1, j = y + 1; i < Config.HENG && j < Config.SHU; i++, j++) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x + 1][y + 1] != jiemian.chesses[i][j]) {
			paintChess2(x, y, i, j);
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xiezuo1(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x - 1][y - 1] != jiemian.chesses[i][j]) {
			paintChess3(x, y, i, j);
			return new int[] { r, s };
		} else {
			return null;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] xiezuo2(int x, int y) {
		int r = -2, s = -2;
		int i, j;
		for (i = x - 1, j = y + 1; i >= 0 && j < Config.SHU; i--, j++) {
			if (jiemian.chesses[i][j] == 0) {
				break;
			}
			if (jiemian.chesses[i][j] == jiemian.chesses[x][y]) {
				r = i;
				s = j;
				break;
			}
		}
		if (r != -2 && s != -2
				&& jiemian.chesses[x - 1][y + 1] != jiemian.chesses[i][j]) {
			paintChess4(x, y, i, j);
			return new int[] { r, s };
		} else {
			return null;
		}
	}
	public int Check(int a) {
		int n = 0;
		for (int j = 0; j < Config.HENG; j++) {
			for (int i = 0; i < Config.SHU; i++) {
				// System.out.println("i="+i+"j="+j);
				if (jiemian.chesses[i][j] != 0) {
					continue;
				} else {
					jiemian.chesses[i][j] = a;
					if (hengYou(i, j) != null || hengZuo(i, j) != null
							|| hengShang(i, j) != null || hengXia(i, j) != null
							|| xieyouS(i, j) != null || xieyouX(i, j) != null
							|| xiezuoS(i, j) != null | xiezuoX(i, j) != null) {
						jiemian.chesses[i][j] = 0;
						n++;
					} else
						jiemian.chesses[i][j] = 0;
				}
			}
		}
		// if (Ch != 0) {
		// chessView();
		// }

		return n;//
	}
	public int WinJudge() {
		if (te == 2) {
			for (int n = 0; n < Config.HENG; n++) {
				for (int m = 0; m < Config.HENG; m++) {
					if (jiemian.chesses[m][n] == 0) {
						jiemian.chesses[m][n] = 2;
					}
				}
			}
			if (jilu[1] > jilu[2]) {
				javax.swing.JOptionPane.showMessageDialog(null, "恭喜黑方胜利");
				return 1;
			}
			if (jilu[1] < jilu[2]) {
				javax.swing.JOptionPane.showMessageDialog(null, "恭喜白方胜利");
				return 2;
			}
			if (jilu[1] == jilu[2]) {
				javax.swing.JOptionPane.showMessageDialog(null, "平局了");
				return 3;
			}
		} else {
			if ((jilu[0] == 0 && jilu[1] > jilu[2]) || jilu[2] == 0) {
				javax.swing.JOptionPane.showMessageDialog(null, "恭喜黑方胜利");
				for (int n = 0; n < Config.HENG; n++) {
					for (int m = 0; m < Config.HENG; m++) {
						if (jiemian.chesses[m][n] == 0) {
							jiemian.chesses[m][n] = 2;
						}
					}
				}
				return 1;
			}
			if ((jilu[0] == 0 && jilu[1] < jilu[2]) || jilu[1] == 0) {
				javax.swing.JOptionPane.showMessageDialog(null, "恭喜白方胜利");
				for (int n = 0; n < Config.HENG; n++) {
					for (int m = 0; m < Config.HENG; m++) {
						if (jiemian.chesses[m][n] == 0) {
							jiemian.chesses[m][n] = 2;
						}
					}
				}
				return 2;
			}
			if (jilu[0] == 0 && jilu[1] == jilu[2]) {
				javax.swing.JOptionPane.showMessageDialog(null, "平局了");
				for (int n = 0; n < Config.HENG; n++) {
					for (int m = 0; m < Config.HENG; m++) {
						if (jiemian.chesses[m][n] == 0) {
							jiemian.chesses[m][n] = 2;
						}
					}
				}
				return 3;
			}
		}
		return 0;
	}

	public void chessView()
		int Hei = 0, Bai = 0, Kong = 0;
		for (int j = 0; j < Config.HENG; j++) {
			for (int i = 0; i < Config.SHU; i++) {
				System.out.print(jiemian.chesses[i][j] + "\t");
				if (jiemian.chesses[i][j] == 1)
					Hei++;
				if (jiemian.chesses[i][j] == -1)
					Bai++;
				if (jiemian.chesses[i][j] == 0)
					Kong++;
			}
			System.out.println();
		}
		jilu[0] = Kong;
		jilu[1] = Hei;
		jilu[2] = Bai;
		return;
	}
}
