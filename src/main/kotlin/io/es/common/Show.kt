package io.es.common

fun String.show(): String {
  return "\"$this\""
}

fun java.util.Date.show(): String {
  return java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this)
}
