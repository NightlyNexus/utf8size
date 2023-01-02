internal val languages = listOf(
  Language(
    "javascript",
    "JavaScript",
    // language=javascript
    """
    for (var i = 0; i < str.length; ) {
      var codePoint = str.codePointAt(i);
      // Process the code point.
      i += codePoint >= 0x010000 ? 2 : 1;
    }
    """.trimIndent()
  ),
  Language(
    "java",
    "Java",
    // language=java
    """
    for (int i = 0; i != string.length(); ) {
      int codePoint = string.codePointAt(i);
      // Process the code point.
      i += Character.charCount(codePoint);
    }
    """.trimIndent()
  ),
  Language(
    "kotlin",
    "Kotlin (JVM)",
    // language=kotlin
    """
    var i = 0
    while (i != string.length) {
      val codePoint = string.codePointAt(i)
      // Process the code point.
      i += Character.charCount(codePoint)
    }
    """.trimIndent()
  )
)

internal class Language(
  // https://github.com/highlightjs/highlight.js/blob/11.7.0/SUPPORTED_LANGUAGES.md
  val highlighterName: String,
  val displayName: String,
  val iterateCode: String
)
