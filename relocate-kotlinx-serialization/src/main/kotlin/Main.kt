import io.ktor.util.* // ktlint-disable no-wildcard-imports

/**
 * relocate后
 * 当POJO被 @Serializable 标记时
 * 匿名内部类 getSimpleBinaryName0() 在jdk8下返回 $serializer jdk9以上返回serializer
 * (未经relocate应当返回$serializer)
 * 在该POJO不含具名伴生对象时不论$serializer serializer都不会报错
 * 在POJO含有具名伴生对象时 $serializer不报错 serializer报错
 *
 * 相关
 * https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8177366
 */

@OptIn(ExperimentalStdlibApi::class, kotlinx.serialization.InternalSerializationApi::class)
fun main() {
  val v = Cbor.encodeToByteArray(Packet("ty", "ddd".encodeToByteArray()))
  println(hex(v))
}
