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
	
	s = $scope

	s.deleteItem = (item) =>
		console.log item
		ownApi.deleteItem item
		$state.go "^"
	
	ownApi.getMyItems().then (data) =>
		$scope.items = data
