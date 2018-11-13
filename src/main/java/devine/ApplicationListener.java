package devine;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Initialisation du nombre de joueurs")
public class ApplicationListener implements ServletContextListener {

	@Override
	// Cette méthode est appelée automatiquement au démarrage de l'application
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();		
		context.setAttribute("numberOfPlayers", 0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
