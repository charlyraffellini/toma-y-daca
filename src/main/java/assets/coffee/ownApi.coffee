# Nuestra Propia API Local

app.factory 'ownApi', ($http, $location) ->
	class OwnApi
		getMyItems: =>
			($http.get "#{@_getBaseUrl()}/items").then (result) =>
				result.data

		getMyFriends: =>
			($http.get "#{@_getBaseUrl()}/friends").then (result) =>
				result.data

		getMyFriendsItems: =>
			# no debería repetir código, pero JS me supera...
			($http.get "#{@_getBaseUrl()}/friends").then (result) =>
				friends = result.data
				ownApp = this
				friends.forEach (friend) ->
					console.debug(friend)
					friend.items = []
					($http.get "#{ownApp._getBaseUrl()}/friends/#{friend.id}/items").then (result2) =>
						friend.items = result2.data
				console.debug(friends)
				friends


		getMyTrades: =>
			($http.get "#{@_getBaseUrl()}/trades").then (result) =>
				result.data

		getMe: =>
			($http.get "#{@_getBaseUrl()}/me").then (result) =>
				result.data

		getUsers: =>
			($http.get "#{@_getBaseUrl()}/users").then (result) =>
				result.data

		createItem: (item) =>
			body =
				meliId: item.id
				wallPost: item.wallPost
			($http.post("#{@_getBaseUrl()}/items", body)).then (result) =>
				result.data

		responseTrade: (trade) =>
			body =
				response: trade.response
				wallPost: trade.wallPost
			$http.put "#{@_getBaseUrl()}/trades/#{trade.id}", body

		createTrade: (friendItem,item) =>
			body =
				friendId: friendItem.owner.id
				friendItemId: friendItem.id
				userItemId: item.id
			console.debug(body)
			($http.post("#{@_getBaseUrl()}/trades", body)).then (result) =>
				result.data

		addFriend: (user) =>
			body =
				friendId: user.id
			($http.post("#{@_getBaseUrl()}/friends", body)).then (result) =>
				result.data

		deleteItem: (item) =>
			($http.delete "#{@_getBaseUrl()}/items/#{item.id}").then (result) =>
				result.data

		_getBaseUrl: ->
			$location.protocol() + "://" + $location.host() + ":" + $location.port()

	new OwnApi()