package vn.neo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.neo.dto.BasicResponseDto;
import vn.neo.utils.ErrorCode;

@ControllerAdvice
@RestController
public class ApplicationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LogManager.getLogger(ApplicationResponseEntityExceptionHandler.class);

//    @Override
//    @SuppressWarnings("unused")
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
//        String message = "";
//        if (fieldErrorList.size() > 0) {
//            message = fieldErrorList.get(0).getDefaultMessage();
//        }
//        ResponseGW res = new ResponseGW();
//        res.setStatus(ErrorCodeKey.BAD_REQUEST);
//        res.setStatusMessage(message);
//        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(res);
//    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BasicResponseDto> handleBadCredentialsException(Exception ex, WebRequest request) {
        logger.error(ex);
        BasicResponseDto res = new BasicResponseDto(ErrorCode.ERR_400, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BasicResponseDto> handleAccessDeniedException(Exception ex, WebRequest request) {
        logger.error(ex);
        BasicResponseDto res = new BasicResponseDto(ErrorCode.ERR_403, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        BasicResponseDto res = new BasicResponseDto(ErrorCode.ERR_500, "Lỗi hệ thống", null);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }


}
