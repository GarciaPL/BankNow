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
    <script type="text/javascript" src='<spring:url value="/resources/js/validation/jqBootstrapValidation.js" />'
            type='text/javascript'></script>
    <script>
        $(function () {
            $("input").not("[type=submit]").jqBootstrapValidation();
        });
        function clearMsg() {
            $("#message").empty();
        }
        function generateIBAN() {

            $("#iban").val('123123123'); //collisions
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
        <h2>Account</h2><br/><br/>

        <div class="row text-center">
            <form method="POST" action="account" accept-charset="UTF-8" role="form" id="accountform"
                  class="form-horizontal">
                <fieldset>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Name</label>

                        <div class="col-md-4">
                            <input id="name" name="name" type="text" minlength="5" maxlength="30"
                                   placeholder="name of account owner"
                                   class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="surname">Surname</label>

                        <div class="col-md-4">
                            <input id="surname" name="surname" type="text" minlength="5" maxlength="30"
                                   placeholder="surname of account owner"
                                   class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Address</label>

                        <div class="col-md-4">
                            <input id="address" name="address" type="text" minlength="5" maxlength="50"
                                   placeholder="address of account owner"
                                   class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Number input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="phone">Phone</label>

                        <div class="col-md-4">
                            <input id="phone" name="phone" type="number" data-validation-regex-regex="/^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/" data-validation-regex-message="Must be a phone number"
                                   placeholder="phone of account owner"
                                   class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="iban">IBAN</label>

                        <div class="col-md-4">
                            <div class="input-group">
                                <input id="iban" name="iban" type="number" minlength="1"
                                       placeholder="number for account"
                                       class="form-control input-md" required="">
                                <span class="input-group-btn">
                                    <button onclick="generateIBAN()" class="btn btn-default" type="button">Generate</button>
                                </span>
                            </div>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="balance">Balance</label>

                        <div class="col-md-4">
                            <input id="balance" name="balance" class="form-control" type="number" value="0" min="0"
                                   step="0.01"
                                   data-number-to-fixed="2" data-number-stepfactor="100"
                                   placeholder="balance for account">
                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="currency">Currency</label>

                        <div class="col-md-4">
                            <select id="currency" name="currency" class="form-control">
                                <c:forEach items="${currencies}" var="element">
                                    <option value="${element}">${element}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div style="margin-top:15px"></div>
                    <div id="message" style="text-align: center; color: red"><c:out value="${message}"/></div>
                    <br/>

                    <!-- Button (Double) -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buttonSave"></label>

                        <div class="col-md-4">
                            <button id="buttonSave" name="buttonSave" class="btn btn-success btn-lg" type="submit">
                                Save
                            </button>
                            <button onclick="clearMsg()" id="buttonCancel" name="buttonCancel"
                                    class="btn btn-danger btn-lg" type="reset">
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
