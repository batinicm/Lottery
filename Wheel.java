package lottery;

import java.awt.*;

public class Wheel extends Label implements Runnable {

	private Thread thr=new Thread(this);
	private int num=0;
	private int dt;
	private int displ;
	private boolean running=false;
	
	
	public Wheel(int dt,boolean dir) {
		this.dt=dt;
		displ=dir?1:-1;
		setBackground(Color.YELLOW);
		setForeground(Color.RED);
		setAlignment(CENTER);
		setFont(new Font(null, Font.BOLD, 24));
		setEmpty();
		thr.start();
	}
	
	public void setEmpty() {
		setText("");
	}
	
	public synchronized void go() {
		running=true;
		notify();
	}
	
	public void pause() {
		running=false;
	}
	
	public void stop() {
		thr.interrupt();
	}
	
	public synchronized int getCurr() {
		running=false;
		return num;
	}
	
	public synchronized void spin(boolean dir) {
		if(running==true) running=false;						
		displ=dir?1:-1;	
		go();
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					if(!running) wait();
					num=(num+displ+10)%10;
					setText(Integer.toString(num));		
				}
				Thread.sleep(dt);
			}
		}
		catch(InterruptedException i) {}

	}

}
