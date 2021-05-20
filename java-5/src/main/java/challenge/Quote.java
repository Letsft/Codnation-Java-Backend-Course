package challenge;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scripts")
@EntityListeners(AuditingEntityListener.class)
public class Quote {

	public Quote(Integer id, String actor, String quote) {
		this.id = id;
		this.actor = actor;
		this.quote = quote;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotNull
	private Integer episode;

	@Column
	@NotNull
	private String episodeName;

	@Column
	@NotNull
	private String segment;

	@Column
	private String type;

	@Column
	@NotNull
	private String actor;

	@Column
	@NotNull
	private String character;

	@Column(name = "detail")
	private String quote;

	@Column
	@NotNull
	private Timestamp recordDate;

	@Column
	@NotNull
	private String series;

	@Column
	@NotNull
	private Timestamp transmissionDate;

}
