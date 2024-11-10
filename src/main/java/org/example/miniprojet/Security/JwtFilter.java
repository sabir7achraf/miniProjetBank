
package org.example.miniprojet.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email = null;
        String token = null;

        // Récupérer le jeton JWT de l'entête Authorization
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Extraire le token après "Bearer "
            email = jwtService.loadUserName(token);  // Charger l'email à partir du jeton
        }

        // Si l'email est trouvé et qu'il n'y a pas encore d'authentification dans le contexte
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Charger les détails de l'utilisateur à partir du service JWT
            UserDetails userDetails = jwtService.loadbyUserName(email);

            // Extraire le rôle depuis le jeton JWT
            String role = jwtService.getRoleFromToken(token);  // Assurez-vous que cette méthode retourne le rôle (par exemple "USER_EMPLOYE")

            // Créer les autorités à partir du rôle (ajouter "ROLE_" si nécessaire)
            Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(role));

            // Créer un token d'authentification avec les détails de l'utilisateur et les rôles
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            // Mettre à jour le contexte de sécurité avec l'authentification
            logger.info("Email from token: " + email);
            logger.info("Role from token: " + role);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Passer à la requête suivante dans le filtre de la chaîne
        filterChain.doFilter(request, response);
    }
}