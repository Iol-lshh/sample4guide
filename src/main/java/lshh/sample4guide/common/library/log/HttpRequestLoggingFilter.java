package lshh.sample4guide.common.library.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.common.library.clock.Clock;

import java.io.IOException;

@Slf4j
public class HttpRequestLoggingFilter implements Filter {
    private final Clock clock;

    public HttpRequestLoggingFilter(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = clock.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String traceId = request.getHeader("traceId");
        String spanId = request.getRequestId() + "_" + startTime;
        LogForm startLog = LogForm.of(traceId, spanId, String.format("""
                {
                    "requestUrl": "%s",
                    "method": "%s"
                }
                """, request.getRequestURI(), request.getMethod()));
        log.info(startLog.toString());
        try{
            servletRequest.setAttribute("spanId", spanId);
            ThreadTraceHelper.setTraceId(traceId);
            ThreadTraceHelper.setSpanId(spanId);
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            ThreadTraceHelper.removeTraceId();
            ThreadTraceHelper.removeSpanId();
            long endTime = clock.currentTimeMillis();
            LogForm endLog = LogForm.of(traceId, spanId, String.format("""
                    {
                        "duration": "%d"
                    }
                    """, endTime - startTime));
            log.info(endLog.toString());
        }
    }
}
