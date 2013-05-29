<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/flights.css">
</head>
<body>
	<s:form action="Search">
					<s:textfield name="source" label="From"/>		
					<s:textfield name="destination" label="To"/>					
					<s:textfield name="date" label="Date"/>					
					<s:submit label="Submit"/>					
	</s:form>
</body>
</html>
