<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rectangles</title>
</head>
<body>
    <h1>Rectangles</h1>
    <form action="/rectangles/create" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${newRectangle.name}"/>
        <label for="color">Color:</label>
        <input type="text" id="color" name="color" value="${newRectangle.color}"/>
        <label for="height">Height:</label>
        <input type="text" id="height" name="height" value="${newRectangle.height}"/>
        <label for="width">Width:</label>
        <input type="text" id="width" name="width" value="${newRectangle.width}"/>
        <input type="submit" value="Create Rectangle"/>
    </form>
    <ul>
        <c:forEach var="rectangle" items="${rectangles}">
            <li>${rectangle.name} - ${rectangle.color} - ${rectangle.height} - ${rectangle.width} 
                <a href="/rectangles/edit/${rectangle.id}">Edit</a> 
                <a href="/rectangles/delete/${rectangle.id}">Delete</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
