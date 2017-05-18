import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HelpPanel extends JPanel
{
	private int drawChords(String[] s, int x, int y, Graphics g)
	{
		int space = 0;
		String someChords = "";
		for(int i=0;i<s.length;i++)
		{
			someChords += s[i];
			for(int q=s[i].length();q<10;q++)
			{
				someChords += " ";
			}
			if((i+1)%8 == 0)
			{
				g.drawString(someChords,x,y);
				y += 18;
				space += 18;
				someChords = "";
			}
		}
		return space + 18;
	}
	@Override
    public void paintComponent(Graphics g)
    {   
    	ChordSheet guitar = new GuitarChords();
    	ChordSheet ukulele = new Ukulele();
    	ChordSheet powerChords = new PowerChords();
    	ChordSheet ukulele_b = new UkuleleBaritone();
    	
		super.paintComponent(g);
		g.setFont(new Font("Monospaced", Font.PLAIN, 18));
		g.setColor(Color.WHITE);
		this.setBackground(new Color(17, 30, 44));
		
		int yVal = 30;
		g.drawString("Guitar (Standard):",10,yVal);
		yVal += 36;
		yVal += drawChords(guitar.getChordNames(),80,yVal,g);
		g.drawString("Guitar (Power chords):", 10, yVal);
		yVal += 36;
		yVal += drawChords(powerChords.getChordNames(),80,yVal,g);
		g.drawString("Ukulele (Standard):", 10, yVal);
		yVal += 36;
		yVal += drawChords(ukulele.getChordNames(),80,yVal,g);
		g.drawString("Ukulele (Baritone):", 10, yVal);
		yVal += 36;
		yVal += drawChords(ukulele_b.getChordNames(),80,yVal,g);
    }
}
