package pfa.project.ForumConnect.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import pfa.project.ForumConnect.model.user;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private User user;

    private static final String secretKey = "E7B1E6BD4DF31022E2EB16E068772F7160686AE5FB1DC656F95B096AD9730D4CEE7CBC587EBF2654CD58B89A54C964793BA888E6AA9870E55344483A1C8DC7A932DE71BF657F7148357D256D40EAE41A2B5366FB6A268738B9C1BCC474816788536D3606EA2058CD5EDF689ED56C473335B0AD2E302340F77BA754D53F1282B4";


    public String extractUsername(String token) {
        String username = extractClaim(token, Claims::getSubject);
        System.out.println("Extracted User from Token: " + username);
        return username;
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("Parsing token: " + token);
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
    public String getName(UserDetails userDetails) {
        user user = (user)  userDetails;
        return user.getName();
    }

    public String generateToken(UserDetails userDetails) {
        String username = getName(userDetails);
        return generateToken(new HashMap<>(), userDetails,username);
    }


    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, String username) {

        extraClaims.put("username", username);
        extraClaims.put("userId", ((user) userDetails).getId());
        extraClaims.put("role", ((user) userDetails).getRole());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractClaims(String token) {
       var claims= Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println("Extracted Claims: " + claims);
        return claims;
    }
public String getUsername (String token){
        String usrn = (String) Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody().get("username");
        return usrn;

}
    private Key getSigningKey() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}