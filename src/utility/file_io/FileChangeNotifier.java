package utility.file_io;

import java.nio.file.StandardWatchEventKinds;
import java.io.IOException;
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
 * The purpose of this class is to provide a means of detecting file changes.
 * In order to be useful, the processEvents method must be able to run on its
 * own thread.  Thus, this class implements the Runnable interface
 * 
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
	
	private Path myPath;
	private WatchEvent.Kind<?>[] myWatchEventKinds;
	
	public FileChangeNotifier(Path aPath, WatchEvent.Kind<?>...aEventKinds) throws IOException
	{
		myPath = aPath;
		
		myWatchEventKinds = aEventKinds;
	}
	
	private void processEvents()
	{
		System.out.println("Watching myPath: " + myPath);

		// We create the new WatchService using the new try() block
		try(WatchService service = myPath.getFileSystem().newWatchService()) {

			Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() {
		        @Override
		        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		            dir.register(service, myWatchEventKinds);
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

	@Override
	public void run() {
		processEvents();
	}

}
