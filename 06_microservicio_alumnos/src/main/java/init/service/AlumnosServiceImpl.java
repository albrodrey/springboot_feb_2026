package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Alumno;
import init.repository.AlumnosRepository;

@Service
public class AlumnosServiceImpl implements AlumnosService {

	
	AlumnosRepository alumnosRepository;
	
	public AlumnosServiceImpl(AlumnosRepository alumnosRepository) {
		this.alumnosRepository = alumnosRepository;
	}

	@Override
	public boolean altaAlumno(Alumno alumno) {
		if(alumnosRepository.findFirstByNombreAndCurso(alumno.getNombre(), alumno.getCurso())==null) {
			alumnosRepository.save(alumno);
			return true;
		}
		return false;
	}

	@Override
	public List<String> cursos() {
		return alumnosRepository.findAllCursos();
	}

	@Override
	public List<Alumno> alumnosCurso(String curso) {
		return alumnosRepository.findByCurso(curso);
	}

	@Override
	public Alumno eliminarAlumno(String email) {
		Alumno alumno=alumnosRepository.findFirstByEmail(email);
		if(alumno!=null) {
			alumnosRepository.deleteByEmail(email);
			
		}
		return alumno;
		
	}

	@Override
	public List<Alumno> alumnos() {
		return alumnosRepository.findAll();
	}

}
