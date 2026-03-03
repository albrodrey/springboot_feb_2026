package init.model;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class EdicionPk {
	private String codigoCurso;
	private Integer dia;
	public EdicionPk(String codigoCurso, Integer dia) {
		super();
		this.codigoCurso = codigoCurso;
		this.dia = dia;
	}
	public EdicionPk() {
		super();
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoCurso, dia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdicionPk other = (EdicionPk) obj;
		return Objects.equals(codigoCurso, other.codigoCurso) && Objects.equals(dia, other.dia);
	}
	
}
