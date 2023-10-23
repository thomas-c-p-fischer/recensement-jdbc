/** Dao */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entities.Region;

/** Dao pour l'entité Region */
public interface RegionDao {
	List<Region> extraire();
	void insert(Region region);
	boolean delete(Region region);
	boolean existe(String code);
	int extraireParNom(String nomRegion);
}