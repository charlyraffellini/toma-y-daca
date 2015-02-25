app.config ($stateProvider) ->
	$stateProvider.state "my-friends",
		url: "/my-friends"
		templateUrl: "myFriends"
		controller: 'myFriendsController'

app.controller 'myFriendsController', ($scope, $state, ownApi) ->
    # carga todos mis amigos
    ownApi.getMyFriends().then (data) =>
        $scope.friends = data



