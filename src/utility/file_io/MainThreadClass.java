package utility.file_io;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

/**
 * Info about making a new thread obtained here:
 * http://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
 * @author matthewfaw
 *
 */
public class MainThreadClass {
	
	public MainThreadClass() throws IOException
	{
		FileRetriever fr = new FileRetriever("SerializedFiles/exampleGame");
		URL url = fr.getUrlRelativeToProject("SerializedFiles");
		Path folder = Paths.get(url.getPath());
		FileChangeNotifier fcn = new FileChangeNotifier(folder, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
		fcn.onWatchEventTriggered(() -> {System.out.println("derp"); return "";});
		Thread t = new Thread(fcn);
		t.start();
		System.out.println("derp");
	}
	
	public static void main(String[] args) throws IOException
	{
		MainThreadClass m = new MainThreadClass();
	}
}
