app.config ($stateProvider) ->
	$stateProvider.state "my-item.list-items",
		url: "/list-items"
		templateUrl: "listItems"
		controller: 'my-item.list-itemsController'

app.controller 'my-item.list-itemsController', ($scope, ownApi) ->
	s = $scope
	s.items = []
	ownApi.getItems().then (items) =>
		s.items = items
