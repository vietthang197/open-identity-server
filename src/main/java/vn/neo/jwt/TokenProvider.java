package vn.neo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.neo.obj.Users;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
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
    private static final String FULLNAME_KEY = "full_name";
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
                .withClaim(FULLNAME_KEY, usersInfo.getFullName())
                .withIssuedAt(new Date(now))
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    /**
     * function get information from token
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaims().get(USERNAME_KEY).asString();
        String fullName = decodedJWT.getClaims().get(FULLNAME_KEY).asString();
        Users users = new Users(username, "", Collections.emptyList());
        users.setFullName(fullName);
        return new UsernamePasswordAuthenticationToken(users, token, Collections.emptyList());
    }

    /**
     * function validate token
     *
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(authToken);
            return true;
        } catch (JWTVerificationException e) {
            logger.info("Invalid JWT token.");
        }
        return false;
    }
}
