package init.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno,Integer>{	
	/*
	 Heredamos:
	 Alumno save(Alumno alumno)
	 y
	 List<Alumno> findAll()
	 */
	
	List<Alumno> findByCurso(String curso);
	
	Optional<Alumno> findFirstByNombreAndCurso(String nombre, String curso);
	boolean existsByNombreAndCurso(String nombre, String curso);
	
	@Query("select distinct(a.curso) from Alumno a")
	//@Query(value="select distinct(curso) from alumnos",nativeQuery = true)
	List<String> findAllCursos();
	
	
	
	Optional<Alumno> findFirstByEmail(String email);
	@Modifying
	@Transactional
	void deleteByEmail(String email);

	
}
