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
    # carga todos mis amigos
    ownApi.getMyFriends().then (data) =>
        $scope.friends = data

app.controller 'myFriendsController', ($scope, $state, ownApi) ->
    # carga todos los usuarios del sistema
    ownApi.getUsers().then (data) =>
        $scope.users = data



