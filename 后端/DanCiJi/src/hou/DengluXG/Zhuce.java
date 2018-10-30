package hou.DengluXG;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.userContr;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/Zhuce")
public class Zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zhuce() {
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
        String password1 = "";
        String password2 = "";
        String Number="";
        String Sciku="";
        name = request.getParameter("name");
        password1 = request.getParameter("password1");
        password2 = request.getParameter("password2");
        Number = request.getParameter("Number");
        Sciku = request.getParameter("ciku");
        int ciku = Integer.parseInt(Sciku);
        System.out.println("-----------------");
        System.out.println(name);
        System.out.println(password1);
        System.out.println(Number);
        System.out.println(Sciku);
        System.out.println("-------------");
        String result = "";
        if(name==""||name==null)
        	result="namenull";
        else if(password1==""||password1==null||password2==""||password2==null)
        	result="passwordnull";
        else if(Number==""||Number==null)
        	result="Numbernull";
        else if(Number.length()>12||Number.length()<10)
        	result="Numbererror";
        else if(name.length()<5||name.length()>13)
        	result="nameerror";
        else if(password1.length()<5||password1.length()>13)
        	result="passworderror";
        else if(!password1.equals(password2))
        	result="passwordsame";
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       if(result==""){
    	   userContr usercontr=new userContr();
    	    result=usercontr.addUser(name, password1, password2, Number, ciku);
       }
      //result=success
        
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       System.out.println(result);
        JSONObject jsonObject = new JSONObject();
     //   URLEncoder.encode(result1,"utf-8");
        
        jsonObject.put("result", result);//这里注意也要改
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonObject);
	}
	
	 public void destroy() {  
		 System.out.println("RegisterServlet destory.");  
	        super.destroy();  
	    }  

}
