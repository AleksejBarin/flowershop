package com.accenture.flowershop.filters;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.flowershop.business.FlowerService;
import com.accenture.flowershop.model.entity.Flower;
@Component
/**
 * Servlet Filter implementation class TrueFindFilter
 */
public class TrueFindFilter implements Filter {
	@Autowired
	private FlowerService flowerService;
	
	private FilterConfig filterConfig = null;
	
    /**
     * Default constructor. 
     */
    public TrueFindFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//if (((HttpServletRequest) request).getParameter("findflower")!=null){
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
/*		if (false){
        	Flower flower = flowerService.findFlowerByLocalName(request.getParameter("localName or scientName"));
        	if (flower == null){
        		flower = flowerService.findFlowerByScientName(request.getParameter("localName or scientName"));        		
        	}
        	if (flower == null){
        		//chain.doFilter(request, response);       		
        	} else{    
        		
    	        response.setContentType("text/html");
    	        PrintWriter out = response.getWriter();
        		
        		
                	String result = "<html><head><title>Insert title here</title></head><body>"
                			+ "<h1> Sort </h1>"
                			+ "<table border=1><caption>Flower</caption>"
                			+ "<tr><th>LocalName</th><th>ScientName</th><th>Count</th></tr>";
                	      	   
                	  // result = result+"<tr><td>"+flower.getLocalName()+"</td><td>"+flower.getScientName()+"</td><td>"+flower.getCount()+"</td></tr>";
                	result = result+"<input type=\"button\" onclick=\"history.back();\" value=\"Back\"/>"
                			+ "</table></body></html>";
               		out.println(result); 
        	}  
		}*/
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
		
	}

}
