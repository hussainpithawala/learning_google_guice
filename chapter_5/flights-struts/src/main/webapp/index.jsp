<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/flights.css">
</head>
<body>
	<s:form action="Search">
	<div class="formContent">
		<div class="formName">
			<span>
				<p class="textElement">
					Flight Search form
				</p>
			<span>
		</div>

		<div class="formCell">
					<span>
				<p class="textElement">
					<s:textfield name="source" label="From"/>		
				</p>
			<span>
		</div>
		<div class="formCell">
			<span>
				<p class="textElement">
					<s:textfield name="destination" label="To"/>					
				</p>
			</span>
		</div>
		<div class="formCell">
			<span>
				<p class="textElement">
					<s:textfield name="date" label="Date"/>					
				</p>
			</span>
		</div>
		<div class="formCell">
			<span>
				<p class="textElement">
					<s:submit label="Submit"/>					
				</p>
			</span>
		</div>
	</div>		
	</s:form>
</body>
</html>
