<%@ include file="includes/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BankNow</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='<spring:url value="/resources/css/bootstrap.css" />' rel='stylesheet' type='text/css'/>
    <style type="text/css">
        .boxer .box-row {
            margin-top: 20px;
            display: table-row;
        }

        .boxer .box {
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }
    </style>
    <script type="text/javascript" src='<spring:url value="/resources/js/jquery-1.11.3.min.js" />'
            type='text/javascript'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />'
            type='text/javascript'></script>
</head>
<body>
<div class="container">
    <div class="row text-center" style="margin-top:80px;">
        <h3 class="sign-up-title" style="text-align: center">
            <div class="box"><img border="0" alt="banknow"
                                  src='<spring:url value="/resources/images/banknow.png" />'>
            </div>
        </h3>
        <h2>Transfer money</h2>

        <div style="margin-top:40px"></div>
        <a class="btn btn-primary btn-lg text-center" href="index">
            Back
        </a>
    </div>
</div>
</body>
</html>
