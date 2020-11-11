<nav class="navbar navbar-expand-lg navbar-custom">
      <a class="navbar-brand px-3" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item px-4">
            <a class="nav-link" href="index.jsp">Home</a>
          </li>
          <li class="nav-item px-4">
            <a class="nav-link" href="#">Movies</a>
          </li>
          <li class="nav-item px-4">
            <a class="nav-link" href="#">Orders</a>
          </li>
        </ul>
        <% if( session.getAttribute("userName") ==null){%>
        <ul class="navbar-nav navbar-right">
          <li><a class="nav-link px-4" href="signup.jsp"> Sign Up</a></li>
          <li><a class="nav-link px-4" href="userLogin.jsp">Login</a></li>
        </ul>
        <%}else{%>
        <ul class="navbar-nav navbar-right">
          <li><a class="nav-link px-4" href="userLogout">Log out</a></li>
        </ul>
        <% } %>
      </div>
</nav>
