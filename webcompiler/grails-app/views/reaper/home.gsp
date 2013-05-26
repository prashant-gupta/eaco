<%--
  Created by IntelliJ IDEA.
  User: prashant
  Date: 11/11/12
  Time: 2:59 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>USIT Web Compiler</title>
</head>
<body>

<h1>This is compiler Home</h1>

<br/><br/><br/><br/><br/><br/>
<div id="sourceCodeDiv">
    <h3> Type in the source code</h3>
    <g:form action="processSourceCode" method="POST">

        %{--<input type="text" name="sourceCode" title="source" />--}%
        <textarea id="textarea" wrap="off" rows="15" cols="80" name="sourceCode" ></textarea>

        <g:submitButton name="submitSource" value="Submit Code" />
    </g:form>
</div>
</body>
</html>