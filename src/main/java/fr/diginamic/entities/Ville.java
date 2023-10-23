/** Entités */
package fr.diginamic.entities;

/** Entité Ville */
public class Ville {
	
	/** id */
	private int id; 
	
	/** nom */
	private String nom;
	
	/** region */
	private Region region;
	
	/** departement */
	private Departement departement;

	/** Constructeur
	 * @param id
	 * @param nom
	 * @param region
	 * @param departement
	 */
	public Ville(int id, String nom, Region region, Departement departement) {
		super();
		this.id = id;
		this.nom = nom;
		this.region = region;
		this.departement = departement;
	}

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", region=" + region + ", departement=" + departement + "]";
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
	 * @return region
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

	/** Getter
	 * @return departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/** Setter
	 * @param departement
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}	
}