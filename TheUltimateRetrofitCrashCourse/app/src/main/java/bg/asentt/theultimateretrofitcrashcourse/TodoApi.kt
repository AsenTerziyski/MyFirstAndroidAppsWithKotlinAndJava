package bg.asentt.theultimateretrofitcrashcourse

import retrofit2.Response
import retrofit2.http.GET

//https://jsonplaceholder.typicode.com/todos
//https://www.youtube.com/watch?v=asuOWE5KuFM&list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ - post/put/get demo
//https://www.youtube.com/watch?v=4JGvDUlfk7Y

interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
}