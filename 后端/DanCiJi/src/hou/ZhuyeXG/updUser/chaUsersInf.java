package hou.ZhuyeXG.updUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.userContr;
import com.model.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/chaUsersInf")
public class chaUsersInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chaUsersInf() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	 System.out.println("2");   
        String method = request.getMethod();  
        System.out.println(method);   
        if ("GET".equals(method)) {  
        	System.out.println("请求方法：GET");  
            doGet(request, response);
        } else if ("POST".equals(method)) {  
         System.out.println("请求方法：POST");  
            doPost(request, response);  
        } else {  
         System.out.println("请求方法分辨失败！");  
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
        String name="";

        name = request.getParameter("name");
        

        String Number="";
        int ciku=4;
        String mima="";	
        int ZongFen4_1;
        int ZongFen4_2;
        int ZongFen6_1;
        int ZongFen6_2;
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       
        userContr usercontr=new userContr();
        User user=usercontr.chaUser(name);
        Number=user.getUserNumber();
        ciku=user.getUserSelect();
        ZongFen4_1=user.getZongFen4_1();
        ZongFen4_2=user.getZongFen4_2();
        ZongFen6_1=user.getZongFen6_1();
        ZongFen6_2=user.getZongFen6_2();
        mima=user.getUserPassword();
        
        
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        JSONObject jsonObject = new JSONObject();
     //   URLEncoder.encode(result1,"utf-8");
        
        jsonObject.put("Number", Number);//这里注意也要改
        jsonObject.put("ciku", Integer.toString(ciku));
        jsonObject.put("ZongFen41", Integer.toString(ZongFen4_1));
        jsonObject.put("ZongFen42", Integer.toString(ZongFen4_2));
        jsonObject.put("ZongFen61", Integer.toString(ZongFen6_1));
        jsonObject.put("ZongFen62", Integer.toString(ZongFen6_2));
        jsonObject.put("mima", mima);
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonObject);
	}
	
	 public void destroy() {  
		 System.out.println("RegisterServlet destory.");  
	        super.destroy();  
	    }  

}

