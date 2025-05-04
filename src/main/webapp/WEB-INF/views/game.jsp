<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style-game.css">
</head>
<body>
<div class="game-container">
    <h2>Вопрос ${questionNumber} из ${totalQuestions}</h2>

    <div class="question-text">
        <p>${question.text}</p>
    </div>

    <form action="/game-answer" method="post">
        <div class="answer-grid">
            <c:forEach var="answer" items="${question.answers}">
                <button type="submit" name="answerId" value="${answer.id}"  class="answer-btn">
                        ${answer.text}
                </button>
            </c:forEach>
        </div>
    </form>

    <div class="progress">
        Прогресс: ${questionNumber}/${totalQuestions}
    </div>
</div>

<!-- Модальное окно результата -->
<c:if test="${showResultModal}">
    <div class="modal-overlay">
        <div class="modal-box ${isWin ? 'win-modal' : 'lose-modal'}">
            <h2>${isWin ? 'Поздравляем! Вы выиграли!' : 'Увы, Вы проиграли!'}</h2>
            <p>${isWin ? 'Вы ответили правильно на все вопросы и выиграли приз!' : 'Вы ответили неправильно и потеряли все свои деньги!'}</p>

            <div class="animation-container">
                <c:forEach begin="1" end="20" varStatus="loop">
                    <div class="falling-item"
                         style="left: ${loop.index * 4}%;
                                 animation-delay: ${loop.index * 0.1}s;
                                 background: ${isWin ? '#f1c40f' : '#e74c3c'};
                                 ${isWin ? 'border-radius: 50%;' : ''}">
                    </div>
                </c:forEach>
            </div>

            <form action="/game-end" method="post">
                <button type="submit" class="modal-btn ${isWin ? 'win-btn' : 'lose-btn'}">
                    Вернуться в личный кабинет
                </button>
            </form>
        </div>
    </div>
</c:if>

<!-- Иконки денег, монеток и биткоинов -->
<svg class="money-icon bill1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="40" height="26" rx="2" fill="#27ae60"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">100$</text>
</svg>
<svg class="money-icon bill2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="40" height="26" rx="2" fill="#f1c40f"/>
    <text x="12" y="18" font-size="8" fill="black" text-anchor="middle">500$</text>
</svg>
<svg class="money-icon bill3" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="40" height="26" rx="2" fill="#e74c3c"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">1000$</text>
</svg>
<svg class="money-icon bill4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <rect x="2" y="4" width="40" height="26" rx="2" fill="#3498db"/>
    <text x="12" y="18" font-size="8" fill="white" text-anchor="middle">200$</text>
</svg>
<svg class="coin coin5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="coin coin6" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="coin coin7" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="coin coin8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <circle cx="12" cy="12" r="10" stroke="gold" stroke-width="2" fill="gold"/>
    <text x="50%" y="50%" font-size="12" fill="black" text-anchor="middle" dy=".3em">$</text>
</svg>
<svg class="bitcoin-icon icon9" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <polygon points="12,2 16,10 22,10 16,14 12,22 8,14 4,10 10,10" fill="#f39c12"/>
    <text x="12" y="14" font-size="8" fill="white" text-anchor="middle">₿</text>
</svg>
<svg class="bitcoin-icon icon10" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <polygon points="12,2 16,10 22,10 16,14 12,22 8,14 4,10 10,10" fill="#f1c40f"/>
    <text x="12" y="14" font-size="8" fill="white" text-anchor="middle">₿</text>
</svg>
<svg class="bitcoin-icon icon11" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <polygon points="12,2 16,10 22,10 16,14 12,22 8,14 4,10 10,10" fill="#e67e22"/>
    <text x="12" y="14" font-size="8" fill="white" text-anchor="middle">₿</text>
</svg>
<svg class="bitcoin-icon icon12" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
    <polygon points="12,2 16,10 22,10 16,14 12,22 8,14 4,10 10,10" fill="#d35400"/>
    <text x="12" y="14" font-size="8" fill="white" text-anchor="middle">₿</text>
</svg>
</body>
</html>
