app.config ($stateProvider) ->
	$stateProvider.state "my-friends",
		url: "/my-friends"
		templateUrl: "myFriends"
		controller: 'myFriendsController'
###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'myFriendsController', ($scope, $state, ownApi) ->
    mixed  = {}
    mixed.friends = []
    mixed.users = []
    # carga todos mis amigos
    ownApi.getMyFriends().then (data) =>
        mixed.friends.push(data)
    # carga todos los usuarios del sistema
    ownApi.getUsers().then (data) =>
        mixed.users.push(data)
    $scope.mixed


