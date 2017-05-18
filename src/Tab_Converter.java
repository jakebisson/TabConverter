import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Tab_Converter extends JPanel
						   implements ActionListener
{   
	private ChordSheet c_type;
	private JButton b1, b2, b3, b4, bHelp, bRefresh;
	private JTextField tf1;
	private boolean flag = false;
	private boolean flag1 = false;
	private boolean flag2 = false;
	private boolean flag3 = false;
	private boolean flag4 = false;
	
	public Tab_Converter()
	{
		int initYVal = 102;
		int initXVal = 119;
		this.setLayout(null);
		b1 = new JButton("A");
		b1.setBounds(initXVal,initYVal,42,18);
		initYVal += 18;
		b1.addActionListener(this);
		b2 = new JButton("B");
		b2.addActionListener(this);
		b2.setBounds(initXVal,initYVal,42,18);
		initYVal += 18;
		b3 = new JButton("C");
		b3.addActionListener(this);
		b3.setBounds(initXVal,initYVal,42,18);
		initYVal += 18;
		b4 = new JButton("D");
		b4.addActionListener(this);
		b4.setBounds(initXVal,initYVal,42,18);
		initYVal += 18;
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		
		bRefresh = new JButton("Reset");
		bRefresh.addActionListener(this);
		bRefresh.setBounds(624,24,90,24);
		this.add(bRefresh);
		
		bHelp = new JButton("Click here for list of all valid chord names");
		bHelp.setBounds(30,24,380,24);
		bHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame helpWindow = new JFrame("Help");
				helpWindow.setBounds(810, 100, 1050, 800);
				helpWindow.setVisible(true);
				JPanel helpPanel = new HelpPanel();
				helpPanel.setMinimumSize(new Dimension(950,1080));
				helpPanel.setPreferredSize(new Dimension(950,1080));
				helpWindow.getContentPane().add(helpPanel);
				
				JScrollPane jscrPane = new JScrollPane(helpPanel,
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				helpWindow.getContentPane().add(jscrPane);
			}
		});
		this.add(bHelp);
		
		tf1 = new JTextField("Click here to enter chord names, seperated with spaces.",1);
		tf1.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				flag = true;
				tf1.setEnabled(false);
				repaint();
			}
		});
		tf1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(tf1.getText().equals("Click here to enter chord names, seperated with spaces."))
					tf1.setText("");
			}
		});
		tf1.setLocation(114,initYVal + 54);
		tf1.setSize(600, 36);
		tf1.setEnabled(false);
		this.add(tf1);
	}
	
	private void setChordType(ChordSheet c)
	{
		c_type = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		if(b == b1)
		{
			setChordType(new GuitarChords());
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			tf1.setEnabled(true);
			flag1 = true;
			repaint();
		}
		else if(b == b2)
		{
			setChordType(new PowerChords());
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			tf1.setEnabled(true);
			flag2 = true;
			repaint();
		}
		else if(b == b3)
		{
			setChordType(new Ukulele());
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			tf1.setEnabled(true);
			flag3 = true;
			repaint();
		}
		else if(b == b4)
		{
			setChordType(new UkuleleBaritone());
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			tf1.setEnabled(true);
			flag4 = true;
			repaint();
		}
		else if(b == bRefresh)
		{
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			tf1.setEnabled(false);
			tf1.setText("Click here to enter chord names, seperated with spaces.");
			flag = false;
			flag1 = false;
			flag2 = false;
			flag3 = false;
			flag4 = false;
			repaint();
		}
	}
	
	@Override
    public void paintComponent(Graphics g)
    {   
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		Color color1 = new Color(27, 40, 54);
		g.setColor(color1);
		g.fillRoundRect(15, 15, w-30, h-30, 15, 15);
		g.setFont(new Font("Courier New", Font.PLAIN, 18));
		g.setColor(Color.WHITE);
		int stry = 89;
        int strx = 124; //equal to initXVal + 5
        
        g.drawString("FIRST: Choose the type of chords to create a tab for: ",35,stry);
        	stry += 27;
        g.drawString("    Guitar (standard - EADGBE)",strx,stry);
        	stry += 18;
        g.drawString("    Guitar (power chords)",strx,stry);
        	stry += 18;
        g.drawString("    Ukulele (standard - GCEA)",strx,stry);
        	stry += 18;
        g.drawString("    Ukulele (baritone - DGBE)",strx,stry);
        	stry += 27;
        g.drawString("NEXT: ", 35, 246);
        
        if(flag)
        {
        	TabPainter.readString(" "+tf1.getText()+" ",c_type,g,35,300);
        }
        if(flag1)
        {
        	g.setFont(new Font("Courier New", Font.ITALIC, 18));
        	g.drawString("Selected: Guitar (standard)",strx,stry);
        	g.setFont(new Font("Courier New", Font.PLAIN, 18));
        }
        if(flag2)
        {
        	g.setFont(new Font("Courier New", Font.ITALIC, 18));
        	g.drawString("Selected: Guitar (Power chords)",strx,stry);
        	g.setFont(new Font("Courier New", Font.PLAIN, 18));
        }
        if(flag3)
        {
        	g.setFont(new Font("Courier New", Font.ITALIC, 18));
        	g.drawString("Selected: Ukulele (standard)",strx,stry);
        	g.setFont(new Font("Courier New", Font.PLAIN, 18));
        }
        if(flag4)
        {
        	g.setFont(new Font("Courier New", Font.ITALIC, 18));
        	g.drawString("Selected: Ukulele (baritone)",strx,stry);
        	g.setFont(new Font("Courier New", Font.PLAIN, 18));
        }
    }
}