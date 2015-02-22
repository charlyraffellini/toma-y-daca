app.config ($stateProvider) ->
	$stateProvider.state "my-items",
		url: "/my-items"
		templateUrl: "myItems"
		controller: 'myItemsController'
###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'myItemsController', ($scope, $state, ownApi) ->
	ownApi.getMyItems().then (data) =>
		$scope.items = data