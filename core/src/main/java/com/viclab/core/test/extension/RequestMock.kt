package com.viclab.core.test.extension

data class RequestMock(
    var code: Int = 200,
    var path: String? = null,
    var urlPath: String? = null,
)