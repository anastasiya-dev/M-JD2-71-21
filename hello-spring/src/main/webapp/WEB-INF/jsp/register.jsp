<jsp:include page="header.jsp"/>
<div class="container">
      <form class="form-signin" method="post" action="/hello-spring/register">
        <h2 class="form-signin-heading">Please sign up</h2>
        <p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="userName" class="form-control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="userPassword" class="form-control" placeholder="Password" required>
        </p>
        <p>
          <label for="role" class="sr-only">Please select role</label>
          <select class="form-control" name="roles[0].roleName" id="role">
            <option>USER</option>
            <option>ADMIN</option>
          </select>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
</div>
<jsp:include page="footer.jsp"/>