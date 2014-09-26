<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
 
    <form method="POST" action="/BringUploadData/uploadFile" enctype="multipart/form-data">
        
        <input type="hidden" name="token" value="${createToken}">
        
        File to upload: <input type="file" name="file"><br /> <br />
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
     
</body>
</html>