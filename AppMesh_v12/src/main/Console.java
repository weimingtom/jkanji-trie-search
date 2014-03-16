package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.dictionary.Dictionary;
import main.file.DictionaryInputStream;
import main.file.DictionaryInput;
import main.file.DictionaryReader;

public class Console {
	public static void main(String [] args) throws IOException {
		String filename;
		boolean IS_LARGE_FILE = false;
		boolean IS_TEXT_FILE = false;
		boolean TEST_INPUT = true;
		Dictionary dictionary = new Dictionary();
		DictionaryInput input = null;
		if (IS_TEXT_FILE) {
			if (IS_LARGE_FILE) {
				filename = "resources/words.txt";
			} else {
				filename = "resources/words_mini.txt"; 
			}
			input = new DictionaryReader(filename);
			input.dumpTree();
			if (!IS_LARGE_FILE) {
				input.dumpNodeList(IS_LARGE_FILE);
				input.dumpFile(filename.replace(".txt", ".bin"), IS_LARGE_FILE);
			} else {
				input.dumpFile(filename.replace(".txt", ".bin"), IS_LARGE_FILE);
			}
		} else {
			if (IS_LARGE_FILE) {
				filename = "resources/words.bin";
			} else {
				filename = "resources/words_mini.bin"; 
			}
			input = new DictionaryInputStream(filename);
		}
		
		Scanner in = new Scanner(System.in);
		if (TEST_INPUT) {
			while (true){
				String prefix = in.nextLine();
				if (prefix.equals("EXIT")) {
					break;
				} else {
					List<String> words = dictionary.findPrefix(prefix, input);
					if(words != null) {
						for(String word : words) {
							System.out.println(" - " + word);
						}
					} else {
						System.out.println(" - NO RESULTS");
					}
				}
			}
		}
	}  
}
