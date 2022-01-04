<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Checkout example · Bootstrap v5.1</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
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
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/checkOutPage.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <main>
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <form action="shoppingMallPage" name="welcomePageForm"
                  method="post"
                  class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <a href="javascript:document.welcomePageForm.submit()"
                   class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32">
                        <use xlink:href="#bootstrap"/>
                    </svg>
                    <span class="fs-4">豆卡商城</span>
                </a>
                <input name="uid" value="${user.uid}" hidden="hidden">
            </form>
            <ul class="nav nav-pills">
                <form action="welcomePage" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <li class="nav-item"><input type="submit" class="nav-link " value="會員中心"></li>
                </form>
                <form action="myProductPage" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <li class="nav-item"><input type="submit" class="nav-link" value="我的商品">
                    </li>
                </form>
                <form action="myCartPage" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <li class="nav-item"><input type="submit" class="nav-link active" aria-current="page" value="購物車">
                    </li>
                </form>

                <li class="nav-item"><a href="index" class="nav-link">登出</a></li>
                <li class="nav-item"><a href="#" class="nav-link">${user.username}</a></li>
            </ul>
        </header>
        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/images/doca2.jpg" alt=""
                 width="150" height="150">
            <h2>結帳表單</h2>
            <p class="lead">請在下面填寫你的資料</p>
            <c:forEach var="mymassage" items="${massage}">
            <p class="text-muted">${mymassage}</p>
            </c:forEach>
            <a class="nav justify-context-center border-bottom pb-3 mb-3"></a>
        </div>
        <div class="row g-5 rightArrow">
            <!--右邊-->
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-primary">商品名細</span>
                    <span class="badge bg-primary rounded-pill">${myCartProducts.size()}</span>
                </h4>
                <c:forEach var="myCartProduct" items="${myCartProducts}">
                <ul class="list-group mb-3">

                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <img src="${pageContext.request.contextPath}/${myCartProduct.sellProduct.sellPicture}"
                                 width="50" height="50">
                            <h6 class="my-0">${myCartProduct.sellProduct.sellName}</h6>
                            <small class="text-muted">單價:$${myCartProduct.sellProduct.sellPrice}</small>
                            <small class="text-muted">購買數量:${myCartProduct.buyQuantity}</small>
                        </div>
                        <span class="text-muted">$ ${myCartProduct.sellProduct.sellPrice*myCartProduct.buyQuantity}</span>
                    </li>
                    </c:forEach>
                    <li class="list-group-item d-flex justify-content-between bg-light">
                        <div class="text-success">
                            <h6 class="my-0">總價格</h6>
                        </div>
                        <span class="text-success">$${total}</span>
                    </li>
                </ul>
                <!--
                <li class="list-group-item d-flex justify-content-between lh-sm">
                    <div>
                        <h6 class="my-0">Product name</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$12</span>
                </li>
                <li class="list-group-item d-flex justify-content-between bg-light">
                    <div class="text-success">
                        <h6 class="my-0">Promo code</h6>
                        <small>EXAMPLECODE</small>
                    </div>
                    <span class="text-success">−$5</span>
                </li>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$20</strong>
                </li>
                <form class="card p-2">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Promo code">
                        <button type="submit" class="btn btn-secondary">Redeem</button>
                    </div>
                </form>
-->
            </div>
            <!--左邊-->
            <div class="col-md-7 col-lg-8 leftArrow">
                <h4 class="mb-3">基本資料</h4>
                <form class="needs-validation" novalidate action="checkOut" method="post">
                    <input name="uid" value="${user.uid}" hidden="hidden">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">本名</label>
                            <input type="text" class="form-control" id="firstName" placeholder="在此填入" value="" required>
                            <div class="invalid-feedback">
                                不得為空
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="username" class="form-label">用戶名稱</label>
                            <div class="input-group has-validation">
                                <span class="input-group-text">@</span>
                                <input type="text" class="form-control" readonly id="username" placeholder="在此填入"
                                       name="username" value="${user.username}" required>
                                <div class="invalid-feedback">
                                    請填入你的用戶名
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <label for="phone" class="form-label">聯絡方式 <span class="text-muted">(手機)</span></label>
                            <input type="email" class="form-control" readonly id="phone" placeholder="在此填入" name="phone"
                                   value="${user.phone}">
                            <div class="invalid-feedback">
                                請填入你的號碼
                            </div>
                        </div>
                        <!-- <div class="col-12">
                             <label for="email" class="form-label">信箱 <span class="text-muted">(常使用)</span></label>
                             <input type="email" class="form-control" id="email" placeholder="在此填入" name="email">
                             <div class="invalid-feedback">
                                 請填入你的信箱
                             </div>
                         </div>
 -->
                        <div class="col-12">
                            <label for="address" class="form-label">地址</label>
                            <input type="text" class="form-control" readonly id="address" placeholder="在此填入"
                                   name="address" value="${user.address}" required>
                            <div class="invalid-feedback">
                                請填入你的地址
                            </div>
                        </div>

                    </div>
                    <hr class="my-4">
                    <%-- 勾選框--%>
                    <!--
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="same-address">
                        <label class="form-check-label" for="same-address">Shipping address is the same as my billing
                            address</label>
                    </div>

                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="save-info">
                        <label class="form-check-label" for="save-info">Save this information for next time</label>
                    </div>
                   -->
                    <h4 class="mb-3">付款方式</h4>
                    <div class="my-3">
                        <div class="form-check">
                            <input id="PayForMoney" name="howToPay" value="貨到付款" type="radio" class="form-check-input" checked
                                   required>
                            <label class="form-check-label" for="PayForMoney">貨到付款</label>
                        </div>
                        <div class="form-check">
                            <input id="debit" name="howToPay" value="匯款" type="radio" class="form-check-input" required>
                            <label class="form-check-label" for="debit">匯款</label>
                        </div>
                    </div>
                    <%-- 信用卡--%>
                    <!--
                                        <div class="row gy-3">
                                            <div class="col-md-6">
                                                <label for="cc-name" class="form-label">Name on card</label>
                                                <input type="text" class="form-control" id="cc-name" placeholder="" required>
                                                <small class="text-muted">Full name as displayed on card</small>
                                                <div class="invalid-feedback">
                                                    Name on card is required
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="cc-number" class="form-label">Credit card number</label>
                                                <input type="text" class="form-control" id="cc-number" placeholder="" required>
                                                <div class="invalid-feedback">
                                                    Credit card number is required
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <label for="cc-expiration" class="form-label">Expiration</label>
                                                <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                                                <div class="invalid-feedback">
                                                    Expiration date required
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <label for="cc-cvv" class="form-label">CVV</label>
                                                <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                                                <div class="invalid-feedback">
                                                    Security code required
                                                </div>
                                            </div>
                                        </div>
                                        <hr class="my-4">
                    -->
                    <button class="w-100 btn btn-primary btn-lg" type="submit">確認下單</button>
                </form>
            </div>
        </div>
    </main>
    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2021 Doca Company</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>
<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="form-validation.js"></script>
</body>
