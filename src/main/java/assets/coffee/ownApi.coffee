app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		createItem: (meliId) =>
			$http.post "#{@_getBaseUrl()}/items",
				meliId: meliId

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()