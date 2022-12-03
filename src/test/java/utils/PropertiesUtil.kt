package utils

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.Properties

class PropertiesUtil : Properties() {

    companion object {
        fun getProperties(propertiesKey: String?): String {
            val propertiesUtil = PropertiesUtil()
            var file: FileInputStream? = null
            try {
                file = FileInputStream(System.getProperty("user.dir") + "/src/test/resources/application.properties")
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            try {
                propertiesUtil.load(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return propertiesUtil.getProperty(propertiesKey)
        }
    }
}