<!DOCTYPE html>
<html>
<head>

</head>
<body>
<h1>CarSales</h1><hr>
<h4>Поробное описание объявления</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название:</b>${car.title}<br>
<b>Описание:</b>${car.description}<br>
<b>Цена:</b>${car.price}<br>
<b>Город:</b>${car.city}<br>
<b>Пробег:</b>${car.mileage}<br>
<b>Год выпуска:</b>${car.yearOfManufacture}<br>
<b>Автор:</b>${car.author}<br>
<hr>
<form action="/car/delete" method="post">
    <input type="submit" value="Удалить объявление"/>
</form>
</body>
</html>