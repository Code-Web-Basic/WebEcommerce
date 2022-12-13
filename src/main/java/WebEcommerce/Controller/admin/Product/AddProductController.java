package WebEcommerce.Controller.admin.Product;

import WebEcommerce.Model.CategoryModel;
import WebEcommerce.Model.ProductModel;
import WebEcommerce.Service.CategoryService;
import WebEcommerce.Service.Impl.CategoryServiceImpl;
import WebEcommerce.Service.Impl.ProductServiceImpl;
import WebEcommerce.Service.ProductService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import vn.iotstar.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet(name = "InsertProductController", value = "/admin/product/add")
public class AddProductController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryModel> categoryList =  categoryService.findAll();
        request.setAttribute("categorys",categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/addProduct.jsp");
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
        ProductModel product = new ProductModel();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("name")) {
                    product.setName(item.getString("UTF-8"));
                } else if (item.getFieldName().equals("description")) {
                    product.setDescription(item.getString("UTF-8"));
                } else if (item.getFieldName().equals("image")) {
                    String originalFileName = item.getName();
                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;
                    File file = new File(Constant.DIR + "/product/" + fileName);
                    item.write(file);
                    product.setListImages(fileName);
                } else if (item.getFieldName().equals("price")) {
                    product.setPrice(Integer.parseInt(item.getString("UTF-8")));
                } else if (item.getFieldName().equals("promotionalprice")) {
                    product.setPromotionalPrice(Integer.parseInt(item.getString("UTF-8")));
                } else if (item.getFieldName().equals("quantity")) {
                    product.setQuantity(Integer.parseInt(item.getString("UTF-8")));
                }
                else if (item.getFieldName().equals("categoryId")) {
                    product.setCategoryId(Integer.parseInt(item.getString("UTF-8")));
                }
                else if (item.getFieldName().equals("isSelling")) {
                    product.setIsSelling(item.getString("UTF-8").equals("on"));
                }
                Date date = java.sql.Date.valueOf(LocalDate.now());
                product.setCreatedAt(date);
                product.setUpdatedAt(date);
            }
            productService.insert(product);
            response.sendRedirect(request.getContextPath() + "/admin/product");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}