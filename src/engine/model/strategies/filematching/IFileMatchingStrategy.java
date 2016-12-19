package engine.model.strategies.filematching;

import java.io.File;

public interface IFileMatchingStrategy<T> {
	public boolean matches(T aT, File aFile);
}
