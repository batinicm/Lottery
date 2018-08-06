package lottery;

import java.awt.*;
import java.awt.event.*;

public class Draft extends Frame {
	
	private Machine mac=new Machine(6);
	private TextArea res=new TextArea(1, 7);
	private Checkbox[] num=new Checkbox[6];
	private Button begin,stop;
	private int n;
	
	public Draft() {
		super("Lottery Draft");
		setSize(300,180);
		pop();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mac.destroy();
				dispose();
			}
		});
		setResizable(false);
		setVisible(true);
	}
	
	private void pop() {
		
		Panel pan=new Panel(new BorderLayout());
		
		pan.add(res,BorderLayout.CENTER);
		Button bu;
		pan.add(bu=new Button("Empty"),BorderLayout.SOUTH);
		bu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				res.setText("");
			}
		});
		
		add(pan,BorderLayout.EAST);
		
		pan=new Panel(new BorderLayout());	
		add(pan,BorderLayout.CENTER);
		pan.add(mac,BorderLayout.NORTH);
		
		Panel npan=new Panel(new GridLayout(2,1));		
		pan.add(npan,BorderLayout.SOUTH);
		
		CheckboxGroup group=new CheckboxGroup();
		String[] numbers= {"6","5","4","3","2","1","0"};
		Panel ns=new Panel();
		for(int i=0; i<6; i++) 
			ns.add(num[i]=new Checkbox(numbers[i], i==0, group));
		npan.add(ns);
		
		
		ns=new Panel();
		
		begin=new Button("Begin");
		stop=new Button("Stop");
		ns.add(begin);
		begin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				endis(false);
				for(Checkbox c: num)
					if(c.getState()) n=Integer.parseInt(c.getLabel());
				mac.spinLast(n);
			}
		});
		
		ns.add(stop);
		stop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				endis(true);
				res.append(mac.stopMachine() + '\n');
			}
		});
		
		npan.add(ns);
		
		endis(true);
			
	}
	
	private void endis(boolean go) {
		begin.setEnabled(go);
		stop.setEnabled(!go);
	}
	
	public static void main(String[] args) {
		new Draft();
	}
}
