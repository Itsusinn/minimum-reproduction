import kotlinx.serialization.* // ktlint-disable no-wildcard-imports
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.modules.SerializersModule
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

@OptIn(ExperimentalSerializationApi::class)
object Cbor {

  @OptIn(InternalSerializationApi::class, ExperimentalStdlibApi::class)
  val inner = Cbor {
    encodeDefaults = true
    ignoreUnknownKeys = true
    val to = typeOf<Packet>()
    val clazz = to.classifier as KClass<Packet>
    val jClass = clazz.java
    val fromNamedCompanion = try {
      jClass.declaredClasses.singleOrNull {
        println("get name ${it.name}")
        println("simple name ${it.simpleName}")
        it.simpleName == ("\$serializer") || it.simpleName == "serializer"
      }?.getField("INSTANCE")?.get(null) as? KSerializer<Packet>
    } catch (e: NoSuchFieldException) {
      null
    }
//    serializersModule = SerializersModule {
//      this.contextual(clazz, fromNamedCompanion as KSerializer<Packet>)
//    }
  }

  inline fun <reified T> encodeToByteArray(value: T): ByteArray =
    inner.encodeToByteArray(value)

  inline fun <reified T> decodeFromByteArray(bytes: ByteArray): T =
    inner.decodeFromByteArray(bytes)
}
