package mainmenu.screens;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Christopher Lu
 * Creates the MenuTableItem class that are the objects that will show up in the table
 * allowing users to select which project to start.
 */

public class MenuTableItem {

	private SimpleStringProperty projectName;
	private long modifiedDate;
	
	public MenuTableItem(String pName, long pDate) {
		this.projectName = new SimpleStringProperty(pName);
		this.modifiedDate = pDate;
	}
	
	public String getProjectName() {
		return projectName.get();
	}
	
	public void setProjectName(String newProjectName) {
		projectName.set(newProjectName);
	}
	
	public long getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(long newDate) {
		modifiedDate = newDate;
	}
	
}
