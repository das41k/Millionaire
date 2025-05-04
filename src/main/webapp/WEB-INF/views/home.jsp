<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style-home.css">
</head>
<body>
<!-- SVG-изображения монет -->
<svg class="money-icon coin coin1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin3" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin6" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin7" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="money-icon coin coin8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>

<div class="container">
    <h2>Пожалуйста, пройдите аутентификацию для продолжения!</h2>
    <div class="error-message" style="${empty errorMessage ? 'display: none;' : ''}">
        ${errorMessage}
    </div>
    <form:form action="/validData" modelAttribute="employee" cssClass="form">
        <div class="form-group">
            <label for="name">Логин</label>
            <form:input path="name" id="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <form:input path="password" id="password" cssClass="form-control"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <input type="submit" name="action" value="Регистрация" class="btn">
        <input type="submit" name="action" value="Вход" class="btn">
    </form:form>
</div>
</body>
</html>