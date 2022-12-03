import gherkin.cli.Main
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
import kotlin.jvm.JvmStatic

@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty", "json:target/cucumber-reports/Report.json"],
        features = ["src/test/resources/features"],
        glue = ["steps_definitions"],
        tags = [""])

object TestRunner {
    @JvmStatic
    fun main(args: Array<String>) {
        run { Main.main(args) }
    }
}
