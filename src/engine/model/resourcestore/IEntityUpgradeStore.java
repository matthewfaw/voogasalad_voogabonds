package engine.model.resourcestore;

import java.util.List;

import authoring.model.EntityData;

/**
 * Tower upgrade store interface
 * TODO: Move to resource store packages
 *
 */
public interface IEntityUpgradeStore {
	public List<EntityData> getBaseEntitesData();
}
