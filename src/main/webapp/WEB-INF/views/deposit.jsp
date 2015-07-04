<%@ include file="includes/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BankNow</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='<spring:url value="/resources/css/bootstrap.css" />' rel='stylesheet' type='text/css'/>
    <script type="text/javascript" src='<spring:url value="/resources/js/jquery-1.11.3.min.js" />'
            type='text/javascript'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />'
            type='text/javascript'></script>
    <script>
        function clearMsg() {
            $("#message").empty();
        }
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
        <h2>Deposit money</h2><br/><br/>

        <div class="row text-center">
            <form method="POST" action="account" accept-charset="UTF-8" role="form" id="accountform"
                  class="form-horizontal">
                <fieldset>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Account name</label>

                        <div class="col-md-4">
                            <input id="name" name="name" type="text" minlength="5" maxlength="30" placeholder="name for account"
                                   class="form-control input-md" required="">
                        </div>
                        <div class="col-md-4">
                            <select id="accounts" name="accounts" class="form-control">
                                <c:forEach items="${accounts}" var="element">
                                    <option value="${element}">${element}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="amount">Amount</label>

                        <div class="col-md-4">
                            <input id="amount" name="amount" class="form-control" type="number" value="0" min="0" step="0.01"
                                   data-number-to-fixed="2" data-number-stepfactor="100"
                                   placeholder="balance">
                        </div>
                    </div>

                    <div style="margin-top:15px"></div>
                    <div id="message" style="text-align: center; color: red"><c:out value="${message}"/></div><br/>

                    <!-- Button (Double) -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buttonSave"></label>

                        <div class="col-md-4">
                            <button id="buttonSave" name="buttonSave" class="btn btn-success btn-lg" type="submit">
                                Transfer
                            </button>
                            <button onclick="clearMsg()" id="buttonCancel" name="buttonCancel" class="btn btn-danger btn-lg" type="reset">
                                Reset
                            </button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>

        <div style="margin-top:40px"></div>
        <a class="btn btn-primary btn-lg text-center" href="index">
            Back
        </a>
    </div>
</div>
</body>
</html>
