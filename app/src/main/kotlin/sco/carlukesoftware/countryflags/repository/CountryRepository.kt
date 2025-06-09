package sco.carlukesoftware.countryflags.repository

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import sco.carlukesoftware.countryflags.ktor.KtorClient
import sco.carlukesoftware.countryflags.ktor.NetworkResult
import sco.carlukesoftware.countryflags.models.Country

class CountryRepository(
    private val ktorClient: KtorClient,
    private val ioDispatcher: CoroutineDispatcher
) : ICountryRepository {

    override suspend fun getCountries(): NetworkResult<CountryResponse> {
        // Switch to IO thread for operation (main thread safe)
        return withContext(ioDispatcher) {
            try {
                // Attempt to retrieve category list from network
                // using our ktor client
                val countryResponse: List<Country> = ktorClient.client
                    .get("countries")
                    .body()

                NetworkResult.Success(CountryResponse(countryResponse))
            } catch (e: ResponseException) {
                Log.e("CountryRepository", "HTTP error: ${e.response.status}", e)
                NetworkResult.Error("Failed to fetch countries: ${e.response.status}", e)
            } catch (e: Exception) {
                Log.e("CountryRepository", "Network error: ${e.message}", e)
                NetworkResult.Error("Network error: ${e.message ?: "Unknown error"}", e)
            }
        }
    }
}
