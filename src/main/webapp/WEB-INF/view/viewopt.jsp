<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <title>Option Chain Data</title>
   </head>
   <body>
<h3>${instrument.symbol}</h3>
SPOT: ${instrument.spotValue}</br>
Expiry: ${instrument.expiryDate}</br>
Current Strike: ${instrument.currentStrike}
<table border="2" width="70%" cellpadding="2">
    <tr>
    <th></th>
    <th>Air</th>
    <th>Chips</th>
    <th>LTP</th>
    <th>STRIKE</th>
    <th>LTP</th>
    <th>Chips</th>
    <th>Air</th>
    <th></th>
    </tr>
        <c:forEach var="strike" items="${instrument.optionChain.strikeList}">
            <tr>
                <td>${strike.ce.place}</td>
                <td>${strike.ce.air} &nbsp (${strike.ce.airPercent}%)</td>
                <td>${strike.ce.chips}</td>
                <td>${strike.ce.lastPrice}</td>
                <td>${strike.strikePrice}</td>
                <td>${strike.pe.lastPrice}</td>
                <td>${strike.pe.chips}</td>
                <td>${strike.pe.air} &nbsp (${strike.pe.airPercent}%)</td>
                <td>${strike.pe.place}</td>
            </tr>
        </c:forEach>
 </table>
   <br/>

   </body>
</html>