app.config ($stateProvider) ->
	$stateProvider.state "list-users",
		url: "/list-users"
		templateUrl: "listUsers"
		controller: 'listUsersController'


app.controller 'listUsersController', ($scope, $state, $timeout, ownApi) =>
	
	s = $scope

	# función que llama a POST /friend
	s.addFriend = (user) =>
		ownApi.addFriend user =>
            $timeout (->
                $state.go '.', {}, reload: true
                return
            ), 100

	# carga todos los usuarios del sistema
	ownApi.getUsers().then (data) =>
		s.users = data

		# ahora cargo a mis amigos
		ownApi.getMyFriends().then (data) =>
			s.friends = data

			ids = []
			for friend in s.friends
				ids.push friend.id

			s.users.forEach (user) ->
				if user.id in ids
					user.canBeAdded = false
				else
					user.canBeAdded = true

	# me cargo a mi mismo
	ownApi.getMe().then (data) =>
		s.me = data

	console.log s
	s


