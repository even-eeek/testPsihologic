import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Plansa extends Canvas {
	Dimension dim = new Dimension(650, 650);
	private Color color[] = { Color.red, Color.blue , Color.green, Color.yellow, Color.black, Color.orange};
	private int index = 0;
	private static int rand;
	static double points = 0;
	public static JLabel label = new JLabel("points : 0.0");
	//public static JLabel timeLabel = new JLabel();
	public MyTimer myTimer = new MyTimer();
	public int time = 50;
	public static int rightCounter = 0;
	public static int wrongCounter = 0;
	
	public static JLabel rightlabel = new JLabel("right : 0");
	public static JLabel wronglabel = new JLabel("wrong : 0");

	public static double min = 100.0;
	public static double max = 0.0;

	public Plansa() {
		label.setFont(new Font("Serif", Font.PLAIN, 60));
		rightlabel.setFont(new Font("Serif", Font.PLAIN, 50));
		wronglabel.setFont(new Font("Serif", Font.PLAIN, 50));
		
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_Z && MyTimer.check == true){
					double result = time - MyTimer.getInterval();
					if(result < min)
						min = result;
					if(result > max)
						max = result;
					
					myTimer.interval = time;
					if(rand == 0){
						points += result;
						rightCounter ++;
					}else{
						wrongCounter ++;
						points -= result / 2;
					}
					repaint();
				}else if(e.getKeyCode() == KeyEvent.VK_X && MyTimer.check == true){
					double result = MyTimer.getInterval() / 5 ;
					myTimer.interval = time;
					if(rand == 1){
						points += result;
						rightCounter ++;
					}else{
						wrongCounter ++;
						points -= result / 2;
					}
					repaint();
				}else if(e.getKeyCode() == KeyEvent.VK_C && MyTimer.check == true){
					double result = time - MyTimer.getInterval();
					myTimer.interval = time;
					if(rand == 2 || rand == -1){
						rightCounter ++;
						points += result;
					}else{
						wrongCounter ++;
						points -= result / 2;
					}
					repaint();
				}
				label.setText("points : "+ String.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(Plansa.getPoints()))));
				wronglabel.setText("wrong : " + wrongCounter);
				rightlabel.setText("right : " + rightCounter);
				if(wrongCounter + rightCounter == 100){
					MyTimer.check = false;
					
				}else if(wrongCounter + rightCounter == 35){
					time = 35;
				}else if(wrongCounter + rightCounter == 60){
					time = 30;
				}else if(wrongCounter + rightCounter == 80){
					time = 25;
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
			
		});
	
		this.setFocusable(true);
	}
//	public static double returnResult(double Result){
//		double x = Result / 5;
//		
//	}
	
	public static double getPoints(){
		return points;
	}
	public void paint(Graphics g) {
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    
//		g.setColor(color[index % 6] );
//		g.drawRect(x, y, dim.width, dim.height);
		g.setColor(color[(index ++) % 6]);
		Random rn = new Random();
		rand = rn.nextInt() % 3;
		if(rand == 0){
			g.fillRect(x, y, dim.width, dim.height);
		}else if(rand == 1){
			g.fillOval(x, y, dim.width, dim.height);
		}else {
			int[] xx = new int[3];
			int[] yy = new int[3];
			xx[0]=0; xx[1]=700; xx[2]=700;
			yy[0]=10; yy[1]=300; yy[2]=1000;
			int n = 3;

			g.fillPolygon(xx, yy, n); 
		}
		
	}

	public Dimension getPreferredSize() {
		return dim;
	}
}

class Fereastra extends JFrame {
	
	public Fereastra(String titlu) {
		super(titlu);


		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
//		setMinimumSize(new Dimension(1200, 1000));
//		setSize(, 200);
		
		
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(5,1));
		
		JLabel instr = new JLabel("<html>z - patrat<br>x - cerc<br>c - triunghi</html>");
		instr.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(instr);
		panel.add(Plansa.label);
		panel.add(MyTimer.timeLabel);
		panel.add(Plansa.rightlabel);
		panel.add(Plansa.wronglabel);
		
		
		add(new Plansa(), BorderLayout.CENTER);
		add(panel,  BorderLayout.EAST);
		pack();
		//setLocationRelativeTo(null);
	}
}

