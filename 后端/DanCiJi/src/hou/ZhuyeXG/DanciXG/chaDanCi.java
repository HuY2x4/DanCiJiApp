package hou.ZhuyeXG.DanciXG;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.wordContr;
import com.model.Word;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Denglu
 */
@WebServlet("/chaDanCi")
public class chaDanCi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chaDanCi() {
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
        SDCid = request.getParameter("DCid");
        SDCciku = request.getParameter("DCciku");
        int DCid = Integer.parseInt(SDCid);
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
        wordContr word=new wordContr();
        Word dc=word.chaDC(DCid, DCciku);
        DC1=dc.getWordId();
        DCyisi=dc.getWordMean();
        DC2=dc.getWord1_Id();
        DCyisi2=dc.getWord1_Mean();
        DC3=dc.getWord2_Id();
        DCyisi3=dc.getWord2_Mean();
        DC4=dc.getWord3_Id();
        DCyisi4=dc.getWord3_Mean();
        DCyinbiao=dc.getWordPA();
        //数据库函数获取!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("DC1", DC1);
        jsonObject.put("DCyisi", DCyisi);
        jsonObject.put("DCliju", DCliju);
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
