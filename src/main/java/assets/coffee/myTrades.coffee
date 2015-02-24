app.config ($stateProvider) ->
	$stateProvider.state "my-trades",
		url: "/my-trades"
		templateUrl: "myTrades"
		controller: 'myTradesController'

###resolve:
	ownApi: 'ownApi'
	myItems: (ownApi) ->
		ownApi.getMyItems()###

app.controller 'myTradesController', ($scope, $state, ownApi) ->
	# carga todos mis trueques en pantalla
	ownApi.getMyTrades().then (data) =>
		$scope.trades = data
