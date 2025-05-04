<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style-room.css">
</head>
<body>
<!-- SVG купюры -->
<svg class="money-bill bill1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#2ecc71"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">100$</text>
</svg>
<svg class="money-bill bill2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#27ae60"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">500$</text>
</svg>
<svg class="money-bill bill3" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#2ecc71"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">100$</text>
</svg>
<svg class="money-bill bill4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#27ae60"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">500$</text>
</svg>
<svg class="money-bill bill5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#2ecc71"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">100$</text>
</svg>
<svg class="money-bill bill6" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#27ae60"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">500$</text>
</svg>
<svg class="money-bill bill7" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#2ecc71"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">100$</text>
</svg>
<svg class="money-bill bill8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="20" height="16" rx="2" fill="#27ae60"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">500$</text>
</svg>

<div class="room-container">
    <h2>Добро пожаловать в игру <br> "Кто хочет стать миллионером!"</h2>

    <div class="call-to-action">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="#2ecc71">
            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 17h-2v-7H9v-2h2V7h2v2h2v7h-2v2zm-1-9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2z"/>
        </svg>
        <h4>Рады тебя видеть: ${employee.name}</h4>
    </div>

    <div class="stats">
        <h4>Твоя статистика: </h4>
        <p>Побед: ${employee.wins != null ? employee.wins : 0}</p>
        <p>Твой капитал: ${employee.capital != null ? employee.capital : 0}$</p>
    </div>

    <div class="divider"></div>
    <form:form action="/game">
        <button class="start-btn">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="white">
                <path d="M8 5v14l11-7z"/>
            </svg>
            НАЧАТЬ ИГРУ
        </button>
    </form:form>
</div>
</body>
</html>
