<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/flightsweb/css/flights.css">
</head>
<body>
	<c:choose>
	<c:when test="${sessionScope.isException}">
		<div class="errorContainer">
			<c:out value="${sessionScope.exceptionMessage}"/>
		</div>
	</c:when>
	<c:otherwise>
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
	  <c:forEach var="searchRS" items="${sessionScope.responseList}">
		<div class="responseElement">
			<c:out value="${searchRS.flightNumber}"/>
		</div>
		
		<div class="responseElement">
			<c:out value="${searchRS.departureLocation}"/>
		</div>
		
		<div class="responseElement">
			<c:out value="${searchRS.arrivalLocation}"/>
		</div>
	
		<div class="responseElement">
			$<c:out value="${searchRS.fare}"/>
		</div>
	  </c:forEach>
	</div>
	</c:otherwise>
	</c:choose>
</body>
</html>
