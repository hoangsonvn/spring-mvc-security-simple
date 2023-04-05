<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <title>Trang chủ</title>

</head>

<body>

<!-- Page Content -->
<div class="container">

    <!-- Heading Row -->
    <div class="row align-items-center my-5">
        <div class="col-lg-7">
            <img class="img-fluid rounded mb-4 mb-lg-0"
                 src="http://placehold.it/900x400" alt="">
        </div>
        <!-- /.col-lg-8 -->
        <div class="col-lg-5">
            <h1 class="font-weight-light">Business Name or Tagline</h1>
            <p>This is a template that is great for small businesses. It
                doesn't have too much fancy flare to it, but it makes a great use
                of the standard Bootstrap core components. Feel free to use this
                template for any project you want!</p>
            <a class="btn btn-primary" href="#">Call to Action!</a>
        </div>
        <!-- /.col-md-4 -->
    </div>
   <c:forEach var="item" items="${categories}">
    <a href="<c:url value="/trang-chu?code=${item.code}"/> " class="link-primary">${item.name}</a>
    </c:forEach>
    <!-- /.row -->
    <c:forEach var="item" items="${model.listResult}">
    <!-- Call to Action Well -->
    <div class="row">
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">Card One</h2>
                    <p>${item.content}</p>
                    <p>${item.title}</p>
                    <p>${item.thumbnail}</p>
                    <p>${item.shortDescription}</p>

                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary btn-sm">More Info</a>
                </div>
            </div>
        </div>
        </c:forEach>
        <!-- Content Row -->
        <div class="row">
            <div class="col-md-4 mb-5">
                <div class="card h-100">
                    <div class="card-body">
                        <h2 class="card-title">Card One</h2>
                        <p class="card-text">Lorem ipsum dolor sit amet, consectetur
                            adipisicing elit. Rem magni quas ex numquam, maxime minus quam
                            molestias corporis quod, ea minima accusamus.</p>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="btn btn-primary btn-sm">More Info</a>
                    </div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                <div class="card h-100">
                    <div class="card-body">
                        <h2 class="card-title">Card Two</h2>
                        <p class="card-text">Lorem ipsum dolor sit amet, consectetur
                            adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt
                            pariatur voluptatem sunt quam eaque, vel, non in id dolore
                            voluptates quos eligendi labore.</p>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="btn btn-primary btn-sm">More Info</a>
                    </div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                <div class="card h-100">
                    <div class="card-body">
                        <h2 class="card-title">Card Three</h2>
                        <p class="card-text">Lorem ipsum dolor sit amet, consectetur
                            adipisicing elit. Rem magni quas ex numquam, maxime minus quam
                            molestias corporis quod, ea minima accusamus.</p>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="btn btn-primary btn-sm">More Info</a>
                    </div>
                </div>
            </div>
            <!-- /.col-md-4 -->

        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->

</body>

</html>