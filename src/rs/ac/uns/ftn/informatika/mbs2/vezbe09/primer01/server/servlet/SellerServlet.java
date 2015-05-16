package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.SellerDaoLocal;
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
 * Created by zieghailo on 5/16/15.
 */
@WebServlet(name = "SellerServlet")
public class SellerServlet extends HttpServlet {

    @EJB
    SellerDaoLocal sellerDao;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        String jsonData = RESTUtility.pullDataFromRequest(request);

        if (url.contains("create")) {
            Map<String, Object> json = RESTUtility.json2Map(jsonData);

            Seller seller;
            if (json.containsKey("id") && json.get("id") != null) {
                seller = sellerDao.findById((int) json.get("id"));
                RESTUtility.mapper.readerForUpdating(seller).readValues(jsonData).next();
                sellerDao.merge(seller);
            }
            else {
                seller = RESTUtility.mapper.readValue(jsonData, Seller.class);
                seller.setPassword("");
                sellerDao.persist(seller);
            }
        }

        if (url.contains("delete")) {
            Map<String, Object> json = RESTUtility.json2Map(jsonData);

            int id = (int) json.get("id");
            Seller seller = sellerDao.findById(id);

            sellerDao.remove(seller);
            response.setStatus(200);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Seller> sellers = sellerDao.findAll();
            RESTUtility.flushJson(response, sellers);
            return;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
