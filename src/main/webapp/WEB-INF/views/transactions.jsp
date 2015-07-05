<%@ include file="includes/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BankNow</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='<spring:url value="/resources/css/bootstrap.css" />' rel='stylesheet' type='text/css'/>
    <link href='<spring:url value="/resources/css/datatables/jquery.dataTables.css" />' rel='stylesheet'
          type='text/css'/>
    <script type="text/javascript" src='<spring:url value="/resources/js/jquery-1.11.3.min.js" />'
            type='text/javascript'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />'
            type='text/javascript'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/datatables/jquery.dataTables.min.js" />'
            type='text/javascript'></script>
    <script>
        $(document).ready(function () {
            $('#transactionsTable').dataTable();
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row text-center" style="margin-top:50px;">
        <h3 class="sign-up-title" style="text-align: center">
            <div class="box"><img border="0" alt="banknow"
                                  src='<spring:url value="/resources/images/banknow.png" />'>
            </div>
        </h3>
        <h2>Transactions</h2><br/><br/>

        <c:choose>
            <c:when test="${fn:length(transactions) gt 0}">
                <table id="transactionsTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Sender</th>
                        <th>Recipient</th>
                        <th>Amount</th>
                        <th>Type</th>
                    </tr>
                    </thead>

                    <tfoot>
                    <tr>
                        <th>Sender</th>
                        <th>Recipient</th>
                        <th>Amount</th>
                        <th>Type</th>
                    </tr>
                    </tfoot>

                    <tbody>
                    <c:forEach items="${transactions}" var="element">
                        <tr>
                            <c:choose>
                                <c:when test="${empty element.sender}">
                                    <td>-</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${element.sender}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${element.recipient}</td>
                            <td>${element.amount}</td>
                            <td>${element.type}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:when>
            <c:otherwise>
                <div class="col-md-12">No transactions</div>
                <div style="margin-bottom:60px"></div>
            </c:otherwise>
        </c:choose>

        <div style="margin-top:40px"></div>
        <a class="btn btn-primary btn-lg text-center" href="index">
            Back
        </a>

        <div style="margin-bottom:40px"></div>
    </div>
</div>
</body>
</html>
