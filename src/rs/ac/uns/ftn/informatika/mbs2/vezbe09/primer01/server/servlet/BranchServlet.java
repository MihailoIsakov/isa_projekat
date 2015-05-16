package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BranchDaoLocal;
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
@WebServlet(name = "BranchServlet")
public class BranchServlet extends HttpServlet {

    @EJB
    SellerDaoLocal sellerDao;

    @EJB
    BranchDaoLocal branchDao;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        String jsonData = RESTUtility.pullDataFromRequest(request);

        if (url.contains("create"))
        {
            Map<String, Object> json = RESTUtility.json2Map(jsonData);

            Branch branch;
            if (json.containsKey("id") && json.get("id") != null) {
                branch = branchDao.findById((Integer) json.get("id"));
                RESTUtility.mapper.readerForUpdating(branch).readValues(jsonData).next();
                branchDao.merge(branch);
            }
            else {
                branch = RESTUtility.mapper.readValue(jsonData, Branch.class);
                Seller seller = (Seller) request.getSession().getAttribute("user");

                branch.setManager(seller);
                branchDao.persist(branch);
            }

            response.setStatus(200);
            return;
        }

        if (url.contains("delete")) {
            Map<String, Object> json = RESTUtility.json2Map(jsonData);

            int id = (int) json.get("id");
            Branch branch = branchDao.findById(id);

            branchDao.remove(branch);
            response.setStatus(200);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameterMap().containsKey("seller")) {
                int sellerID = Integer.parseInt(request.getParameter("seller"));
                Seller seller = sellerDao.findById(sellerID);

                List<Branch> branches = branchDao.findByManager(seller);
                RESTUtility.flushJson(response, branches);
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
