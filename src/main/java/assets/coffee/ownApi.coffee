app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		createItem: (meliId) =>
			$http.post "#{@_getBaseUrl()}/items",
				meliId: meliId

		getItems: =>
			$http.get "#{@_getBaseUrl()}/items"

		_getBaseUrl: =>
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()