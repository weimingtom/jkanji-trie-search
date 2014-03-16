package main.dictionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.file.DictionaryInput;

public class Dictionary {
	public Dictionary() {

	}

	public List<String> findPrefix(String prefix, DictionaryInput input) {
		char[] ch = prefix.toCharArray();
		Node node = input.getRoot();
		for (int i = 0; i < ch.length; i++) {
			node = input.getNodeById(node.getChild(ch[i]));
			if (node == null) {
				break; 
			}
		}
		List<String> searchResults = new LinkedList<String>();
		Queue<Node> searchQueue = new LinkedList<Node>();
		searchQueue.offer(node);
		Node currNode;
		while ((currNode = searchQueue.poll()) != null) {
			if (currNode.isTerminal()) { 
				searchResults.add(currNode.getPrefix());
			}
			for (Integer id : currNode.getChildren()) {
				Node nextNode = input.getNodeById(id);
				searchQueue.offer(nextNode);
			}
		}
		return searchResults;
	}
}
