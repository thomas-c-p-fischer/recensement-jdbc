/** Dao */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entities.Departement;

/** Dao pour l'entité Departement */
public interface DepartementDao {
	List<Departement> extraire();
	void insert(Departement departement);
	boolean delete();
}