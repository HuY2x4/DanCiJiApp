package hou.ZhuyeXG.ZDDanciXG;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.PointwordContr;
import com.model.Word;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/chaPoDanCi")
public class chaPoDanCi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chaPoDanCi() {
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
        String SDCxh="";
        String SDCciku="";
        String name="";
	    name = request.getParameter("name");
        SDCxh = request.getParameter("DCxh");
        SDCciku = request.getParameter("DCciku");
        int DCxh = Integer.parseInt(SDCxh);
        int DCciku = Integer.parseInt(SDCciku);

        String DC1="";
        String DCyisi = "";
        String DCliju = "";
        String DCyinbiao = "";
        String DC2="";
        String DC3="";
        String DC4="";
        String DCyisi2 = "";
        String DCyisi3 = "";
        String DCyisi4 = "";
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("????????here");
        PointwordContr point =new PointwordContr();
        System.out.println("????????here1");
        Word word=point.chaPoDC(DCxh,DCciku , name);
        System.out.println("????????here2");
        DC1=word.getWordId();
        System.out.println("????????here3");
        DC2=word.getWord1_Id();
        System.out.println("????????here4");
        DC3=word.getWord2_Id();
        System.out.println("????????here5");
        DC4=word.getWord3_Id();
        System.out.println("????????here6");
        DCyisi=word.getWordMean();
        DCyisi2=word.getWord1_Mean();
        DCyisi3=word.getWord2_Mean();
        DCyisi4=word.getWord3_Mean();
        DCyinbiao=word.getWordPA();
        System.out.println("????????here7");
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String result="";
        System.out.println("????????here8");
        if(DC1.equals("1")){
        	System.out.println("????????here9");
        	result="fail";
        }
        System.out.println("????????"+DC1);
        System.out.println("????????"+result);
        System.out.println("????????"+DC2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("DC1", DC1);
        jsonObject.put("DCyisi", DCyisi);
        jsonObject.put("DCyinbiao", DCyinbiao);
        jsonObject.put("DC2", DC2);
        jsonObject.put("DC3", DC3);
        jsonObject.put("DC4", DC4);
        jsonObject.put("DCyisi2", DCyisi2);
        jsonObject.put("DCyisi3", DCyisi3);
        jsonObject.put("DCyisi4", DCyisi4);//这里注意也要改
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(jsonObject);
	}
	
	 public void destroy() {  
		 System.out.println("RegisterServlet destory.");  
	        super.destroy();  
	    }  

}

