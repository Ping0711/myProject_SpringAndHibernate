<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>註冊頁面</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
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
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">
</head>
<body class="text-center">
<main class="form-register">
    <header>
        <div class="collapse bg-dark" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8 col-md-7 py-4">
                        <h4 class="text-white">About</h4>
                        <p class="text-muted">Add some information about the album below, the author, or any other
                            background context. Make it a few sentences long so folks can pick up some informative
                            tidbits.
                            Then, link them off to some social networking sites or contact information.</p>
                    </div>
                    <div class="col-sm-4 offset-md-1 py-4">
                        <h4 class="text-white">Contact</h4>
                        <ul class="list-unstyled">
                            <li><a href="#" class="text-white">Follow on Twitter</a></li>
                            <li><a href="#" class="text-white">Like on Facebook</a></li>
                            <li><a href="#" class="text-white">Email me</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container">
                <a href="index" class="navbar-brand d-flex align-items-center">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-house-door-fill" viewBox="0 0 16 16">
                        <path d="M6.5 14.5v-3.505c0-.245.25-.495.5-.495h2c.25 0 .5.25.5.5v3.5a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5z"/>
                    </svg>
                    <strong>首頁</strong>
                </a>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>
    </header>
    <form action="SignUp" method="post" name="IDCardForm">
        <img class="mb-4" src="${pageContext.request.contextPath}/images/doca.jpg" alt="" width="150" height="150">
        <h1 class="h3 mb-3 fw-normal">註冊頁面</h1>
        <p class="mt-5 mb-3 text-muted" style="color: red">${Error}</p>
        <div class="form-floating">
            <input type="text" class="form-control" id="UserName" name="username" placeholder="UserName">
            <label for="UserName">註冊帳號 <small>(中英數字3-10之間)</small></label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="Password" name="password" placeholder="Password">
            <label for="Password">註冊密碼 <small>(中英數字 2-15之間)</small></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="Address" name="address" placeholder="Address">
            <label for="Address">註冊地址 <small>(市區必填)</small></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="Phone" name="phone" placeholder="Phone">
            <label for="Phone">註冊手機 <small>(10碼號碼)</small></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="IDCard" name="IDCard" placeholder="IDCard">
            <label for="IDCard">身分證號碼</label>
            <button class="w-100 btn btn-lg btn-secondary" onclick="document.IDCardForm.submit()">驗證</button>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">註冊</button>
        <p class="mt-5 mb-3 text-muted">&copy; DocaSignIn</p>
    </form>
</main>

</body>
</html>
