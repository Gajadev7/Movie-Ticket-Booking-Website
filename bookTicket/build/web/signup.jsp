<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
 
    <link rel="stylesheet" href="signupstyles.css"/> 
    <script>
        function validate() { 
                var password1 = form.password.value; 
                var password2 = form.confirmPassword.value; 
                console.log(password1);
                if(document.getElementById("sp2").innerHTML!==""){
                    document.getElementById("sp2").innerHTML = "*Email Aleady Exists";
                    form.email.focus();
                    return false; 
                }
                else{
                    document.getElementById("sp2").innerHTML = "";
                    
                }
                if (password1 !== password2) {
                    document.getElementById("sp1").innerHTML = "*Passwords does not match";
                    form.confirmPassword.focus();
                    return false; 
                } 
                else{
                   
                    document.getElementById("sp1").innerHTML = "";
                    return true;
                }
         }
        
    
    </script> 
    <script>
                $(document).ready(function(){
                      $('#inputEmail').change(function(){
                          var email = $('#inputEmail').val();
                          var str1 = "Email Already Exists";
                          $.ajax({
                              type : 'POST',
                              data: {email :email},
                              url: 'checkEmail',
                              success : function(result){
                                //$('#sp2').html(result);
                                if(result==1){
                                    $('#sp2').html(str1);
                                }
                                else{
                                    $('#sp2').html("");
                                }
                              }
                        });
                      });
                  });
    </script>
    
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-lg-10 col-xl-9 mx-auto">
        <div class="card card-signin flex-row my-5">
          <div class="card-img-left d-none d-md-flex signup">
             <!-- Background image for card set in CSS! -->
          </div>
          <div class="card-body">
            <h5 class="card-title text-center">Sign Up</h5>
            <form name="form" class="form-signin" action="<%= request.getContextPath() %>/UserServlet" method="post" onsubmit="return validate();">
              <div class="form-label-group">
                <input type="text" name="fullName" id="inputUserame" class="form-control" placeholder="Username" required pattern ="[a-z A-Z]+" title="Please enter only alphabets" autofocus>
                <label for="inputUserame">Full Name</label>
              </div>

              <div class="form-label-group">
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Enter the email in valid format" >
                <label for="inputEmail">Email address</label>
              </div>
              <span id="sp2"></span>
              <div class="form-label-group">
                <input type="text" name="phoneNumber" id="inputphoneNumber" class="form-control" placeholder="Phone Number" required pattern="[0-9]{10}" title="Enter only numbers & length should be 10" >
                <label for="inputphoneNumber">Phone Number</label>
              </div>  
              
              <hr>

              <div class="form-label-group">
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <label for="inputPassword">Password</label>
              </div>
              
              <div class="form-label-group">
                <input type="password" name="confirmPassword" id="inputConfirmPassword" class="form-control" placeholder="Password" required>
                <label for="inputConfirmPassword">Confirm password</label>
              </div>
              
              <span id="sp1"></span>
              <br><br>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign Up</button>
              <a class="d-block text-center mt-2 small" href="userLogin.jsp">Log In</a>
              <hr class="my-4">
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
              
              
              
              
</body>
</html>
