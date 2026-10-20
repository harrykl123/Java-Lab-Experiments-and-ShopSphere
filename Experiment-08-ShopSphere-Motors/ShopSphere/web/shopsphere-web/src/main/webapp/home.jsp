<%@ page import="com.shopsphere.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%! private String esc(String s) { if (s == null) return ""; return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\'", "&#39;"); } %>
<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Your dashboard | ShopSphere Motors</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body>
<header class="site-header"><a class="brand" href="index.jsp">ShopSphere<span>Motors</span></a><nav><a href="cars">Explore cars</a><a href="wishlist">Wishlist</a><a href="my-bookings">My bookings</a><a href="logout">Logout</a></nav></header>
<main class="dashboard-wrap">
<section class="welcome-panel"><p class="eyebrow">YOUR GARAGE STARTS HERE</p><h1>Welcome back, <%= esc(currentUser.getName()) %>.</h1><p>Manage your saved cars and booking requests from one place.</p><a class="button" href="cars">Explore cars</a></section>
<div class="dashboard-grid">
<a class="dashboard-card" href="cars"><span class="card-icon">↗</span><h2>Explore cars</h2><p>Search the available inventory and view details.</p></a>
<a class="dashboard-card" href="wishlist"><span class="card-icon">♡</span><h2>Your wishlist</h2><p>Keep your favourite cars together.</p></a>
<a class="dashboard-card" href="my-bookings"><span class="card-icon">◷</span><h2>Booking requests</h2><p>Check the status of your requests.</p></a>
<% if ("ADMIN".equalsIgnoreCase(currentUser.getRole())) { %><a class="dashboard-card" href="admin"><span class="card-icon">⚙</span><h2>Admin dashboard</h2><p>Manage car inventory and booking status.</p></a><% } %>
</div>
</main><footer class="footer">© 2026 ShopSphere Motors</footer>
</body></html>
