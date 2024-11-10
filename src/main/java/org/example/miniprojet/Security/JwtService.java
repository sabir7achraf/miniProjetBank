package org.example.miniprojet.Security;
import jakarta.transaction.Transactional;
import org.example.miniprojet.Entity.GrandUser;
import org.example.miniprojet.Services.UtilisateurDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class JwtService {

        @Autowired
        public  UtilisateurDetails userService;

        final String SecretKey="2722e6648a2a10ba4b772d7f6c9ad5ca9a5fb1b7791e5be35fcfcb11ddf76a7f";

        public String generate(UserDetails userDetails){
            final String barear = generateJwt(userDetails);
            return  barear;
        }
        public UserDetails loadbyUserName(String email){
            return this.userService.loadUserByUsername(email);
        }

        private String generateJwt(UserDetails userDetails) {
            GrandUser user= (GrandUser)userDetails;
            final long currentTime = System.currentTimeMillis();
            final long expirationTime =currentTime+30*60*1000;

            Map<String, Object> claims = Map.of( Claims.SUBJECT, user.getEmail() ,"role", "ROLE_"+user.getRole(),  Claims.EXPIRATION,new Date(expirationTime));

            String barear = Jwts.builder().
                    setIssuedAt(new Date(currentTime))
                    .setExpiration(new Date(expirationTime))
                    .setSubject(user.getEmail())
                    .setClaims(claims)
                    .signWith(getKey(), SignatureAlgorithm.HS256)
                    .compact();
            return barear;
        }
    public String getRoleFromToken(String token) {
        Claims claims = getAllClaims(token);
        return (String) claims.get("role");
    }

        private Key getKey() {
            byte[] decode = Decoders.BASE64.decode(SecretKey);
            return Keys.hmacShaKeyFor(decode);
        }

        public String loadUserName(String token) {
            return this.getClaim(token ,Claims::getSubject);
        }

        private <T> T getClaim(String token, Function<Claims,T> function ) {
            Claims claims=  getAllClaims(token);
            return function.apply(claims);
        }
        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return getAllClaims(token).getExpiration();
        }

        private Claims getAllClaims(String token) {
            return Jwts.parser()
                    .setSigningKey(this.getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }


    }

