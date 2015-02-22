app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		getMyItems: =>
			($http.get "#{@_getBaseUrl()}/items").then (result) =>
				result.data

		getMyFriends: =>
			($http.get "#{@_getBaseUrl()}/friends").then (result) =>
				result.data

		getMe: =>
            		($http.get "#{@_getBaseUrl()}/me").then (result) =>
                		result.data

		createItem: (item) =>
			body =
				meliId: item.id
				wallPost: item.wallPost
			($http.post("#{@_getBaseUrl()}/items", body)).then (result) =>
                result.data


		deleteItem: (item) =>
			($http.delete "#{@_getBaseUrl()}/items/#{item.id}").then (result) =>
                result.data

		acceptYumboTrade: =>
			body =
				response: "accepted"
				wallPost: true
			$http.put "#{@_getBaseUrl()}/trades/1", body

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()