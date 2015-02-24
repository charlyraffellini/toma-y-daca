app.config ($stateProvider) ->
	$stateProvider.state "list-users",
		url: "/list-users"
		templateUrl: "listUsers"
		controller: 'listUsersController'
###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'listUsersController', ($scope, $state, ownApi, userFound) ->
	
	s = $scope
	s.user = userFound.user

	# llama a POST /friend
	s.addFriend = (user) =>
		console.log user
		ownApi.addFriend user

	# carga todos los usuarios del sistema
	ownApi.getUsers().then (data) =>
		console.log data
		$scope.users = data


