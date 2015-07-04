<%@ include file="includes/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BankNow</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='<spring:url value="/resources/css/bootstrap.css" />' rel='stylesheet' type='text/css'/>
    <style type="text/css">
        .boxer {
            width: 300px;
            height: 150px;
            display: inline-block;
            vertical-align: middle;
            text-align: center;
            display: table;
            border-collapse: collapse;
        }

        .boxer .box-row {
            margin-top: 20px;
            display: table-row;
        }

        .boxer .box {
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }

        .btn {
            background: #3498db;
            background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
            background-image: -moz-linear-gradient(top, #3498db, #2980b9);
            background-image: -ms-linear-gradient(top, #3498db, #2980b9);
            background-image: -o-linear-gradient(top, #3498db, #2980b9);
            background-image: linear-gradient(to bottom, #3498db, #2980b9);
            -webkit-border-radius: 28;
            -moz-border-radius: 28;
            border-radius: 28px;
            font-family: Arial;
            color: #ffffff;
            font-size: 20px;
            padding: 10px 20px 10px 20px;
            text-decoration: none;
        }

        .btn:hover {
            background: #3cb0fd;
            background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
            background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
            text-decoration: none;
        }

        body {
            margin-top: 10%;
        }
    </style>
    <script type="text/javascript" src='<spring:url value="/resources/js/jquery-1.11.3.min.js" />'
            type='text/javascript'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />'
            type='text/javascript'></script>
</head>
<body>
<div class="container" align="center">
    <div class="boxer">
        <div class="box-row">
            <div class="box"><img border="0" alt="banknow"
                                  src='<spring:url value="/resources/images/banknow.png" />'>
            </div>
        </div>
        <div style="margin-top:30px"></div>
        <div class="box-row">
            <div class="box"><a class="btn" href="account">Create account</a></div>
        </div>
        <div style="margin-top:40px"></div>
        <div class="box-row">
            <div class="box"><a class="btn" href="deposit">Deposit money</a></div>
        </div>
        <div style="margin-top:40px"></div>
        <div class="box-row">
            <div class="box"><a class="btn" href="transfer">Transfer money</a></div>
        </div>
        <div style="margin-top:40px"></div>
        <div class="box-row">
            <div class="box"><a class="btn" href="transactions">View transactions</a></div>
        </div>
        <div style="margin-top:30px"></div>
    </div>
</div>

</body>
</html>