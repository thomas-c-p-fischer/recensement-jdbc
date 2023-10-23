/** Entités */
package fr.diginamic.entities;

/** Entité Departement */
public class Departement {
	
	/** id */
	private int id;
	
	/** nom */
	private String nom;
	
	/** region */
	private Region region;

	/** Constructeur
	 * @param id
	 * @param nom
	 * @param region
	 */
	public Departement(int id, String nom, Region region) {
		super();
		this.id = id;
		this.nom = nom;
		this.region = region;
	}

	@Override
	public String toString() {
		return "Departement [id=" + id + ", nom=" + nom + ", region=" + region + "]";
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

	/** Getter
	 * @return
	 */
	public Region getRegion() {
		return region;
	}

	/** Setter
	 * @param region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}	
}