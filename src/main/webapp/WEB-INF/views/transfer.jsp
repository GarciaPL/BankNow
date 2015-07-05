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
            $( '#transferform' ).each(function(){
                this.reset();
            });
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
        <h2>Transfer money</h2><br/><br/>

        <div class="row text-center">
            <form method="POST" action="transfer" accept-charset="UTF-8" role="form" id="transferform"
                  class="form-horizontal">
                <fieldset>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="sender">Sender</label>

                        <c:choose>
                            <c:when test="${fn:length(accounts) gt 0}">
                                <div class="col-md-6">
                                    <select id="sender" name="sender" class="form-control">
                                        <c:forEach items="${accounts}" var="element">
                                            <option value="${element.iban}">${element.name} ${element.surname} | IBAN : ${element.iban} | Balance : ${element.balance}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-4">No accounts</div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="recipient">Recipient</label>

                        <c:choose>
                            <c:when test="${fn:length(accounts) gt 0}">
                                <div class="col-md-6">
                                    <select id="recipient" name="recipient" class="form-control">
                                        <c:forEach items="${accounts}" var="element">
                                            <option value="${element.iban}">${element.name} ${element.surname} | IBAN : ${element.iban} | Balance : ${element.balance}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-4">No accounts</div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="amount">Amount</label>

                        <div class="col-md-6">
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
