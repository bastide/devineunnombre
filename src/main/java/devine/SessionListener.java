package devine;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("Compteur de sessions")
public class SessionListener implements HttpSessionListener {

	@Override
	/**
	 * Cette méthode est appelée automatiquement quand une session est créée
	 * par @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		int totalActiveSessions =
			(Integer) context.getAttribute("numberOfPlayers");
		totalActiveSessions++;
		context.setAttribute("numberOfPlayers", totalActiveSessions);
	}

	@Override
	/**
	 * Cette méthode est appelée automatiquement quand une session est détruite
	 * par @see javax.servlet.http.HttpSession#invalidate()
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		int totalActiveSessions =
			(Integer) context.getAttribute("numberOfPlayers");
		totalActiveSessions--;
		context.setAttribute("numberOfPlayers", totalActiveSessions);
	}
}
