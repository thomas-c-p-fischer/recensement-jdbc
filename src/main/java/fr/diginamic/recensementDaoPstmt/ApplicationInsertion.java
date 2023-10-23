/** package où se trouve la classe Application */
package fr.diginamic.recensementDaoPstmt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.dao.DepartementDaoJdbc;
import fr.diginamic.dao.RegionDaoJdbc;
import fr.diginamic.dao.VilleDaoJdbc;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Region;
import fr.diginamic.entities.Ville;

/** Classe permettant l'éxecution du programme */
public class ApplicationInsertion {

	/** Méthode main
	 * @param args
	 * @author thomas.fischer
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		
		long start = System.currentTimeMillis();
		// J'initialise ma connection à la bdd
		Connection connection = null;
		
		try {
			DriverManager.registerDriver(new Driver());
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/recensement", "root", "");
			
			// J'inntialise mes 3 Dao
			RegionDaoJdbc regionDao = new RegionDaoJdbc(connection);
			DepartementDaoJdbc departementDao = new DepartementDaoJdbc(connection);
			VilleDaoJdbc villeDao = new VilleDaoJdbc(connection);
			
			// J'indique à l'application où se trouve le fichier
			Path fichierRecensement = Paths.get("C:/recensement.csv"); 
			
			// Je vérifie que le fichier est trouvé
			boolean existe = Files.exists(fichierRecensement);
			
			// J'affiche true sinon c'est que le fichier n'a pas été trouvé
			System.out.println(existe);
			
			// Je lis le fichier
			List<String> lignes = Files.readAllLines(fichierRecensement, StandardCharsets.UTF_8);
			
			// Suppression des entêtes de colones
			lignes.remove(0);
			
			// Je parcours le fichier pour l'insertion des Régions et Départements
			for (String ligne : lignes) {
				String[] tokens = ligne.split(";");
				String codeRegion = tokens[0];
				String nomRegion = tokens[1];
				String codeDepartement = tokens[2];
				Region region = new Region(nomRegion, codeRegion);
				// Je vérifie si la région existe déjà ou pas et si la région n'existe pas je l'insère en BDD
				if (!regionDao.existe(codeRegion)) {
					regionDao.insert(region);					
				}
				int idRegion = regionDao.extraireParNom(nomRegion);
				region.setId(idRegion);
				Departement departement = new Departement(codeDepartement);
				if (!departementDao.existe(codeDepartement)) {
					departementDao.insert(departement, region.getId());
				}
				departement.setId(departementDao.extraireParCode(codeDepartement));
			}
			// Je parcours le fichier pour l'insertion des villes
			for (String ligne : lignes) {
				String[] tokens = ligne.split(";");
				String nomRegion = tokens[1];
				String codeDepartement = tokens[2];
				String nomVille = tokens[6];
				Ville ville = new Ville(nomVille, codeDepartement);
				if (!villeDao.existe(nomVille, codeDepartement)) {
					villeDao.insert(ville, regionDao.extraireParNom(nomRegion), departementDao.extraireParCode(codeDepartement), codeDepartement);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL.");
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}	
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		System.out.println(start);
	}
}