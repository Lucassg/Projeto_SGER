package Controle;

import com.google.gson.Gson;
import dao.DaoArea_Entrega;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Area_Entrega;



@WebServlet(name = "validacaoServlet", urlPatterns = {"/validacaoServlet"})
public class validacaoServlet extends HttpServlet {
    
    DaoArea_Entrega acessohibernateareaentrega;
    Area_Entrega area_entrega;
    
    public validacaoServlet(){
        
        acessohibernateareaentrega = new DaoArea_Entrega();
        area_entrega = new Area_Entrega();
        
    }
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String cep;
        Boolean result;
        cep = request.getParameter("CEP");
        area_entrega = (Area_Entrega) acessohibernateareaentrega.verificaAreaEntrega(cep);
        
        if (area_entrega == null){
            result = false;
        } else{
            result = true;
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json"); 
        response.setCharacterEncoding("utf-8"); 
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
