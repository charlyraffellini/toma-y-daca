app.config ($stateProvider) ->
	$stateProvider.state "my-friends-items.trade-item",
		url: "/trade-item"
		templateUrl: "tradeItem"
		controller: 'my-friends-items.trade-itemController'

app.controller 'my-friends-items.trade-itemController', ($scope, $state, ownApi, itemFound) ->
	s = $scope
	s.item = itemFound.item

	s.createTrade = (item) =>
    	console.log item
    	ownApi.createTrade item
    	$state.go "^"