/** DaoJdbc */
package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entities.Region;

/** Classe avec les méthodes SQL */
public class RegionDaoJdbc implements RegionDao {
	
	/** connection */
	private Connection connection;
	
	/** Constructeur
	 * @param connection
	 */
	public RegionDaoJdbc(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<Region> extraire() {
		ArrayList<Region> regions = new ArrayList<Region>();
		PreparedStatement selectRegion = null;
		ResultSet curseur = null;
		try {
			selectRegion = connection.prepareStatement("SELECT * FROM REGION");
			curseur = selectRegion.executeQuery();
			while(curseur.next()) {
				String nom = curseur.getString("NOM");
				String code = curseur.getString("CODE");
				Region region = new Region(nom, code);
				regions.add(region);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(curseur != null) {
					curseur.close();
				}
				if(selectRegion != null)	{
					selectRegion.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de requêtes ou de connection couramment établie.");
			}
		}
		return regions;
	}
	
	@Override
	public int extraireParNom(String nomRegion) {
		int idRegion = -1;
		PreparedStatement selectRegion = null;
		ResultSet curseur = null;
		try {
			selectRegion = connection.prepareStatement("SELECT ID FROM REGION WHERE NOM=?");
			selectRegion.setString(1, nomRegion);
			curseur = selectRegion.executeQuery();
			if(curseur.next()) {
				idRegion = curseur.getInt("ID");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(curseur != null) {
					curseur.close();
				}
				if(selectRegion != null)	{
					selectRegion.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de requêtes ou de connection couramment établie.");
			}
		}
		return idRegion;
	}

	@Override
	public void insert(Region region) {
		PreparedStatement insertRegion = null;
		try {
			insertRegion = connection.prepareStatement("INSERT INTO REGION (NOM, CODE) VALUES (?, ?)");
			insertRegion.setString(1, region.getNom());
			insertRegion.setString(2, region.getCode());
			int requeteInsert = insertRegion.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(insertRegion != null) {
					insertRegion.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
	}

	@Override
	public boolean delete(Region region) {
		PreparedStatement deleteRegion = null;
		try {
			deleteRegion = connection.prepareStatement("DELETE FROM REGION WHERE ID=(?)");
			deleteRegion.setInt(1, region.getId());
			int requeteDelete = deleteRegion.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(deleteRegion != null) {
					deleteRegion.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		return false;
	}
	
	@Override
	public boolean existe(String code) {
		PreparedStatement regionExiste = null;
		ResultSet rs = null;
		try {
			regionExiste = connection.prepareStatement("SELECT COUNT(*) FROM REGION WHERE CODE=?");
			regionExiste.setString(1, code);
			rs = regionExiste.executeQuery();
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
				if(regionExiste != null) {
					regionExiste.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		return false;
	}
}