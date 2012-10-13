package b;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class A extends JFrame{

	private JButton add;
	private JButton close;
	private JButton neew;
	private JList l3;
	private JList adda;
	private JTextField value;
	private JTextField totvalu;
	private JTextField tdate;
	private JTextField tmon;
	private JTextField tyear;
	private JScrollPane sp;
	private DefaultListModel model;
	private DefaultListModel model2;
	private G g;
	private S s;
	private D d;
	private N n;
	private int max;
	private A a;
	
	public A(G g, D d){
		this.g =g;
		s = new S(g);
		this.d = d;
		a = this;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		max = d.getMaxhmSize();
		
		add = new JButton("+");
		add.addActionListener(new AL());
		close = new JButton("-");
		close.addActionListener(new AL2());
		l3 = new JList();
		l3.setSize(new Dimension(100,100));
		l3.setPreferredSize(new Dimension(17,50));
		adda = new JList();
		value = new JTextField();
		value.setPreferredSize(new Dimension(120,45));
		value.setFont(new Font("sansserif", Font.BOLD, 40));
		tdate = new JTextField("");
		tdate.setPreferredSize(new Dimension(40,20));
		tmon = new JTextField("");
		tyear = new JTextField("");
		tmon.setPreferredSize(new Dimension(40,20));
		tyear.setPreferredSize(new Dimension(40,20));
		sp = new JScrollPane(adda);
		sp.setPreferredSize(new Dimension(150,max*18-14));
		model = new DefaultListModel();
		l3.setModel(model);
		l3.addListSelectionListener(new LS());
		model2 = new DefaultListModel();
		adda.setModel(model2);
		adda.addListSelectionListener(new LS2());
		neew = new JButton("new");
		neew.addActionListener(new AL3());
		totvalu = new JTextField();
		totvalu.setPreferredSize(new Dimension(120,45));
		totvalu.setEditable(false);
		totvalu.setFont(new Font("sansserif", Font.BOLD, 40));
		
		model.addElement("T");
		model.addElement("F");
		model.addElement("M");
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight=3;
		add(l3,c);
		c.gridx=1;
		add(sp,c);
		c.gridheight=1;
		c.gridwidth=3;
		c.gridx=2;
		add(value,c);
		c.gridy=1;
		add(totvalu,c);
		c.gridwidth=1;
		c.gridy=2;
		add(tdate,c);
		c.gridx=3;
		add(tmon,c);
		c.gridx=4;
		add(tyear,c);
		c.gridx=0;
		c.gridy=3;
		add(close,c);
		c.gridx=1;
		c.gridy=3;
		add(neew,c);
		c.gridx=3;
		c.gridy=3;
		add(add,c);
		pack();
	}
	
	private class AL3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(l3.getSelectedIndex()>=0){
				n = new N(g, (String)l3.getSelectedValue(), d, a);
				n.setVisible(true);
				n.setLocation(1600, 800);
			}
		}
	}
	
	private class AL2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	private boolean checkDV(){
		
		if(Integer.parseInt(tmon.getText())>0&&Integer.parseInt(tmon.getText())<=12&&Integer.parseInt(tyear.getText())>0){
			int maxday = d.getMonMax(Integer.parseInt(tmon.getText())-1, Integer.parseInt(tyear.getText()));
			if(Integer.parseInt(tdate.getText())>0&&Integer.parseInt(tdate.getText())<=maxday){
				return true;
			}
		}
		return false;
	}
	
	private String makeDateString(){
		String d = "";
		d = ":"+tdate.getText()+":"+(Integer.parseInt(tmon.getText())-1)+":"+tyear.getText();
		return d;
	}
	
	private class AL implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(l3.getSelectedIndex()>=0&&adda.getSelectedIndex()>=0){
				if(l3.getSelectedValue().equals("F")){
					if(tdate.getText().equals("")||tmon.getText().equals("")||tyear.getText().equals("")){
						g.addFUse((String)adda.getSelectedValue(), value.getText());
					}else if(checkDV()){
						g.addFUse((String)adda.getSelectedValue(), value.getText(), makeDateString());
					}
				}else if(l3.getSelectedValue().equals("T")){
					if(tdate.getText().equals("")||tmon.getText().equals("")||tyear.getText().equals("")){
						g.addTUse((String)adda.getSelectedValue(), value.getText());
					}else if(checkDV()){
						g.addTUse((String)adda.getSelectedValue(), value.getText(), makeDateString());
					}
				}else if(l3.getSelectedValue().equals("M")){
					if(tdate.getText().equals("")||tmon.getText().equals("")||tyear.getText().equals("")){
						g.addMUse((String)adda.getSelectedValue(), value.getText());
					}else if(checkDV()){
						g.addMUse((String)adda.getSelectedValue(), value.getText(), makeDateString());
					}
				}
			}
			totvalu.setText(""+d.getTV(d.getTFM().get(l3.getSelectedValue()).get(adda.getSelectedValue())));
			s.save();
			d = d.update();
			value.setText("");
		}
	}
	
	private class LS2 implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(adda.getSelectedIndex()>=0){
				totvalu.setText(""+d.getTV(d.getTFM().get(l3.getSelectedValue()).get(adda.getSelectedValue())));
			}else{
				totvalu.setText("");
			}
		}
	}	
	
	private class LS implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			model2.clear();
			if(l3.getSelectedValue().equals("F")){
				String[] s = new String[g.getF().size()];
				for(int i = 0; i < g.getF().size(); i++){
					s[i] = (String)g.getF().keySet().toArray()[i];
				}
				for(int i = 0; i < s.length; i++){
					model2.addElement(s[i]);
				}
			}else if(l3.getSelectedValue().equals("T")){
				String[] s = new String[g.getT().size()];
				for(int i = 0; i < g.getT().size(); i++){
					s[i] = (String)g.getT().keySet().toArray()[i];
				}
				for(int i = 0; i < s.length; i++){
					model2.addElement(s[i]);
				}
			}else if(l3.getSelectedValue().equals("M")){
				String[] s = new String[g.getM().size()];
				for(int i = 0; i < g.getM().size(); i++){
					s[i] = (String)g.getM().keySet().toArray()[i];
				}
				for(int i = 0; i < s.length; i++){
					model2.addElement(s[i]);
				}
			}
		}
	}
}
