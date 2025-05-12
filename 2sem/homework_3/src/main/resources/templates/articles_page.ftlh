<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/styles.css"/>
    <title>Articles</title>

</head>
<body>
<a class="logout" href="/logout">logout</a>
<#if userId??>
<div class="article-form">
    <form action="/articles" method="post">
        <input name="name" type="text" placeholder="Article title" required>
        <input name="text" type="text" placeholder="Article content" required>
        <input type="submit">
    </form>
</div>
</#if>

<div>
    <#list articles as article>
        <div class="article-div">
            <div style="display: flex; justify-content: space-between;">
                <h3>${article.name}</h3>
                <form action="/favourites_articles/${article.id}" method="post">
                    <#if favoritesArticles?? && favoritesArticles?seq_contains(article) >
                        <button type="submit" value="updateFavorites" class="fav_button">
                            <img src="/images/saved_icon_fill.svg" style="width: 20px"/>
                        </button>

                   <#else>
                       <button type="submit" value="updateFavorites" class="fav_button">
                           <img src="/images/saved_icon.svg" style="width: 20px"/>
                       </button>
                    </#if>
                </form>
            </div>
            <p>${article.text}</p>
            <a href="/article/${article.id}">View comments...</a>
        </div>
    </#list>
</div>
</body>
</html>