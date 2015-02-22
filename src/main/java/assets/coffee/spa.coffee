app.controller 'spaController', ($scope, $state, ownApi) ->
	ownApi.getMe().then (data) =>
		$scope.user = data
