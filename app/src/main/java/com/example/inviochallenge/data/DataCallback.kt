package com.example.inviochallenge.data

interface DataCallback <T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}