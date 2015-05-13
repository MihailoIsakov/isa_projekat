package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;
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

/**
 * Created by zieghailo on 5/12/15.
 */
@WebServlet(name = "OfferServlet")
public class OfferServlet extends HttpServlet {

    @EJB
    private OfferDaoLocal offerDao;

    @EJB
    private CategoryDaoLocal categoryDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
