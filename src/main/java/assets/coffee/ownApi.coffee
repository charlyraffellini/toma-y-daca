app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		getMyItems: =>
			($http.get "#{@_getBaseUrl()}/items").then (result) =>
				result.data

		getMe: =>
            		($http.get "#{@_getBaseUrl()}/me").then (result) =>
                		result.data

		createItem: (item) =>
			body =
				meliId: item.id
				wallPost: item.wallPost
			$http.post "#{@_getBaseUrl()}/items", body

		deleteItem: (item) =>
			body =
				id: item.id
			$http.delete "#{@_getBaseUrl()}/items", body

		acceptYumboTrade: =>
			body =
				response: "accepted"
				wallPost: true
			$http.put "#{@_getBaseUrl()}/trades/1", body

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()