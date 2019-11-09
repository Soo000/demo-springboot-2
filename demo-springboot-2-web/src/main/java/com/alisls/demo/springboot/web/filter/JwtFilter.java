package com.alisls.demo.springboot.web.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtFilter extends GenericFilter {

	private static final long serialVersionUID = 4668127295306489000L;
	
	private static final String secret = "1q2w3e4r5t6y";
    Algorithm algHMAC256 = Algorithm.HMAC256(secret);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        // Get authorization from Http request
        final String authHeader = request.getHeader("authorization");
        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else { // Except OPTIONS, other request should be checked by JWT
            // Check the authorization"
            if (authHeader == null || authHeader.length() <= 0) {
                throw new ServletException("Missing or invalid Authorization header");
            }
            // Then get the JWT token from authorization
            final String token = authHeader;
            try {
                // Use JWT parser to check if the signature is valid with the Key "secretkey"
                /*final Claims claims = Jwts.parser()
                        .setSigningKey("1q2w3e4r5t6y")
                        .parseClaimsJws(token)
                        .getBody();*/
                // Add the claim to request header
                //request.setAttribute("claims", claims);

                JWTVerifier verifier = JWT.require(algHMAC256)
                        .withIssuer("Service")
                        .build(); //Reusable verifier instance

                DecodedJWT jwt = verifier.verify(token);
                String subject = jwt.getSubject();
                System.out.println(subject);

                Map<String, Claim> claims = jwt.getClaims();
                Claim claim = claims.get("loginName");
                System.out.println(claim.asString());

                List<String> audience = jwt.getAudience();
                System.out.println(audience.get(0));
            } catch (Exception e) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }

    }

}
