import kotlinx.browser.document
import kotlinx.dom.addClass
import kotlinx.dom.appendElement
import okio.Buffer
import org.w3c.dom.Element
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener

fun main() {
  val stringInput = document.getElementById("string") as HTMLTextAreaElement
  val sizeResult = document.getElementById("size")!!
  val bytesResult = document.getElementById("bytes")!!
  val codePointCountResult = document.getElementById("code_point_count")!!
  val codePointsResult = document.getElementById("code_points")!!
  stringInput.selectionEnd = stringInput.value.length
  for (language in languages) {
    document.body!!.appendElement("details") {
      appendElement("summary") {
        setAttribute("style", "font-size: 0.9em;")
        textContent = language.displayName
      }
      appendElement("pre") {
        appendElement("code") {
          addClass("language-${language.highlighterName}")
          setAttribute("style", "font-size: 0.8em;")
          textContent = language.iterateCode
        }
      }
    }
  }

  fun update() {
    val string = stringInput.value
    val bytes = Buffer().writeUtf8(string)
    val codePoints = string.codePoints()

    sizeResult.setSizeResult(bytes.size)
    bytesResult.setBytesResult(bytes)
    codePointCountResult.setCodePointCountResult(codePoints.size)
    codePointsResult.setCodePointsResult(codePoints)
  }

  update()

  stringInput.addEventListener("input", object : EventListener {
    override fun handleEvent(event: Event) {
      update()
    }
  })
}

private fun Element.setSizeResult(size: Long) {
  textContent = size.toString()
}

private fun Element.setBytesResult(bytes: Buffer) {
  val bytesBuilder = StringBuilder()
  var i = 0L
  while (i != bytes.size) {
    val byte = bytes[i].toUnsignedInt()
    if (i != 0L) {
      bytesBuilder
        .append(' ')
    }
    bytesBuilder
      .append(byte.toString(16))
    i++
  }
  textContent = bytesBuilder.toString()
}

private fun Element.setCodePointCountResult(codePointCount: Int) {
  textContent = codePointCount.toString()
}

private fun Element.setCodePointsResult(codePoints: List<Int>) {
  val codePointsBuilder = StringBuilder()
  for (i in codePoints.indices) {
    val codePoint = codePoints[i]
    if (i != 0) {
      codePointsBuilder
        .append(' ')
    }
    codePointsBuilder
      .append(codePoint.toString(16))
  }
  textContent = codePointsBuilder.toString()
}

private fun String.codePoints(): List<Int> {
  val codePoints = mutableListOf<Int>()
  var i = 0
  while (i < length) {
    val codePoint = codePointAt(i)
    codePoints += codePoint
    i += codePoint.charCount()
  }
  return codePoints
}

private fun String.codePointAt(index: Int): Int {
  val string = this
  return js("string.codePointAt(index)") as Int
}

private fun Int.charCount(): Int {
  return if (this >= 0x010000) 2 else 1
}

private fun Byte.toUnsignedInt(): Int {
  return toInt() and 0xFF
}
