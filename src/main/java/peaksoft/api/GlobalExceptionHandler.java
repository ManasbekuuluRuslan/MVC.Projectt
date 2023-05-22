package peaksoft.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import peaksoft.exception.MyException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public String handleGlobalException() {
        return "error1";
    }
//    @ExceptionHandler(MyException.class)
//    public String notNull(MyException e,Model model) {
//        model.addAttribute("errorMessage",e.getMessage());
//        return "error4";
//    }

    @ExceptionHandler(MyException.class)
    public String handleAgencyNameExistsException(MyException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error2";
    }
}
