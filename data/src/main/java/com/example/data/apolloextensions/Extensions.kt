package com.example.data.apolloextensions

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Query

suspend inline fun <reified T : Any, D : Query.Data> ApolloClient.executeQuery(
    query: Query<D>,
    transform: (D) -> T?
): T {
    val response = this.query(query).execute()
    if (response.hasErrors()) {
        throw Exception("Error fetching data")
    }
    val data = response.data
    return data?.let { transform(it) } ?: throw Exception("${T::class.simpleName} not found")
}

suspend inline fun <reified T : Any, D : Query.Data> ApolloClient.executeListQuery(
    query: Query<D>,
    transform: (D) -> List<T>
): List<T> {
    val response = this.query(query).execute()
    if (response.hasErrors()) {
        return emptyList()
    }
    val data = response.data
    return data?.let { transform(it) } ?: emptyList()
}

fun <T> T.toOptional(): Optional<T> {
    return Optional.present(this)
}