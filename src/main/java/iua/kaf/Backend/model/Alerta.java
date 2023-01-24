package iua.kaf.Backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alertas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = true)
	private Detalle detalle;
	
	@Column(nullable = true)
	private float tempUmbral;
	
	@Column(nullable = true)
	private String user;
	
	@Column(nullable = true)
	private Date fechaAcep;
	
	@Column(nullable = true)
	private Boolean acep = false;
}