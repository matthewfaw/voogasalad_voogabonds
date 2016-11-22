package engine.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * The purpose of this class is to retrieve all files in a given file path
 * This class can be used by the GameEngine to get all the files that it needs to construct
 * @author matthewfaw
 *
 */
public class FileRetriever {
	private String myDefaultPath;

	public FileRetriever(String aDefaultPath)
	{
		myDefaultPath = aDefaultPath;
	}
	
	/**
	 * Given a directory relative to the default path constructed, 
	 * list all of the files contained in that directory
	 * 
	 * TODO: This method currently assumes that the requested file path is valid: 
	 * need to throw error if invalid
	 * @param aDirectory
	 * @return
	 */
	public List<File> getFileNames(String aDirectory)
	{
		File folder = new File(myDefaultPath + aDirectory);
		File[] files = folder.listFiles();
		ArrayList<File> filesInDirectory = new ArrayList<File>();
		for (File aFile: files){
			if (aFile.isFile()) {
				filesInDirectory.add(aFile);
			}
		}
		return filesInDirectory;
	}

	/*
	public static void main(String[] args)
	{
		FileRetriever fr = new FileRetriever("src/SerializedFiles");
		List<String> l = fr.getFileNames("/towers");
		System.out.println(l);
	}
	*/
}
