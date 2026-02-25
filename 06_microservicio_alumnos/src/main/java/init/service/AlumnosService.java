package init.service;

import java.util.List;

import init.model.Alumno;

public interface AlumnosService {
	boolean altaAlumno(Alumno alumno);
	List<Alumno> alumnos();
	
	List<String> cursos();
	List<Alumno> alumnosCurso(String curso);
	Alumno eliminarAlumno(String email);
}
