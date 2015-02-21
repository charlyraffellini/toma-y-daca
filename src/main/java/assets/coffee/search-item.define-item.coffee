app.config ($stateProvider) ->
	$stateProvider.state "search-item.define-item",
		url: "/define-item"
		templateUrl: "defineItem"
		controller: 'search-item.define-itemController'

app.controller 'search-item.define-itemController', ($scope, $state, ownApi, itemFound) ->
	s = $scope
	s.item = itemFound.item

	s.saveItem = (item) =>
		console.log item
		ownApi.createItem item
		$state.go "^"


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
	$scope.items = ownApi.getMyItems()

