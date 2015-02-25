#mvn coffeescript:watch -- esto es bloqueante
app = angular.module 'app',[
	'ui.router',
	'ui.bootstrap'
]


app.config ($stateProvider, $urlRouterProvider) ->
	$urlRouterProvider.when "/search-item", "/search-item/list-items"
	$urlRouterProvider.when "/my-friends-items", "/my-friends-items/list-items"

	$stateProvider.state "search-item",
		url: "/search-item"
		templateUrl: "searchItem"

	$stateProvider.state "my-friends-items",
    	url: "/my-friends-items"
    	templateUrl: "myFriendsItems"

app.factory "itemFound", ->
	item: null


