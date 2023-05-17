<!DOCTYPE html>
<html>
<head>

</head>
<body>
<h1>CarSales</h1><hr>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list announcements as car>
    <div>
        <p><b>${car.title}</b>${car.price} | <a href="/car/${car.id}">Подробнее</a></p>
    </div>
</#list>
<hr>
<h3>Создать новое объявление</h3>
<form action="/car/create" method="post" enctype="multipart/form-data">
    Название: <input type="text" name="title"><br><br>
    Описание: <input type="text" name="description"><br><br>
    Цена: <input type="number" name="price"><br><br>
    Город: <input type="text" name="city"><br><br>
    Пробег: <input type="number" name="mileage"><br><br>
    Год выпуска: <input type="number" name="yearOfManufacture"><br><br>
    Автор: <input type="text" name="author"><br><br>
    Первая фотография: <input type="file" name="file1"><br><br>
    Вторая фотография: <input type="file" name="file2"><br><br>
    Третья фотография: <input type="file" name="file3"><br><br>

    <input type="submit" value="Добавить объявление"/>

</form>
</body>
</html>