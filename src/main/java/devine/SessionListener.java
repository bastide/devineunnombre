package devine;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("Compteur de sessions")
public class SessionListener implements HttpSessionListener {

    /**
     * Cette méthode est appelée automatiquement quand une session est créée 
     * @see javax.servlet.http.HttpServletRequest#getSession()
     *
     * @param event the session event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        int totalActiveSessions
                = (Integer) context.getAttribute("numberOfPlayers");
        totalActiveSessions++;
        context.setAttribute("numberOfPlayers", totalActiveSessions);
    }

    /**
     * Cette méthode est appelée automatiquement quand une session est détruite
     * @see javax.servlet.http.HttpSession#invalidate()
     *
     * @param event the session event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        int totalActiveSessions
                = (Integer) context.getAttribute("numberOfPlayers");
        totalActiveSessions--;
        context.setAttribute("numberOfPlayers", totalActiveSessions);
    }
}
