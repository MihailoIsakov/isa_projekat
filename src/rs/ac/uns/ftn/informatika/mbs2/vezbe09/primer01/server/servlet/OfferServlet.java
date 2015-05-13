package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OfferDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//        try {
//            int id = RESTUtility.parseURL(request.getPathInfo());
//
//            if (id == 0) {
//                List<Offer> offers = offerDao.f
//            }
//        }
    }
}
