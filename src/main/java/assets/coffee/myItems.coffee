app.config ($stateProvider) ->
	$stateProvider.state "my-items",
		url: "/my-items"
		templateUrl: "myItems"
		controller: 'myItemsController'
###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'myItemsController', ($scope, $state, ownApi, itemFound) ->
	
	s = $scope
	s.item = itemFound.item

	# llama a DELETE /item/{item} y recarga la vista
	s.deleteItem = (item) =>
		console.log item
		ownApi.deleteItem(item).then (data) =>
		    $state.reload()

	# carga todos mis items en pantalla
	ownApi.getMyItems().then (data) =>
		$scope.items = data
