import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.ByteString

@Serializable
data class Packet constructor(
  val type: String,
  @ByteString
  val content: ByteArray,
) {
  companion object{
    fun create(){
      println("dddd")
    }
  }
}
