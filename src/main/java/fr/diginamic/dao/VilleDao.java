/** Dao */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entities.Ville;

/** Dao pour l'entité Ville */
public interface VilleDao {
	List<Ville> extraire();
	void insert(Ville ville,int regionId, int departementId, String codeDpt);
	boolean delete(Ville ville);
	boolean existe(String nom, String codeDpt);
}