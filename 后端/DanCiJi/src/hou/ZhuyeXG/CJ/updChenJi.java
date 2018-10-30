package hou.ZhuyeXG.CJ;

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
@WebServlet("/updChenJi")
public class updChenJi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updChenJi() {
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
		int gongneng;
        int siliuji;
        int fenshu;
        int xiaociku;
        String name = "";
	    name = request.getParameter("name");
        gongneng =Integer.parseInt(request.getParameter("gongneng")) ;
        siliuji =Integer.parseInt(request.getParameter("siliuji"));
        fenshu =Integer.parseInt(request.getParameter("fenshu"));
        xiaociku =Integer.parseInt(request.getParameter("xiaociku"));
        String result = "";
        //���ݿ⺯����ȡ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("��˴���ķ�����"+fenshu+"С�ʿ⣺"+xiaociku); 
        
        chenjiContr chenji=new chenjiContr();
        chenji.updCJ(name, gongneng, siliuji, xiaociku, fenshu);
        result="success";
        //���ݿ⺯����ȡ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
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