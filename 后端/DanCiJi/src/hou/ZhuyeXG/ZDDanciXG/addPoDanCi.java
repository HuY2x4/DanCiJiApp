package hou.ZhuyeXG.ZDDanciXG;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.PointwordContr;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/addPoDanCi")
public class addPoDanCi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addPoDanCi() {
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
        String SDCid="";
        String SDCciku="";
        String name="";
        String DC="";
        DC=request.getParameter("DC");
        name = request.getParameter("name");
        SDCid = request.getParameter("DCid");
        SDCciku = request.getParameter("DCciku");
        int DCid = Integer.parseInt(SDCid);
        int DCciku = Integer.parseInt(SDCciku);
        String if_cunzai="";
        String result = "fail";
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       PointwordContr point =new PointwordContr();
       if_cunzai=point.hasPoDC(name, DC, DCciku);
       if(if_cunzai.equals("fail")){
    	   result=point.addPoDC(DCid, DCciku, name);
       }
       
        
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (("success".equals(result))){
            result="success";
        }else{
            result = "fail";
            
        }
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

