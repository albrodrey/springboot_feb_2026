package init.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ediciones")
public class Edicion {
	@EmbeddedId
	private EdicionPk id;
	private int totalAlumnos;
	private String horario;
	public Edicion(EdicionPk id, int totalAlumnos, String horario) {
		super();
		this.id = id;
		this.totalAlumnos = totalAlumnos;
		this.horario = horario;
	}
	public Edicion() {
		super();
	}
	public EdicionPk getId() {
		return id;
	}
	public void setId(EdicionPk id) {
		this.id = id;
	}
	public int getTotalAlumnos() {
		return totalAlumnos;
	}
	public void setTotalAlumnos(int totalAlumnos) {
		this.totalAlumnos = totalAlumnos;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
