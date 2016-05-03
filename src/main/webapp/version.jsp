<%@ page import="java.io.*"
%><%@ page import="java.util.Map"
%><%@ page import="java.util.jar.Manifest"%><%@ page import="java.util.jar.Attributes"%><%@ page contentType="application/json;charset=UTF-8"
%><%
InputStream inputStream = request.getSession().getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");
Manifest manifest = new Manifest(inputStream);
Attributes attributes = manifest.getMainAttributes();

%>{<%
    for (Map.Entry<Object, Object> entry : attributes.entrySet()) {
%>"<%=entry.getKey()%>": "<%=entry.getValue()%>", <%
    }
 %> "swaggerui-version":"<%=manifest.getAttributes("Libraries").getValue("swaggerui-version")%>"
  }