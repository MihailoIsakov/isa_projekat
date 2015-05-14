package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.*;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CommentDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OfferDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils.RESTUtility;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zieghailo on 5/12/15.
 */
@WebServlet(name = "OfferServlet")
public class OfferServlet extends HttpServlet {

    @EJB
    private OfferDaoLocal offerDao;

    @EJB
    private CategoryDaoLocal categoryDao;

    @EJB
    private CommentDaoLocal commentDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = RESTUtility.parseURL(request.getPathInfo());

        String jsonData = RESTUtility.pullDataFromRequest(request);
        Map jsonMap = RESTUtility.json2Map(jsonData);

        if (jsonMap.containsKey("comment")) {
            String message = (String) jsonMap.get("comment");
            Offer offer = offerDao.findById(id);
            User buyer = (User) request.getSession().getAttribute("user");

            if (buyer instanceof Buyer) {
                Comment comment = new Comment(message, offer, (Buyer) buyer);
                commentDao.persist(comment);
            }
        }

        // Add to cart logic
        if (request.getPathInfo().contains("buy")) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // In case the user specified the category of the offers
            if (request.getParameterMap().containsKey("category")) {
                int categoryID = Integer.parseInt(request.getParameter("category"));
                Category category = categoryDao.findById(categoryID);

                List<Offer> offers = offerDao.findByCategory(category);

                RESTUtility.flushJson(response, offers);
                return;
            }

            // Get the rest of the URL
            int id = RESTUtility.parseURL(request.getPathInfo());

            // In case the user didn't specify the id
            if (id == 0) {
                List<Offer> offers = offerDao.findActiveOffers();
                RESTUtility.flushJson(response, offers);
                return;
            }

            // In case he did specify the id
            else if (id > 0) {
                Offer offer = offerDao.findById(id);
                RESTUtility.flushJson(response, offer);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            response.sendError(400);
        }
    }
}
