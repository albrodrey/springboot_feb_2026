package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;

@ComponentScan(basePackages= {"controller","inicio","service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    WebClient getClient() {
		return WebClient.create();
	}
	/*@Bean
    public Tracer openTelemetryTracer() {
        return GlobalOpenTelemetry.getTracer("traceador_personalizado");
    }*/
	//@Bean
    public Tracer tracer() {
        // Configuración del proveedor de Tracers sin acoplarlo a un backend
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .build();

        // Registrar el proveedor en el SDK global
        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .build();

        GlobalOpenTelemetry.set(openTelemetry);

        // Retornar el Tracer desde OpenTelemetry
        return openTelemetry.getTracer("default");
    }
}
