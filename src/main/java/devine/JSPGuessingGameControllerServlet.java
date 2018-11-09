package devine;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "devineUnNombre", urlPatterns = {"/devineUnNombre"})
public class JSPGuessingGameControllerServlet extends HttpServlet {

	private static final int MAX = 100;
	// Un générateur aléatoire
	Random generator = new Random();

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (actionIs(request, "Rejouer")) {
			startNewGame(session, request, response);
		} else if (actionIs(request, "Déconnexion")) {
			endGame(session, request, response);
		} else if (actionIs(request, "Connexion")) {
			String playerName = request.getParameter("playerName");
			session.setAttribute("playerName", playerName);
			startNewGame(session, request, response);
		} else if (actionIs(request, "Deviner")) {
			guessNumber(session, request, response);
		} else if (null == session.getAttribute("playerName")) {
			showView("unknownPlayer.jsp", request, response);
		}
	}

	private boolean actionIs(HttpServletRequest request, String action) {
		return action.equals(request.getParameter("action"));
	}

	private void guessNumber(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int guess = Integer.parseInt(request.getParameter("guess"));
		int target = (Integer) session.getAttribute("target");
		int attempts = (Integer) session.getAttribute("attempts");
		String playerName = (String) session.getAttribute("playerName");
		session.setAttribute("attempts", ++attempts);
		if (guess == target) { // Gagné !
			updateBestScore(playerName, attempts);
			showView("winGame.jsp", request, response);
		} else {
			request.setAttribute("message", (guess > target) ? "trop haut" : "trop bas");
			showView("guessNumber.jsp", request, response);
		}
	}

	private void updateBestScore(String playerName, int attempts) {
		ServletContext context = getServletContext();
		if (context.getAttribute("bestPlayer") != null) {
			int bestScore = (Integer) context.getAttribute("bestScore");
			if (attempts < bestScore) {
				context.setAttribute("bestPlayer", playerName);
				context.setAttribute("bestScore", attempts);
			}
		} else {
			context.setAttribute("bestPlayer", playerName);
			context.setAttribute("bestScore", attempts);
		}
	}

	private void endGame(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
		showView("unknownPlayer.jsp", request, response);
	}

	private void startNewGame(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.setAttribute("target", generator.nextInt(MAX + 1));
		session.setAttribute("attempts", 0);
		showView("guessNumber.jsp", request, response);
	}

	private void showView(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletConfig().getServletContext().getRequestDispatcher("/views/" + jsp).forward(request, response);
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
