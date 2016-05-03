<%@ page import="java.io.*"
%><%@ page import="java.util.Arrays"
%><%@ page contentType="application/json;charset=UTF-8"
%><% File jsp = new File(request.getSession().getServletContext().getRealPath(request.getServletPath()));
File dir = jsp.getParentFile();
String requestPath = request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("index.jsp"));
File[] list = dir.listFiles(new FilenameFilter() {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".yaml") || name.endsWith(".json");
    }
});
%>[<%
    for(int i = 0; i < list.length; i++) {
%><%=i!=0?", ":"" %>
"<%=requestPath+list[i].getName()%>"<%
    } %>
]