package com.example.ascendcommerce.android.app.lifecycle

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer

abstract class ResponseObserver<RESPONSE> : Observer<Resource<RESPONSE>> {
    abstract fun postValue(value: RESPONSE?)
    abstract fun postError(errorMessage: String?)

    @CallSuper
    override fun onChanged(resource: Resource<RESPONSE>) {
        if (resource.state == ResourceState.Success) {
            postValue(resource.data)
        } else if (resource.state == ResourceState.Error) {
            postError(resource.errorMessage)
        }
    }

}