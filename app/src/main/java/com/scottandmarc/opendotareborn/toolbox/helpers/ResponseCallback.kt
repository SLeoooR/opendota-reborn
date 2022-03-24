package com.scottandmarc.opendotareborn.toolbox.helpers

interface ResponseCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(msg: String)
}