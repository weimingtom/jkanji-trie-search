package com.iteye.weimingtom.appmesh;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.iteye.weimingtom.appmesh.dictionary.Dictionary;
import com.iteye.weimingtom.appmesh.dictionary.Node;
import com.iteye.weimingtom.appmesh.dictionary.OnAddNodeListener;
import com.iteye.weimingtom.appmesh.file.DictionaryInput;
import com.iteye.weimingtom.appmesh.file.DictionaryInputStream;
import com.iteye.weimingtom.appmesh.file.DictionaryReader;


public class Console {
	public static void main(String [] args) throws IOException {
		String filename;
		boolean IS_TEXT_FILE = true;
		boolean TEST_INPUT = true;
		Dictionary dictionary = new Dictionary();
		DictionaryInput input = null;
		if (IS_TEXT_FILE) {
			filename = "appmesh/n5.txt"; 
			input = new DictionaryReader(filename);
			input.dumpTree();
			//input.dumpNodeList(true);
			input.dumpFile(filename.replace(".txt", ".bin"), false);
		} else {
			filename = "appmesh/n5.bin"; 
			input = new DictionaryInputStream(filename);
		}
		
		Scanner in = new Scanner(System.in);
		if (TEST_INPUT) {
			final int limit = 10;
			final List<Node> words = new LinkedList<Node>();
			dictionary.setOnAddNodeListener(new OnAddNodeListener() {
				@Override
				public void onAddNode(Node node) {
					words.add(node);
				}
			});
			while (true){
				String prefix = in.nextLine();
				if (prefix != null && prefix.toLowerCase().equals("exit")) {
					break;
				} else if (prefix != null && prefix.toLowerCase().equals("next")) {
					if (dictionary.hasNext()) {
						dictionary.findNext(input, limit);
						if (!words.isEmpty()) {
							for (Node word : words) {
								System.out.println(" - " + word.toString());
							}
							if (dictionary.hasNext()) {
								System.out.println(" - TYPE next FOR MORE RESULTS");
							}
						} else {
							System.out.println(" - NO RESULTS");
						}						
					} else {
						System.out.println(" - NO MORE");
					}
					words.clear();
				} else {
					dictionary.findPrefix(prefix, input, limit);
					if (!words.isEmpty()) {
						for (Node word : words) {
							System.out.println(" - " + word.toString());
						}
						if (dictionary.hasNext()) {
							System.out.println(" - TYPE next FOR MORE RESULTS");
						}
					} else {
						System.out.println(" - NO RESULTS");
					}
					words.clear();
				}
			}
		}
	}  
}
