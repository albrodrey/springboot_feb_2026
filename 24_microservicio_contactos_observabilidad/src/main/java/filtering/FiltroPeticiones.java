package filtering;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MetricService;

@Component
public class FiltroPeticiones extends OncePerRequestFilter{
	private MetricService metricService;
	

	public FiltroPeticiones(MetricService metricService) {
		super();
		this.metricService = metricService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(request, response);
		metricService.incrementar();
		
	}
	
}
