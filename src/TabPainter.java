import java.awt.Graphics;

public class TabPainter
{
    //(helper) constructs an empty tab for a certain length and instrument/chord type
    //chordToTab ; create the empty tab before adding notes
    private static char[][] blankTab(int numChords, ChordSheet c_type)
    {
        char[][] tab = new char[c_type.getNumStrings()][1+numChords*4];
        for(int r=0;r<c_type.getNumStrings();r++)
        {
            for(int c=0;c<1+numChords*4;c++)
            {
                if(c==0 || c== numChords*4)
                    tab[r][c] = '|';
                else
                    tab[r][c] = '-';
            }
        }
        return tab;
    }
    
    //converts String[] of chord names into char[][]
    //automatically called by readString
    private static void chordToTab(String[] chords, ChordSheet c_type, Graphics g, int x, int y)
    {   
        char[][] tab = blankTab(chords.length,c_type);
        
        for(int i=0;i<chords.length;i++)
        {
            int indexNo = chordLocation(chords[i], c_type);
            String notes = c_type.getChordNotes(indexNo);
            for(int r=0;r<=c_type.getNumStrings()-1;r++)
            {
                if(Character.isDigit(notes.charAt(r)) || notes.charAt(r) == 'X')
                tab[r][1+i*4] = notes.charAt(r);
                else if(notes.charAt(r) == 't')//10
                {
                    tab[r][1+i*4] = '1';
                    tab[r][2+i*4] = '0';
                }
                else if(notes.charAt(r) == 'v')//11
                {
                    tab[r][1+i*4] = '1';
                    tab[r][2+i*4] = '1';
                }
            }
        }
        print2dCharArray(tab, g, x, y);
    }
    
    //(helper) finds the index number of a chord (by name) -->
    //chordToTab ; matches chord name to its respective notes
    //numChords ; checks if chord exists (within selected ChordSheet)
    private static int chordLocation(String s, ChordSheet c_type)
    {
        String target = s;
        int temp = -1;
        for (int k = 0; k < c_type.getChordNames().length; k++)
        {
            if (target.equalsIgnoreCase(c_type.getChordNames(k)))
                return k;
        }
        return temp;
    }
    
    //(helper) neatly prints out 2d char array
    //used by chordToTab ; used to print tab (a char[][])
    private static void print2dCharArray(char[][] arrayName, Graphics g, int x, int y)
    {
    	String temp = "";
        for(int r=0;r<arrayName.length;r++)
        {
            for(int c=0;c<arrayName[r].length;c++)
            {
            	temp += arrayName[r][c];
            }
            g.drawString(temp, x, y);
            y += 18;
            temp = "";
        }
    }
    
    //(helper) returns the number of chord names found in a String of text
    //used by readString ; counts valid chord names in the user's input
    private static int numChords(String s, ChordSheet c_type)
    {
    	
        int numberOfChords = 0;
        for(int i=0;i<s.length();i++)
        {
            String currentWord = s.substring(s.lastIndexOf(' ',i),s.indexOf(' ',i));
            if(chordLocation(currentWord.trim(),c_type) != -1)
            {
                numberOfChords++;
                i = s.indexOf(' ',i);
            }
        }
        return numberOfChords;
    }
    
    /**grab user's text from JTextField in T_C panel**/
    //used to convert the user's text into a String[]
    //automatically runs chordToTab on the user's text
    public static void readString(String userString, ChordSheet c_type, Graphics g, int x, int y)
    {
        String[] userChords = new String[numChords(userString,c_type)];
        int currentPos = 0;
        for(int i=1;i<userString.length();i++)
        {
            String currentWord = userString.substring(userString.lastIndexOf(' ',i),userString.indexOf(' ',i));
            if(chordLocation(currentWord.trim(),c_type) != -1)
            {
                userChords[currentPos] = currentWord.trim();
                currentPos++;
                i = userString.indexOf(' ',i);
            }
            else
            	i = userString.indexOf(' ',i);
        }
        chordToTab(userChords,c_type,g,x,y);
    }
}