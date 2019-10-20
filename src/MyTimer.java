import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyTimer {
	public static double interval = 40.0;
	public static Timer timer;
	private static double period = 1000;
	public static boolean check = true;
	public static JLabel timeLabel = new JLabel("miliseconds : 40.0");

	public MyTimer() {
		timeLabel.setFont(new Font("Serif", Font.PLAIN, 80));
		// new Fereastra(" Test Canvas ").show();
		int delay = 1000;
		// int period = 1000;
		timer = new Timer();
		// int secs = 10;
		// interval = Integer.parseInt(secs);
		// System.out.println(secs);
		((Timer) timer).scheduleAtFixedRate(new TimerTask() {

			public void run() {
				if (check == true) {
					timeLabel.setText("miliseconds :" + String
							.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(MyTimer.getInterval()))));
					setInterval();
					check();
				}
			}
		}, delay, (long) (period / 10));
	}

	public void check(){
		if(check == false){
			MyJDialogClass obj = new MyJDialogClass(new JFrame(), "Result", "<html>You have scored : " + 
					String.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(Plansa.points))) + " points" +
					 "<br>Your best reaction time : " + String.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(Plansa.min))) + " miliseconds" + 
					 "<br>Your worst reaction time : " + String.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(Plansa.max))) + " miliseconds</html>");
		}
	}
	private static final double setInterval() {
		if (interval < 0 && check == true) {
			check = false;
			timer.cancel();
		}

		interval -= period / 1000;
		return interval;
	}

	public static double getInterval() {
		return Double.parseDouble(new DecimalFormat("##.####").format(interval));
	}
}