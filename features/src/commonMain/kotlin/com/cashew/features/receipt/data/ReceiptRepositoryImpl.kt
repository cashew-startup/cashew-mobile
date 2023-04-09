package com.cashew.features.receipt.data

import com.cashew.core.network.exceptions.UnauthorizedException
import com.cashew.core.storage.storages.CredentialsStorage
import com.cashew.features.receipt.data.dto.ReceiptRequestDTO
import com.cashew.features.receipt.data.dto.ReceiptResponseDTO
import com.cashew.features.receipt.data.dto.toDomain
import com.cashew.features.receipt.domain.Receipt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ReceiptRepositoryImpl(
    private val httpClient: HttpClient,
    private val credentialsStorage: CredentialsStorage
) : ReceiptRepository {

    override suspend fun getReceipt(receiptString: String): Receipt {
        val username = credentialsStorage.getUsername()?.value
            ?: throw UnauthorizedException(NullPointerException("null credentials"))
        return httpClient.post("/receipt") {
            setBody(
                ReceiptRequestDTO(
                    username = username,
                    receiptString = receiptString
                )
            )
        }
            .body<ReceiptResponseDTO>()
            .toDomain()
    }

}