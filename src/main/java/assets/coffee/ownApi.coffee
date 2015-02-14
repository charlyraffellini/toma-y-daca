app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		createItem: (item) =>
			body =
				meliId: item.id
				wallPost: item.wallPost
			$http.post "#{@_getBaseUrl()}/items", body

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()