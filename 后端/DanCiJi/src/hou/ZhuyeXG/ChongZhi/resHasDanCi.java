package hou.ZhuyeXG.ChongZhi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.chenjiContr;
import com.controller.hasDC;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/resHasDanCi")
public class resHasDanCi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resHasDanCi() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	 System.out.println("2");   
        String method = request.getMethod();  
        System.out.println(method);   
        if ("GET".equals(method)) {  
        	System.out.println("���󷽷���GET");  
            doGet(request, response);
        } else if ("POST".equals(method)) {  
         System.out.println("���󷽷���POST");  
            doPost(request, response);  
        } else {  
         System.out.println("���󷽷��ֱ�ʧ�ܣ�");  
        }  
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("3");
		 doPost(request, response);
		 System.out.println("4");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String name = "";
	    name = request.getParameter("name");
        String result = "";
        //���ݿ⺯����ȡ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        hasDC hasdc=new hasDC();
        hasdc.resHasDC(name);
        chenjiContr chenji=new chenjiContr();
        chenji.resCJ(name);
        //���ݿ⺯����ȡ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        result="success";
        
        JSONObject jsonObject = new JSONObject();
     //   URLEncoder.encode(result1,"utf-8");
        
        jsonObject.put("result", result);//����ע��ҲҪ��
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonObject);
	}
	
	 public void destroy() {  
		 System.out.println("RegisterServlet destory.");  
	        super.destroy();  
	    }  

}

