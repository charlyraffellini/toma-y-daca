app.config ($stateProvider) ->
	$stateProvider.state "my-items",
		url: "/my-items"
		templateUrl: "myItems"
		controller: 'myItemsController'


app.controller 'myItemsController', ($scope, $state, $timeout, ownApi, itemFound) ->
	
	s = $scope
	s.item = itemFound.item

	# llama a DELETE /item/{item} y recarga la vista
	s.deleteItem = (item) =>
		console.log item
		ownApi.deleteItem(item).then ->
			$timeout (->
				$state.go '.', {}, reload: true
				return
			), 100

	# carga todos mis items en pantalla
	ownApi.getMyItems().then (data) =>
		$scope.items = data
