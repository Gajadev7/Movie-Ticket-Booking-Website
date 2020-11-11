<%@page import="net.javaguides.usermanagement.model.movies"%> 
<%@page import="java.util.ArrayList"%> 
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>    
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
   <link rel="stylesheet" href="indexstyles.css">
   <link rel="stylesheet" href="navstyles.css">
  </head>
  <body>
      <jsp:include page="navbar.jsp" />  
      

  <div class="citiesBox">
     
      <% if(session.getAttribute("cityName")!=null){ %>
       <% if( session.getAttribute("userName") !=null){%>
        <div class="welcomeBox">
           <h2>Hi</h2>
            <h3><%= (String)session.getAttribute("userName")%></h3>
        </div>
      <%}%>
      <div class="chosenContainer">
        <div class="imageContainer">
            <img src="<%= (String)session.getAttribute("cityUrl")%>" class="image"/>
        </div>
        <span><%= (String)session.getAttribute("cityName")%></span>
      </div>
      <%}%>
      
      <% if(session.getAttribute("cityName")==null){ %>
      <span class="text">Select your City</span>
      <ul class="citiesList">
          <li class="listItem">
              <a href="chooseCity?city=Chennai">
                  <div class="itemContainer">
                      <div class="imageContainer">
                        <img src="//in.bmscdn.com/m6/images/common-modules/regions/chen.png" alt="CHEN" class="image">
                      </div>
                      <span class="cityName">Chennai</span></div>
               </a>
              </li>
          <li class="listItem">
              <a href="chooseCity?city=Mumbai">
              <div class="itemContainer">
                  <div class="imageContainer">
                      <img src="//in.bmscdn.com/m6/images/common-modules/regions/mumbai.png" alt="MUMBAI" class="image">
                  </div>
                  <span class="cityName">Mumbai</span>
              </div>
              </a>
          </li>
         
          <li class="listItem">
              <a href="chooseCity?city=Bengaluru">
              <div class="itemContainer">
                      <div class="imageContainer">
                          <img src="//in.bmscdn.com/m6/images/common-modules/regions/bang.png" alt="BANG" class="image">
                      </div>
                      <span class="cityName">Bengaluru</span>
              </div>
              </a>
          </li>
          <li class="listItem">
              <a href="chooseCity?city=Hyderabad">
              <div class="itemContainer">
                  <div class="imageContainer">
                      <img src="//in.bmscdn.com/m6/images/common-modules/regions/hyd.png" alt="HYD" class="image">
                  </div>
                  <span class="cityName">Hyderabad</span>
              </div>
              </a>
          </li>
          <li class="listItem">
              <a href="chooseCity?city=Ahmedabad">
              <div class="itemContainer">
                   <div class="imageContainer">
                       <img src="//in.bmscdn.com/m6/images/common-modules/regions/ahd.png" alt="AHD" class="image">
                   </div>
                  <span class="cityName">Ahmedabad</span>
              </div>
              </a>
          </li>
          <li class="listItem">
              <a href="chooseCity?city=Kolkata">
                    <div class="itemContainer">
                        <div class="imageContainer">
                            <img src="//in.bmscdn.com/m6/images/common-modules/regions/kolk.png" alt="KOLK" class="image">
                        </div><span class="cityName">Kolkata</span>
                    </div>
                  </a>
            </li>
           
      </ul>
      <%}%>
      
  </div>
  <div class="movies">
    <h3>Movies</h3>
    <%if(session.getAttribute("cityName")!=null){%>
    <div class="container">

        <% ArrayList<movies> mov = (ArrayList<movies>)session.getAttribute("data");%>
        <% for(movies s:mov){%> 
	<div class="movie-card">
		<div>
                    <img class="movie-header manOfSteel" src="<%= s.getmovieImageUrl()%>">
		</div>
		<div class="movie-content">
			<div class="movie-content-header">
				<a href="moviePage?movieID=<%= s.getmovieID()%>">
					<h3 class="movie-title"><%= s.getmovieName()%></h3>
				</a>
			</div>
			<div class="movie-info">
				<div class="info-section">
					<label>Liked By</label>
					<span><%= s.getlikedBy()%></span>
				</div><!--date,time-->
				
			</div>
		</div><!--movie-content-->
	</div><!--movie-card-->
	<%}%>
	

	
</div><!--container-->
<%}%>
  </body>
</html>