public class ChordSheet
{
    private String[] chordNames;
    private String[] chordNotes;
        //strings ordered highest to lowest "EBGDAE"
        //for example C on standard tuning guitar = "01023X"
        //for frets 10 and 11 use 't' and 'v', respectively
    private int numStrings;
    
    public ChordSheet(String[] s1, String[] s2)
    {
        chordNames = s1;
        chordNotes = s2;
        numStrings = 6;
    }
    public ChordSheet(String[] s1, String[] s2, int numberOfStrings)
    {
        chordNames = s1;
        chordNotes = s2;
        numStrings = numberOfStrings;
    }
    public String[] getChordNames()
    {
        return chordNames;
    }
    public String[] getChordNotes()
    {
        return chordNotes;
    }
    public String getChordNames(int i)
    {
        return chordNames[i];
    }
    public String getChordNotes(int i)
    {
        return chordNotes[i];
    }
    public int getNumStrings()
    {
        return numStrings;
    }
}