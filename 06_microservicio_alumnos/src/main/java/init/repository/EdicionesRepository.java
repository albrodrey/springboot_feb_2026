package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Edicion;
import init.model.EdicionPk;

public interface EdicionesRepository extends JpaRepository<Edicion, EdicionPk> {
	List<Edicion> findByIdCodigoCurso (String codigoCurso);
	@Modifying
	@Transactional
	//@Query("delete from Edicion e where e.id.dia=?1")
	void deleteByIdDia(int dia);
}
