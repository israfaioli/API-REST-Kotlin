package utils

import com.github.javafaker.Faker

class Helpers {
    var faker: Faker = Faker()

    companion object {
        fun getNewTitle(): String {
            return Faker().letterify("apiRest" + "??????")
        }

        fun getIntegerNumber(digits: Int?): Int {
            val faker = Faker()
            return faker.number().numberBetween(1, 1000)
        }
    }
}