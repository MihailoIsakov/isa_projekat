package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.*;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.*;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils.RESTUtility;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @EJB
    private PaymentDaoLocal paymentDao;

    @EJB
    private SellerDaoLocal sellerDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        String jsonData = RESTUtility.pullDataFromRequest(request);

        if (url.contains("create")) {
            Map<String, Object> json = RESTUtility.json2Map(jsonData);

            Offer offer;
            if (json.containsKey("id")) {
                offer = offerDao.findById((Integer) json.get("id"));
                System.out.println(jsonData);
                System.out.println(offer);
                System.out.println(RESTUtility.mapper.readerForUpdating(offer).readValues(jsonData).next());
                System.out.println("merged" + offer);
                offerDao.merge(offer);
            }
            else {
                offer = new Offer();
                offer = RESTUtility.mapper.readValue(jsonData, Offer.class);
                offer.setDateCreated(new Date());
                offer.setPurchasedOffers(0);

                Seller seller = (Seller) request.getSession().getAttribute("user");
                offer.setManager(seller);
                offerDao.persist(offer);
                System.out.println("persisted");
            }
            response.setStatus(200);
            return;
        }

        int id = RESTUtility.getId(url);
        Offer offer = offerDao.findById(id);

        // URL: /offer/:offerid/comment
        if (url.contains("comment")) {
            String message = jsonData;
            User buyer = (User) request.getSession().getAttribute("user");

            if (buyer instanceof Buyer) {
                Comment comment = new Comment(message, offer, (Buyer) buyer);
                commentDao.persist(comment);
                response.setStatus(201);
            }
        }

        // URL: /offer/:offerid/buy
        if (url.contains("buy") &&
            request.getSession().getAttribute("user") instanceof Buyer)
            {
                Payment payment = new Payment(offer.getSalePrice(), offer, (Buyer) request.getSession().getAttribute("user"));
                paymentDao.persist(payment);
                response.setStatus(201);
            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if (request.getParameterMap().containsKey("seller")) {
                int sellerID = Integer.parseInt(request.getParameter("seller"));
                Seller seller = sellerDao.findById(sellerID);

                List<Offer> offers = offerDao.findByManager(seller);
                RESTUtility.flushJson(response, returnActive(offers));
                return;
            }

            // In case the user specified the category of the offers
            if (request.getParameterMap().containsKey("category")) {
                int categoryID = Integer.parseInt(request.getParameter("category"));
                Category category = categoryDao.findById(categoryID);

                List<Offer> offers = offerDao.findByCategory(category);
                System.out.println(offers);
                offers = returnActive(offers);
                System.out.println(offers);
                RESTUtility.flushJson(response, returnActive(offers));
                return;
            }

            // Get the rest of the URL
            try {
                int id = RESTUtility.getId(request.getPathInfo());

                Offer offer = offerDao.findById(id);
                if (offer == null)
                    response.sendError(404);
                RESTUtility.flushJson(response, offer);
                return;
            } catch (IllegalArgumentException ex) {
                List<Offer> offers = offerDao.findActiveOffers();
                RESTUtility.flushJson(response, returnActive(offers));
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            response.sendError(400);
        }
    }

    private static List<Offer> returnActive(List<Offer> offers) {
        List<Offer> newOffers = new ArrayList<Offer>();
        for (Offer offer : offers)
            if(offer.isActive())
                newOffers.add(offer);
        return newOffers;
    }
}
