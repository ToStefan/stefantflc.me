var basePath = 'http://localhost:8080/api';
var activeEl = 0;

var RadioManager = {
		
	startBroadcast : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/start',
			success : function(data) {
				
			}
		});
	},
	stopBroadcast : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/stop',
			success : function(data) {
				
			}			
		});
	},
	refrashBroadcast : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/refrash',
			success : function(data) {
				SongManager.getPlaylist();
			}			
		});		
	},
	startTime : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/start-time',
			success : function(startTime) {
				RadioManager.playRadio(startTime);
			},
			error : function(e) {
				DocumentManager.playerStopState();
				alert("Radio broadcast is offline!");
			}
		});
	},
	playRadio : function(startTime) {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/play',
			success : function(currentPlaying) {
				var player = $("#player");

				$("#mp3Source").attr("src", "data:audio/mpeg;base64," + currentPlaying);
			    player[0].load();
			    player[0].play();
			    console.log(startTime);
			    player[0].currentTime = startTime;
			    
			    player[0].addEventListener('ended', function() {
			    	RadioManager.playRadio(0);
				});
			    
			    RadioManager.currentSong();
			},
			error : function() {
				DocumentManager.playerStopState();
				alert("Queue list is empty!");
				$('#showSongTitle').text('Press play to start');
			}
		});
	},
	currentSong : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/radio/current-song',
			success : function(currentSong) {
				$('#showSongTitle').text(currentSong.title);
				DocumentManager.playerRunState();
			}
		})
	}		
}

var SongManager = {

	addSong : function() {
		var form = $('#uploadSongForm')[0];
		var data = new FormData(form);
		$.ajax({
			type : 'POST',
			enctype : 'multipart/form-data',
			url : basePath + '/songs/add',
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			success : function(data) {
				$('#songTitle').val('');
				$('#songFileInput').val('');
				SongManager.getPlaylist();
			},
			error : function(e) {
				
			}
		});
	},
	getPlaylist : function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/songs',
			dataType : 'json',
			success : function(songs) {
				$('#radioPlaylist').empty();
				for (let song of songs) {
					$('#radioPlaylist').append(
					'<div class="form-group">' +
					'<input type="text" class="form-control songText" value="#'+ song.id + ' '+ song.title + '" disabled="disabled">' +
					'</div>');
				}
			}
		});		
	},
	deleteSong : function(id) {
		$.ajax({
			type : 'DELETE',
			url : basePath + '/songs/' + id,
			success : function(data) {
				$('#songIdToDelete').val('');
				SongManager.getPlaylist();
			}
		});	
	}
}

var UserManager = {
		
	signIn : function() {
		$("#btnSubmitSignIn").prop("disabled",true);
		$.ajax({
			url : basePath + '/auth/sign-in',
			type : 'POST',
			contentType : 'application/json',
			data : UserManager.loginUserData(),
			success : function(data, textStatus, xhr) {
				UtilManager.setLocalItem(0, data.token);
				UserManager.showLoggedUser();
				DocumentManager.loggedState();
				DocumentManager.homeState();
				$('#loginUsername').val('');
				$('#loginPassword').val('');
				DocumentManager.navFix(0, $('#btnSignIn'));
				$("#btnSubmitSignIn").prop("disabled",false);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#btnSubmitSignIn").prop("disabled",false);
				//alert('User not found!');
				console.log(jqXHR);
			}
		});
	},
	loginUserData : function() {
		return JSON.stringify({
			"username" : $('#loginUsername').val(),
			"password" : $('#loginPassword').val()
		});
	},
	showLoggedUser : function() {
		$.ajax({
			url : basePath + '/auth/me',
			dataType : 'json',
			type : 'POST',
			contentType : 'application/json',
			headers : UtilManager.createAuthorizationTokenHeader(),
			success : function(data, textStatus, xhr) {
				UtilManager.setLocalItem(1, data.authorities.length);
				$('.navigationGroup').append(
						'<div id="logoutDiv" class="btn-group">' + 
							'<button id="btnLogout" type="button" class="btn btn-nav"><p>Logout</p></button>' + 
						'</div>');
				
				$('#btnLogout').click(function(e) {
					e.preventDefault();
					UtilManager.removeLocalItem(0);
					UtilManager.removeLocalItem(1);
					DocumentManager.loggedOutState();
					DocumentManager.homeState();
					$('#logoutDiv').remove();
					$('#radioControlPanel').empty();
					$('#usersInfo').empty();
					DocumentManager.navFix(0, $('#btnPortfolio'));
					DocumentManager.navFix(0, $('#btnRadio'));
				});
				
				if(data.authorities.length == "3") {
					$('#radioControlPanel').append(
						'<hr><form id="uploadSongForm">' +
							'<h2 class="headText">Playlist update options</h2>' +
							'<h5 class="midText">Update playlist with new songs</h5>' +
							'<div class="form-group">' +
								'<input class="form-control" type="text" id="songTitle" name="title" placeholder="Song name"/>' +
							'</div>' +
							'<div class="file-field">' +
								'<div class="btn btn-primary btn-sm" id="songFileInputParent">' +
									'<span>Choose file</span>' +
									'<input type="file" id="songFileInput" name="song" required="required"/>' +
								'</div>' +
							'</div>' +
							'<button class="btn btn-default" type="button" id="btnAddSong">Submit</button>' +
						'</form>' +
						'<h5 class="midText smallTopMargin">Playlist manipulation</h5>' +
						'<button class="btn btn-default" type="button" id="btnRefrashList">Refresh playlist</button>' +
						'<div class="form-group">' +
							'<input class="form-control smallTopMargin" type="text" id="songIdToDelete" placeholder="Song id"/>' +
						'</div>' +
						'<button class="btn btn-default" type="button" id="btnDeleteSong">Delete song</button>' +
						'<hr>' +
						'<h2 class="headText">Broadcast options</h2>' +
						'<button class="btn btn-default" type="button" id="btnStartBroadcast">Start Broadcast</button>' +
						'<button class="btn btn-default" type="button" id="btnStopBroadcast">Stop Broadcast</button>');
					
					$('#btnDeleteSong').click(function(e) {
						e.preventDefault();
						var songId = $('#songIdToDelete').val();
						SongManager.deleteSong(songId);
						
					});
					$('#btnStopBroadcast').click(function(e) {
						e.preventDefault();
						RadioManager.stopBroadcast();
					});
					$('#btnRefrashList').click(function(e) {
						e.preventDefault();
						RadioManager.refrashBroadcast();
					});
					$('#btnAddSong').click(function(e) {
						e.preventDefault();
						SongManager.addSong();
					});
					$('#btnStartBroadcast').click(function(e) {
						e.preventDefault();
						RadioManager.startBroadcast();
					});
				}
			}
		});
	},
	loadUsersInfo: function() {
		$.ajax({
			type : 'GET',
			url : basePath + '/users',
			dataType : 'json',
			headers : UtilManager.createAuthorizationTokenHeader(),
			success : function(users) {
				$('#usersInfo').empty();
				for (let user of users) {
					$('#usersInfo').append(
							'<p>Id: ' + user.id + '</p>' +
							'<p>Username: ' + user.username + '</p>' +  
							'<p>Password: ' + user.password + '</p>' +
							'<hr>'
					);
				}
			}
		});
	}
		
}

