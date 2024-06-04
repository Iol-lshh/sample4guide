package lshh.sample4guide.common.library.log;

public class ThreadTraceHelper {
    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> SPAN_ID = new ThreadLocal<>();

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }
    public static String getTraceId() {
        return TRACE_ID.get();
    }
    public static void removeTraceId() {
        TRACE_ID.remove();
    }

    public static void setSpanId(String spanId) {
        SPAN_ID.set(spanId);
    }
    public static String getSpanId() {
        return SPAN_ID.get();
    }
    public static void removeSpanId() {
        SPAN_ID.remove();
    }
}
