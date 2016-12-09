package utility.file_io;

import java.io.File;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.sun.org.apache.xpath.internal.functions.Function;

/**
 * The purpose of this class is to provide a means of detecting file changes.
 * In order to be useful, the processEvents method must be able to run on its
 * own thread.  Thus, this class implements the Runnable interface
 * 
 * Note that the FileChangeNotifier monitors the root folder and all child folders
 * 
 * One may use this class in the following way:
 * 
 * First, import the type of watch events you would like to use:
 * import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
 * import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
 * import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
 * 
 * Next, choose the root file path to monitor
 * Path filePath = "...";
 * FileChangeNotifier fileChangeNotifier = new FileChangeNotifier(filePath, ENTRY_CREATE);
 * or
 * FileChangeNotifier fileChangeNotifier = new FileChangeNotifier(filePath, ENTRY_CREATE, ENTRY_MODIFY);
 * or any other combination of watch events
 * 
 * If you want a function to be called every time a file change is detected,
 * fileChangeNotifier.onFileChangeDetected(file -> doStuff(file));
 * where doStuff is any method that takes a File as its input, and returns null
 * 
 * In order for this method to be useful, one should run this the FileChangeNotifier on its own thread.
 * To do this:
 * Create a thread with the fileChangeNotifier
 * Thread t = new Thread(fileChangeNotifier);
 * Start the thread
 * t.start();
 * One may stop this thread by calling
 * t.stop();
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
	private List<Kind<?>> myWatchEventKindsList;
	private Consumer<File> myOnFileChangeDetectedMethod;
	
	public FileChangeNotifier(String aPath, Kind<?>...aEventKinds) throws IllegalArgumentException
	{
		File path = new File(aPath);
		if (!path.isDirectory()) {
			throw new IllegalArgumentException("The specified path must be a directory");
		}
		myPath = path.toPath();
		
		myWatchEventKindsList = new ArrayList<Kind<?>>(Arrays.asList(aEventKinds));
		
		myOnFileChangeDetectedMethod = (file -> System.out.println("Change detected in: "+file.getAbsolutePath()));
	}
	
	/**
	 * This method sets up the function to be performed when a file change is detected
	 * This function takes a File as an input, and returns void.
	 * An example usage:
	 * fileChangeNotifier.onFileChangeDetected(file -> System.out.println(file));
	 * @param aFunction the function to be called when a File change is detected
	 */
	public void onFileChangeDetected(Consumer<File> aFunction)
	{
		myOnFileChangeDetectedMethod = aFunction;
	}

	/**
	 * This method runs the FileChangeNotifier
	 * Note that this method runs indefinitely, and thus should be run on its own thread
	 * 
	 * In order to run this method on its own thread, 
	 * assuming the FileChangeNotifier has been constructed, one may do the following:
	 * 
	 * Thread thread = new Thread(fileChangeNotifier);
	 * thread.start();
	 * 
	 */
	@Override
	public void run() 
	{
		try(WatchService service = myPath.getFileSystem().newWatchService()) {

			registerWatchServiceWithEntireFileTree(service, myPath);
			
			pollForFileChange(service);

		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void pollForFileChange(WatchService aWatchService) throws InterruptedException
	{
		while(true) {
			WatchKey key = aWatchService.take();
			
			for(WatchEvent<?> watchEvent : key.pollEvents()) {
				Kind<?> polledEventKind = watchEvent.kind();
				
				for (Kind<?> specifiedKind: myWatchEventKindsList) {
					handleWatchEvent(specifiedKind, polledEventKind, key, watchEvent);
				}
			}

			if(!key.reset()) {
				break; //loop
			}
		}
	}
	
	private void handleWatchEvent(Kind<?> aUserSpecifiedKind, Kind<?> aPollEventKind, WatchKey aKey, WatchEvent<?> aWatchEvent)
	{
		Path absolutePath = getAbsolutePath(aKey, aWatchEvent);
		if (aUserSpecifiedKind == aPollEventKind && absolutePath.toFile().isFile()) {
			myOnFileChangeDetectedMethod.accept(absolutePath.toFile());
		}
	}
	
	private Path getAbsolutePath(WatchKey aKey, WatchEvent<?> aWatchEvent)
	{
		Path baseDirectory = (Path)aKey.watchable();
		Path newPath = ((WatchEvent<Path>) aWatchEvent).context();

		return baseDirectory.resolve(newPath);
	}
	private void registerWatchServiceWithEntireFileTree(WatchService aWatchService, Path aPath) throws IOException
	{
		Files.walkFileTree(aPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				dir.register(aWatchService, myWatchEventKindsList.toArray(new Kind<?>[myWatchEventKindsList.size()]));
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
