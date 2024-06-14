<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rectangle Details</title>
    <style>
        .rectangle {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5em;
            color: black;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Rectangle Details</h1>
    
    <div class="rectangle" style="background-color:#${rectangle.color}; height:${rectangle.height}px; width:${rectangle.width}px;">
        ${rectangle.name}
    </div>
    
    <p>Name: ${rectangle.name}</p>
    <p>Color: ${rectangle.color}</p>
    <p>Height: ${rectangle.height}</p>
    <p>Width: ${rectangle.width}</p>
    <a href="/rectangles">Back to Rectangles</a>

    <h2>Edit Rectangle</h2>
    <form action="/rectangles/update/${rectangle.id}" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${rectangle.name}"/>
        <label for="color">Color: #</label>
        <input type="text" id="color" name="color" value="${rectangle.color}"/>
        <label for="height">Height:</label>
        <input type="text" id="height" name="height" value="${rectangle.height}"/>
        <label for="width">Width:</label>
        <input type="text" id="width" name="width" value="${rectangle.width}"/>
        <input type="submit" value="Update Rectangle"/>
    </form>
    
    <h2>Delete Rectangle</h2>
    <form action="/rectangles/delete/${rectangle.id}" method="get">
        <input type="submit" value="Delete Rectangle"/>
    </form>
</body>
</html>
