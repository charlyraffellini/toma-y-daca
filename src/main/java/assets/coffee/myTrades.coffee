app.config ($stateProvider) ->
	$stateProvider.state "my-trades",
		url: "/my-trades"
		templateUrl: "myTrades"
		controller: 'myTradesController'


app.controller 'myTradesController', ($scope, $state, $timeout, ownApi) ->
	s = $scope

	s.responseTradeAccepted = (trade) =>
		console.log trade
		trade.response = "accepted"
		ownApi.responseTrade(trade).then =>
			$timeout (->
				$state.go '.', {}, reload: true
				return
			), 100

	s.responseTradeRejected = (trade) =>
		console.log trade
		trade.response = "rejected"
		ownApi.responseTrade(trade).then =>
			$timeout (->
				$state.go '.', {}, reload: true
				return
			), 100

	# carga todos mis trueques en pantalla
	ownApi.getMyTrades().then (data) =>
		console.log data
		$scope.trades = data

