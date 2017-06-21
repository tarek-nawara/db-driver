package edu.db.driver.physical.data

import scala.collection.mutable.ListBuffer

class Relation(val name: String, val header: List[(String, String)]) {
  type Record = Map[String, String]
  val records = new ListBuffer[Record]()

  override def toString(): String =
    s"Relation(name = ${name}, Header = ${header}, Records = ${records})"
}