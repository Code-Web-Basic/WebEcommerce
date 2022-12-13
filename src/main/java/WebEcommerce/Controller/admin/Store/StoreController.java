package WebEcommerce.Controller.admin.Store;

import WebEcommerce.Model.StoreLevelModel;
import WebEcommerce.Model.StoreModel;
import WebEcommerce.Service.Impl.StoreServiceImpl;
import WebEcommerce.Service.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StoreController", value = "/admin/store")
public class StoreController extends HttpServlet {
    StoreService storeService = new StoreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StoreModel> listStore = storeService.getAllStore();
        request.setAttribute("listStore", listStore);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/store/store.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
