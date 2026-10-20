<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>My bookings | ShopSphere Motors</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><header class="site-header"><a class="brand" href="index.jsp">ShopSphere<span>Motors</span></a><nav><a href="cars">Explore cars</a><a href="wishlist">Wishlist</a><a href="home.jsp">Dashboard</a></nav></header>
<main class="page-wrap"><div class="page-title"><p class="eyebrow">YOUR REQUESTS</p><h1>My bookings.</h1><c:if test="${param.success == 'true'}"><div class="notice success">Your booking request has been sent.</div></c:if></div>
<c:choose><c:when test="${empty bookings}"><div class="empty-state"><h2>No booking requests yet</h2><p>Found a car you like? Request a visit or test drive.</p><a class="button" href="cars">Explore cars</a></div></c:when><c:otherwise>
<div class="table-wrap"><table><thead><tr><th>Request #</th><th>Car</th><th>Preferred date</th><th>Message</th><th>Status</th><th>Created</th></tr></thead><tbody>
<c:forEach var="booking" items="${bookings}"><tr><td>#<c:out value="${booking.id}"/></td><td><c:out value="${booking.carName}"/></td><td><c:out value="${booking.preferredDate == '' ? 'Not specified' : booking.preferredDate}"/></td><td><c:out value="${booking.message}"/></td><td><span class="status"><c:out value="${booking.status}"/></span></td><td><c:out value="${booking.createdAt}"/></td></tr></c:forEach>
</tbody></table></div></c:otherwise></c:choose></main><footer class="footer">© 2026 ShopSphere Motors</footer></body></html>
