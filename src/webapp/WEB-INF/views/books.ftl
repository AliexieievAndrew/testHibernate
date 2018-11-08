<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="index.ftl">back to main</a>
<br>
<br>
<h1>BookList</h1>
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Title</th>
        <th width="120">Author</th>
        <th width="120">Price</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <#list books as book>
    <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
        <td>${book.author}</td>
        <#--получаем остаток от деления на 100-->
        <td>${book.price/100}.${book.price%100}</td>
        <td><a href="/edit/${book.id}">edit</a> </td>
        <td><a href="/remove/${book.id}">remove</a> </td>
    </tr>
    </#list>

</table>

<br>
<h1>Add a book</h1>


<form name="book" action="/books/add" method="post">
    <p>Title</p>
    <input title="Title" type="text" name = "title" value="${book.title}"
    <p>Author</p>
    <input title="Author" type="text" name = "author" value="${book.author}"
    <p>Price</p>
    <input title="Price" type="text" name = "price" value="${book.price}"
    <input type="submit" value="OK">
</form>

</body>
</html>