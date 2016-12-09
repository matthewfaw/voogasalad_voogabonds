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
	
	public FileChangeNotifier(Path aPath, WatchEvent.Kind<?>...aEventKinds) throws IOException
	{
		myPath = aPath;
		
		myWatchEventKindsList = new ArrayList<Kind<?>>(Arrays.asList(aEventKinds));
	}
	
	public void onFileChangeDetected(Consumer<File> aFunction)
	{
		myOnFileChangeDetectedMethod = aFunction;
	}
	
	private void processEvents()
	{
		System.out.println("Watching myPath: " + myPath);

		// We create the new WatchService using the new try() block
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

	@Override
	public void run() {
		processEvents();
	}

}
