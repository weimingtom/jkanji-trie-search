package main.file;

import java.io.IOException;

import main.dictionary.Node;

public interface DictionaryInput {
	public Node getRoot();
	
	public Node getNodeById(Integer id);
	
	public void dumpTree();
	
	public void dumpNodeList(boolean isLargeFile);
	
	public void dumpFile(String filename, boolean isLargeFile) throws IOException;
}
