<%@page import="Dao.ProductDao"%>
<%@page import="Model.Product"%>
<%@page import="Model.Seller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!--A Design by W3layouts
   Author: W3layout
   Author URL: http://w3layouts.com
   License: Creative Commons Attribution 3.0 Unported
   License URL: http://creativecommons.org/licenses/by/3.0/
   -->
<!DOCTYPE html>
<html lang="zxx">
   <head>
      <title>Toys Shop an Ecommerce Category Bootstrap Responsive Web Template | Home :: w3layouts</title>
      <!--meta tags -->
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="keywords" content="Toys Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
      <script>
         addEventListener("load", function () {
         	setTimeout(hideURLbar, 0);
         }, false);
         
         function hideURLbar() {
         	window.scrollTo(0, 1);
         }
      </script>
      <!--//meta tags ends here-->
      <!--booststrap-->
      <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
      <!--//booststrap end-->
      <!-- font-awesome icons -->
      <link href="css/fontawesome-all.min.css" rel="stylesheet" type="text/css" media="all">
      <!-- //font-awesome icons -->
      <!--Shoping cart-->
      <link rel="stylesheet" href="css/shop.css" type="text/css" />
      <!--//Shoping cart-->
      <!--stylesheets-->
      <link href="css/style.css" rel='stylesheet' type='text/css' media="all">
      <!--//stylesheets-->
      <link href="//fonts.googleapis.com/css?family=Sunflower:500,700" rel="stylesheet">
      <link href="//fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
   </head>
   <body>
    <%
   Seller s= null;
   if(session.getAttribute("data")!=null){
	   s=(Seller)session.getAttribute("data");
   }
   else{
	   response.sendRedirect("Seller-Login.jsp");
   }
   %>
      <!--headder-->
      <div class="header-outs" id="home">
      <div class="header-bar">
         <div class="info-top-grid">
            <div class="info-contact-agile">
               <ul>
                  <li>
                     <span class="fas fa-phone-volume"></span>
                     <p>+(000)123 4565 32</p>
                  </li>
                  <li>
                     <span class="fas fa-envelope"></span>
                     <p><a href="mailto:info@example.com">info@example1.com</a></p>
                  </li>
                  <li>
                  </li>
               </ul>
            </div>
         </div>
         <div class="container-fluid">
            <div class="hedder-up row">
               <div class="col-lg-3 col-md-3 logo-head">
                  <h1><a class="navbar-brand" href="index.jsp">Toys-Shop</a></h1>
               </div>
               <div class="col-lg-5 col-md-6 search-right">
                  <form class="form-inline my-lg-0">
                     <input class="form-control mr-sm-2" type="search" placeholder="Search">
                     <button class="btn" type="submit">Search</button>
                  </form>
               </div>
               <div class="col-lg-4 col-md-3 right-side-cart">
                  <div class="cart-icons">
                     <ul>
                        <li>
                           <span class="far fa-heart"></span>
                        </li>
                        <li>
                           <button type="button" data-toggle="modal" data-target="#exampleModal"> <span class="far fa-user"></span></button>
                        </li>
                        <li class="toyscart toyscart2 cart cart box_1">
                           <form action="#" method="post" class="last">
                              <input type="hidden" name="cmd" value="_cart">
                              <input type="hidden" name="display" value="1">
                              <button class="top_toys_cart" type="submit" name="submit" value="">
                              <span class="fas fa-cart-arrow-down"></span>
                              </button>
                           </form>
                        </li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
         <nav class="navbar navbar-expand-lg navbar-light">
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
                  <ul class="navbar-nav ">
                     <li class="nav-item ">
                        <a class="nav-link" href="Seller-home.jsp">Home <span class="sr-only">(current)</span></a>
                     </li>
                     
                      <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Products
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                           <a class="nav-link" href="seller-upload-product">Upload Product</a>
                           <a class="nav-link " href="Customer-Register.jsp">Mannage Product</a>                          
                        </div>
                     </li>
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       <%=s.getName() %>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                           <a class="nav-link" href="Seller-Profile.jsp">Profile</a>
                           <a class="nav-link" href="Customer-ChangePassword.jsp">Change Password</a>
                            <a class="nav-link" href="Seller-Logout.jsp">Logout</a>
                        </div>
                     </li>
                   
                  </ul>
               </div>
            </nav>
         </div>
	  </div>
      <!--//headder-->
      <!-- banner -->
      <div class="inner_page-banner one-img">
      </div>
      <!--//banner -->
      <!-- short -->
      <div class="using-border py-3">
         <div class="inner_breadcrumb  ml-4">
            <ul class="short_ls">
               <li>
                  <a href="index.jsp">Home</a>
                  <span>/ /</span>
               </li>
               <li>Upload Product</li>
            </ul>
         </div>
      </div>
      <!-- //short-->
      <!--//banner -->
      <!--Typography-->
      <section class="inner-pages py-5">
         <div class="container py-xl-5 py-sm-3">
         
         <%int id = Integer.parseInt(request.getParameter("id")); %>
	<%Product p = ProductDao.getProductByPid(id); %>
            <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">Edit Product</h3>
            <!-- forms -->
            <section class="typo-section py-4 border-top border-bottom">
              
               <!-- form1 -->
               <form action="ProductController"method="post" enctype="multipart/form-data">
                 
                <img src="image/<%=p.getImage()%>" height="200px" width="200px" alt="">
               
                   <div class="form-group">                  
                     <input type="hidden" name="pid" value= "<%=p.getPid()%>">
                  </div>
                 
               <div class="form-group">
                     <label for="inputproduct">image</label>
                     <input type="file" class="form-control" name="image" placeholder="Name" >
                  </div>
                  <div class="form-row">
                     <div class="form-group col-md-6">
                        <label for="inputproduct">product name</label>
                        <input type="text" class="form-control" name="pname" value="<%=p.getPname()%>">
                     </div>
                     <div class="form-group col-md-6">
                        <label for="inputproduct">product price</label>
                        <input type="text" class="form-control" name="pprice" value="<%=p.getPprice()%>">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="inputproduct">product category</label>
                     <input type="text" class="form-control" name="pcategory" value="<%=p.getPcategory()%>">
                  </div>
                  <div class="form-group">
                     <label for="inputproduct">product description</label>
                     <input type="text" class="form-control" name="pdesc" value="<%=p.getPdesc()%>">
                  </div>
                   
                  
          </select>
                    
                 
                  <button type="submit"  class="btn btn-primary" name="action" value ="edit">Edit</button>
               </form>
              
            </section>
                 
    
      <!--js working-->
      <script src='js/jquery-2.2.3.min.js'></script>
      <!--//js working-->
      <!-- cart-js -->
      <script src="js/minicart.js"></script>
      <script>
         toys.render();
         
         toys.cart.on('toys_checkout', function (evt) {
         	var items, len, i;
         
         	if (this.subtotal() > 0) {
         		items = this.items();
         
         		for (i = 0, len = items.length; i < len; i++) {}
         	}
         });
      </script>
      <!-- //cart-js -->
      <!-- start-smoth-scrolling -->
      <script src="js/move-top.js"></script>
      <script src="js/easing.js"></script>
      <script>
         jQuery(document).ready(function ($) {
         	$(".scroll").click(function (event) {
         		event.preventDefault();
         		$('html,body').animate({
         			scrollTop: $(this.hash).offset().top
         		}, 900);
         	});
         });
      </script>
      <!-- start-smoth-scrolling -->
      <!-- here stars scrolling icon -->
      <script>
         $(document).ready(function () {
         
         	var defaults = {
         		containerID: 'toTop', // fading element id
         		containerHoverID: 'toTopHover', // fading element hover id
         		scrollSpeed: 1200,
         		easingType: 'linear'
         	};
         
         
         	$().UItoTop({
         		easingType: 'easeOutQuart'
         	});
         
         });
       
      </script>
      <!-- //here ends scrolling icon -->
      <!--bootstrap working-->
      <script src="js/bootstrap.min.js"></script>
      <!-- //bootstrap working-->      <!-- //OnScroll-Number-Increase-JavaScript -->
   </body>
</html>
