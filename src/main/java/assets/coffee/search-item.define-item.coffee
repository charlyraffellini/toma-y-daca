app.config ($stateProvider) ->
	$stateProvider.state "search-item.define-item",
		url: "/define-item"
		templateUrl: "defineItem"
		controller: 'search-item.define-itemController'

app.controller 'search-item.define-itemController', ($scope, $state, ownApi, itemFound) ->
	s = $scope
	s.item = itemFound.item

	s.saveItem = (item) =>
		ownApi.createItem item.id
		$state.go "^"
