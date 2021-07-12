# Servlets & JSP

## Introduction to Servlets

1. Static Page vs Dynamic Page
2. How Webservers serve Dynamic content?
    - Deployment Descriptor (web.xml)
    - server.xml
    - check <context> tag at the end of the file
    - based on the context path, each app has its own Deployment Descriptor(web.xml)

## Setup tomcat in Eclipse

1. Download tomcat from internet
2. Add server runtime in eclipse
    - In case tomcat adapter is not availble in Eclipse
        - Help -> Install New Software
        - Choose site based on your eclipse version
        - Expand "Web, XML, and Java EE Development", select the following
            - JST Server Adapters
            - JST Server Adapters Extensions (Apache Tomcat)
            - Complete the rest of the installation wizard

## Create a Dynamic web project in Eclipse

1. Create dynamic web project
    - In case dynamic web project is not available
        - Help -> Install New Software
        - Choose site based on your eclipse version
        - Expand "Web, XML, and Java EE Development", select the following
            - Eclipse Java EE Developer Tools
            - Eclipse Java Web Developer Tools
            - Eclipse Java Web Developer Tools - JavaScript Support
            - Eclipse Web Developer Tools
            - Eclipse Web JavaScript Developer Tools
            - Eclipse XML Editors and Tools
            - Complete the rest of the installation wizard
2. select target runtime
3. Select dynamic web module version
4. provide context-root
5. Generate web.xml ()🔴
6. finish

## Create HTML Page

1. Create a simple html page
    - put simple hello world text
    - start the server
    - verify localhost:8080/context
2. Add form to html page    
    - the form default method="post"
        ```
        <!DOCTYPE html>
        <html>
            <body>
            <form action="add">
                Enter the first number: <input type="text" name="num1"><br>
                Enter the second number: <input type="text" name="num2"><br>
                <input type="submit">
            </form>
            </body>
        </html>
        ```
## Create Servlet

1. create simple class and extend HttpServlet
2. Overide service() method. this is 

    ```
    package com.rudra;

    import java.io.IOException;
    import java.io.PrintWriter;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    public class AddServlet extends HttpServlet {

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("inside service method...");
            
            int num1 = Integer.parseInt(req.getParameter("num1"));
            int num2 = Integer.parseInt(req.getParameter("num2"));
            
            int result = num1 + num2;
            
            PrintWriter out = resp.getWriter();
            out.println("result is :" + result);
        }	
    }
    ```
3. Run the server and verify response in browser - 404 not found. Because the tomcat doesn't have information to call AddServlet for /add url pattern

## Update web.xml

1. Add Servlet Name
    ```
    <servlet>
		<servlet-name>AddServlet</servlet-name>
		<servlet-class>com.rudra.AddServlet</servlet-class>
	</servlet>
    ```
2. Add Servlet Mapping.
    ```
	<servlet-mapping>
		<servlet-name>AddServlet</servlet-name>
		<url-pattern>/add</url-pattern>
	</servlet-mapping>
    ```
3. Run the server and test

## doGet() and doPost()

1. add method="post" in the html form
    ```
    <form action="add" method="post">
    ```
2. run the server without adding doPost() in the servlet. It will work because by default all the calls to service()
3. add doPost() and test again. Still the request will go to service() only
4. remove the service() method and run. now it will call doPost(). Internally the call will first come to service() then passed on to doPost().

## RequestDispatcher - calling one servlet from another

1. Create new servlet as below
    ```
    public class SquareServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int num3 = Integer.parseInt(request.getParameter("num3"));
            int result = num3 * num3;
            PrintWriter out = response.getWriter();
            out.println("Square value is :" + result);
        }
    }
    ```
2. Remove PrintWriter from original class and include RequestDispatch 
    ```
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside post method...");
        
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        
        int result = num1 + num2;
        
        req.setAttribute("num3", result);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/square");
        requestDispatcher.forward(req, resp);
    }
    ```

## sendRedirect()

1. Using URL redirect 
    ```
    // In servlet one
    resp.sendRedirect("square?num3="+result);

    // In servlet two
    int num3 = Integer.parseInt(request.getParameter("num3"));
    ```
2. Using Session 
    ```
    // In servlet one
    HttpSession session = req.getSession();
    session.setAttribute("num3", result);
    resp.sendRedirect("square"); 

    // In servlet two
    int num3 = (int)request.getSession().getAttribute("num3");
    ```
3. Using Cookie 
    ```
    Cookie cookie = new Cookie("num3", String.valueOf(result));
	resp.addCookie(cookie);	
	resp.sendRedirect("square"); 

    // In servlet two
    String num3 = Stream.of(request.getCookies()).filter(c -> c.getName().equals("num3")).map(c -> c.getValue()).findFirst().get();
    ```

## ServletContext

1. Common config across all the servlets
2. Add this to web.xml
    ```
    <context-param>
		<param-name>name</param-name>
		<param-value>Siva</param-value>
	</context-param>
    ```
3. In servlet do this
    ```
    ServletContext ctx = getServletContext();
	resp.getWriter().println("Context Param - Name: "+ ctx.getInitParameter("name"));
    ```

## ServletConfig

1. Config for a specific servlet
2. update the servlet in web.xml to include init-param
    ```
    <servlet>
		<servlet-name>add</servlet-name>
		<servlet-class>com.rudra.AddServlet</servlet-class>
		<init-param>
			<param-name>name</param-name>
			<param-value>Rudra</param-value>
		</init-param>
	</servlet>
    ```
3. In servlet do this
    ```
    ServletConfig config = getServletConfig();
	resp.getWriter().println("Config Param - Name: "+ config.getInitParameter("name"));
    ```

## Annotation based config

1. Use @WebServlet. Eg:

    ```
    @WebServlet("/square")
    ```

    ```
    @WebServlet(
        urlPatterns = "/add", 
        initParams = {
            @WebInitParam(name = "name", value = "abc"),
            @WebInitParam(name = "lastname", value = "def")
        }
    )
    ```

