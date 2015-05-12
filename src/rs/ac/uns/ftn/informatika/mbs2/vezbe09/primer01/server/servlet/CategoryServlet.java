package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;
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
 * Created by zieghailo on 5/11/15.
 */
@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends HttpServlet {

    @EJB
    private CategoryDaoLocal categoryDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getPathInfo());
        try {
            int id = RESTUtility.parseURL(request.getPathInfo());

            if (id == 0) {
                List<Category> categories = categoryDao.findAll();
                RESTUtility.flushJson(response, categories);
            }

            else if (id > 0) {
                Category category = categoryDao.findById(id);

                RESTUtility.flushJson(response, category);
            }
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            response.sendError(400);
        }

    }
}
