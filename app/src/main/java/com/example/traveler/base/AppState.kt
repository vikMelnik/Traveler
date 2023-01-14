package com.example.traveler.base

sealed class AppState{

	data class Success<T>(val data: T): AppState()
	data class Error(val error: Throwable): AppState()
	object Loading: AppState()


//
//sealed class State<T> {
//	class Loading<T> : State<T>()
//
//	data class Success<T>(val data: T) : State<T>()
//
//	data class Error<T>(val message: String) : State<T>()
//
//	fun isLoading(): Boolean = this is Loading
//
//	fun isSuccessful(): Boolean = this is Success
//
//	fun isFailed(): Boolean = this is Error
//
	companion object {

		/**
		 * Returns [AppState.Loading] instance.
		 */
		fun loading() = Loading

		/**
		 * Returns [AppState.Success] instance.
		 * @param data Data to emit with status.
		 */
		private fun <T> success(data: T) =
			Success(data)

		/**
		 * Returns [AppState.Error] instance.
		 * @param message Description of failure.
		 */
		private fun  error(message: String) =
			Error(message)

		/**
		 * Returns [AppState] from [Resource]
		 */
		fun <T> fromResource(resource: Resource<T>): AppState = when (resource) {
			is Resource.Success -> success(resource.data)
			is Resource.Failed -> error(resource)
		}
	}
}
