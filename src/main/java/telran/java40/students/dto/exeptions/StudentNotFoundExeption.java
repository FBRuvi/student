package telran.java40.students.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student not found")
public class StudentNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3336129597972433635L;

}
