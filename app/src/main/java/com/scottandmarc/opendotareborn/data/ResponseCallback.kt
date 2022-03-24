package com.scottandmarc.opendotareborn.data

interface ResponseCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(msg: String)
}