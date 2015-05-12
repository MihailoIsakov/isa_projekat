package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import org.apache.log4j.Logger;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.UserDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils.RESTUtility;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7345471861052209628L;

	private static Logger log = Logger.getLogger(LoginController.class);

	@EJB
	private UserDaoLocal userDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = RESTUtility.pullDataFromRequest(request);
		Map jsonMap = RESTUtility.json2Map(jsonData);
		System.out.println("Login attempt: " + jsonMap);

        // In case the user is trying to log off
        if (jsonMap.get("logout") != null) {
            log.info("User " + request.getSession(true).getAttribute("user"));
            request.getSession(true).invalidate();
        }

		try {
			String username = (String) jsonMap.get("username");
			String password = (String) jsonMap.get("password");
			User user = userDao.findUserWithCredentials(username, password);

			if (user != null) {
				System.out.println("Found user: " + username);
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", user);
				log.info("User " + user.getUsername() + " has logged in.");

                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("sessionid", request.getSession().getId());
                jsonResponse.put("user", user);
                RESTUtility.flushJson(response, jsonResponse);
			}
            else {
                System.out.println("No such user");
                response.sendError(403);
            }

		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
