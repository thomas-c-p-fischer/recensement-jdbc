/** Entités */
package fr.diginamic.entities;

/** Entité Ville */
public class Ville {
	
	/** id */
	private int id; 
	
	/** nom */
	private String nom;
	
	private String codeDpt;

	/** Constructeur
	 * @param nom
	 */
	public Ville(String nom, String codeDpt) {
		super();
		this.nom = nom;
		this.codeDpt = codeDpt;
	}

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + "]";
	}

	/** Getter
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodeDpt() {
		return codeDpt;
	}

	public void setCodeDpt(String codeDpt) {
		this.codeDpt = codeDpt;
	}
}