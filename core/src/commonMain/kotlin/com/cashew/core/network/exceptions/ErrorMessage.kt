package com.cashew.core.network.exceptions

import com.cashew.core.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

val Exception.errorMessage: StringDesc
    get() = when (this) {
        is ClientRequestException -> MR.strings.exception_client_request.desc()
        is NoInternetException -> MR.strings.exception_no_internet.desc()
        is NoResponseException -> MR.strings.exception_no_response.desc()
        is SerializationException -> MR.strings.exception_serialization.desc()
        is ServerResponseException -> MR.strings.exception_server_response.desc()
        is UnauthorizedException -> MR.strings.exception_unauthorized.desc()
        else -> MR.strings.exception_unknown.desc()
    }