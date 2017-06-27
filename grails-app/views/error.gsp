<!doctype html>
<html>
<head>
    <asset:stylesheet src="bootstrap/css/bootstrap.css"/>
    <title>404 - Not found</title>
</head>
<style>
.error-template {padding: 40px 15px;text-align: center;}
.error-actions {margin-top:15px;margin-bottom:15px;}
.error-actions .btn { margin-right:10px; }
</style>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>Oops!</h1>
                <h2>Some thing has gone wrong</h2>

                <div class="error-details">

                    <g:if env="development">
                        <g:if test="${Throwable.isInstance(exception)}">
                            <g:renderException exception="${exception}" />
                        </g:if>
                        <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                            <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
                        </g:elseif>
                        <g:else>
                            <ul class="errors">
                                <li>An error has occurred</li>
                                <li>Exception: ${exception}</li>
                                <li>Message: ${message}</li>
                                <li>Path: ${path}</li>
                            </ul>
                        </g:else>
                    </g:if>
                    <g:else>
                        Sorry, an error has occured!
                    </g:else>

                </div>
                <div class="error-actions">
                    <a href="/" class="btn btn-primary btn-default">Take Me Home</a>
                    <a href="/blog" class="btn btn-primary btn-default">Read Blog</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>