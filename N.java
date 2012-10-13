package b;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class N extends JFrame{

	private G g;
	private JButton add;
	private JButton close;
	private JTextField name;
	private String s;
	private S sa;
	private D d;
	private A a;
	
	public N(G g, String s, D d, A a){
		this.g = g;
		this.s = s;
		sa = new S(g);
		this.d = d;
		this.a = a;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		name = new JTextField();
		name.setPreferredSize(new Dimension(100,25));
		close = new JButton("-");
		close.addActionListener(new AL());
		add = new JButton("+");
		add.addActionListener(new AL2());
		
		c.gridwidth=3;
		add(name,c);
		c.gridwidth=1;
		c.gridy=1;
		add(close, c);
		c.gridx=2;
		add(add,c);
		pack();
	}
	
	private class AL implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			a.dispose();
			A a = new A(g, d);
			a.setVisible(true);
			a.setLocation(1500, 200);
			dispose();
		}
	}
	
	private class AL2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!name.getText().equals("")){
				if(s.equals("F")){
					g.addNewF(name.getText());
				}else if(s.equals("T")){
					g.addNewT(name.getText());
				}else if(s.equals("M")){
					g.addNewM(name.getText());
				}
				sa.save();
			}
			name.setText("");
			d = d.update();
		}
	}
}
