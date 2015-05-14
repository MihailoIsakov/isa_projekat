package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Payment;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PaymentDaoLocal;
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
 * Created by zieghailo on 5/14/15.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {

    @EJB
    private PaymentDaoLocal paymentDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Buyer buyer = (Buyer) request.getSession().getAttribute("user");
            List<Payment> paymentList = paymentDao.findPaymentsByBuyer(buyer);
            RESTUtility.flushJson(response, paymentList);

        } catch (ClassCastException ex) {
            response.sendError(403);
        }
    }
}
