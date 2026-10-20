<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Car details | ShopSphere Motors</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><header class="site-header"><a class="brand" href="index.jsp">ShopSphere<span>Motors</span></a><nav><a href="cars">Explore cars</a><a href="wishlist">Wishlist</a><a href="my-bookings">My bookings</a></nav></header>
<main class="page-wrap">
<c:if test="${param.error == 'date'}"><div class="notice error">Please enter a valid preferred date.</div></c:if>
<div class="detail-layout"><div class="detail-image"><img src="<c:out value='${car.image}'/>" alt="<c:out value='${car.name}'/>" onerror="this.src='https://placehold.co/900x650/17202b/f4f0e8?text=ShopSphere+Motors'"></div>
<section class="detail-copy"><p class="eyebrow"><c:out value="${car.categoryName}"/></p><h1><c:out value="${car.name}"/></h1><p class="car-price large">₹ <c:out value="${car.price}"/></p>
<p class="muted">Inventory code: <c:out value="${car.sku}"/> · <c:out value="${car.stock}"/> currently available</p><p class="detail-description"><c:out value="${car.description}"/></p>
<div class="detail-actions"><form action="wishlist" method="post"><input type="hidden" name="carId" value="${car.id}"><input type="hidden" name="action" value="add"><button class="button button-outline" type="submit">♡ Save to wishlist</button></form></div>
<div class="booking-panel"><h2>Request a visit / test drive</h2><p>Send a request to the team. This is a booking request, not a payment.</p>
<form action="book-car" method="post" class="stack-form"><input type="hidden" name="carId" value="${car.id}">
<label>Preferred date <input type="date" name="preferredDate" min="<%= java.time.LocalDate.now() %>"></label>
<label>Message (optional) <textarea name="message" rows="3" maxlength="500" placeholder="Tell us when you would like to visit"></textarea></label>
<button class="button" type="submit">Send booking request</button></form></div></section></div>
</main><footer class="footer">© 2026 ShopSphere Motors</footer></body></html>
