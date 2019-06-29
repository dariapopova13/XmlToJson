<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 29.06.19
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main</title>
  </head>
  <body>

  <div style="margin: 30px">
    <form action="file/post" method="post" enctype="multipart/form-data">
      <div>
        <label for="xml_file">Choose file to upload</label>
        <input type="file" id="xml_file" name="xml_file"
               accept=".xml">
      </div>
      <div style="margin-top: 10px;">
        <button>Submit</button>
      </div>
    </form>
  </div>

  <div style="margin: 30px">
    <pre id="json"></pre>
  </div>



  <script>
      document.getElementById("json").innerHTML = JSON.stringify(${jsonFromXmlObject}, null, 1);
  </script>
  </body>
</html>
