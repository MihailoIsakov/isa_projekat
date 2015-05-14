package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BuyerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils.RESTUtility;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zieghailo on 5/14/15.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @EJB
    private BuyerDaoLocal buyerDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = RESTUtility.pullDataFromRequest(request);
        Map jsonMap = RESTUtility.json2Map(jsonData);

        if (jsonMap.containsKey("username") &&
            jsonMap.containsKey("firstName") &&
            jsonMap.containsKey("lastName") &&
            jsonMap.containsKey("password") &&
            jsonMap.containsKey("phoneNumber") &&
            jsonMap.containsKey("address") &&
            jsonMap.containsKey("email")) {

            String firstName   = (String) jsonMap.get("firstName");
            String lastName    = (String) jsonMap.get("lastName");
            String username    = (String) jsonMap.get("username");
            String password    = (String) jsonMap.get("password");
            String phoneNumber = (String) jsonMap.get("phoneNumber");
            String address     = (String) jsonMap.get("address");
            String email       = (String) jsonMap.get("email");

            Buyer buyer = new Buyer(firstName, lastName, username, password, phoneNumber, email, address);
            buyerDao.persist(buyer);
        }
        else {
            System.out.println("Invalid POST request parameters: " + jsonMap);
        }
    }
}
