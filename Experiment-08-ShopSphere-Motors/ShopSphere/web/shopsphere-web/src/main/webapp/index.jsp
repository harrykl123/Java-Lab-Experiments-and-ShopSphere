<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ShopSphere Motors | Find your next drive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<header class="site-header">
    <a class="brand" href="${pageContext.request.contextPath}/index.jsp">ShopSphere<span>Motors</span></a>
    <nav>
        <a href="${pageContext.request.contextPath}/cars">Explore cars</a>
        <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        <a class="nav-button" href="${pageContext.request.contextPath}/register.jsp">Create account</a>
    </nav>
</header>
<main>
    <section class="hero">
        <div class="hero-copy">
            <p class="eyebrow">THE DRIVE STARTS HERE</p>
            <h1>Find a car that<br><span>moves you.</span></h1>
            <p class="hero-text">Explore SUVs, sedans, hatchbacks and premium cars in one place.</p>
            <a class="button" href="${pageContext.request.contextPath}/cars">Explore inventory</a>
        </div>
        <div class="hero-art">
            <div class="hero-orbit"></div>
            <img src="https://placehold.co/900x520/17202b/f4f0e8?text=YOUR+NEXT+DRIVE" alt="ShopSphere Motors featured car">
        </div>
    </section>
    <section class="section">
        <div class="section-heading"><div><p class="eyebrow">YOUR KIND OF DRIVE</p><h2>Explore by category</h2></div><a class="text-link" href="${pageContext.request.contextPath}/cars">View all cars →</a></div>
        <div class="category-grid">
            <a class="category-card" href="${pageContext.request.contextPath}/cars?category=SUV"><span>01</span><h3>SUVs</h3><p>Room for every journey</p></a>
            <a class="category-card" href="${pageContext.request.contextPath}/cars?category=Sedan"><span>02</span><h3>Sedans</h3><p>Comfort meets confidence</p></a>
            <a class="category-card" href="${pageContext.request.contextPath}/cars?category=Hatchback"><span>03</span><h3>Hatchbacks</h3><p>Made for city life</p></a>
            <a class="category-card" href="${pageContext.request.contextPath}/cars?category=Luxury"><span>04</span><h3>Luxury</h3><p>Take the finer road</p></a>
        </div>
    </section>
    <section class="callout"><div><p class="eyebrow">A BETTER WAY TO CHOOSE</p><h2>Compare. Explore. Book a visit.</h2><p>Save your favourites and send a booking request to arrange your next step.</p></div><a class="button button-light" href="${pageContext.request.contextPath}/cars">Browse cars</a></section>
</main>
<footer class="footer">© 2026 ShopSphere Motors · Built for your next journey.</footer>
</body>
</html>
