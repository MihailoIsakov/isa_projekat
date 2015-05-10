package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import org.apache.log4j.Logger;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.UserDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils.JsonUtility;

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
		String jsonData = JsonUtility.pullDataFromRequest(request);
		Map jsonMap = JsonUtility.json2Map(jsonData);
		System.out.println("Login attempt: " + jsonMap);

		try {
			String username = (String) jsonMap.get("username");
			String password = (String) jsonMap.get("password");


//			if ((username == null) || (username.equals("")) || (password == null) || (password.equals(""))) {
//				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
//				return;
//			}

			User user = userDao.findUserWithCredentials(username, password);

			if (user != null) {
				System.out.println("Found user: " + username);
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", user);
				log.info("Korisnik " + user.getUsername() + " se prijavio.");

                response.setContentType("application/json");
                Map<String, String> jsonResponse = new HashMap<>();
                jsonResponse.put("sid", request.getSession().getId());
                jsonResponse.put("userid", Integer.toString(user.getId()));
                jsonResponse.put("userrole", "admin");

                JsonUtility.flushJson(response, jsonResponse);
			}
            else {
                System.out.println("No such user");
            }

		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
//		} catch (ServletException e) {
//			log.error(e);
//			throw e;
//		} catch (IOException e) {
//			log.error(e);
//			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
