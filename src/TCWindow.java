import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TCWindow extends JPanel
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Tab Converter");
		window.setBounds(94, 80, 770, 850);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel tcPanel = new Tab_Converter();
		tcPanel.setBackground(new Color(44,65,88));
		window.getContentPane().add(tcPanel);
		window.setVisible(true);
	}
}
