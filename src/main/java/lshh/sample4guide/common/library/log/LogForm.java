package lshh.sample4guide.common.library.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;
import java.util.stream.Stream;

public class LogForm {
    private final String traceId;
    private final String spanId;
    private final String payload;

    public LogForm(String traceId, String spanId, String payload) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.payload = payload;
    }

    public static LogForm of(String traceId, String spanId, String payload) {
        return new LogForm(traceId, spanId, payload);
    }
    public static LogForm of(String traceId, String spanId, ProceedingJoinPoint joinPoint){
        String payload = String.join(",", Stream.of(joinPoint.getArgs()).map(o->{
                    try{
                        return new ObjectMapper().writeValueAsString(o);
                    } catch (Exception e){
                        return "";
                    }
                }).toList());
        return new LogForm(traceId, spanId, payload);
    }
    public static LogForm of(String traceId, String spanId, Map<String, String> args) throws JsonProcessingException {
        String payload = new ObjectMapper().writeValueAsString(args);
        return new LogForm(traceId, spanId, payload);
    }

    @Override
    public String toString(){
        return String.format("""
                {
                "traceId": "%s"
                "spanId": "%s"
                "payload": "%s"
                }
                """,
                traceId,
                spanId,
                payload);
    }
}
