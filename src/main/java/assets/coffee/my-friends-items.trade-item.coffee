app.config ($stateProvider) ->
	$stateProvider.state "my-friends-items.trade-item",
		url: "/trade-item"
		templateUrl: "tradeItem"
		controller: 'my-friends-items.trade-itemController'


app.controller 'my-friends-items.trade-itemController', ($scope, $state, ownApi, itemTrade) ->
	s = $scope
	s.friendItem = itemTrade.item
	console.log itemTrade

	# carga todos mis items en pantalla
	ownApi.getMyItems().then (data) =>
		$scope.items = data

	s.createTrade = (friendItem,item) =>
    	console.log item
    	ownApi.createTrade(friendItem,item)
    	$state.go "^"