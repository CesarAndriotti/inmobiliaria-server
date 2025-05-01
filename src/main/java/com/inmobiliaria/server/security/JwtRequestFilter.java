package com.inmobiliaria.server.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        
        //Obtener el token del encabezado
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        //Si el encabezado de autorización está presente y contiene el prefijo "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            
            //Extraemos el token JWT de la cabecera, eliminando el prefijo "Bearer "
            jwt = authorizationHeader.substring(7); 
            //Extraemos el nombre de usuario del token JWT
            username = jwtUtil.extractUsername(jwt);  
        }

        //Verificamos si el nombre de usuario es válido y si el contexto de seguridad aún no tiene autenticación
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            //Cargamos los detalles del usuario (roles, permisos, etc.) desde el servicio de detalles de usuario
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            //Verificamos si el token es válido para el usuario extraído
            if (jwtUtil.isTokenValid(jwt, username)) {
                
                //Si el token es válido, creamos un objeto de autenticación con los detalles del usuario
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                //Establecemos detalles adicionales del objeto de autenticación como la IP de la solicitud
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                //Colocamos el objeto de autenticación en el contexto de seguridad
                //Esto indica que el usuario ha sido autenticado para esta solicitud
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        //Continuamos con la cadena de filtros para que la solicitud siga siendo procesada
        chain.doFilter(request, response);
    }
}
