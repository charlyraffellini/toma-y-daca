app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		createItem: (item) =>
			body =
				meliId: item.id
				wallPost: item.wallPost
			$http.post "#{@_getBaseUrl()}/items", body

		acceptYumboTrade: =>
			body =
				response: "accepted"
				wallPost: true
			$http.put "#{@_getBaseUrl()}/trades/1", body

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()