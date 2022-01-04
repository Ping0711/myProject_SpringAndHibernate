<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>首頁</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/album/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


</head>
<body>
<main>
    <form action="${pageContext.request.contextPath}/shoppingMallPage" name="welcomePageForm"
          method="post"
          class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <input name="uid" value="${user.uid}" hidden="hidden">
    </form>
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="javascript:document.welcomePageForm.submit()"
               class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#bootstrap"/>
                </svg>
                <span class="fs-4">豆卡商城</span>
            </a>
            <ul class="nav nav-pills">
                <form action="welcomePage" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <li class="nav-item"><input type="submit" class="nav-link active" aria-current="page" value="會員中心">
                    </li>
                </form>
                <form action="myCartPage" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <li class="nav-item"><input type="submit" class="nav-link" value="購物車">
                    </li>
                </form>
                <li class="nav-item"><a href="index" class="nav-link">登出</a></li>
                <li class="nav-item"><a href="#" class="nav-link">${user.username}</a></li>
            </ul>
        </header>
    </div>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">豆卡購物商城</h1>
                <p class="lead text-muted">快來挑選你喜歡的東西吧!</p>
                <p>
                <form action="sellProductPage" method="post" name="sellProductPageForm">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                </form>
                <form action="myProductPage" method="post" name="myProductPageForm">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                </form>
                <a href="javaScript:document.sellProductPageForm.submit()" class="btn btn-primary my-2">販賣商品</a>
                <a href="javaScript:document.myProductPageForm.submit()" class="btn btn-secondary my-2">我的商品</a>
                <a class="nav justify-content-center border-bottom pb-3 mb-3"></a>
                <p class="text-muted text-center"><strong >${reMind}</strong></p>
                </p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="sellProducts" items="${sellProductList}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <c:if test="${sellProducts.sellQuantity!=0}">
                            <img src="${pageContext.request.contextPath}/${sellProducts.sellPicture}"
                                 class="bd-placeholder-img card-img-top" width="100%" height="225"
                                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                                 preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title>${sellProducts.sellName}</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            </img>
                            </c:if>
                            <c:if test="${sellProducts.sellQuantity==0}">
                                <strong class="text-center">${massage}</strong>
                            </c:if>
                            <div class="card-body text-muted">
                                <p>賣家 : ${sellProducts.user.username}<p>
                                <p>商品名稱 : ${sellProducts.sellName}<p>
                                <p>商品價格 : ${sellProducts.sellPrice}<p>
                                <p>剩餘數量 : ${sellProducts.sellQuantity}<p>
                                <!-- addMyCart按鈕-->
                                <form action="addMyCart" method="post" name="addMyCartForm${sellProducts.sid}">
                                    <input name="uid" value="${user.uid}" hidden="hidden">
                                    <input name="sid" value="${sellProducts.sid}" hidden="hidden">
                                    <input type="number" class="form-control" id="floatingInput" name="buyQuantity"
                                           placeholder="購買數量">
                                </form>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary"
                                                onclick="document.addMyCartForm${sellProducts.sid}.submit()">加入購物車
                                        </button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">查看</button>
                                    </div>
<%--                                    <small class="text-muted">9 mins</small>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">尚未擁有商品</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">尚未擁有商品</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
        <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
                href="../getting-started/introduction/">getting started guide</a>.</p>
    </div>
</footer>


<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
