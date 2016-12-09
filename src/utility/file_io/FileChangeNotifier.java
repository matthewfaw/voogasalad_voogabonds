package utility.file_io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.function.Supplier;

import com.sun.org.apache.xpath.internal.functions.Function;

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
 * 
 * To resolve the absolute file path on the change event:
 * http://stackoverflow.com/questions/7801662/java-nio-file-watchevent-gives-me-only-relative-path-how-can-i-get-the-absolute
 * @author matthewfaw
 *
 */
public class FileChangeNotifier implements Runnable {
	
	private Path myPath;
	private WatchEvent.Kind<?>[] myWatchEventKinds;
	private Supplier<?> myOnWatchEventTrigger;
	
	public FileChangeNotifier(Path aPath, WatchEvent.Kind<?>...aEventKinds) throws IOException
	{
		myPath = aPath;
		
		myWatchEventKinds = aEventKinds;
	}
	
	public void onWatchEventTriggered(Supplier<?> aFunction)
	{
		myOnWatchEventTrigger = aFunction;
	}
	
	private void processEvents()
	{
		System.out.println("Watching myPath: " + myPath);

		// We create the new WatchService using the new try() block
		try(WatchService service = myPath.getFileSystem().newWatchService()) {

			registerWatchServiceWithEntireFileTree(service, myPath);

			// Start the infinite polling loop
			while(true) {
				WatchKey key = service.take();

				for(WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event
					Kind<?> kind = watchEvent.kind();
					for(int i=0; i<myWatchEventKinds.length; ++i) {
						WatchEvent.Kind<?> specifiedKind = myWatchEventKinds[i];
						if (specifiedKind == kind) {
							Path newPath = ((WatchEvent<Path>) watchEvent).context();
							Path dir = (Path)key.watchable();
							System.out.println("Path change detected: " + dir.resolve(newPath));
							if (newPath.toFile().isFile()) {
								Scanner s = new Scanner(newPath.toFile());
								while(s.hasNextLine()) {
									System.out.println(s.nextLine());
								}
								myOnWatchEventTrigger.get();
								break;
							}
						}
					}
//					if (StandardWatchEventKinds.OVERFLOW == kind) {
//						continue; //loop
//					} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
//						// A new Path was created 
//						Path newPath = ((WatchEvent<Path>) watchEvent).context();
//						// Output
//						System.out.println("New myPath created: " + newPath);
//					} else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
//						Path newPath = ((WatchEvent<Path>) watchEvent).context();
//						System.out.println("myPath modified: " + newPath);
//					}
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
	
	
	private void registerWatchServiceWithEntireFileTree(WatchService aWatchService, Path aPath) throws IOException
	{
		Files.walkFileTree(aPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				dir.register(aWatchService, myWatchEventKinds);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	@Override
	public void run() {
		processEvents();
	}

}
