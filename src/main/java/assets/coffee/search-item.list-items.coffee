app.config ($stateProvider) ->
	$stateProvider.state "search-item.list-items",
		url: "/list-items"
		templateUrl: "selectItem"
		controller: 'search-item.list-itemsController'

app.factory "itemFound", ->
	item: null

app.controller 'search-item.list-itemsController', ($scope, meliApi, ownApi, itemFound) ->
	s = $scope
	s.totalItems = 0
	s.itemsPerPage = 50
	s.currentPage = 0
	s.items = []

	getOffset = => s.currentPage * s.itemsPerPage

	s.pageChanged = () =>
		results = meliApi.search s.searchText, getOffset()
		results.then ({results, paging}) =>
			s.totalItems = paging.total
			s.items = results

	s.saveItem = (item) =>
		itemFound.item = item