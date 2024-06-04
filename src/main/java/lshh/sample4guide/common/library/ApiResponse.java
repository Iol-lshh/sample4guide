package lshh.sample4guide.common.library;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    T payload;
    HttpStatus status;

    protected static <T> ApiResponse<T> of(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.payload = data;
        return response;
    }

    public static ApiResponse<?> ok() {
        ApiResponse<?> response = new ApiResponse<>();
        response.status = HttpStatus.OK;
        return response;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = ApiResponse.of(data);
        response.status = HttpStatus.OK;
        return response;
    }

    public static <T> ApiResponse<T> badRequest(T data) {
        ApiResponse<T> response = ApiResponse.of(data);
        response.status = HttpStatus.BAD_REQUEST;
        return response;
    }
}
