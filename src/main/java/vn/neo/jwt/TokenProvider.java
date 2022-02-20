package vn.neo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.neo.obj.Users;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author thanglv on 5/24/2021
 * @project webapp
 */
@Component
public class TokenProvider {

    private final Logger logger = LogManager.getLogger(TokenProvider.class);
    private static final String SECRET = "24jlrkweuoYIUOI#@%$#&&^^aafsdefajjfklui78953gg75789iothjkp9sdfxcbyeuyYJLKJGY798tr67538*^#%^&%^$&*hfjhajkdfhiuy423rwiurhj567542346^^^jkerjwiry67^$*&#^*($hjkdahfnjasydf5674893";

    private static final String USERNAME_KEY = "username";
    private long tokenValidityInMilliseconds;
    private final Algorithm algorithm;

    public TokenProvider() {
        algorithm = Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    @PostConstruct
    public void init() {
        this.tokenValidityInMilliseconds = 8640000 * 6; // 12 tieng
    }

    /**
     * function generate token from username
     *
     * @param authentication
     * @return
     */
    public String createToken(Authentication authentication) throws JsonProcessingException {
        long now = System.currentTimeMillis();
        Users usersInfo = (Users) authentication.getPrincipal();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);
        return JWT.create()
                .withClaim(USERNAME_KEY, usersInfo.getUsername())
                .withIssuedAt(new Date(now))
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    /**
     * function get information from token
     *
     * @param token
     * @param path
     * @param method
     * @return
     */
    public Authentication getAuthentication(String token, String path, String method) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        Set<CustomGrantedAuthority> authorities = Arrays.stream(String.valueOf(claims.get(TokenProvider.AUTHORITIES_KEY)).split(",")).map(CustomGrantedAuthority::new).collect(Collectors.toSet());
//        Users principal = new Users(claims, claims.getSubject(), "", authorities);
//
//        if (claims.getSubject() == null) { // thang sua o day
//            return new UsernamePasswordAuthenticationToken(null, token, authorities);
//        }
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        return null;
    }

    /**
     * function validate token
     *
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            logger.info("Invalid JWT token.");
//        }
        return false;
    }
}
