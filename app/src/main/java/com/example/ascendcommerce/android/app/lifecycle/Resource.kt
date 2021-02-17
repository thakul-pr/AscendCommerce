package com.example.ascendcommerce.android.app.lifecycle

class Resource<RESPONSE> private constructor(
    val data: RESPONSE? = null,
    val exception: Exception? = null,
    val state: ResourceState = ResourceState.Idle
) {

    constructor(response: RESPONSE?) : this(
        data = response,
        state = ResourceState.Success
    )

    constructor(exception: Exception?) : this(
        exception = exception,
        state = ResourceState.Error
    )

    companion object {
        fun <RESPONSE> isLoading(): Resource<RESPONSE> {
            return Resource(state = ResourceState.Loading)
        }

        fun <RESPONSE> success(response: RESPONSE?): Resource<RESPONSE> {
            return Resource(response)
        }

        fun <RESPONSE> error(exception: Exception?): Resource<RESPONSE> {
            return Resource(exception)
        }
    }
}