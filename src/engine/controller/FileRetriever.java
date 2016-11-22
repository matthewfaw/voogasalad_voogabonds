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
	public List<String> getFileNames(String aDirectory)
	{
		File folder = new File(myDefaultPath + aDirectory);
		File[] files = folder.listFiles();
		ArrayList<String> filesInDirectory = new ArrayList<String>();
		for (File aFile: files){
			if (aFile.isFile()) {
				String relativeFilePath = getRelativeFilePath(aFile);
				filesInDirectory.add(relativeFilePath);
			}
		}
		return filesInDirectory;
	}
	/**
	 * A method to retrieve the file path relative to myDefaultPath 
	 * (the path with which the object was constructed)
	 * @param aFile
	 * @return
	 */
	private String getRelativeFilePath(File aFile)
	{
		String absoluteFilePath = aFile.getAbsolutePath();
		return absoluteFilePath.substring(absoluteFilePath.indexOf(myDefaultPath) + myDefaultPath.length() + 1);
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
