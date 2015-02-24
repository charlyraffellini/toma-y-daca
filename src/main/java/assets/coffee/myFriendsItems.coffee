app.config ($stateProvider) ->
	$stateProvider.state "my-friends-items",
		url: "/my-friends-items"
		templateUrl: "myFriendsItems"
		controller: 'myFriendsItemsController'

###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'myFriendsItemsController', ($scope, $state, ownApi) ->
	# carga todos mis amigos con sus items
	ownApi.getMyFriendsItems().then (data) =>
		$scope.friends = data

