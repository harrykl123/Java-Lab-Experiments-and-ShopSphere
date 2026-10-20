<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Explore cars | ShopSphere Motors</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body>
<header class="site-header"><a class="brand" href="index.jsp">ShopSphere<span>Motors</span></a><nav><a href="cars">Explore cars</a><a href="wishlist">Wishlist</a><a href="my-bookings">My bookings</a><a href="home.jsp">Dashboard</a></nav></header>
<main class="page-wrap">
<div class="page-title"><p class="eyebrow">THE INVENTORY</p><h1>Find your next car.</h1><p>Filter the available cars and explore the details that matter to you.</p></div>
<form class="filter-bar" action="cars" method="get">
<input type="search" name="keyword" value="<c:out value='${keyword}'/>" placeholder="Search by car, brand or SKU">
<select name="category"><option value="ALL">All categories</option>
<option value="SUV" ${category == 'SUV' ? 'selected' : ''}>SUV</option><option value="Sedan" ${category == 'Sedan' ? 'selected' : ''}>Sedan</option>
<option value="Hatchback" ${category == 'Hatchback' ? 'selected' : ''}>Hatchback</option><option value="Luxury" ${category == 'Luxury' ? 'selected' : ''}>Luxury</option></select>
<input type="number" min="0" step="1000" name="maxPrice" value="<c:out value='${maxPrice}'/>" placeholder="Max price (₹)">
<button class="button" type="submit">Apply filters</button><a class="reset-link" href="cars">Reset</a>
</form>
<c:choose><c:when test="${empty cars}"><div class="empty-state"><h2>No cars found</h2><p>Try another search or remove a filter.</p></div></c:when><c:otherwise>
<div class="car-grid"><c:forEach var="car" items="${cars}">
<article class="car-card">
<a class="car-image" href="car-details?id=${car.id}"><img src="<c:out value='${car.image}'/>" alt="<c:out value='${car.name}'/>" onerror="this.src='https://placehold.co/800x500/17202b/f4f0e8?text=ShopSphere+Motors'"><span class="category-pill"><c:out value="${car.categoryName}"/></span></a>
<div class="car-card-body"><div class="car-card-top"><h2><c:out value="${car.name}"/></h2><span class="stock"><c:out value="${car.stock}"/> available</span></div>
<p class="muted"><c:out value="${car.sku}"/></p><p class="car-price">₹ <c:out value="${car.price}"/></p>
<div class="card-actions"><a class="button button-small" href="car-details?id=${car.id}">View details</a>
<form action="wishlist" method="post"><input type="hidden" name="carId" value="${car.id}"><input type="hidden" name="action" value="add"><button class="icon-button" title="Add to wishlist" type="submit">♡</button></form></div></div>
</article></c:forEach></div></c:otherwise></c:choose>
</main><footer class="footer">© 2026 ShopSphere Motors</footer></body></html>
