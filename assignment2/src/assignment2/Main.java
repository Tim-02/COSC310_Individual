package assignment2;

import net.didion.jwnl.*;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		try {
			JWNL.initialize(new FileInputStream("config/file_properties.xml"));
			Dictionary d = Dictionary.getInstance();
			String m = "muscles";
			IndexWord i = d.lookupIndexWord(POS.NOUN, m);

			System.out.println("User input: " + m);
			System.out.println(i);
			System.out.println(i.getSense(i.getSenseCount()));
			System.out.println(i.getSynsetOffsets());
		}
		catch(FileNotFoundException | JWNLException e){
			System.out.println(e);
		}
		Window w = new Window(800, 800);
	}
}
