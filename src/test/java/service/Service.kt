package service

import io.restassured.response.Response
import org.json.simple.JSONObject
import utils.Keys
import utils.PropertiesUtil
import utils.RestUtil
import utils.RestUtil.setBaseUrl
import utils.RestUtil.setBody
import utils.RestUtil.setPathParameter

class Service {
    private var jsonPayload: JSONObject? = null
    private val jsonPath = "src/test/resources/payloads/"
    private var response: Response? = null

    //---------------------------- SET URLS ---------------------------- //
    fun setBasePath(endpoint: String?) {
        setBaseUrl(PropertiesUtil.getProperties("prop.environment"), PropertiesUtil.getProperties(endpoint))
    }

    //---------------------------- HEADERS ---------------------------- //
    fun setDefaultHeaders() {
        val headers = HashMap<String, String>()
        headers["Content-type"] = Keys.APPLICATION_JSON
        RestUtil.setHeader(headers)
    }

    //---------------------------- REQUESTS ---------------------------- //
    fun getRequest(endpoint: String?) {
        setBasePath(endpoint)
        setDefaultHeaders()
        response = RestUtil.getRequest()
        println("---------------BACKEND RESPONSE---------------")
        response!!.prettyPrint()
    }

    fun getRequestWithPathParam(endpoint: String, param: String) {
        setBasePath(endpoint)
        setDefaultHeaders()
        setPathParameter("id", param)
        response = RestUtil.getRequest()
        println("---------------BACKEND RESPONSE---------------")
        response!!.prettyPrint()
    }

    fun postRequest(endpoint: String?, json: String?) {
        setBasePath(endpoint)
        setDefaultHeaders()
        requestBody(json)
        response = RestUtil.postRequest()
        println("---------------BACKEND RESPONSE---------------")
        response!!.prettyPrint()
    }

    fun getRequestWithQueryParam(endpoint: String?, key: String?, value: String) {
        setBasePath(endpoint)
        setDefaultHeaders()

        response = RestUtil.getRequest()
        println("---------------BACKEND RESPONSE---------------")
        response!!.prettyPrint()
    }

    fun requestQueryParam(key: String?, value: String?) {
        RestUtil.setQueryParam(key, value)
    }

    fun requestQueryParams(params: Map<String?, String?>?) {
        RestUtil.setQueryParams(params)
    }

    fun requestBody(jsonTemplate: String?) {
        jsonPayload = RestUtil.readJson(jsonPath, jsonTemplate!!)
        setBody(jsonPayload)
    }

    //---------------------------- RESPONSE ---------------------------- //
    fun getStatusCode(): Int {
        println("The response status code is: " + response!!.statusCode)
        return response!!.statusCode
    }

    fun getBody(key: String?): String {
        return response!!.body.jsonPath().getString(key)
    }

}