package lottery;

import java.awt.*;

public class Machine extends Panel {

	private Wheel[] wheels;
	private int num;
	private boolean running=false;
	
	public Machine(int k) {
		wheels=new Wheel[k];
		for(int i=0; i<k; i++) {
			int dt=(int)(10 + Math.random()*41);
			add(wheels[i]=new Wheel(dt,i%2==0));
		}
	}
	
	public void spinLast(int n) {
		if(running) return;
		if(n>wheels.length|| n<0) n=wheels.length;
		num=n;
		for(int i=0;i<n;i++)
			wheels[wheels.length-1-i].go();
		running=true;
	}
	
	public void pauseWheels() {
		if(!running) return;
		for(int i=0; i<wheels.length; i++)
			wheels[i].pause();
		running=false;
	}
	
	protected void destroy() {
		for(int i=0; i<wheels.length; i++)
			wheels[i].stop();
	}
	
	
	public String stopMachine() {
		running=false;
		StringBuilder sb=new StringBuilder();
		for(int i=wheels.length-num; i<wheels.length; i++)
			sb.append(wheels[i].getCurr());
		return sb.toString();
	}
	
}
