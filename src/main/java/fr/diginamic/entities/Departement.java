/** Entités */
package fr.diginamic.entities;

/** Entité Departement */
public class Departement {
	
	/** id */
	private int id;
	
	/** code */
	private String code;

	/** Constructeur
	 * @param code
	 * @param region
	 */
	public Departement(String code) {
		super();
		this.code = code;
	}

	@Override
	public String toString() {
		return "Departement [id=" + id + ", code=" + code + "]";
	}

	/** Getter
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/** Setter
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
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
}