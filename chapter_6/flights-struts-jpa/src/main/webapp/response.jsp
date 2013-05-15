<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/flightsweb/css/flights.css">
</head>
<body>
	  <div class="responseContainer">
	   <div>
	  	<div class="responseTag">
			FlightNumber
		</div>
		<div class="responseTag">
			Departure
		</div>
		<div class="responseTag">
			Arrival
		</div>
		<div class="responseTag">
			Fare
		</div>			
	  </div>
	  	
	  <logic:iterate name="results" id="searchRS">
		<div class="responseElement">
			<bean:write name="searchRS" property="flightNumber"/>
		</div>
		
		<div class="responseElement">
			<bean:write name="searchRS" property="departureLocation"/>
		</div>
		
		<div class="responseElement">
			<bean:write name="searchRS" property="arrivalLocation"/>
		</div>
	
		<div class="responseElement">
			<bean:write name="searchRS" property="fare" format="$"/>
		</div>
	  </logic:iterate>
	</div>
	<div>
		<bean:write name="requestCounter" property="searches" format=" "/> requests served in current session.
	</div>
</body>
</html>
