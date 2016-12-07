package utility.file_io;

import java.nio.file.LinkOption;
import java.nio.file.StandardWatchEventKinds;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * adapted from 
 * http://andreinc.net/2013/12/06/java-7-nio-2-tutorial-writing-a-simple-filefolder-monitor-using-the-watch-service-api/
 * for the basic implementation of file change notification
 * 
 * To examine the entire tree:
 * http://stackoverflow.com/questions/18701242/how-to-watch-a-folder-and-subfolders-for-changes
 * @author matthewfaw
 *
 */
public class FileChangeNotifier implements Runnable {
	
	private WatchService watcher;
	private Path myPath;
	
	public FileChangeNotifier(Path aPath) throws IOException
	{
		watcher = FileSystems.getDefault().newWatchService();
		myPath = aPath;
	}
	
	private void processEvents()
	{
		// Sanity check - Check if myPath is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(myPath,
					"basic:isDirectory", LinkOption.NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + myPath + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}

		System.out.println("Watching myPath: " + myPath);

		// We obtain the file system of the Path
		FileSystem fs = myPath.getFileSystem ();

		// We create the new WatchService using the new try() block
		try(WatchService service = fs.newWatchService()) {

			// We register the myPath to the service
			// We watch for creation events
//			myPath.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
			Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() {
		        @Override
		        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		            dir.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		            return FileVisitResult.CONTINUE;
		        }
		    });

			// Start the infinite polling loop
			WatchKey key = null;
			while(true) {
				key = service.take();

				// Dequeueing events
				Kind<?> kind = null;
				for(WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event
					kind = watchEvent.kind();
					if (StandardWatchEventKinds.OVERFLOW == kind) {
						continue; //loop
					} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
						// A new Path was created 
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						// Output
						System.out.println("New myPath created: " + newPath);
					} else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("myPath modified: " + newPath);
					}
				}

				if(!key.reset()) {
					break; //loop
				}
			}

		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args)
	{
		FileChangeNotifier f;
		try {
			f = new FileChangeNotifier();
			FileRetriever fr = new FileRetriever("SerializedFiles/exampleGame");
			URL url = fr.getUrlRelativeToProject("SerializedFiles");
			Path folder = Paths.get(url.getPath());
			f.processEvents(folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

	@Override
	public void run() {
		processEvents();
	}

}
