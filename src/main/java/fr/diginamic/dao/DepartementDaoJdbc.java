/** DaoJdbc */
package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Region;

/** Classe avec les méthodes SQL */
public class DepartementDaoJdbc implements DepartementDao {
	
	/** connection */
	private Connection connection;
	
	/** Constructeur
	 * @param connection
	 */
	public DepartementDaoJdbc(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<Departement> extraire() {
		ArrayList<Departement> departements = new ArrayList<Departement>();
		PreparedStatement selectDepartement = null;
		ResultSet curseur = null;
		try {
			selectDepartement = connection.prepareStatement("SELECT * FROM DEPARTEMENT");
			curseur = selectDepartement.executeQuery();
			while(curseur.next()) {
				String code = curseur.getString("CODE");
				Departement departement = new Departement(code);
				departements.add(departement);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(curseur != null) {
					curseur.close();
				}
				if(selectDepartement != null)	{
					selectDepartement.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de requêtes ou de connection couramment établie.");
			}
		}
		return departements;
	}
	
	@Override
	public int extraireParCode(String codeDepartement) {
		int idDepartement = -1;
		PreparedStatement selectDepartement = null;
		ResultSet curseur = null;
		try {
			selectDepartement = connection.prepareStatement("SELECT ID FROM DEPARTEMENT WHERE CODE=?");
			selectDepartement.setString(1, codeDepartement);
			curseur = selectDepartement.executeQuery();
			if(curseur.next()) {
				idDepartement = curseur.getInt("ID");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(curseur != null) {
					curseur.close();
				}
				if(selectDepartement != null)	{
					selectDepartement.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de requêtes ou de connection couramment établie.");
			}
		}
		return idDepartement;
	}

	@Override
	public void insert(Departement departement, int regionId) {
		PreparedStatement insertDepartement = null;
		try {
			insertDepartement = connection.prepareStatement("INSERT INTO DEPARTEMENT (REGION_ID, CODE) VALUES (?, ?)");
			insertDepartement.setInt(1, regionId);
			insertDepartement.setString(2, departement.getCode());
			int requeteInsert = insertDepartement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(insertDepartement != null) {
					insertDepartement.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
	}

	@Override
	public boolean delete(Departement departement) {
		PreparedStatement deleteDepartement = null;
		try {
			deleteDepartement = connection.prepareStatement("DELETE FROM DEPARTEMENT WHERE ID=(?)");
			deleteDepartement.setInt(1, departement.getId());
			int requeteDelete = deleteDepartement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Connection impossible vérifiez votre URL ou votre requête.");
		} finally {
			try {
				if(deleteDepartement != null) {
					deleteDepartement.close();
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
		PreparedStatement departementExiste = null;
		ResultSet rs = null;
		try {
			departementExiste = connection.prepareStatement("SELECT COUNT(*) FROM DEPARTEMENT WHERE CODE=?");
			departementExiste.setString(1, code);
			rs = departementExiste.executeQuery();
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
				if(departementExiste != null) {
					departementExiste.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Il n'y a pas de connection couramment établie.");
			}
		}
		return false;
	}
}