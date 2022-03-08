import com.charleskorn.kaml.Yaml
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import java.io.File
import java.io.InputStreamReader
import kotlin.random.Random

private val logger = LoggerFactory.getLogger("MainKt")

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    routing {
        get("/reply") {
            val file = File("/app/config/responses.yaml")
            logger.info("Config file ${file.path}")
            val result = Yaml.default.decodeFromStream(Responses.serializer(), file.inputStream())
            call.respondText(result.replies.random())
        }
        get("/ready") {
            call.respond(HttpStatusCode.OK, "")
        }
    }
}