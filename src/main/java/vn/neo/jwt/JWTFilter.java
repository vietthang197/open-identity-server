package vn.neo.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author thanglv on 10/9/2020
 * @project NBD
 */
public class JWTFilter extends GenericFilterBean {

	private Logger logger = LogManager.getLogger(JWTFilter.class);

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private final TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	/**
	 * function validate token and permission for URI ENDPOINT and HTTP METHOD
	 *
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwt = resolveToken(httpServletRequest);
		String path = httpServletRequest.getRequestURI();
		String method = httpServletRequest.getMethod();

		// log test
		logger.info("path : {}, method: {}, token: {}", path, method, jwt);

		if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
			Authentication authentication = this.tokenProvider.getAuthentication(jwt);
			if (authentication.getPrincipal() != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
