<!DOCTYPE html>
<html>
<head>
    <title>Edit Rectangle</title>
</head>
<body>
    <h1>Edit Rectangle</h1>
    <form action="/rectangles/update/${rectangle.id}" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${rectangle.name}"/>
        <label for="color">Color:</label>
        <input type="text" id="color" name="color" value="${rectangle.color}"/>
        <label for="height">Height:</label>
        <input type="text" id="height" name="height" value="${rectangle.height}"/>
        <label for="width">Width:</label>
        <input type="text" id="width" name="width" value="${rectangle.width}"/>
        <input type="submit" value="Update Rectangle"/>
    </form>
    <a href="/rectangles">Back to List</a>
</body>
</html>
