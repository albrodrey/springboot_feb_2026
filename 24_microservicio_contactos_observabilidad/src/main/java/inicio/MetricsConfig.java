package inicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
@Configuration
public class MetricsConfig {
    @Bean
    Meter openTelemetryMeter() {
        return GlobalOpenTelemetry.getMeter("medidor_test");
    }
}
