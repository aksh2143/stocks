<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <title>Option Chain Data</title>
   </head>
   <body>
<h3>${instrument.symbol}</h3>
SPOT: ${instrument.spotValue}</br>
Expiry: ${instrument.expiryDate}
<table border="2" width="70%" cellpadding="2">
    <tr>
    <th>Chips</th>
    <th>Air</th>
    <th>LTP</th>
    <th>STRIKE</th>
    <th>Ltp</th>
    <th>Air</th>
    <th>Chips</th>
    </tr>
        <c:forEach var="strike" items="${instrument.optionChain.strikeList}">
            <tr>
                <td>${strike.ce.chips}</td>
                <td>${strike.ce.air}</td>
                <td>${strike.ce.lastPrice}</td>
                <td>${strike.strikePrice}</td>
                <td>${strike.pe.lastPrice}</td>
                <td>${strike.ce.air}</td>
                <td>${strike.ce.chips}</td>
            </tr>
        </c:forEach>
 </table>
   <br/>

   </body>
</html>