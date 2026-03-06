package service;

import org.springframework.stereotype.Service;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@Service
public class TestSpanImpl implements TestService {
	Tracer tracer;
	public TestSpanImpl(OpenTelemetry openTelemetry) {
		this.tracer = GlobalOpenTelemetry.getTracer("com.example.myapp");
	}
	@Override
	public void testSpan() {
		Span span = tracer.spanBuilder("spanPersonalizado")
				//.setSpanKind(SpanKind.INTERNAL)
				.startSpan();
		try(Scope scope = span.makeCurrent();){
			span.setAttribute("custom.attribute", "value");
            span.setAttribute("another.attribute", 42);
			span.addEvent("se va a crear una persona");
			span.addEvent("se ha creado una persona");
		}
		finally {
			span.end();
		}
	}

}
