app.config ($stateProvider) ->
	$stateProvider.state "my-friends-items.list-items",
		url: "/list-items"
		templateUrl: "listFriendsItems"
		controller: 'my-friends-items.list-itemsController'

app.factory "itemTrade", ->
	item: null

app.controller 'my-friends-items.list-itemsController', ($scope, $state, ownApi, itemTrade) ->
	s = $scope

	# carga todos mis amigos con sus items
	ownApi.getMyFriendsItems().then (data) =>
		$scope.friends = data

	s.tradeItem = (item) =>
		itemTrade.item = item

