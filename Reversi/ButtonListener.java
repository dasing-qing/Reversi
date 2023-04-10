package code;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.jiemian.MyPanel;
/**
 * @author dasing
 *
 */
public class ButtonListener implements ActionListener {
	private MyPanel jp;
	private Graphics g;//绘图
	public ButtonListener(MyPanel jp){
		this.jp=jp;		
		g=jp.getGraphics();
	}
	
//执行
	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand();
		System.out.println(text);
		if (text.equals("start")) {
			for(int j=0;j<Config.HENG;j++){
				for (int i=0;i<Config.HENG;i++){
					if(jiemian.chesses[i][j]!=0){
						jiemian.chesses[i][j]=0;
					}
				}
			}
			jiemian.chesses[Config.HENG / 2 - 1][Config.SHU / 2 - 1] = -1;
			jiemian.chesses[Config.HENG / 2][Config.SHU / 2 - 1] = 1;
			jiemian.chesses[Config.HENG / 2 - 1][Config.SHU / 2] = 1;
			jiemian.chesses[Config.HENG / 2][Config.SHU / 2] = -1;
			ChessListener.jilu[0]=60 ;
			ChessListener.jilu[1]=2 ;
			ChessListener.jilu[2]=2 ;
			ChessListener.count=0;
			jp.update(g);
		}
	}



}
