var userProfile;

$('.btn-login').click(function(e) {
  e.preventDefault();
  lock.show();
});

$(document).ready(function() {
  var lock = new Auth0Lock('1wN2D7f28qRiRo30KgguaNNyGSH22LiP', 'arcuss.auth0.com', {
    auth: { 
      params: { 
        scope: 'openid email' 
      }
    }
  });
});

lock.on("authenticated", function(authResult) {
	  lock.getProfile(authResult.idToken, function(error, profile) {
	    if (error) {
	      // Handle error
	      return;
	    }

	    localStorage.setItem('id_token', authResult.idToken);

	    // Display user information
	    //$('.nickname').text(profile.nickname);
	   // $('.avatar').attr('src', profile.picture);
	    alert(profile.nickname);
	  });
	});
