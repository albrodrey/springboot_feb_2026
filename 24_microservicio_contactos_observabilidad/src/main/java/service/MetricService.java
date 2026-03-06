package service;

import org.springframework.stereotype.Component;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;

@Component
public class MetricService {
	 private final LongCounter contador;
	 public MetricService(Meter meter) {
		 this.contador=meter
				 .counterBuilder("contador_peticiones")
				 .setUnit("request")
				 .build();
	 }
	 public void incrementar() {
		 contador.add(1);
	 }
	 
}
