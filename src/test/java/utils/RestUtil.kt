package utils

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.util.RequestPayload
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

object RestUtil {
    var request: RequestSpecification? = null
    var response: Response? = null
    var requestPayload: RequestPayload? = null

    @JvmStatic
    fun setBaseUrl(baseUrl: String, path: String) {
        println("baseUrl $baseUrl$path")
        RestAssured.baseURI = baseUrl
        RestAssured.basePath = path
        RestAssured.useRelaxedHTTPSValidation()
        request = RestAssured.given()
    }

    @JvmStatic
    fun setHeader(contentHeader: HashMap<String, String>) {
        request!!.headers(contentHeader)
    }

    @JvmStatic
    fun setPathParameter(param: String, value: String) {
        request!!.pathParam(param, value)
    }

    @JvmStatic
    fun setQueryParam(key: String?, value: String?) {
        request!!.queryParam(key, value).log().all()
    }

    @JvmStatic
    fun setQueryParams(params: Map<String?, String?>?) {
        request!!.queryParams(params).log().all()
    }

    @JvmStatic
    fun setBody(contentBody: Map<Any?, Any?>?) {
        println("SENDING REQUEST: " + request!!.body(contentBody).log().all())
        request!!.body(contentBody)
    }

    @JvmStatic
    fun getRequest(): Response {
        return request!!.get()
    }

    @JvmStatic
    fun postRequest(): Response {
        return request!!.post()
    }

    @JvmStatic
    fun readJson(jsonPath: String, file: String): JSONObject? {
        val parser = JSONParser()
        var json: JSONObject? = null
        try {
            json = parser.parse(FileReader(jsonPath + file)) as JSONObject
        } catch (e: Exception) {
            println("Erro na leitura do arquivo")
        }
        return json
    }

    @JvmStatic
    fun tearDown() {
        request = null
        response = null
        requestPayload = null
    }
}