package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = RESTUtility.parseURL(request.getPathInfo());

            if (id == 0) {
                List<Offer> offers = offerDao.findActiveOffers();
                RESTUtility.flushJson(response, offers);
            }

            else if (id > 0) {
                Offer offer = offerDao.findById(id);
                RESTUtility.flushJson(response, offer);
            }
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            response.sendError(400);
        }
    }
}
