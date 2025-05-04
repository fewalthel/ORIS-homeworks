<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${article.name}</title>
    <link rel="stylesheet" href="/styles/styles.css"/>
</head>
<body>
<h1>${article.name}</h1>
<p>${article.text}</p>
<p>Author: ${article.authorEmail}</p>
<a href="/articles">Back to articles...</a>

<h2>Comments</h2>
<div>
    <form action="/article/${article.id}/comments" method="post">
        <input name="text" type="text" placeholder="Your comment" required>
        <input type="submit" value="Add Comment">
    </form>
</div>
<#if error??>
    <div class="alert" role="alert">${error} </div>
</#if>

<div>
    <#list comments as comment>
        <div class="comment">
            <strong>Author: ${comment.authorEmail}</strong>
            <p>${comment.text}</p>
            <#if comment.authorId == currentUserId>
                <small>(Your comment)</small>
            </#if>
        </div>
    </#list>
</div>
</body>
</html>