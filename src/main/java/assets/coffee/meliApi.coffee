app.factory 'meliApi', ($http) ->
	class MeliApi
		search: (query, offset = 0) ->
			result = $http.get "https://api.mercadolibre.com/sites/MLA/search",
				params:
					q: query
					offset: offset

			result.then ({data}) -> data

	new MeliApi()