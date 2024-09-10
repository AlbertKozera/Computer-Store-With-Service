  Created by IntelliJ IDEA.
  User: Alfu
  Date: 24.06.2019
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String nazwa = request.getParameter("nazwa");
        String cena = request.getParameter("cena");
        String ilosc = request.getParameter("ilosc");
        String data = request.getParameter("data");
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String miasto = request.getParameter("miasto");
        String ulica = request.getParameter("ulica");
        String numer_domu = request.getParameter("numer_domu");
        String kod_pocztowy = request.getParameter("kod_pocztowy");
        String email = request.getParameter("email");
        String control = request.getParameter("control");

    %>
    <br/>
    <%=nazwa%>
    <br/>
    <%=cena%>
    <br/>
    <%=ilosc%>
    <br/>
    <%=data%>
    <br/>
    <%=imie%>
    <br/>
    <%=nazwisko%>
    <br/>
    <%=miasto%>
    <br/>
    <%=ulica%>
    <br/>
    <%=numer_domu%>
    <br/>
    <%=kod_pocztowy%>
    <br/>
    <%=email%>
    <br/>
    <%=control%>
    <br/>
    <script type="text/javascript">

        function wywolajResta() {
            var opis = document.getElementById("opis").value='nazwa: <%=nazwa%>  ilosc: <%=ilosc%>';
            var cena = document.getElementById("cena").value='<%=cena%>';
            var data = document.getElementById("data").value='<%=data%>';
            var imie = document.getElementById("imie").value='<%=imie%>';
            var nazwisko = document.getElementById("nazwisko").value='<%=nazwisko%>';
            var miasto = document.getElementById("miasto").value='<%=miasto%>';
            var ulica = document.getElementById("ulica").value='<%=ulica%>';
            var numer_domu = document.getElementById("numer_domu").value='<%=numer_domu%>';
            var kod_pocztowy = document.getElementById("kod_pocztowy").value='<%=kod_pocztowy%>';
            var email = document.getElementById("email").value='<%=email%>';
            var control = document.getElementById("control").value='<%=control%>';

            document.getElementById("rest").click();
        }

    </script>
</head>
<body onload="wywolajResta();">
<iframe name="hiddenFrame" width="0" height="0"  style="display: none;"></iframe>
<form action="https://ssl.dotpay.pl/test_seller/api/v1/accounts/784906/payment_links/" method="POST" target="_parent" >
    <input name="amount" type="hidden"                                                  id="cena"/>
    <input name="currency" value="PLN" type="hidden"/>
    <input name="description" type="hidden"                                             id="opis">
    <input name="control" type="hidden"                                                 id="control"/>
    <input name="language" value="pl" type="hidden"/>
    <input name="ignore_last_payment_channel" value="1" type="hidden"/>
    <input name="redirection_type" value="0" type="hidden"/>
    <input name="url" value="http://localhost:8080/_User/Faktury.xhtml" type="hidden"/>
    <input name="urlc" value="http://localhost:8080/odpowiedz.jsp" type="hidden"/>
    <input name="payer.first_name" type="hidden"                                        id="imie"/>
    <input name="payer.last_name" type="hidden"                                         id="nazwisko"/>
    <input name="payer.email" type="hidden"                                             id="email"/>
    <input name="payer.country" value="POL" type="hidden"/>
    <input name="payer.flat_number" type="hidden"                                       id="numer_domu"/>
    <input name="operation_datetime" type="hidden"                                      id="data"/>
    <input name="payer.street" type="hidden"                                            id="ulica"/>
    <input name="payer.city" type="hidden"                                              id="miasto"/>
    <input name="payer.postcode" type="hidden"                                          id="kod_pocztowy"/>
    <p><button id="rest">REST</button></p>
</form>




</body>



</html>
