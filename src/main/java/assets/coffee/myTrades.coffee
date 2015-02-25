app.config ($stateProvider) ->
	$stateProvider.state "my-trades",
		url: "/my-trades"
		templateUrl: "myTrades"
		controller: 'myTradesController'


app.controller 'myTradesController', ($scope, $state, ownApi) ->
	s = $scope

	# carga todos mis trueques en pantalla
	ownApi.getMyTrades().then (data) =>
		$scope.trades = data

	s.acceptYumboTrade = ownApi.acceptYumboTrade