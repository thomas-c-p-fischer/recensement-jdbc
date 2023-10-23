/** Entités */
package fr.diginamic.entities;

/** Entité Région */
public class Region {
	
	/** id */
	private int id;
	
	/** nom */
	private String nom;

	/** Constructeur
	 * @param id
	 * @param nom
	 */
	public Region(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", nom=" + nom + "]";
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
	
	
}