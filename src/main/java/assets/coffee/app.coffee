#mvn coffeescript:watch -- esto es bloqueante
app = angular.module 'app',[
	'ui.router',
	'ui.bootstrap'
]


app.config ($stateProvider, $urlRouterProvider) ->
	$urlRouterProvider.otherwise "/search-item"
	$urlRouterProvider.when "/search-item", "/search-item/list-items"
	$urlRouterProvider.when "/my-item", "/my-item/list-items"

	$stateProvider.state "search-item",
		url: "/search-item"
		templateUrl: "searchItem"

