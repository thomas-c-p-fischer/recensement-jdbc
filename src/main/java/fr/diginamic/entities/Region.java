/** Entités */
package fr.diginamic.entities;

/** Entité Région */
public class Region {
	
	/** id */
	private int id;
	
	/** nom */
	private String nom;
	
	/** code */
	private String code;

	/** Constructeur
	 * @param nom
	 */
	public Region(String nom, String code) {
		super();
		this.nom = nom;
		this.code = code;
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

	/** Setter
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/** Getter
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/** Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}