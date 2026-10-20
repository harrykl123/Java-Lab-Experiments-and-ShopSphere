<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Login | ShopSphere Motors</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body class="auth-page"><a class="brand auth-brand" href="index.jsp">ShopSphere<span>Motors</span></a><main class="auth-card"><p class="eyebrow">WELCOME BACK</p><h1>Sign in.</h1><p class="muted">Your next journey is waiting.</p>
<% if ("true".equals(request.getParameter("registered"))) { %><div class="notice success">Registration successful. Please sign in.</div><% } %>
<% if ("invalid".equals(request.getParameter("error"))) { %><div class="notice error">Invalid email or password.</div><% } %>
<% if ("true".equals(request.getParameter("logout"))) { %><div class="notice success">You have logged out.</div><% } %>
<form action="login" method="post" class="stack-form"><label>Email address<input type="email" name="email" autocomplete="email" required></label><label>Password<input type="password" name="password" autocomplete="current-password" required></label><button class="button button-wide" type="submit">Sign in</button></form>
<p class="auth-foot">New to ShopSphere? <a href="register.jsp">Create an account</a></p><a class="back-link" href="index.jsp">← Back to home</a></main></body></html>
