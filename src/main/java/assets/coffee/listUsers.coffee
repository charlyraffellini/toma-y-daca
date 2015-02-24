app.config ($stateProvider) ->
	$stateProvider.state "listUsers",
		url: "/listUsers"
		templateUrl: "listUsers"
		controller: 'usersController'
###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'usersController', ($scope, $state, ownApi, userFound) ->
	
	s = $scope
	s.user = userFound.user

	# llama a POST /friend
	s.addFriend = (user) =>
		console.log user
		ownApi.addFriend user

	# carga todos los usuarios del sistema
	ownApi.getUsers().then (data) =>
		$scope.users = data


