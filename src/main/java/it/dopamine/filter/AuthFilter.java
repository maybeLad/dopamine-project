package it.dopamine.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.dopamine.model.Utente;

public class AuthFilter extends HttpFilter implements Filter {

    public AuthFilter() {
        super();
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getServletPath();

        if (!path.startsWith("/admin") && !path.startsWith("/common")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        
        Utente uLoggato = (session != null) ? (Utente) session.getAttribute("utenteLoggato") : null;

        boolean autorizzato = (uLoggato != null && uLoggato.isAdmin());

        if (autorizzato) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/index");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}