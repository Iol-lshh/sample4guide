package lshh.sample4guide.controller.demo;

import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.common.library.lock.AdvisoryLockException;
import lshh.sample4guide.common.library.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "lshh.sample4guide.controller.demo")
public class DemoControllerAdvice {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn("IllegalArgumentException: {}", exception.getMessage());
        return ApiResponse.badRequest(exception.getMessage());
    }

    @ExceptionHandler(value = AdvisoryLockException.class)
    public ApiResponse<?> handleAdvisoryLockException(AdvisoryLockException exception) {
        log.warn("AdvisoryLockException: {}", exception.getMessage());
        return ApiResponse.badRequest("time out");
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResponse<?> handleException(Exception exception) {
        log.error("Internal Server Error: {}", exception.getMessage());
        return ApiResponse.badRequest("Internal Server Error");
    }

}
