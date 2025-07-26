package customerService.customerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class CustomerServiceExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> customException(CustomException ce)
    {
        return new ResponseEntity<>(ce.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
