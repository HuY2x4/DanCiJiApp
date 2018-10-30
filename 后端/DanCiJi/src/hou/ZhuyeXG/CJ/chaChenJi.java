package hou.ZhuyeXG.CJ;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.chenjiContr;
import com.controller.hasDC;
import com.controller.userContr;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/chaChenJi")
public class chaChenJi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chaChenJi() {
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
		int gongneng;
        int siliuji;
        String name = "";
	    name = request.getParameter("name");
        gongneng =Integer.parseInt(request.getParameter("gongneng")) ;
        System.out.println("功能是不是12："+gongneng);
        siliuji =Integer.parseInt(request.getParameter("siliuji"));
        String result = "";
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        chenjiContr chenji=new chenjiContr();
        if(gongneng==12){
            System.out.println("功能进入12");

        	int[] ciku1=chenji.chaCJ(name, 1, siliuji);
        	int[] ciku2=chenji.chaCJ(name, 2, siliuji);
        	
        	 int zongfen1=0;
             int zongfen2=0;
             for(int i=0;i<ciku1.length;i++){
                 zongfen1+=ciku1[i];
             }
             for(int i=0;i<ciku2.length;i++){
                 zongfen2+=ciku2[i];
             }
        	userContr usercontr=new userContr();
        	String addZFresult=usercontr.updUserZF(name, siliuji, zongfen1, zongfen2);
        	
        	result=Integer.toString(zongfen1)+"M"+Integer.toString(zongfen2);
            System.out.println("result："+result);
        	
        }
        else{
        	int[] ciku=chenji.chaCJ(name, gongneng, siliuji);
            System.out.println("gongneng：："+gongneng);
        	int i=0;
        	for(;i<=ciku.length;i++){
        		if(i<ciku.length-1)
        			result=result+Integer.toString(ciku[i])+";";
        		else if(i==ciku.length-1)
        			result=result+Integer.toString(ciku[i]);
        	}
        }
        
        System.out.println("!!!"+result);
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
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