var UtilManager = {

	getLocalItem : function(index) {
		return localStorage.getItem(index);
	},
	setLocalItem : function(index, value) {
		localStorage.setItem(index, value);
	},
	removeLocalItem : function(index) {
		localStorage.removeItem(index);
	},
	createAuthorizationTokenHeader : function() {
		var token = UtilManager.getLocalItem(0);
		if(token != null){
			return {
				"Authorization" : "Bearer " + token
			};
		}
		return null;
	}
}

var DocumentManager = {
		
	navCheck : 	function() {
		var a = $(".btn-nav");
		$(a[activeEl]).addClass("active"), $(".btn-nav").click(
				function() {
					$(a[activeEl]).removeClass("active"), $(this).addClass("active"), activeEl = $(".btn-nav").index(this)
				})
	},
	navFix : function(active, navElement) {
		activeEl = active;
		DocumentManager.navCheck();
		navElement.removeClass("active");
	},
	playerStopState : function() {
		$('#btnPlayRadio').show();
		$('#btnStopRadio').hide();		
	},
	playerRunState : function() {
		$('#btnPlayRadio').hide();
		$('#btnStopRadio').show();
	},
	homeState : function() {
		$('#homePage').show();
		$('#portfolioPage').hide();
		$('#radioPage').hide();
		$('#signInPage').hide();
	},
	portfolioState : function() {
		$('#homePage').hide();
		$('#portfolioPage').show();
		$('#radioPage').hide();
		$('#signInPage').hide();
	},
	radioState : function() {
		$('#homePage').hide();
		$('#portfolioPage').hide();
		$('#radioPage').show();
		$('#signInPage').hide();
	},
	signInState : function() {
		$('#homePage').hide();
		$('#portfolioPage').hide();
		$('#radioPage').hide();
		$('#signInPage').show();
	},
	loggedState : function() {
		$('#btnSignIn').hide();
	},
	loggedOutState : function() {
		$('#btnSignIn').show();
	}
}

$(document).ready(function() {
	
	DocumentManager.homeState();
	DocumentManager.playerStopState();
	$('#showSongTitle').text('Press play to start');
	DocumentManager.navCheck();
	
	if (UtilManager.getLocalItem(0) != null) {
		UserManager.showLoggedUser();
		DocumentManager.loggedState();
	} else {
		DocumentManager.loggedOutState();
	}
	

	//Sign in
	$('#btnSubmitSignIn').click(function(e) {
		e.preventDefault();
		UserManager.signIn();
	});
	
	//Radio
	$('#btnPlayRadio').click(function(e) {
		e.preventDefault();
		RadioManager.startTime();
	});
	$('#btnStopRadio').click(function(e) {
		e.preventDefault();
		var player = $("#player");
		player[0].pause();
		$('#showSongTitle').text('Press play to start');
		DocumentManager.playerStopState();
	});
	
	//Navigation
	$('#btnHome').click(function(e) {
		e.preventDefault();
		DocumentManager.homeState();
	});
	$('#btnPortfolio').click(function(e) {
		e.preventDefault();
		DocumentManager.portfolioState();
	});	
	$('#btnRadio').click(function(e) {
		e.preventDefault();
		DocumentManager.radioState();
		SongManager.getPlaylist();
		UserManager.loadUsersInfo();
	});	
	$('#btnSignIn').click(function(e) {
		e.preventDefault();
		DocumentManager.signInState();
	});
});