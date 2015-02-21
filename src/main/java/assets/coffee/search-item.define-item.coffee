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
  $stateProvider.state "me",
    url: "/me"
    templateUrl: "me"
    controller: 'meController'

app.controller 'meController', ($scope, $state, ownApi, itemFound) ->
  $scope.user =
    name: "rasta"
    lastname: "palu"
