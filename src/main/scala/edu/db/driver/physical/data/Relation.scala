package edu.db.driver.physical.data

import scala.collection.mutable.ListBuffer

/** Data representation of a Sql table.
  *
  * @param name   name of the table
  * @param header holding table structure and types of each column
  * @author Tarek Nawara
  */
class Relation(val name: String, val header: List[(String, String)]) {
  type Record = Map[String, String]
  val records = new ListBuffer[Record]()

  override def toString: String =
    s"Relation(name = $name, Header = $header, Records = $records)"
}