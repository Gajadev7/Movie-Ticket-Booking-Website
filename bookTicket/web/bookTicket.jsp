<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=session.getAttribute("chosenTheatre")%></title>
        <link rel="stylesheet" href="bookTicketStyles.css">

    </head>
    <body>
        <%char alpha = 'A';%>
        <h1><%=session.getAttribute("chosenTheatre")%></h1>
        <h3><%=session.getAttribute("timeslot")%></h3>
        <form method="post" action="<%= request.getContextPath() %>/bookTicket">
            <input type="hidden" name="seats" id="hiddenField" value=""/>
            <button class="btn btn-primary">Book Ticket</button>
        </form>
        <div class="theatre">

            <div class="cinema-seats left">
                <div class="cinema-row row-1">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha + "1"%>" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-2">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>2" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-3">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>3" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-4">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>4" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-5">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>5" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-6">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>6" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-7">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>7" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-8">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>8" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-9">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>9" class="seat"></div>
                    <%}%>  
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-10">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>10" class="seat"></div>
                    <%}%>
                </div>
            </div>


            <div class="cinema-seats right">
                <%alpha = 'A';%>
                <div class="cinema-row row-1">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>11" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-2">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>12" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-3">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>13" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-4">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>14" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-5">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>15" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-6">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>16" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-7">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>17" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-8">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>18" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-9">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>19" class="seat"></div>
                    <%}%>
                </div>
                <%alpha = 'A';%>
                <div class="cinema-row row-10">
                    <%for (int i = 1; i <= 20; i++, alpha++) {%>
                    <div id="<%=alpha%>20" class="seat"></div>
                    <%}%>
                </div>
            </div>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script >
            $('.cinema-seats .seat').on('click', function () {
                $(this).toggleClass('active');
            });

        </script>
        <script>
            if(<%=session.getAttribute("bookedSeatData")%>!==null){
            var obj = <%=session.getAttribute("bookedSeatData")%>;
            for (index = 0; index < obj.length; index++) {
                var id = String(obj[index].bookedSeat);
                var element = document.getElementById(id);
                console.log(id);
                element.classList.remove("seat");
                element.classList.add("chosen");
            }}
        </script>
        <script>
           var IdStore;
            $(document).ready(function(){
                IdStore = new Array();
                $('button').click(function(){ 
                    var className = document.getElementsByClassName('active');
                    var classnameCount = className.length;
                    for (var j = 0; j < classnameCount; j++){
                        IdStore.push(className[j].id);
                    }
                    console.log(IdStore);
                    var seatsString = IdStore.toString();
                    $("#hiddenField").val(seatsString);
                });
            });
        </script>
    </body>
</html>
