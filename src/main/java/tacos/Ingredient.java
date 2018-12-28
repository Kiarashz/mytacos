package tacos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@Entity
@Table(name="ingredients")
public class Ingredient {

	@Id
	@Column(name="iid")
	private Long id;
	private String name;
	
	private Type itype;
	
	public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE }
}
