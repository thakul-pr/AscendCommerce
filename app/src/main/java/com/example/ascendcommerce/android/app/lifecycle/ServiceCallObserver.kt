package com.example.ascendcommerce.android.app.lifecycle

abstract class ServiceCallObserver<RESPONSE> : ResponseObserver<RESPONSE>() {
    abstract fun postIsLoading(value: Boolean)

    override fun onChanged(resource: Resource<RESPONSE>) {
        if (resource.state == ResourceState.Loading) {
            postIsLoading(true)
        } else {
            postIsLoading(false)
            super.onChanged(resource)
        }
    }
}
