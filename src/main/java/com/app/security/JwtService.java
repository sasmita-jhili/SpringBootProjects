package com.app.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	
	private static final String SECRET = "cY65taat9MtH2UR8/t9FApvYoQochuiy28dZkKjkYKC5vCmVNmCaOswaIPqkJK9m7O3M7ea/jBM1yWZP5CShdPXpyu+JIm1qwIcMrc3rnYsm/5jWabLGRmrSc17at1uQvtXuMot04AFD6Y0a4Xc81T4SBC6EFSupXGbMgN6lnAEutxiB5wi31ETDK72KinhoBQo9sNWHC4gG/LFw7JOCx6u3+8jL/v70FIXlUa+mU+X0frR3XB8nBwBaeN8rHE58cg/zW+jV3uPIlEZ5uirqw1YpzUd0csx9Xbb+w22C13D5W8mv8DohMGMBc+qwcO7nIZZR+MAQATbPrIE/gKQQa+lxm3i0xYT53XCIRPb+pOE=\r\n"
			+ "";
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	public String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}
	private Key getSignKey() {
		byte[] keyBItes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBItes);
	}
}
