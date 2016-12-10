package authoring.model.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import authoring.model.LevelData;
import authoring.model.PlayerData;

public class GameStateDeserializer {

	public void loadGameState(String filepath) throws Exception{

		List<Object> objects = new ArrayList<Object>();
		File file = new File(filepath);
		File[] subFiles = file.listFiles();
		List<Class> classList = new ArrayList<Class>(Arrays.asList(EntityData.class, MapDataContainer.class, LevelDataContainer.class, PlayerData.class));

		JSONDeserializer jdes = new JSONDeserializer();
		for (File f : subFiles){
			File[] fFiles = f.listFiles();
			for(File fsub : fFiles){
				for (Class c:classList){
					try{
						if (fsub.toString().contains(c.getSimpleName())){
							// removes the "src/" portion
							String fp = fsub.getPath().substring(4, fsub.getPath().length());
							Object obj = jdes.deserializeFromFile(fp, c);
							objects.add(obj);
						}
					}
					catch(Exception e){
						throw new Exception("Error in loading authoring environment.");
					}
				}

			}
		}

		System.out.println(objects.size());

	}

}
