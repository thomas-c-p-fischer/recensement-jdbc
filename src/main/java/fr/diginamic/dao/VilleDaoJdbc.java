/** DaoJdbc */
package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entities.Region;
import fr.diginamic.entities.Ville;

/** Classe avec les méthodes SQL */
public class VilleDaoJdbc implements VilleDao {
	
	/** connection */
	private Connection connection;
	
	/** Constructeur
	 * @param connection
	 */
	public VilleDaoJdbc(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<Ville> extraire() {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		PreparedStatement selectVille = null;
		ResultSet curseur = null;
		try {
			selectVille = connection.prepareStatement("SELECT * FROM VILLE");
			curseur = selectVille.executeQuery();
			while(curseur.next()) {
				String nom = curseur.getString("NOM");
				String codeDpt = curseur.getString("CODE_DEPARTEMENT");
				Ville ville = new Ville(nom, codeDpt);
				villes.add(ville);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(curseur != null) {
					curseur.close();
				}
				if(selectVille != null)	{
					selectVille.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de requêtes ou de connection couramment établie.");
			}
		}
		return villes;
	}

	@Override
	public void insert(Ville ville,int regionId, int departementId, String codeDpt) {
		PreparedStatement insertVille = null;
		try {
			insertVille = connection.prepareStatement("INSERT INTO VILLE (NOM, REGION_ID, DEPARTEMENT_ID, CODE_DEPARTEMENT) VALUES (?, ?, ?, ?)");
			insertVille.setString(1, ville.getNom());
			insertVille.setInt(2, regionId);
			insertVille.setInt(3, departementId);
			insertVille.setString(4, codeDpt);
			int requeteInsert = insertVille.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(insertVille != null) {
					insertVille.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
	}

	@Override
	public boolean delete(Ville ville) {
		PreparedStatement deleteVille = null;
		try {
			deleteVille = connection.prepareStatement("DELETE FROM VILLE WHERE ID=(?)");
			deleteVille.setInt(1, ville.getId());
			int requeteDelete = deleteVille.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(deleteVille != null) {
					deleteVille.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		return false;
	}
	
	@Override
	public boolean existe(String nom, String codeDpt) {
		PreparedStatement villeExiste = null;
		ResultSet rs = null;
		try {
			villeExiste = connection.prepareStatement("SELECT COUNT(*) FROM VILLE WHERE NOM=? AND CODE_DEPARTEMENT=?");
			villeExiste.setString(1, nom);
			villeExiste.setString(2, codeDpt);
			rs = villeExiste.executeQuery();
			if(rs.next()) {
				int compteur = rs.getInt(1);
				return compteur > 0;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(villeExiste != null) {
					villeExiste.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		return false;
	}
}